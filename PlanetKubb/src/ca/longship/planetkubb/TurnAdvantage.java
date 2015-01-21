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
import android.widget.TextView;

public class TurnAdvantage extends Activity implements OnClickListener {

	TextView titletv, teamtv;
	Button inkast;
	CheckBox cAdvant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.advantage);

		titletv = (TextView) findViewById(R.id.tvTitle);
		titletv.setText("Turn " + GlobalVars.getTurnNumber());
		teamtv = (TextView) findViewById(R.id.textView1);
		teamtv.setText("Team : " + GlobalVars.getCurrentTeam());
		inkast = (Button) findViewById(R.id.btInkast);
		inkast.setOnClickListener(this);
		cAdvant = (CheckBox) findViewById(R.id.cbAdvantage);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		if (cAdvant.isChecked()) {
			GlobalVars.setAdvantage(true);

		} else {
			GlobalVars.setAdvantage(false);

		}

		GlobalVars.setCurrentTurnString();
		Intent iinkast = new Intent("ca.longship.planetkubb.TURNINKAST");
		startActivity(iinkast);
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
