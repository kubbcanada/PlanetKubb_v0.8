package ca.longship.planetkubb;

import android.app.ListActivity;
import android.view.MenuInflater;

public class menu extends ListActivity {


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // TODO Auto-generated method stub
        super.onCreateOptionsMenu(menu);
        MenuInflater blowup = getMenuInflater();
        blowup.inflate(R.menu.main, menu);
        return true;

    }


}
