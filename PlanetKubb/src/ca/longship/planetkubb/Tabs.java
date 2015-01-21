package ca.longship.planetkubb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;

public class Tabs extends Activity implements OnClickListener,
		OnTabChangeListener {
	TextView teamtv, tTurnString, tInkastError;
	EditText etInkast, etRethrow, etPenalty;
	Integer inkast, rethrow, penalty;
	CheckBox cAdvant;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		TabHost th = (TabHost) findViewById(R.id.tabhost);
		th.setup();
		th.setOnTabChangedListener(this);
		TabSpec specs = th.newTabSpec("tag1");
		specs.setContent(R.id.tab1);
		specs.setIndicator("Inkast");
		th.addTab(specs);
		specs = th.newTabSpec("tag2");
		specs.setContent(R.id.tab2);
		specs.setIndicator("1");
		th.addTab(specs);
		specs = th.newTabSpec("tag3");
		specs.setContent(R.id.tab3);
		specs.setIndicator("2");
		th.addTab(specs);
		specs = th.newTabSpec("tag4");
		specs.setContent(R.id.tab4);
		specs.setIndicator("3");
		th.addTab(specs);
		specs = th.newTabSpec("tag5");
		specs.setContent(R.id.tab5);
		specs.setIndicator("4");
		th.addTab(specs);
		specs = th.newTabSpec("tag6");
		specs.setContent(R.id.tab6);
		specs.setIndicator("5");
		th.addTab(specs);
		specs = th.newTabSpec("tag7");
		specs.setContent(R.id.tab7);
		specs.setIndicator("6");
		th.addTab(specs);
		specs = th.newTabSpec("tag7");
		specs.setContent(R.id.tab8);
		specs.setIndicator("End");
		th.addTab(specs);

		// Code for Inkast

		GlobalVars.setTeam();
		GlobalVars.setCurrentTurnString();
		teamtv = (TextView) findViewById(R.id.textView1);
		teamtv.setText("Team : " + GlobalVars.getCurrentTeam());
		etInkast = (EditText) findViewById(R.id.etInkast);
		etRethrow = (EditText) findViewById(R.id.etRethrow);
		etPenalty = (EditText) findViewById(R.id.etPenalty);
		cAdvant = (CheckBox) findViewById(R.id.cbAdvantage);
		tInkastError = (TextView) findViewById(R.id.tvInkastError);
		tInkastError.setVisibility(View.INVISIBLE);
		etInkast.setText(String.valueOf(GlobalVars.getKubbsKnockedDown()));

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabChanged(String arg0) {
		// TODO Auto-generated method stub
		if (etInkast.getText().toString().trim().length() == 0) {
			etInkast.setText("0");
		} else if (etRethrow.getText().toString().trim().length() == 0) {
			etRethrow.setText("0");
		} else if (etPenalty.getText().toString().trim().length() == 0) {
			etPenalty.setText("0");
		}

		inkast = Integer.parseInt(etInkast.getText().toString());
		rethrow = Integer.parseInt(etRethrow.getText().toString());
		penalty = Integer.parseInt(etPenalty.getText().toString());

		if (rethrow > inkast) {
			tInkastError.setVisibility(View.VISIBLE);
			tInkastError.setText("Rethrows value is too high.");

		} else if ((rethrow - inkast) > 0 && (penalty > rethrow)) {
			tInkastError.setVisibility(View.VISIBLE);
			tInkastError.setText("Penalty value is too high.");
		} else {

			GlobalVars.setInkast(inkast, rethrow, penalty);
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

		}
	}

}
