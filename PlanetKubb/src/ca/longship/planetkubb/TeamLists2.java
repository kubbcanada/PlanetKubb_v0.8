package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeamLists2 extends Activity implements View.OnClickListener {
	public String Team1Name;
	public String Team2Name;
	int team2Players;
	Spinner spnPlayer1, spnPlayer2, spnPlayer3, spnPlayer4, spnPlayer5,
			spnPlayer6;
	Button startMatch;
	int playercount = 1;
	TextView tvt2p6,tvt2p5,tvt2p4,tvt2p3,tvt2p2,tvt2p1; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team2);

		team2Players = GlobalVars.getTeam2PlayNum();
		Team2Name = GlobalVars.getTeam2Name();

		TextView teamnametitle = (TextView) findViewById(R.id.tvTeam2Players);
		teamnametitle.setText(Team2Name);
		startMatch = (Button) findViewById(R.id.btStartMatch);
		startMatch.setOnClickListener(this);

		spnPlayer1 = (Spinner) findViewById(R.id.spnT2P1);
		spnPlayer2 = (Spinner) findViewById(R.id.spnT2P2);
		spnPlayer3 = (Spinner) findViewById(R.id.spnT2P3);
		spnPlayer4 = (Spinner) findViewById(R.id.spnT2P4);
		spnPlayer5 = (Spinner) findViewById(R.id.spnT2P5);
		spnPlayer6 = (Spinner) findViewById(R.id.spnT2P6);
		
		tvt2p6 = (TextView) findViewById(R.id.tvTeam2Player6);
		tvt2p5 = (TextView) findViewById(R.id.tvTeam2Player5);
		tvt2p4 = (TextView) findViewById(R.id.tvTeam2Player4);
		tvt2p3 = (TextView) findViewById(R.id.tvTeam2Player3);
		tvt2p2 = (TextView) findViewById(R.id.tvTeam2Player2);
		tvt2p1 = (TextView) findViewById(R.id.tvTeam2Player1);
		
		
		
		Button addplayer = (Button) findViewById(R.id.bAddPlayer2);
		addplayer.setOnClickListener(this);
		addPlayersToList();
		setVisiblePlayers();
		Button delplayer = (Button) findViewById(R.id.bDelPlayer2);
		delplayer.setOnClickListener(this);

	}

	public void addPlayersToList() {
		DBAccessMatch info = new DBAccessMatch(this);
		info.open();
		ArrayList<String> alPlayers = info.getData();
		info.close();
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, alPlayers);
		spnPlayer1.setAdapter(adapter);
		spnPlayer2.setAdapter(adapter);
		spnPlayer3.setAdapter(adapter);
		spnPlayer4.setAdapter(adapter);
		spnPlayer5.setAdapter(adapter);
		spnPlayer6.setAdapter(adapter);

	}

	public void setVisiblePlayers() {
		// TODO Auto-generated method stub
		switch (team2Players) {
		case 6:
			
			tvt2p6.setVisibility(View.VISIBLE);

			spnPlayer6.setVisibility(View.VISIBLE);

		case 5:
			
			tvt2p5.setVisibility(View.VISIBLE);

			spnPlayer5.setVisibility(View.VISIBLE);

		case 4:
			
			tvt2p4.setVisibility(View.VISIBLE);

			spnPlayer4.setVisibility(View.VISIBLE);

		case 3:
			
			tvt2p3.setVisibility(View.VISIBLE);

			spnPlayer3.setVisibility(View.VISIBLE);

		case 2:
			
			tvt2p2.setVisibility(View.VISIBLE);

			spnPlayer2.setVisibility(View.VISIBLE);

		case 1:
			
			tvt2p1.setVisibility(View.VISIBLE);

			spnPlayer1.setVisibility(View.VISIBLE);

			break;

		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btStartMatch:

			addTeam2Members();
			// Add Match to Database
			boolean didItWork = true;
			try {

				DBAccessMatch dbAccess = new DBAccessMatch(this);
				// GlobalVars.varsNotNull();

				dbAccess.open();
				dbAccess.createMatch(GlobalVars.sTeam1Name,
						GlobalVars.sTeam2Name, GlobalVars.sStartDate,
						GlobalVars.sLocation, GlobalVars.sEvent,
						GlobalVars.sPitchSurface, GlobalVars.sScoredBy,
						GlobalVars.sT1P1, GlobalVars.sT1P2, GlobalVars.sT1P3,
						GlobalVars.sT1P4, GlobalVars.sT1P5, GlobalVars.sT1P6,
						GlobalVars.sT2P1, GlobalVars.sT2P2, GlobalVars.sT2P3,
						GlobalVars.sT2P4, GlobalVars.sT2P5, GlobalVars.sT2P6);
				GlobalVars.sMatchID = dbAccess.getMatchID();
				dbAccess.close();
			} catch (Exception e) {
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dang it!");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();
			} finally {
				if (didItWork) {
					Dialog d = new Dialog(this);
					d.setTitle("Oh Yeah!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
			}
			// GlobalVars.createMatchInDb();

			Intent whoisfirst = new Intent("ca.longship.planetkubb.WHOFIRST");
			startActivity(whoisfirst);
			break;
		case R.id.bAddPlayer2:
			if (GlobalVars.sTeam2PlayNum == 1) {

				playercount = 2;
			} else if (GlobalVars.sTeam2PlayNum == 2) {

				playercount = 3;
			} else if (GlobalVars.sTeam2PlayNum == 3) {

				playercount = 4;

			} else if (GlobalVars.sTeam2PlayNum == 4) {

				playercount = 5;

			} else if (GlobalVars.sTeam2PlayNum == 5) {

				playercount = 6;

			} else if (GlobalVars.sTeam2PlayNum == 6) {
				Toast.makeText(getBaseContext(),
						"Cannot add any more players.  Max is 6",
						Toast.LENGTH_SHORT).show();
				playercount = 6;

			}
			
			GlobalVars.sTeam2PlayNum = playercount;
			team2Players = GlobalVars.sTeam2PlayNum;
			setVisiblePlayers();
			break;
			
		case R.id.bDelPlayer2:
			if (GlobalVars.sTeam2PlayNum == 1) {
				Toast.makeText(getBaseContext(),
						"Must have at least 1 player",
						Toast.LENGTH_SHORT).show();
				playercount = 1;
			} else if (GlobalVars.sTeam2PlayNum == 2) {
				tvt2p2.setVisibility(View.INVISIBLE);
				spnPlayer2.setVisibility(View.INVISIBLE);
				playercount = 1;
			} else if (GlobalVars.sTeam2PlayNum == 3) {
				tvt2p3.setVisibility(View.INVISIBLE);
				spnPlayer3.setVisibility(View.INVISIBLE);
				playercount = 2;

			} else if (GlobalVars.sTeam2PlayNum == 4) {
				tvt2p4.setVisibility(View.INVISIBLE);
				spnPlayer4.setVisibility(View.INVISIBLE);
				playercount = 3;

			} else if (GlobalVars.sTeam2PlayNum == 5) {
				tvt2p5.setVisibility(View.INVISIBLE);
				spnPlayer5.setVisibility(View.INVISIBLE);
				playercount = 4;

			} else if (GlobalVars.sTeam2PlayNum == 6) {
				tvt2p6.setVisibility(View.INVISIBLE);
				spnPlayer6.setVisibility(View.INVISIBLE);
				playercount = 5;

			}
			GlobalVars.sTeam2PlayNum = playercount;
			team2Players = GlobalVars.sTeam2PlayNum;
			setVisiblePlayers();
			
			break;
		}
	}

	public void addTeam2Members() {
		// TODO Auto-generated method stub
		int x;

		String pname = null, pinit = null;
		String[] aname;

		for (x = 0; x < team2Players; x++) {
			switch (x) {
			case 0:
				aname = spnPlayer1.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P1 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);

				break;
			case 1:
				aname = spnPlayer2.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P2 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);

				break;
			case 2:
				aname = spnPlayer3.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P3 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);
				break;
			case 3:
				aname = spnPlayer4.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P4 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);
				break;
			case 4:
				aname = spnPlayer5.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P5 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);
				break;
			case 5:
				aname = spnPlayer6.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam2PlayerNames(pname, x);
				GlobalVars.sT2P6 = pname;

				GlobalVars.setTeam2PlayerInit(pinit, x);
				break;

			}

		}

	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle("Quit Match")
				.setMessage("Do you really want to quit scoring?")
				.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int which) {
								// continue with delete
								Intent openMainActivity = new Intent(
										"ca.longship.planetkubb.MAINACTIVITY");
								startActivity(openMainActivity);
								finish();
							}
						})
				.setNegativeButton("No", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// do nothing
					}
				}).show();

		return;
	}

}
