package ca.longship.planetkubb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class WhoFirst extends Activity implements View.OnClickListener {

	Button startTurns;
	RadioButton team1, team2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.whofirst);
		team1 = (RadioButton) findViewById(R.id.radio0);
		team2 = (RadioButton) findViewById(R.id.radio1);
		team1.setText(GlobalVars.getTeam1Name());
		team2.setText(GlobalVars.getTeam2Name());
		startTurns = (Button) findViewById(R.id.btWhoFirst);
		startTurns.setOnClickListener(this);

	}

	// ********************************
	// ** Setup which team goes first *
	// ********************************

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Needs to be reversed here due to the setting of the team in the
		// Inkast module
		if (team1.isChecked()) {
			GlobalVars.setFirstTeam(team2.getText().toString());
		} else if (team2.isChecked()) {
			GlobalVars.setFirstTeam(team1.getText().toString());
		}

		GlobalVars.setTurnNumber(1);
		GlobalVars.initializegame();

		GlobalVars.addToString("{{Game initialize}}\n");
		Intent processturn = new Intent("ca.longship.planetkubb.TURNINKAST");

		startActivity(processturn);

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
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
