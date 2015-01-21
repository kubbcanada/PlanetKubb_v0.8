package ca.longship.planetkubb;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

	Button start;
	

	// EditText etTeam1Name, etTeam2Name, etTeam1Players, etTeam2Players;
	// Integer team1Players, team2Players;
	// String Team1Name, Team2Name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		start = (Button) findViewById(R.id.bStart);
		Button matches = (Button) findViewById(R.id.bListMatches);
		Button playerinfo = (Button) findViewById(R.id.bPlayerInfo);
		start.setOnClickListener(this);
		matches.setOnClickListener(this);
		playerinfo.setOnClickListener(this);
		Button instructions = (Button) findViewById(R.id.bInstructions);
		instructions.setOnClickListener(this);

	
	}

	@Override
	public void onClick(View v) {
		
		switch (v.getId()) {
		case R.id.bStart:
			Intent teamLists = new Intent("ca.longship.planetkubb.TEAMENTRY");
			startActivity(teamLists);
			finish();
			
			break;
		case R.id.bListMatches:
			Intent matchLists = new Intent("ca.longship.planetkubb.MATCHLIST");
			startActivity(matchLists);
			finish();
			break;
		case R.id.bPlayerInfo:
			Intent playerinfo = new Intent("ca.longship.planetkubb.PLAYERINFO");
			startActivity(playerinfo);
			finish();
			break;
		case R.id.bInstructions:
			AlertDialog.Builder ad = new AlertDialog.Builder(this);
			ad.setTitle("Instructions");
			ad.setView(LayoutInflater.from(this).inflate(R.layout.instructions, null));
			ad.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			ad.show();
			break;

		}
		
		

	}

}
