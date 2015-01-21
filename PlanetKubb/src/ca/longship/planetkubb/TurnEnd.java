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
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.*;
import static ca.longship.planetkubb.GlobalVars.*;

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
                createThrow1String();
                createThrow2String();
                createThrow3String();
                createThrow4String();
                createThrow5String();
                createThrow6String();
                addTurnToDB(this);
                initializeNewTurn();
                Intent inkast = new Intent("ca.longship.planetkubb.TURNINKAST");
                startActivity(inkast);
                finish();
                break;
            case R.id.bEditT1:
                // Show Throw form
                setTurnEdit(1);
                // Intent editthrow = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT2:
                setTurnEdit(2);
                // Intent editthrow2 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT3:
                setTurnEdit(3);
                // Intent editthrow3 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT4:
                setTurnEdit(4);
                // Intent editthrow4 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT5:
                setTurnEdit(5);
                // Intent editthrow5 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;
            case R.id.bEditT6:
                setTurnEdit(6);
                // Intent editthrow6 = new
                // Intent("ca.longship.planetkubb.THROWEDIT");
                startActivity(editthrow);
                finish();
                break;

        }

    }

    void showThrows() {
        String throw1;
        if (t1bh == 0 && t1fh == 0) {
            throw1 = "Miss";
        } else {
            throw1 = t1bh + " base / " + t1fh + " field";
        }
        String throw2;
        if (t2bh == 0 && t2fh == 0) {
            throw2 = "Miss";
        } else {
            throw2 = t2bh + " base / " + t2fh + " field";
        }
        String throw3;
        if (t3bh == 0 && t3fh == 0) {
            throw3 = "Miss";
        } else {
            throw3 = t3bh + " base / " + t3fh + " field";
        }
        String throw4;
        if (t4bh == 0 && t4fh == 0) {
            throw4 = "Miss";
        } else {
            throw4 = t4bh + " base / " + t4fh + " field";
        }
        String throw5;
        if (t5bh == 0 && t5fh == 0) {
            throw5 = "Miss";
        } else {
            throw5 = t5bh + " base / " + t5fh + " field";
        }
        String throw6;
        if (t6bh == 0 && t6fh == 0) {
            throw6 = "Miss";
        } else {
            throw6 = t6bh + " base / " + t6fh + " field";
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
                                resetValues();
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
			makeText(cont, "Adding Turn to Database", LENGTH_SHORT).show();
            DBAccessMatch dbAccess = new DBAccessMatch(cont);
            varsNotNull();
            dbAccess.open();
            dbAccess.createTurn(sMatchID,
                    iTurn.toString(), GlobalVars.sCurrentTeam,
                    sInkast, iInkast.toString(),
                    bAdvantage.toString(), sTurn1Player,
                    sTurn1Hit, sTurn2Player,
                    sTurn2Hit, sTurn3Player,
                    sTurn3Hit, sTurn4Player,
                    sTurn4Hit, sTurn5Player,
                    sTurn5Hit, sTurn6Player,
                    sTurn6Hit);
            dbAccess.close();
            iTurn++;
			makeText(cont, "Turn Added!!!", LENGTH_SHORT).show();

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
