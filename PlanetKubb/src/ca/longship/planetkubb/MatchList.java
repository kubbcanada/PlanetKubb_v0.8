package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MatchList extends Activity implements OnItemClickListener {

	private static final String sItem = "";
	int i;
	public String[] menuItems = { "Show", "Edit", "Delete", "Copy" };
	public TextView tvMatch;
	ListView lv;
	ArrayList<String> MList;
	ArrayAdapter<String> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.match_list);
		lv = (ListView) findViewById(R.id.lvMatches);

		DBAccessMatch info = new DBAccessMatch(this);
		info.open();

		MList = info.getMatchList();

		arrayAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, MList);

		lv.setAdapter(arrayAdapter);
		registerForContextMenu(lv);
		info.close();

	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub

	}

	public void showMatchDetails(String matchid) {
		setContentView(R.layout.matchdetails);
		tvMatch = (TextView) findViewById(R.id.tvMatchDetails);
		registerForContextMenu(tvMatch);
		DBAccessMatch info = new DBAccessMatch(this);
		info.open();

		// Create final text from database information here
		tvMatch.setText(info.createFinalData(matchid));
		info.close();

	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.lvMatches) {
			menu.setHeaderTitle("What to do?");

			for (int i = 0; i < menuItems.length - 1; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
		if (v.getId() == R.id.tvMatchDetails) {
			ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
			ClipData clip = ClipData.newPlainText("Game", tvMatch.getText()
					.toString());
			clipboard.setPrimaryClip(clip);
			 Toast.makeText(getApplicationContext(), "Copied Match Data to Clipboard", Toast.LENGTH_LONG).show();

		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		int menuItemIndex = item.getItemId();
		String menuItemName = menuItems[menuItemIndex];
		String listItemName = (String) lv.getItemAtPosition(info.position);
		// String listItemName = playerarray[info.position];

		if (menuItemName == "Show") {

			String[] separated = listItemName.split("-");

//			Toast.makeText(getApplicationContext(),
//					"Match Number : " + separated[0].trim(), Toast.LENGTH_SHORT)
//					.show();
			showMatchDetails(separated[0].trim());

		}
		if (menuItemName == "Delete") {
			DBAccessMatch db = new DBAccessMatch(this);
			String[] separated = listItemName.split("-");
			db.deleteRecord("matchTable", DBAccessMatch.KEY_MATCHID,
					separated[0].trim());
			arrayAdapter.notifyDataSetChanged();
			lv.refreshDrawableState();
			Intent goback = new Intent("ca.longship.planetkubb.MATCHLIST");
			startActivity(goback);
			finish();
			

		}
		if (menuItemName == "Edit") {
			String[] separated = listItemName.split("-");
			Intent intent = new Intent("ca.longship.planetkubb.MATCHEDIT");
			intent.putExtra("MatchID", separated[0]);
			startActivity(intent);
			finish();
			
		}


		return true;
	}
	
	@Override
	public void onBackPressed() {
		GlobalVars.resetValues();
		Intent openMainActivity = new Intent(
				"ca.longship.planetkubb.MAINACTIVITY");
		startActivity(openMainActivity);
		finish();
	}

}
