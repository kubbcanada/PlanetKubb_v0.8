package ca.longship.planetkubb;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class GameOver extends Activity implements OnClickListener {

	EditText tvFinalString;
	Button bSubmitFinal, bCopyData;
	TextView tvMatch;
	String matchid;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.matchdetails);
//		setContentView(R.layout.gameover);
//
//		tvFinalString = (EditText) findViewById(R.id.tvScroller);
//		bSubmitFinal = (Button) findViewById(R.id.btSubmitGame);
//		bSubmitFinal.setOnClickListener(this);
//		bCopyData = (Button) findViewById(R.id.bCopyData);
//		bCopyData.setOnClickListener(this);
		
		
		tvMatch = (TextView) findViewById(R.id.tvMatchDetails);
		registerForContextMenu(tvMatch);
		DBAccessMatch info = new DBAccessMatch(this);
		info.open();

		// Create final text from database information here
		matchid = GlobalVars.sMatchID;
		tvMatch.setText(info.createFinalData(matchid));
		info.close();
		
	}

	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
//		switch (arg0.getId()) {
//		case R.id.btSubmitGame:
//			tvFinalString.setText(GlobalVars.getGameInfo());
//			bCopyData.setVisibility(View.VISIBLE);
//			break;
//		case R.id.bCopyData:
//			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//			ClipData clip = ClipData.newPlainText("Game", tvFinalString.getText().toString());
//			clipboard.setPrimaryClip(clip);
//			
//			
//		}
		
		
		
	}

	@Override
	public void onBackPressed() {
		new AlertDialog.Builder(this)
				.setTitle("Return")
				.setMessage("Do you really want to return to the main menu?")
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
	
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		
		if (v.getId() == R.id.tvMatchDetails) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Game", tvMatch.getText()
					.toString());
			clipboard.setPrimaryClip(clip);
			 Toast.makeText(getApplicationContext(), "Copied Match Data to Clipboard", Toast.LENGTH_LONG).show();

		}

}}
