package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class PlayerInfo extends Activity implements OnClickListener {

	ListView playerlist;
	ArrayList<String> playerarray;
	String[] menuItems = { "Edit", "Delete" };
	ArrayAdapter<String> adapter;
	Button bAddPlayer;
	public String value = null, value2 = null;
	DBAccessMatch db = new DBAccessMatch(this);
	String[] menuiteminfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.playerdata);
		addPlayersToString();
		playerlist = (ListView) findViewById(R.id.lvPlayerInfo);
		bAddPlayer = (Button) findViewById(R.id.bAddPlayer);

		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, playerarray);

		playerlist.setAdapter(adapter);
		registerForContextMenu(playerlist);

		bAddPlayer.setOnClickListener(this);

	}

	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		if (v.getId() == R.id.lvPlayerInfo) {
			AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
			menu.setHeaderTitle("What to do?");

			for (int i = 0; i < menuItems.length; i++) {
				menu.add(Menu.NONE, i, i, menuItems[i]);
			}
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item
				.getMenuInfo();

		int menuItemIndex = item.getItemId();
		String menuItemName = menuItems[menuItemIndex];
		String listItemName = (String) playerlist
				.getItemAtPosition(info.position);

		Toast.makeText(this,
				"Selected " + menuItemName + " for item " + listItemName,
				Toast.LENGTH_SHORT).show();
		if (menuItemName == "Delete") {

			DBAccessMatch db = new DBAccessMatch(this);
			String[] username = listItemName.split("-");
			db.deleteRecord("playerTable", DBAccessMatch.KEY_PLAYERNAME,
					username[0].trim());
			refreshList();

		}
		if (menuItemName == "Edit") {
			LayoutInflater factory = LayoutInflater.from(this);
			final View playeraddview = factory.inflate(R.layout.playerentry,
					null);
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Edit Player");
			alert.setMessage("Enter Player Name and Initials");
			alert.setView(playeraddview);
			AlertDialog playeredit = alert.create();
			final EditText input1 = (EditText) playeraddview
					.findViewById(R.id.etPlayerName);
			final EditText input2 = (EditText) playeraddview
					.findViewById(R.id.etPlayerInitials);
			menuiteminfo = listItemName.split("-");
			input1.setText(menuiteminfo[0]);
			input2.setText(menuiteminfo[1]);
			alert.setPositiveButton("Edit",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							value = input1.getText().toString().trim();
							value2 = input2.getText().toString().trim();
							// Toast.makeText(getApplicationContext(), value,
							// Toast.LENGTH_SHORT).show();
							Toast.makeText(
									getApplicationContext(),
									"Change from " + menuiteminfo[0] + " - "
											+ menuiteminfo[1] + "To : " + value
											+ " - " + value2,
									Toast.LENGTH_SHORT).show();

							db.open();

							db.editPlayer(menuiteminfo[0].trim(),
									menuiteminfo[1].trim(), value, value2);

							db.close();

							refreshList();

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							dialog.cancel();

						}
					});

			alert.show();

		}

		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

		switch (arg0.getId()) {
		case R.id.bAddPlayer:

			LayoutInflater factory = LayoutInflater.from(this);
			final View playeraddview = factory.inflate(R.layout.playerentry,
					null);
			AlertDialog.Builder alert = new AlertDialog.Builder(this);
			alert.setTitle("Add Player");
			alert.setMessage("Enter Player Name and Initials");
			alert.setView(playeraddview);
			AlertDialog playerentry = alert.create();
			final EditText input1 = (EditText) playeraddview
					.findViewById(R.id.etPlayerName);
			final EditText input2 = (EditText) playeraddview
					.findViewById(R.id.etPlayerInitials);

			alert.setPositiveButton("Add",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							value = input1.getText().toString().trim();
							value2 = input2.getText().toString().trim();
							// Toast.makeText(getApplicationContext(), value,
							// Toast.LENGTH_SHORT).show();
							Toast.makeText(getApplicationContext(),
									value + " - " + value2, Toast.LENGTH_SHORT)
									.show();

							db.open();

							db.createPlayer(value, value2);

							db.close();

							refreshList();

						}
					});

			alert.setNegativeButton("Cancel",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog,
								int whichButton) {
							dialog.cancel();

						}
					});

			alert.show();

			break;
		}
	}

	public void addPlayersToString() {

		DBAccessMatch info = new DBAccessMatch(this);
		info.open();

		playerarray = info.getData();

		info.close();

	}

	public void refreshList() {

		playerarray.clear();
		adapter.clear();
		// repopulate the array
		addPlayersToString();

		int i = 0;
		for (i = 0; i < playerarray.size(); i++) {
			adapter.add(playerarray.get(i));
		}
		adapter.notifyDataSetChanged();
	}

	public void editPlayer(String playername, String playerinitials) {

	}

	@Override
	public void onBackPressed() {

		Intent openMainActivity = new Intent(
				"ca.longship.planetkubb.MAINACTIVITY");
		startActivity(openMainActivity);
		finish();
		return;
	}

}
