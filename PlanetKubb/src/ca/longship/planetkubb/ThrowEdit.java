package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

public class ThrowEdit extends Activity implements OnClickListener {

    TextView titletv;
    TextView teamtv;
    TextView tTurnString;
    private TextView tvThrow1Error;
    private Spinner spnPlayerNames;
    private Spinner spnKubbsHit;
    private String sCurrentTeam;
    CheckBox cbKing;
    String sTurnString;
    String sBase;
    String sField;
    private final ArrayList<String> saPlayerInitials = new ArrayList<String>();
    private final String[] saKubbsHit = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10"};
    ArrayAdapter<String> playerinits;
    ArrayAdapter<String> numberofkubbs;
    String kubbshit = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.throw1alt);
        cbKing = (CheckBox) findViewById(R.id.cbKing);
        spnPlayerNames = (Spinner) findViewById(R.id.spnPlayer);
        spnKubbsHit = (Spinner) findViewById(R.id.spnKubbsHit);
        sCurrentTeam = GlobalVars.getCurrentTeam();
        setupspinner();
        Button turn2 = (Button) findViewById(R.id.btTurn2);
        turn2.setText("Submit");
        turn2.setOnClickListener(this);
        tvThrow1Error = (TextView) findViewById(R.id.tvThrow1Error);

        switch (GlobalVars.iTurnEdit) {
            case 1:
                // Set Up Turn 1 info in form
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn1Player));
                spnKubbsHit.setSelection(GlobalVars.t1bh + GlobalVars.t1fh);
                if (GlobalVars.sTurn1Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
            case 2:
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn2Player));
                spnKubbsHit.setSelection(GlobalVars.t2bh + GlobalVars.t2fh);
                if (GlobalVars.sTurn2Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
            case 3:
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn3Player));
                spnKubbsHit.setSelection(GlobalVars.t3bh + GlobalVars.t3fh);
                if (GlobalVars.sTurn3Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
            case 4:
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn4Player));
                spnKubbsHit.setSelection(GlobalVars.t4bh + GlobalVars.t4fh);
                if (GlobalVars.sTurn4Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
            case 5:
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn5Player));
                spnKubbsHit.setSelection(GlobalVars.t5bh + GlobalVars.t5fh);
                if (GlobalVars.sTurn5Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
            case 6:
                spnPlayerNames.setSelection(saPlayerInitials
                        .indexOf(GlobalVars.sTurn6Player));
                spnKubbsHit.setSelection(GlobalVars.t6bh + GlobalVars.t6fh);
                if (GlobalVars.sTurn6Hit.equals("K")){
                    cbKing.setChecked(true);
                }
                break;
        }

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Boolean bError = false;
        Integer iKubbHit = Integer.parseInt(spnKubbsHit.getSelectedItem()
                .toString());
        if (cbKing.isChecked()){
            GlobalVars.bKingHit = true;
        } else {
            GlobalVars.bKingHit = false;
        }


        switch (GlobalVars.iTurnEdit) {
            case 1:
                // Set Up Turn 1 info in form

                GlobalVars.setTurnNumber(1);

                Integer iField = GlobalVars.t1fh + GlobalVars.getFieldKubbsLeft();
                Integer iBase = GlobalVars.t1bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }
                if (cbKing.isChecked()){
                    GlobalVars.sTurn1Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn1Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
            case 2:
                GlobalVars.setTurnNumber(2);

                iField = GlobalVars.t2fh + GlobalVars.getFieldKubbsLeft();
                iBase = GlobalVars.t2bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }

                if (cbKing.isChecked()){
                    GlobalVars.sTurn2Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn2Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
            case 3:
                GlobalVars.setTurnNumber(3);

                iField = GlobalVars.t3fh + GlobalVars.getFieldKubbsLeft();
                iBase = GlobalVars.t3bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }

                if (cbKing.isChecked()){
                    GlobalVars.sTurn3Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn3Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
            case 4:
                GlobalVars.setTurnNumber(4);

                iField = GlobalVars.t4fh + GlobalVars.getFieldKubbsLeft();
                iBase = GlobalVars.t4bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }

                if (cbKing.isChecked()){
                    GlobalVars.sTurn4Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn4Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
            case 5:
                GlobalVars.setTurnNumber(5);

                iField = GlobalVars.t5fh + GlobalVars.getFieldKubbsLeft();
                iBase = GlobalVars.t5bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }

                if (cbKing.isChecked()){
                    GlobalVars.sTurn5Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn5Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
            case 6:
                GlobalVars.setTurnNumber(6);

                iField = GlobalVars.t6fh + GlobalVars.getFieldKubbsLeft();
                iBase = GlobalVars.t6bh + GlobalVars.getBaseKubbsLeft();

                if (iKubbHit == 0) {

                    GlobalVars.setKubbsKnockedDown(0, 0);

                } else if (iKubbHit >= iField) {
                    if ((iKubbHit - iField) > iBase) {
                        tvThrow1Error.setText("Kubbs hit exceeds kubbs remaining.");
                        bError = true;
                    } else {

                        GlobalVars.setFieldKubbs(0);
                        GlobalVars.setBaseKubbs(iBase - (iKubbHit - iField));
                        GlobalVars.setKubbsKnockedDown(iField, iKubbHit - iField);

                    }
                } else if (iKubbHit < iField) {

                    GlobalVars.setFieldKubbs(iField - iKubbHit);
                    GlobalVars.setKubbsKnockedDown(iKubbHit, 0);
                }

                if (cbKing.isChecked()){
                    GlobalVars.sTurn6Hit.equals("K");
                    GlobalVars.bKingHit.equals(true);
                }else{
                    GlobalVars.sTurn6Hit.equals(" ");
                    GlobalVars.bKingHit.equals(false);
                }

                if (!bError) {


                    GlobalVars.setCurrentTurnString();
                    Intent turnend = new Intent("ca.longship.planetkubb.TURNEND");
                    startActivity(turnend);
                }

                break;
        }

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

}