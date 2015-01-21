package ca.longship.planetkubb;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.location.Address;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchEdit extends Activity implements View.OnTouchListener{

	TextView matchEditTitle;
	ListView matchEditList;
	String[] matchID = { "0" };
	ArrayList<String> TList = new ArrayList<String>();
	ArrayAdapter arrayAdapter;
	String sTurnToEdit;
	TextView tvThrow1, tvThrow2, tvThrow3, tvThrow4, tvThrow5, tvThrow6;
	String throw1, throw2, throw3, throw4, throw5, throw6;
	Button btNext, btThrow1Player, btThrow2Player, btThrow3Player,
			btThrow4Player, btThrow5Player, btThrow6Player;
	TextView titletv, teamtv, tTurnString, tvThrow1Error;
	DBAccessMatch db = new DBAccessMatch(this);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.editmatch);
		matchEditTitle = (TextView) findViewById(R.id.tvMatchEditTitle);
		matchEditList = (ListView) findViewById(R.id.lvMatchEditTurns);
		matchID[0] = getIntent().getExtras().getString("MatchID");
//		DBAccessMatch db = new DBAccessMatch(this);
		db.open();
		String[] columns1 = new String[] { db.KEY_MATCHID, db.KEY_EVENTNAME,
				db.KEY_DATE };

		String[] almatchinfo = db.getMatchInfo(columns1, matchID);
		matchEditTitle.setText("Match : " + almatchinfo[0].toString() + " - "
				+ almatchinfo[1] + " - " + almatchinfo[2]);

		TList = db.getTurnInfo(matchID);

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, TList);

		matchEditList.setAdapter(arrayAdapter);
		matchEditList.setClickable(true);
