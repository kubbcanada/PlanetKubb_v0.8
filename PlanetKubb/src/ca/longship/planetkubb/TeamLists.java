package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class TeamLists extends Activity implements View.OnClickListener {

	public String Team1Name;
	public String Team2Name;
	int team1Players, team2Players;
	int teamnumber = 1;
	Button team2button;
	Button startmatch;
	Spinner spnPlayer1, spnPlayer2, spnPlayer3, spnPlayer4, spnPlayer5,
			spnPlayer6;
	TextView tvt1p6, tvt1p5, tvt1p4, tvt1p3, tvt1p2, tvt1p1;
	int playercount = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.team1);
		team1Players = GlobalVars.getTeam1PlayNum();

		team2Players = GlobalVars.getTeam2PlayNum();

		Team1Name = GlobalVars.getTeam1Name();
		Team2Name = GlobalVars.getTeam2Name();

		spnPlayer1 = (Spinner) findViewById(R.id.spnT1P1);
		spnPlayer2 = (Spinner) findViewById(R.id.spnT1P2);
		spnPlayer3 = (Spinner) findViewById(R.id.spnT1P3);
		spnPlayer4 = (Spinner) findViewById(R.id.spnT1P4);
		spnPlayer5 = (Spinner) findViewById(R.id.spnT1P5);
		spnPlayer6 = (Spinner) findViewById(R.id.spnT1P6);

		tvt1p6 = (TextView) findViewById(R.id.tvTeam1Player6);
		tvt1p5 = (TextView) findViewById(R.id.tvTeam1Player5);
		tvt1p4 = (TextView) findViewById(R.id.tvTeam1Player4);
		tvt1p3 = (TextView) findViewById(R.id.tvTeam1Player3);
		tvt1p2 = (TextView) findViewById(R.id.tvTeam1Player2);
		tvt1p1 = (TextView) findViewById(R.id.tvTeam1Player1);
		
		addPlayersToList();
		setVisiblePlayers();
		TextView teamnametitle = (TextView) findViewById(R.id.tvTeam1Players);
		teamnametitle.setText(Team1Name);
		team2button = (Button) findViewById(R.id.btTeam2);
		team2button.setOnClickListener(this);
		Button addplayer = (Button) findViewById(R.id.bAddPlayer1);
		addplayer.setOnClickListener(this);
		Button delplayer = (Button) findViewById(R.id.bDelPlayer1);
		delplayer.setOnClickListener(this);
		
		
		
		

	}

	public void addPlayersToList() {
		// String[] arrayColumns = new String[]{"player_name",
		// "plyaer_initials"};
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

		switch (team1Players) {

		case 6:
			
			tvt1p6.setVisibility(View.VISIBLE);

			spnPlayer6.setVisibility(View.VISIBLE);

		case 5:
			
			tvt1p5.setVisibility(View.VISIBLE);

			spnPlayer5.setVisibility(View.VISIBLE);

		case 4:
			
			tvt1p4.setVisibility(View.VISIBLE);

			spnPlayer4.setVisibility(View.VISIBLE);

		case 3:
			
			tvt1p3.setVisibility(View.VISIBLE);

			spnPlayer3.setVisibility(View.VISIBLE);

		case 2:
			
			tvt1p2.setVisibility(View.VISIBLE);

			spnPlayer2.setVisibility(View.VISIBLE);

		case 1:
			
			tvt1p1.setVisibility(View.VISIBLE);

			spnPlayer1.setVisibility(View.VISIBLE);

			break;

		}

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btTeam2:
			addTeam1Members();
			Intent team2entry = new Intent("ca.longship.planetkubb.TEAMLISTS2");
			startActivity(team2entry);

			break;
		case R.id.bAddPlayer1:
			if (GlobalVars.sTeam1PlayNum == 1) {
				
				playercount = 2;
			} else if (GlobalVars.sTeam1PlayNum == 2) {
				
				playercount = 3;
			} else if (GlobalVars.sTeam1PlayNum == 3) {
				
				playercount = 4;

			} else if (GlobalVars.sTeam1PlayNum == 4) {
				
				playercount = 5;

			} else if (GlobalVars.sTeam1PlayNum == 5) {
				
				playercount = 6;

			} else if (GlobalVars.sTeam1PlayNum == 6) {
				Toast.makeText(getBaseContext(),
						"Cannot add any more players.  Max is 6",
						Toast.LENGTH_SHORT).show();
				playercount = 6;

			}
			GlobalVars.sTeam1PlayNum = playercount;
			team1Players = GlobalVars.sTeam1PlayNum;
			setVisiblePlayers();
			break;
		case R.id.bDelPlayer1:
			if (GlobalVars.sTeam1PlayNum == 1) {
				Toast.makeText(getBaseContext(),
						"Must have at least 1 player",
						Toast.LENGTH_SHORT).show();
				playercount = 1;
			} else if (GlobalVars.sTeam1PlayNum == 2) {
				tvt1p2.setVisibility(View.INVISIBLE);
				spnPlayer2.setVisibility(View.INVISIBLE);
				playercount = 1;
			} else if (GlobalVars.sTeam1PlayNum == 3) {
				tvt1p3.setVisibility(View.INVISIBLE);
				spnPlayer3.setVisibility(View.INVISIBLE);
				playercount = 2;

			} else if (GlobalVars.sTeam1PlayNum == 4) {
				tvt1p4.setVisibility(View.INVISIBLE);
				spnPlayer4.setVisibility(View.INVISIBLE);
				playercount = 3;

			} else if (GlobalVars.sTeam1PlayNum == 5) {
				tvt1p5.setVisibility(View.INVISIBLE);
				spnPlayer5.setVisibility(View.INVISIBLE);
				playercount = 4;

			} else if (GlobalVars.sTeam1PlayNum == 6) {
				tvt1p6.setVisibility(View.INVISIBLE);
				spnPlayer6.setVisibility(View.INVISIBLE);
				playercount = 5;

			}
			GlobalVars.sTeam1PlayNum = playercount;
			team1Players = GlobalVars.sTeam1PlayNum;
			setVisiblePlayers();
			
			break;
		}
	}

	public void addTeam1Members() {
		// TODO Auto-generated method stub

		String pname = null, pinit = null;
		String[] aname;
		for (int x = 0; x < team1Players; x++) {
			switch (x) {
			case 0:
				aname = spnPlayer1.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P1 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);

				break;
			case 1:
				aname = spnPlayer2.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P2 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);

				break;
			case 2:
				aname = spnPlayer3.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P3 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);
				break;
			case 3:
				aname = spnPlayer4.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P4 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);
				break;
			case 4:
				aname = spnPlayer5.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P5 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);
				break;
			case 5:
				aname = spnPlayer6.getSelectedItem().toString().split("-");
				pname = aname[0].trim();
				pinit = aname[1].trim();

				GlobalVars.setTeam1PlayerNames(pname, x);
				GlobalVars.sT1P6 = pname;

				GlobalVars.setTeam1PlayerInit(pinit, x);
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
