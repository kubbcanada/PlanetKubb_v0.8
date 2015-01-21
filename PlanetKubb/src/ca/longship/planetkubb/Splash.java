package ca.longship.planetkubb;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import ca.longship.planetkubb.R;

public class Splash extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        TextView versionTitle = (TextView) findViewById(R.id.splashVersion);
        String version = "Pre-Beta 0.8 January 21, 2015";
        versionTitle.setText("Version : " + version);
        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openMainActivity = new Intent("ca.longship.planetkubb.MAINACTIVITY");
                    startActivity(openMainActivity);

                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        finish();
    }


}