//		matchEditTitle.setClickable(true);
		matchEditTitle.setOnTouchListener(this);
		matchEditList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				String[] myString = matchEditList.getItemAtPosition(arg2).toString().split(" ");
				
				sTurnToEdit = myString[2];
				editThisMatch(matchID[0], sTurnToEdit);
				
			}
			
			
			
		});
		db.close();
	}

	@Override
	public void onBackPressed() {
		GlobalVars.resetValues();
		Intent openMainActivity = new Intent(
				"ca.longship.planetkubb.MATCHLIST");
		startActivity(openMainActivity);
		finish();
	}

	public void editThisMatch(String thisMatchID, String turnID){
		Toast.makeText(getBaseContext(), "I will edit Match : " + thisMatchID + " Turn # : " + turnID, Toast.LENGTH_SHORT).show();
		setContentView(R.layout.turnend);
//		ArrayList<String> turn = new ArrayList<String>();
		
		String[] matchID = {thisMatchID};
		tvThrow1 = (TextView) findViewById(R.id.tvTurn1);
		tvThrow2 = (TextView) findViewById(R.id.tvTurn2);
		tvThrow3 = (TextView) findViewById(R.id.tvTurn3);
		tvThrow4 = (TextView) findViewById(R.id.tvTurn4);
		tvThrow5 = (TextView) findViewById(R.id.tvTurn5);
		tvThrow6 = (TextView) findViewById(R.id.tvTurn6);
		btNext = (Button) findViewById(R.id.btNext);
		btThrow1Player = (Button) findViewById(R.id.bEditT1);
		btThrow2Player = (Button) findViewById(R.id.bEditT2);
		btThrow3Player = (Button) findViewById(R.id.bEditT3);
		btThrow4Player = (Button) findViewById(R.id.bEditT4);
		btThrow5Player = (Button) findViewById(R.id.bEditT5);
		btThrow6Player = (Button) findViewById(R.id.bEditT6);
		
//
//		btNext.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow1Player.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow2Player.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow3Player.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow4Player.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow5Player.setOnClickListener((android.view.View.OnClickListener) this);
//		btThrow6Player.setOnClickListener((android.view.View.OnClickListener) this);
		
		
		// Populate the Global Vars with this current turn info 
		
		
		String[] myString; 
		db.open();
		ArrayList<String> EditList = db.getTurnEditInfo(matchID);
		db.close();
		for (String s : EditList){
			myString = s.split("\\^");
			if (myString[0].trim().equalsIgnoreCase(turnID.trim())){
			String sTurnNumber = myString[0];
			String sTeamName = myString[1];
			String sInkast = myString[2];
			String sAdvantage = myString[3];
			String sThrow1 = myString[4];
			String sThrow2 = myString[5];
			String sThrow3 = myString[6];
			String sThrow4 = myString[7];
			String sThrow5 = myString[8];
			String sThrow6 = myString[9];
//			Toast.makeText(getBaseContext(), "Turn : " + sTurnNumber + " Team : " + sTeamName + " Inkast : " + sInkast, Toast.LENGTH_SHORT).show();
			tvThrow1.setText(sThrow1);
			tvThrow2.setText(sThrow2);
			tvThrow3.setText(sThrow3);
			tvThrow4.setText(sThrow4);
			tvThrow5.setText(sThrow5);
			tvThrow6.setText(sThrow6);
			
			}
			
		}
		
		
		
		
//		showThrows();
	
	}



	private void editMatchInfo() {
		// TODO Auto-generated method stub
		setContentView(R.layout.teams);
		final String[] matchtoedit = {matchID[0]};
		final EditText etTeam1Name;
		final EditText etTeam2Name, etTeam1Players, etTeam2Players, date, location, event, pitch, scoredby;
		Button update = (Button) findViewById(R.id.bTeam1);
		Button previous = (Button) findViewById(R.id.bPrevious);
		etTeam1Name = (EditText) findViewById(R.id.etTeam1Name);
		etTeam2Name = (EditText) findViewById(R.id.etTeam2Name);
		etTeam1Players = (EditText) findViewById(R.id.etTeam1NumberPlayers);
		etTeam2Players = (EditText) findViewById(R.id.etTeam2NumberPlayers);
		date = (EditText) findViewById(R.id.etDate);
		location = (EditText) findViewById(R.id.etLocation);
		event = (EditText) findViewById(R.id.etEvent);
		pitch = (EditText) findViewById(R.id.etSurface);
		scoredby = (EditText) findViewById(R.id.etScoredBy);
		previous.setVisibility(View.INVISIBLE);
		
		// Get information from DB
		db.open();
		final String[] sMatchInfo = db.getAllMatchInfo(matchtoedit);
		db.close();
		// Add information to TextFields
		etTeam1Name.setText(sMatchInfo[1]);
		etTeam2Name.setText(sMatchInfo[2]);
		etTeam1Players.setVisibility(View.INVISIBLE);
		etTeam2Players.setVisibility(View.INVISIBLE);
		date.setText(sMatchInfo[3]);
		location.setText(sMatchInfo[4]);
		event.setText(sMatchInfo[5]);
		pitch.setText(sMatchInfo[6]);
		scoredby.setText(sMatchInfo[7]);
		update.setText("Update Match Info");
		update.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String[] updateInfo = new String[]{"","","","","","","",""};
				updateInfo[0] = matchtoedit[0];
				updateInfo[1] = etTeam1Name.getText().toString();
				updateInfo[2] = etTeam2Name.getText().toString();
				updateInfo[3] = date.getText().toString();
				updateInfo[4] = location.getText().toString();
				updateInfo[5] = event.getText().toString();
				updateInfo[6] = pitch.getText().toString();
				updateInfo[7] = scoredby.getText().toString();
				
				db.open();
				db.updateMatchInfo(updateInfo);
				db.close();
				Intent doneedit = new Intent("ca.longship.planetkubb.MATCHLIST");
				startActivity(doneedit);
				finish();
				
				
				
			}});	
		
		
		
		
		


	}

	public void showThrows() {
		if (GlobalVars.t1bh == 0 && GlobalVars.t1fh == 0) {
			throw1 = "Miss";
		} else {
			throw1 = GlobalVars.t1bh + " base / " + GlobalVars.t1fh + " field";
		}
		if (GlobalVars.t2bh == 0 && GlobalVars.t2fh == 0) {
			throw2 = "Miss";
		} else {
			throw2 = GlobalVars.t2bh + " base / " + GlobalVars.t2fh + " field";
		}
		if (GlobalVars.t3bh == 0 && GlobalVars.t3fh == 0) {
			throw3 = "Miss";
		} else {
			throw3 = GlobalVars.t3bh + " base / " + GlobalVars.t3fh + " field";
		}
		if (GlobalVars.t4bh == 0 && GlobalVars.t4fh == 0) {
			throw4 = "Miss";
		} else {
			throw4 = GlobalVars.t4bh + " base / " + GlobalVars.t4fh + " field";
		}
		if (GlobalVars.t5bh == 0 && GlobalVars.t5fh == 0) {
			throw5 = "Miss";
		} else {
			throw5 = GlobalVars.t5bh + " base / " + GlobalVars.t5fh + " field";
		}
		if (GlobalVars.t6bh == 0 && GlobalVars.t6fh == 0) {
			throw6 = "Miss";
		} else {
			throw6 = GlobalVars.t6bh + " base / " + GlobalVars.t6fh + " field";
		}
		tvThrow1.setText(throw1);
		tvThrow2.setText(throw2);
		tvThrow3.setText(throw3);
		tvThrow4.setText(throw4);
		tvThrow5.setText(throw5);
		tvThrow6.setText(throw6);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {

		editMatchInfo();
		
		
		return false;
	}
	
}
