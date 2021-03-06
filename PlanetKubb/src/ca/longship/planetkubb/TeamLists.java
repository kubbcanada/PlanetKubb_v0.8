package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import static ca.longship.planetkubb.GlobalVars.*;

public class TeamLists extends Activity implements View.OnClickListener {

    private int team1Players;
    int teamnumber = 1;
    Button startmatch;
    private Spinner spnPlayer1;
    private Spinner spnPlayer2;
    private Spinner spnPlayer3;
    private Spinner spnPlayer4;
    private Spinner spnPlayer5;
    private Spinner spnPlayer6;
    private TextView tvt1p6;
    private TextView tvt1p5;
    private TextView tvt1p4;
    private TextView tvt1p3;
    private TextView tvt1p2;
    private TextView tvt1p1;
    private int playercount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.team1);
        team1Players = getTeam1PlayNum();

        int team2Players = getTeam2PlayNum();

        String team1Name = getTeam1Name();
        String team2Name = getTeam2Name();

        spnPlayer1 = (Spinner) findViewById(R.id.spnT1P1);
        spnPlayer2 = (Spinner) findViewById(R.id.spnT1P2);
        spnPlayer3 = (Spinner) findViewById(R.id.spnT1P3);
        spnPlayer4 = (Spinner) findViewById(R.id.spnT1P4);
        spnPlayer5 = (Spinner) findViewById(R.id.spnT1P5);
        spnPlayer6 = (Spinner) findViewById(R.id.spnT1P6);

        tvt1p6 = (TextView) findViewById(R.id.tvTeam1Player6);
        tvt1p5 = (TextView) findViewById(R.id.tvTeam1Player5);
        tvt1p4 = (TextView) findViewById(R.id.tvTeam1Player4);
        tvt1p3 = (TextView) findViewById(R.id.tvTeam1Player3);
        tvt1p2 = (TextView) findViewById(R.id.tvTeam1Player2);
        tvt1p1 = (TextView) findViewById(R.id.tvTeam1Player1);

        addPlayersToList();
        setVisiblePlayers();
        TextView teamnametitle = (TextView) findViewById(R.id.tvTeam1Players);
        teamnametitle.setText(team1Name);
        Button team2button = (Button) findViewById(R.id.btTeam2);
        team2button.setOnClickListener(this);
        Button addplayer = (Button) findViewById(R.id.bAddPlayer1);
        addplayer.setOnClickListener(this);
        Button delplayer = (Button) findViewById(R.id.bDelPlayer1);
        delplayer.setOnClickListener(this);


    }

    void addPlayersToList() {
        // String[] arrayColumns = new String[]{"player_name",
        // "plyaer_initials"};
        DBAccessMatch info = new DBAccessMatch(this);
        info.open();
        ArrayList<String> alPlayers = info.getData();
        info.close();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, alPlayers);
        spnPlayer1.setAdapter(adapter);
        spnPlayer2.setAdapter(adapter);
        spnPlayer3.setAdapter(adapter);
        spnPlayer4.setAdapter(adapter);
        spnPlayer5.setAdapter(adapter);
        spnPlayer6.setAdapter(adapter);

    }

    void setVisiblePlayers() {
        // TODO Auto-generated method stub

        switch (team1Players) {

            case 6:

                tvt1p6.setVisibility(View.VISIBLE);

                spnPlayer6.setVisibility(View.VISIBLE);

            case 5:

                tvt1p5.setVisibility(View.VISIBLE);

                spnPlayer5.setVisibility(View.VISIBLE);

            case 4:

                tvt1p4.setVisibility(View.VISIBLE);

                spnPlayer4.setVisibility(View.VISIBLE);

            case 3:

                tvt1p3.setVisibility(View.VISIBLE);

                spnPlayer3.setVisibility(View.VISIBLE);

            case 2:

                tvt1p2.setVisibility(View.VISIBLE);

                spnPlayer2.setVisibility(View.VISIBLE);

            case 1:

                tvt1p1.setVisibility(View.VISIBLE);

                spnPlayer1.setVisibility(View.VISIBLE);

                break;

        }

    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btTeam2:
                addTeam1Members();
                Intent team2entry = new Intent("ca.longship.planetkubb.TEAMLISTS2");
                startActivity(team2entry);

                break;
            case R.id.bAddPlayer1:
                if (sTeam1PlayNum == 1) {

                    playercount = 2;
                } else if (sTeam1PlayNum == 2) {

                    playercount = 3;
                } else if (sTeam1PlayNum == 3) {

                    playercount = 4;

                } else if (sTeam1PlayNum == 4) {

                    playercount = 5;

                } else if (sTeam1PlayNum == 5) {

                    playercount = 6;

                } else if (sTeam1PlayNum == 6) {
                    Toast.makeText(getBaseContext(),
                            "Cannot add any more players.  Max is 6",
                            Toast.LENGTH_SHORT).show();
                    playercount = 6;

                }
                sTeam1PlayNum = playercount;
                team1Players = sTeam1PlayNum;
                setVisiblePlayers();
                break;
            case R.id.bDelPlayer1:
                if (sTeam1PlayNum == 1) {
                    Toast.makeText(getBaseContext(),
                            "Must have at least 1 player",
                            Toast.LENGTH_SHORT).show();
                    playercount = 1;
                } else if (sTeam1PlayNum == 2) {
                    tvt1p2.setVisibility(View.INVISIBLE);
                    spnPlayer2.setVisibility(View.INVISIBLE);
                    playercount = 1;
                } else if (sTeam1PlayNum == 3) {
                    tvt1p3.setVisibility(View.INVISIBLE);
                    spnPlayer3.setVisibility(View.INVISIBLE);
                    playercount = 2;

                } else if (sTeam1PlayNum == 4) {
                    tvt1p4.setVisibility(View.INVISIBLE);
                    spnPlayer4.setVisibility(View.INVISIBLE);
                    playercount = 3;

                } else if (sTeam1PlayNum == 5) {
                    tvt1p5.setVisibility(View.INVISIBLE);
                    spnPlayer5.setVisibility(View.INVISIBLE);
                    playercount = 4;

                } else if (sTeam1PlayNum == 6) {
                    tvt1p6.setVisibility(View.INVISIBLE);
                    spnPlayer6.setVisibility(View.INVISIBLE);
                    playercount = 5;

                }
                sTeam1PlayNum = playercount;
                team1Players = sTeam1PlayNum;
                setVisiblePlayers();

                break;
        }
    }

    void addTeam1Members() {
        // TODO Auto-generated method stub

        String pname, pinit;
        String[] aname;
        for (int x = 0; x < team1Players; x++) {
            switch (x) {
                case 0:
                    aname = spnPlayer1.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P1 = pname;

                    setTeam1PlayerInit(pinit, x);

                    break;
                case 1:
                    aname = spnPlayer2.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P2 = pname;

                    setTeam1PlayerInit(pinit, x);

                    break;
                case 2:
                    aname = spnPlayer3.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P3 = pname;

                    setTeam1PlayerInit(pinit, x);
                    break;
                case 3:
                    aname = spnPlayer4.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P4 = pname;

                    setTeam1PlayerInit(pinit, x);
                    break;
                case 4:
                    aname = spnPlayer5.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P5 = pname;

                    setTeam1PlayerInit(pinit, x);
                    break;
                case 5:
                    aname = spnPlayer6.getSelectedItem().toString().split("-");
                    pname = aname[0].trim();
                    pinit = aname[1].trim();

                    setTeam1PlayerNames(pname, x);
                    sT1P6 = pname;

                    setTeam1PlayerInit(pinit, x);
                    break;

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
