package ca.longship.planetkubb;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class TeamEntry extends Activity implements View.OnClickListener {

	EditText etTeam1Name, etTeam2Name, etTeam1Players, etTeam2Players;
	TextView tvErrorMsg;
	public String Team1Name, Team2Name;
	public Integer team1Players, team2Players;
	EditText date, location, event, pitch, scoredby;
	String currentDate;
	Button nextTeam;
	List<Address> addresses = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teams);

		initialize();

		nextTeam = (Button) findViewById(R.id.bTeam1);
		nextTeam.setOnClickListener(this);
		// date = (EditText) findViewById(R.id.etDate);
		location = (EditText) findViewById(R.id.etLocation);
		event = (EditText) findViewById(R.id.etEvent);
		pitch = (EditText) findViewById(R.id.etSurface);
		scoredby = (EditText) findViewById(R.id.etScoredBy);
		Button previousmatch = (Button) findViewById(R.id.bPrevious);
		previousmatch.setOnClickListener(this);
		

	}

	private void initialize() {
		// TODO Auto-generated method stub
		etTeam1Name = (EditText) findViewById(R.id.etTeam1Name);
		etTeam2Name = (EditText) findViewById(R.id.etTeam2Name);
		etTeam1Players = (EditText) findViewById(R.id.etTeam1NumberPlayers);
		etTeam2Players = (EditText) findViewById(R.id.etTeam2NumberPlayers);
		etTeam1Name.setText("Team 1");
		etTeam2Name.setText("Team 2");
		etTeam1Players.setText("2");
		etTeam2Players.setText("2");
		// Calendar c = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA);
		currentDate = df.format(new Date());
		date = (EditText) findViewById(R.id.etDate);
		date.setText(currentDate);
		tvErrorMsg = (TextView) findViewById(R.id.tvError1);

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.bTeam1:
			convertvariables();
			etTeam1Players.setTextColor(Color.BLACK);
			etTeam2Players.setTextColor(Color.BLACK);
			if (team1Players >= 1 && team1Players <= 6 && team2Players >= 1
					&& team2Players <= 6) {

				GlobalVars.setTeam1Name(etTeam1Name.getText());
				GlobalVars.setTeam2Name(etTeam2Name.getText());
				GlobalVars.setTeam1PlayNum(team1Players);
				GlobalVars.setTeam2PlayNum(team2Players);
				GlobalVars.setGameInformation(date.getText().toString(),
						location.getText().toString(), event.getText()
								.toString(), pitch.getText().toString(),
						scoredby.getText().toString());

				Intent team1List = new Intent(
						"ca.longship.planetkubb.TEAMLISTS");
				startActivity(team1List);

			} else if (team1Players <= 0 || team1Players >= 7) {
				etTeam1Players.setTextColor(Color.RED);
				tvErrorMsg.setVisibility(View.VISIBLE);
			} else if (team2Players <= 0 || team2Players >= 7) {
				etTeam2Players.setTextColor(Color.RED);
				tvErrorMsg.setVisibility(View.VISIBLE);
			}

			break;
		case R.id.bPrevious:
			///  Get last match data from database
			DBAccessMatch db = new DBAccessMatch(this);
			db.open();
			String[] matchID = {db.getMatchID()};
			String[] sMatchInfo = db.getAllMatchInfo(matchID);
			etTeam1Name.setText(sMatchInfo[1]);
			etTeam2Name.setText(sMatchInfo[2]);
			date.setText(sMatchInfo[3]);
			location.setText(sMatchInfo[4]);
			event.setText(sMatchInfo[5]);
			pitch.setText(sMatchInfo[6]);
			scoredby.setText(sMatchInfo[7]);
			db.close();
		}
	}

	private void convertvariables() {
		// TODO Auto-generated method stub
		Team1Name = etTeam1Name.getText().toString();
		Team2Name = etTeam2Name.getText().toString();
		team1Players = Integer.parseInt(etTeam1Players.getText().toString());
		team2Players = Integer.parseInt(etTeam2Players.getText().toString());

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
