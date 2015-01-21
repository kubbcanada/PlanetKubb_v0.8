package ca.longship.planetkubb;

import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Throw5Alt extends Activity implements OnClickListener,
		CompoundButton.OnCheckedChangeListener {

	TextView titletv, teamtv, tTurnString, tvThrow1Error;
	Button Turn2;
	Spinner spnPlayerNames, spnKubbsHit;
	String sCurrentTeam, sTurnString, sBase, sField;
	ArrayList<String> saPlayerInitials = new ArrayList<String>();
	String saKubbsHit[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"10" };
	CheckBox cbKing;
	Integer iBase, iField;
	Boolean bError = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.throw1alt);
		GlobalVars.setCurrentTurnString();
		titletv = (TextView) findViewById(R.id.tvTitle);
		titletv.setText("Throw " + GlobalVars.getTurnNumber());
		teamtv = (TextView) findViewById(R.id.textView1);
		teamtv.setText("Team : " + GlobalVars.getCurrentTeam());
		tTurnString = (TextView) findViewById(R.id.tvTurnString);
		tTurnString.setText(GlobalVars.getCurrentTurnString());

		Turn2 = (Button) findViewById(R.id.btTurn2);
		Turn2.setText("Throw 6");
		Turn2.setOnClickListener(this);

		// Get Current Team
		sCurrentTeam = GlobalVars.getCurrentTeam();
		spnPlayerNames = (Spinner) findViewById(R.id.spnPlayer);
		spnKubbsHit = (Spinner) findViewById(R.id.spnKubbsHit);
		cbKing = (CheckBox) findViewById(R.id.cbKing);

		tvThrow1Error = (TextView) findViewById(R.id.tvThrow1Error);
		// tvThrow1Error.setVisibility(View.INVISIBLE);
		tvThrow1Error.setText(GlobalVars.getBaseKubbsLeft() + " - "
				+ GlobalVars.getFieldKubbsLeft());

		cbKing.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub

				if (isChecked) {
					Turn2.setText("Game Ended");

				} else {
					Turn2.setText("Throw 6");
				}
			}
		});

		setupspinner();
		//
		

	}

	private void setupspinner() {
		// TODO Auto-generated method stub

		int t1 = GlobalVars.getTeam1PlayNum();
		int t2 = GlobalVars.getTeam2PlayNum();
		String t1name = GlobalVars.getTeam1Name();
		String t2name = GlobalVars.getTeam2Name();

		// / Add players to the player spinner

		if (sCurrentTeam == t1name) {
			for (int x = 0; x < t1; x++) {
				saPlayerInitials.add(GlobalVars.getTeam1PlayerInitials(x));
			}

		} else if (sCurrentTeam == t2name) {
			for (int x = 0; x < t2; x++) {
				saPlayerInitials.add(GlobalVars.getTeam2PlayerInitials(x));
			}

		}
		ArrayAdapter<String> playerinits = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, saPlayerInitials);
		playerinits
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnPlayerNames.setAdapter(playerinits);

		// / Add Kubbs Hit spinner

		ArrayAdapter<String> numberofkubbs = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, saKubbsHit);
		numberofkubbs
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spnKubbsHit.setAdapter(numberofkubbs);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub



		// If the king is hit, change the string to end the game

		if (cbKing.isChecked()) {

			
			GlobalVars.createThrow1String();
			GlobalVars.createThrow2String();
			GlobalVars.createThrow3String();
			GlobalVars.createThrow4String();
			GlobalVars.addToString("|Throw 5 player=" + spnPlayerNames.getSelectedItem().toString() + "\n");
			
			sTurnString = spnPlayerNames.getSelectedItem().toString() + ":K";
			GlobalVars.addToString("|Throw 5=K\n");
			GlobalVars.addToString("|Throw 6=X\n");
			GlobalVars.addToString("}}");
			
			GlobalVars.sTurn5Hit="K";
			GlobalVars.sTurn6Hit="X";

		} else {

			// Calculate What Kubbs were hit based on what remains on Field/Base
			bError = false;
			iBase = GlobalVars.getBaseKubbsLeft();
			iField = GlobalVars.getFieldKubbsLeft();
			Integer iKubbHit = Integer.parseInt(spnKubbsHit.getSelectedItem()
					.toString());
			GlobalVars.addPlayerName(spnPlayerNames.getSelectedItem().toString());
			if (iKubbHit == 0) {
				sTurnString = spnPlayerNames.getSelectedItem().toString()
						+ ":-";
				GlobalVars.setKubbsKnockedDown(0, 0);
			}

			else if (iKubbHit >= iField) {
				if ((iKubbHit - iField) > iBase) {
					tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
					bError = true;
				} else {
					sTurnString = spnPlayerNames.getSelectedItem().toString()
							+ ":" + (iKubbHit - iField) + "b" + iField + "f";
					GlobalVars.setFieldKubbs(0);
					GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
					GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);
				}
			} else {
				GlobalVars.setFieldKubbs(iField - iKubbHit);
				GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
			}

		}

		if (bError) {
		} else {
			GlobalVars.setTurnNumber(6);
			if (Turn2.getText().toString() == "Game Ended") {
				TurnEnd.addTurnToDB(this);
				Intent gameover = new Intent("ca.longship.planetkubb.GAMEOVER");
				startActivity(gameover);
				finish();

			} else {
				GlobalVars.setCurrentTurnString();
				Intent throw6 = new Intent("ca.longship.planetkubb.THROW6ALT");
				startActivity(throw6);
				finish();
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
								GlobalVars.resetValues();
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

	@Override
	public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
		// TODO Auto-generated method stub

	}

}
