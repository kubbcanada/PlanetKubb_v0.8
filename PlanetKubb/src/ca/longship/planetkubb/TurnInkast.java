package ca.longship.planetkubb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class TurnInkast extends Activity implements OnClickListener {

	TextView titletv, teamtv, tTurnString, tInkastError;
	Button throw1;
	EditText etInkast, etRethrow, etPenalty;
	Integer inkast, rethrow, penalty;
	CheckBox cAdvant;
	Spinner spnPlayerInkast;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.inkast);
		GlobalVars.setTeam();
		GlobalVars.setCurrentTurnString();
		titletv = (TextView) findViewById(R.id.tvTitle);
		titletv.setText("Turn " + GlobalVars.getTurnNumber());
		teamtv = (TextView) findViewById(R.id.textView1);
		teamtv.setText("Team : " + GlobalVars.getCurrentTeam());
		tTurnString = (TextView) findViewById(R.id.tvTurnString);
		tTurnString.setText(GlobalVars.getCurrentTurnString());
		throw1 = (Button) findViewById(R.id.btThrow1);
		throw1.setOnClickListener(this);
		spnPlayerInkast = (Spinner) findViewById(R.id.spnPlayerInkast);
		etInkast = (EditText) findViewById(R.id.etInkast);
		etRethrow = (EditText) findViewById(R.id.etRethrow);
		etPenalty = (EditText) findViewById(R.id.etPenalty);
		cAdvant = (CheckBox) findViewById(R.id.cbAdvantage);
		tInkastError = (TextView) findViewById(R.id.tvInkastError);
		tInkastError.setVisibility(View.INVISIBLE);
		etInkast.setText(String.valueOf(GlobalVars.getKubbsKnockedDown()));
		if (GlobalVars.getAdv()){
			cAdvant.setChecked(true);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		if (etInkast.getText().toString().trim().length() == 0){
			etInkast.setText("0");
		} else if (etRethrow.getText().toString().trim().length() == 0){
			etRethrow.setText("0");
		} else if (etPenalty.getText().toString().trim().length() == 0){
			etPenalty.setText("0");
		}
		
		inkast = Integer.parseInt(etInkast.getText().toString());
		rethrow = Integer.parseInt(etRethrow.getText().toString());
		penalty = Integer.parseInt(etPenalty.getText().toString());
		
		

		if (rethrow > inkast) {
			tInkastError.setVisibility(View.VISIBLE);
			tInkastError.setText("Rethrows value is too high.");
//		} else if ((rethrow - inkast) > 0 && penalty > (rethrow - inkast) || (penalty > rethrow) || (penalty > (rethrow/2))) {
		} else if ((rethrow - inkast) > 0 && (penalty > rethrow)) {
			tInkastError.setVisibility(View.VISIBLE);
			tInkastError.setText("Penalty value is too high.");
		} else	{

			GlobalVars.setInkast(inkast, rethrow, penalty);
			GlobalVars.createInkastString();
			GlobalVars.setCurrentTurnString();
			tTurnString.setText(GlobalVars.getCurrentTurnString());
			GlobalVars.addToString("{{Game turn\n|Kubb throw 1=" + inkast + "i"
					+ rethrow + "r\n|Advantage line=");
			if (cAdvant.isChecked()) {
				GlobalVars.setAdvantage(true);
				GlobalVars.addToString("Yes\n");
			} else {
				GlobalVars.setAdvantage(false);
				GlobalVars.addToString("No\n");

			}

			GlobalVars.setFieldKubbs(GlobalVars.getFieldKubbsLeft() + inkast);
//			Intent throw1 = new Intent("ca.longship.planetkubb.THROW1");
			Intent throw1 = new Intent("ca.longship.planetkubb.THROW1ALT");
			startActivity(throw1);
			finish();
		}
	}
	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
	    .setTitle("Quit Match")
	    .setMessage("Do you really want to quit scoring?")
	    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // continue with delete
				Intent openMainActivity = new Intent("ca.longship.planetkubb.MAINACTIVITY");
				startActivity(openMainActivity);
				finish();
	        }
	     })
	    .setNegativeButton("No", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int which) { 
	            // do nothing
	        }
	     })
	     .show();
		
		return;
	}
	

}


