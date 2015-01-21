package ca.longship.planetkubb;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class TurnEnd extends Activity implements OnClickListener {


    private TextView tvThrow1;
    private TextView tvThrow2;
    private TextView tvThrow3;
    private TextView tvThrow4;
    private TextView tvThrow5;
    private TextView tvThrow6;
    TextView titletv, teamtv, tTurnString, tvThrow1Error;
    Button Turn2;
    Spinner spnPlayerNames, spnKubbsHit;
    String sCurrentTeam, sTurnString, sBase, sField;
    ArrayList<String> saPlayerInitials = new ArrayList<String>();
    String saKubbsHit[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
            "10"};
    CheckBox cbKing;
    Integer iBase, iField;
    Boolean bError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.turnend);
        tvThrow1 = (TextView) findViewById(R.id.tvTurn1);
        tvThrow2 = (TextView) findViewById(R.id.tvTurn2);
        tvThrow3 = (TextView) findViewById(R.id.tvTurn3);
        tvThrow4 = (TextView) findViewById(R.id.tvTurn4);
        tvThrow5 = (TextView) findViewById(R.id.tvTurn5);
        tvThrow6 = (TextView) findViewById(R.id.tvTurn6);
        Button btNext = (Button) findViewById(R.id.btNext);
        Button btThrow1Player = (Button) findViewById(R.id.bEditT1);
        Button btThrow2Player = (Button) findViewById(R.id.bEditT2);
        Button btThrow3Player = (Button) findViewById(R.id.bEditT3);
        Button btThrow4Player = (Button) findViewById(R.id.bEditT4);
        Button btThrow5Player = (Button) findViewById(R.id.bEditT5);
        Button btThrow6Player = (Button) findViewById(R.id.bEditT6);
        showThrows();

        btNext.setOnClickListener(this);
        btThrow1Player.setOnClickListener(this);
        btThrow2Player.setOnClickListener(this);
        btThrow3Player.setOnClickListener(this);
        btThrow4Player.setOnClickListener(this);
        btThrow5Player.setOnClickListener(this);
        btThrow6Player.setOnClickListener(this);

    }

    @Override
    public void onClick(View arg0) {
        // TODO Auto-generated method stub
        Intent editthrow = new Intent("ca.longship.planetkubb.THROWEDIT");
        switch (arg0.getId()) {
            case R.id.btNext:
//			GlobalVars.createInkastString();
                GlobalVars.createThrow1String();
                GlobalVars.createThrow2String();
                GlobalVars.createThrow3String();
                GlobalVars.createThrow4String();
                GlobalVars.createThrow5String();
                GlobalVars.createThrow6String();
                addTurnToDB(this);
                GlobalVars.initializeNewTurn();
                Intent inkast = new Intent("ca.longship.planetkubb.TURNINKAST");
                startActivity(inkast);
                finish();
                break;
            case R.id.bEditT1:
                // Show Throw form
                GlobalVars.setTurnEdit(1);
                // Intent editthrow = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT2:
                GlobalVars.setTurnEdit(2);
                // Intent editthrow2 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT3:
                GlobalVars.setTurnEdit(3);
                // Intent editthrow3 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT4:
                GlobalVars.setTurnEdit(4);
                // Intent editthrow4 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT5:
                GlobalVars.setTurnEdit(5);
                // Intent editthrow5 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT6:
                GlobalVars.setTurnEdit(6);
                // Intent editthrow6 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;

        }

    }

    void showThrows() {
        String throw1;
        if (GlobalVars.t1bh == 0 && GlobalVars.t1fh == 0) {
            throw1 = "Miss";
        } else {
            throw1 = GlobalVars.t1bh + " base / " + GlobalVars.t1fh + " field";
        }
        String throw2;
        if (GlobalVars.t2bh == 0 && GlobalVars.t2fh == 0) {
            throw2 = "Miss";
        } else {
            throw2 = GlobalVars.t2bh + " base / " + GlobalVars.t2fh + " field";
        }
        String throw3;
        if (GlobalVars.t3bh == 0 && GlobalVars.t3fh == 0) {
            throw3 = "Miss";
        } else {
            throw3 = GlobalVars.t3bh + " base / " + GlobalVars.t3fh + " field";
        }
        String throw4;
        if (GlobalVars.t4bh == 0 && GlobalVars.t4fh == 0) {
            throw4 = "Miss";
        } else {
            throw4 = GlobalVars.t4bh + " base / " + GlobalVars.t4fh + " field";
        }
        String throw5;
        if (GlobalVars.t5bh == 0 && GlobalVars.t5fh == 0) {
            throw5 = "Miss";
        } else {
            throw5 = GlobalVars.t5bh + " base / " + GlobalVars.t5fh + " field";
        }
        String throw6;
        if (GlobalVars.t6bh == 0 && GlobalVars.t6fh == 0) {
            throw6 = "Miss";
        } else {
            throw6 = GlobalVars.t6bh + " base / " + GlobalVars.t6fh + " field";
        }
        tvThrow1.setText(throw1);
        tvThrow2.setText(throw2);
        tvThrow3.setText(throw3);
        tvThrow4.setText(throw4);
        tvThrow5.setText(throw5);
        tvThrow6.setText(throw6);
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

    public static void addTurnToDB(Context cont) {
        boolean didItWork = true;
        try {
//			Toast.makeText(cont, "Adding Turn to Database", Toast.LENGTH_SHORT).show();
            DBAccessMatch dbAccess = new DBAccessMatch(cont);
            GlobalVars.varsNotNull();
            dbAccess.open();
            dbAccess.createTurn(GlobalVars.sMatchID,
                    GlobalVars.iTurn.toString(), GlobalVars.sCurrentTeam,
                    GlobalVars.sInkast, GlobalVars.iInkast.toString(),
                    GlobalVars.bAdvantage.toString(), GlobalVars.sTurn1Player,
                    GlobalVars.sTurn1Hit, GlobalVars.sTurn2Player,
                    GlobalVars.sTurn2Hit, GlobalVars.sTurn3Player,
                    GlobalVars.sTurn3Hit, GlobalVars.sTurn4Player,
                    GlobalVars.sTurn4Hit, GlobalVars.sTurn5Player,
                    GlobalVars.sTurn5Hit, GlobalVars.sTurn6Player,
                    GlobalVars.sTurn6Hit);
            dbAccess.close();
            GlobalVars.iTurn++;
//			Toast.makeText(cont, "Turn Added!!!", Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            didItWork = false;
            String error = e.toString();
            Dialog d = new Dialog(cont);
            d.setTitle("Dang it!");
            TextView tv = new TextView(cont);
            tv.setText(error);
            d.setContentView(tv);
            d.show();
        } finally {

        }
    }
}
