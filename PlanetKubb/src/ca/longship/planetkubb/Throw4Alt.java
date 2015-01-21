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

public class Throw4Alt extends Activity implements OnClickListener,
        CompoundButton.OnCheckedChangeListener {

    private TextView tvThrow1Error;
    private Button Turn2;
    private Spinner spnPlayerNames;
    private Spinner spnKubbsHit;
    private String sCurrentTeam;
    String sBase;
    String sField;
    private final ArrayList<String> saPlayerInitials = new ArrayList<String>();
    private final String[] saKubbsHit = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10"};
    private CheckBox cbKing;
    private Boolean bError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.throw1alt);
        GlobalVars.setCurrentTurnString();
        TextView titletv = (TextView) findViewById(R.id.tvTitle);
        titletv.setText("Throw " + GlobalVars.getTurnNumber());
        TextView teamtv = (TextView) findViewById(R.id.textView1);
        teamtv.setText("Team : " + GlobalVars.getCurrentTeam());
        TextView tTurnString = (TextView) findViewById(R.id.tvTurnString);
        tTurnString.setText(GlobalVars.getCurrentTurnString());

        Turn2 = (Button) findViewById(R.id.btTurn2);
        Turn2.setText("Throw 5");
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
                    Turn2.setText("Throw 5");
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

        if (sCurrentTeam.equals(t1name)) {
            for (int x = 0; x < t1; x++) {
                saPlayerInitials.add(GlobalVars.getTeam1PlayerInitials(x));
            }

        } else if (sCurrentTeam.equals(t2name)) {
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

        String sTurnString;
        if (cbKing.isChecked()) {

            sTurnString = spnPlayerNames.getSelectedItem().toString() + ":K";

            GlobalVars.createThrow1String();
            GlobalVars.createThrow2String();
            GlobalVars.createThrow3String();
            GlobalVars.addToString("|Throw 4 player=" + spnPlayerNames.getSelectedItem().toString() + "\n");


            GlobalVars.addToString("|Throw 4=K\n");
            GlobalVars.addToString("|Throw 5=X\n");
            GlobalVars.addToString("|Throw 6=X\n");
            GlobalVars.addToString("}}");
            GlobalVars.sTurn4Hit = "K";
            GlobalVars.sTurn5Hit = "X";
            GlobalVars.sTurn6Hit = "X";

        } else {

            // Calculate What Kubbs were hit based on what remains on Field/Base
            bError = false;
            Integer iBase = GlobalVars.getBaseKubbsLeft();
            Integer iField = GlobalVars.getFieldKubbsLeft();
            Integer iKubbHit = Integer.parseInt(spnKubbsHit.getSelectedItem()
                    .toString());
            GlobalVars.addPlayerName(spnPlayerNames.getSelectedItem().toString());
            if (iKubbHit == 0) {
                sTurnString = spnPlayerNames.getSelectedItem().toString()
                        + ":-";
                GlobalVars.setKubbsKnockedDown(0, 0);
            } else if (iKubbHit >= iField) {
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

        if (!bError) {

            GlobalVars.setTurnNumber(5);
            if (Turn2.getText().toString().equals("Game Ended")) {
                TurnEnd.addTurnToDB(this);
                Intent gameover = new Intent("ca.longship.planetkubb.GAMEOVER");
                startActivity(gameover);
                finish();

            } else {
                GlobalVars.setCurrentTurnString();
                Intent throw5 = new Intent("ca.longship.planetkubb.THROW5ALT");
                startActivity(throw5);
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


    }

    @Override
    public void onCheckedChanged(CompoundButton arg0, boolean arg1) {
        // TODO Auto-generated method stub

    }

}
