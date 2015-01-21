package ca.longship.planetkubb;

import java.util.ArrayList;

import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

public class DBAccessMatch {

	// MATCH Table IDs
	public static final String KEY_MATCHID = "match_id";
	public static final String KEY_TEAM1NAME = "team1_name";
	public static final String KEY_TEAM2NAME = "team2_name";
	public static final String KEY_DATE = "date";
	public static final String KEY_LOCATION = "location";
	public static final String KEY_EVENTNAME = "event_name";
	public static final String KEY_PITCHSURFACE = "pitch_surface";
	public static final String KEY_SCOREDBY = "scored_by";
	public static final String KEY_T1P1 = "t1p1";
	public static final String KEY_T1P2 = "t1p2";
	public static final String KEY_T1P3 = "t1p3";
	public static final String KEY_T1P4 = "t1p4";
	public static final String KEY_T1P5 = "t1p5";
	public static final String KEY_T1P6 = "t1p6";
	public static final String KEY_T2P1 = "t2p1";
	public static final String KEY_T2P2 = "t2p2";
	public static final String KEY_T2P3 = "t2p3";
	public static final String KEY_T2P4 = "t2p4";
	public static final String KEY_T2P5 = "t2p5";
	public static final String KEY_T2P6 = "t2p6";

	// TURN Table IDs
	public static final String KEY_TURNNUMBER = "turn_number";
	public static final String KEY_TEAMNAME = "team_name";
	public static final String KEY_KUBBINKAST = "kubb_inkast";
	public static final String KEY_PLAYERINKAST = "player_inkast";
	public static final String KEY_ADVANTAGE = "advantage";
	public static final String KEY_THROW1PLAYER = "throw1_player";
	public static final String KEY_THROW1 = "throw1";
	public static final String KEY_THROW2PLAYER = "throw2_player";
	public static final String KEY_THROW2 = "throw2";
	public static final String KEY_THROW3PLAYER = "throw3_player";
	public static final String KEY_THROW3 = "throw3";
	public static final String KEY_THROW4PLAYER = "throw4_player";
	public static final String KEY_THROW4 = "throw4";
	public static final String KEY_THROW5PLAYER = "throw5_player";
	public static final String KEY_THROW5 = "throw5";
	public static final String KEY_THROW6PLAYER = "throw6_player";
	public static final String KEY_THROW6 = "throw6";

	// PLAYER Table IDs
	public static final String KEY_PLAYERNAME = "player_name";
	public static final String KEY_PLAYERINITIALS = "player_initials";

	public static String sInits = "";

	// #1 is the Match Table
	// #2 is the Turn Table
	// #3 is the Player Table

	private static final String DATABASE_NAME = "Kubb";
	private static final String DATABASE_TABLE1 = "matchTable";
	private static final String DATABASE_TABLE2 = "turnTable";
	private static final String DATABASE_TABLE3 = "playerTable";
	private static final int DATABASE_VERSION = 1;

	private DbHelper ourHelper;
	private static Context ourContext;
	private static SQLiteDatabase ourDatabase;

	private static class DbHelper extends SQLiteOpenHelper {

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			// db.execSQL("CREATE TABLE " + DATABASE_TABLE + " (" + KEY_ROWID
			// + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_NAME
			// + " TEXT NOT NULL, " + KEY_HOTNESS + " TEXT NOT NULL);");

			// Create Match Table
			db.execSQL("CREATE TABLE " + DATABASE_TABLE1 + " (" + KEY_MATCHID
					+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TEAM1NAME
					+ " TEXT NOT NULL, " + KEY_TEAM2NAME + " TEXT NOT NULL, "
					+ KEY_DATE + " TEXT NOT NULL, " + KEY_LOCATION
					+ " TEXT NOT NULL, " + KEY_EVENTNAME + " TEXT NOT NULL, "
					+ KEY_PITCHSURFACE + " TEXT NOT NULL, " + KEY_SCOREDBY
					+ " TEXT NOT NULL, " + KEY_T1P1 + " TEXT NOT NULL, "
					+ KEY_T1P2 + " TEXT NOT NULL, " + KEY_T1P3
					+ " TEXT NOT NULL, " + KEY_T1P4 + " TEXT NOT NULL, "
					+ KEY_T1P5 + " TEXT NOT NULL, " + KEY_T1P6
					+ " TEXT NOT NULL, " + KEY_T2P1 + " TEXT NOT NULL, "
					+ KEY_T2P2 + " TEXT NOT NULL, " + KEY_T2P3
					+ " TEXT NOT NULL, " + KEY_T2P4 + " TEXT NOT NULL, "
					+ KEY_T2P5 + " TEXT NOT NULL, " + KEY_T2P6
					+ " TEXT NOT NULL " + ");");

			// Create Turn Table
			db.execSQL("CREATE TABLE " + DATABASE_TABLE2 + " (" + KEY_MATCHID
					+ " INTEGER, " + KEY_TURNNUMBER + " TEXT NOT NULL, "
					+ KEY_TEAMNAME + " TEXT NOT NULL, " + KEY_PLAYERINKAST
					+ "TEXT NOT NULL, " + KEY_KUBBINKAST + " TEXT NOT NULL, "
					+ KEY_ADVANTAGE + " TEXT NOT NULL, " + KEY_THROW1PLAYER
					+ " TEXT NOT NULL, " + KEY_THROW1 + " TEXT NOT NULL, "
					+ KEY_THROW2PLAYER + " TEXT NOT NULL, " + KEY_THROW2
					+ " TEXT NOT NULL, " + KEY_THROW3PLAYER
					+ " TEXT NOT NULL, " + KEY_THROW3 + " TEXT NOT NULL, "
					+ KEY_THROW4PLAYER + " TEXT NOT NULL, " + KEY_THROW4
					+ " TEXT NOT NULL, " + KEY_THROW5PLAYER
					+ " TEXT NOT NULL, " + KEY_THROW5 + " TEXT NOT NULL, "
					+ KEY_THROW6PLAYER + " TEXT NOT NULL, " + KEY_THROW6
					+ " TEXT NOT NULL " + ");");

			// Create Player Table
			db.execSQL("CREATE TABLE " + DATABASE_TABLE3 + " ("
					+ KEY_PLAYERNAME + " TEXT PRIMARY KEY NOT NULL, "
					+ KEY_PLAYERINITIALS + " TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub

			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE3);
			onCreate(db);
		}

	}

	public DBAccessMatch(Context c) {
		ourContext = c;

	}

	public DBAccessMatch open() throws SQLException {
		// SQLiteDatabase ourDatabase =
		// ourContext.openOrCreateDatabase(DATABASE_NAME,
		// SQLiteDatabase.CREATE_IF_NECESSARY, null);
		ourHelper = new DbHelper(ourContext);
		ourDatabase = ourHelper.getWritableDatabase();
		return this;
	}

	public void close() {
		ourHelper.close();
	}

	public long createMatch(String team1, String team2, String date,
			String location, String eventname, String pitchsurface,
			String scoredby, String t1p1, String t1p2, String t1p3,
			String t1p4, String t1p5, String t1p6, String t2p1, String t2p2,
			String t2p3, String t2p4, String t2p5, String t2p6) {
		ContentValues cv = new ContentValues();
		cv.put(KEY_TEAM1NAME, team1);
		cv.put(KEY_TEAM2NAME, team2);
		cv.put(KEY_DATE, date);
		cv.put(KEY_LOCATION, location);
		cv.put(KEY_EVENTNAME, eventname);
		cv.put(KEY_PITCHSURFACE, pitchsurface);
		cv.put(KEY_SCOREDBY, scoredby);
		cv.put(KEY_T1P1, t1p1);
		cv.put(KEY_T1P2, t1p2);
		cv.put(KEY_T1P3, t1p3);
		cv.put(KEY_T1P4, t1p4);
		cv.put(KEY_T1P5, t1p5);
		cv.put(KEY_T1P6, t1p6);
		cv.put(KEY_T2P1, t2p1);
		cv.put(KEY_T2P2, t2p2);
		cv.put(KEY_T2P3, t2p3);
		cv.put(KEY_T2P4, t2p4);
		cv.put(KEY_T2P5, t2p5);
		cv.put(KEY_T2P6, t2p6);
		return ourDatabase.insert(DATABASE_TABLE1, null, cv);

	}

	public long createTurn(String matchid, String turnnumber, String teamname,
			String playerinkast, String kubbinkast, String advantage,
			String throw1player, String throw1, String throw2player,
			String throw2, String throw3player, String throw3,
			String throw4player, String throw4, String throw5player,
			String throw5, String throw6player, String throw6) {

		ContentValues cv = new ContentValues();
		cv.put(KEY_MATCHID, matchid);
		cv.put(KEY_TURNNUMBER, turnnumber);
		cv.put(KEY_TEAMNAME, teamname);
		cv.put(KEY_PLAYERINKAST, playerinkast);
		cv.put(KEY_KUBBINKAST, kubbinkast);
		cv.put(KEY_ADVANTAGE, advantage);
		cv.put(KEY_THROW1PLAYER, throw1player);
		cv.put(KEY_THROW1, throw1);
		cv.put(KEY_THROW2PLAYER, throw2player);
		cv.put(KEY_THROW2, throw2);
		cv.put(KEY_THROW3PLAYER, throw3player);
		cv.put(KEY_THROW3, throw3);
		cv.put(KEY_THROW4PLAYER, throw4player);
		cv.put(KEY_THROW4, throw4);
		cv.put(KEY_THROW5PLAYER, throw5player);
		cv.put(KEY_THROW5, throw5);
		cv.put(KEY_THROW6PLAYER, throw6player);
		cv.put(KEY_THROW6, throw6);
		return ourDatabase.insert(DATABASE_TABLE2, null, cv);

	}

	public void createPlayer(String playername, String playerinitials) {

		try {

			ContentValues cv = new ContentValues();
			cv.put(KEY_PLAYERNAME, playername);
			cv.put(KEY_PLAYERINITIALS, playerinitials);
			ourDatabase.insert(DATABASE_TABLE3, null, cv);

		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

	}

	public String getMatchID() {
		String[] columns = new String[] { KEY_MATCHID };
		Cursor c = ourDatabase.query(DATABASE_TABLE1, columns, null, null,
				null, null, null);
		String result = "";
		int iRow = c.getColumnIndex(KEY_MATCHID);
		c.moveToLast();
		result = c.getString(iRow);
		return result;
	}

	public ArrayList<String> getMatchList() {

		// Create an array of match items
		String[] columns = new String[] { KEY_MATCHID, KEY_EVENTNAME, KEY_DATE };
		Cursor c = null;
		// boolean tableExists = false;
		try {

			c = ourDatabase.query(DATABASE_TABLE1, columns, null, null, null,
					null, null);
			// tableExists = true;
		} catch (Exception e) {
			Log.d("PLANETKUBB", DATABASE_TABLE1 + " doesn't exist :((");
		}
		ArrayList<String> result = new ArrayList<String>();

		// String[] result = {};
		int iRow = c.getColumnIndex(KEY_MATCHID);
		int iEventName = c.getColumnIndex(KEY_EVENTNAME);
		int iDate = c.getColumnIndex(KEY_DATE);

		if (c.getCount() > 0) {
			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result.add(c.getString(iRow) + " - " + c.getString(iDate)
						+ " - " + c.getString(iEventName));

			}
		} else {
			result.add("Nothing to return");
		}
		return result;

	}

	public String[] getMatchInfo(String[] columns, String[] matchID) {
		String[] result = new String[] { "", "", "" };
		Cursor c = null;
		try {

			c = ourDatabase.query(DATABASE_TABLE1, columns, KEY_MATCHID + "=?",
					matchID, null, null, null);
			// tableExists = true;
		} catch (Exception e) {
			Log.d("PLANETKUBB", DATABASE_TABLE1 + " doesn't exist :((");
		}

		// String[] result = {};
		int iRow = c.getColumnIndex(columns[0]);
		int iEventName = c.getColumnIndex(columns[1]);
		int iDate = c.getColumnIndex(columns[2]);
		int i = 0;
		if (c.getCount() > 0) {
			c.moveToFirst();
			result[0] = c.getString(iRow);
			result[1] = c.getString(iEventName);
			result[2] = c.getString(iDate);
		} else {
			result[0] = "Nothing to return";
		}
		return result;

	}

	public void updateMatchInfo(String[] matchInfo) {

		String whereClause = "match_id =?";
		String[] whereArgs = new String[] { matchInfo[0] };
		ContentValues values = new ContentValues();
		values.put(KEY_MATCHID, matchInfo[0]);
		values.put(KEY_TEAM1NAME, matchInfo[1]);
		values.put(KEY_TEAM2NAME, matchInfo[2]);
		values.put(KEY_DATE, matchInfo[3]);
		values.put(KEY_LOCATION, matchInfo[4]);
		values.put(KEY_EVENTNAME, matchInfo[5]);
		values.put(KEY_PITCHSURFACE, matchInfo[6]);
		values.put(KEY_SCOREDBY, matchInfo[7]);
		Toast.makeText(ourContext, "Match number : " + matchInfo[0],
				Toast.LENGTH_SHORT).show();
		try {
			ourDatabase.update(DATABASE_TABLE1, values, whereClause, whereArgs);

		} catch (Exception e) {
			Log.d("PLANETKUBB", "Update Failed");

		}
		//
	}

	public String[] getAllMatchInfo(String[] matchID) {
		String[] result = new String[] { "", "", "", "", "", "", "", "" };
		Cursor c = null;
		try {

			c = ourDatabase.query(DATABASE_TABLE1, null, KEY_MATCHID + "=?",
					matchID, null, null, null);
			// tableExists = true;
		} catch (Exception e) {
			Log.d("PLANETKUBB", DATABASE_TABLE1 + " doesn't exist :((");
		}

		int iRow = c.getColumnIndex(KEY_MATCHID);
		int iTeam1Name = c.getColumnIndex(KEY_TEAM1NAME);
		int iTeam2Name = c.getColumnIndex(KEY_TEAM2NAME);
		int iDate = c.getColumnIndex(KEY_DATE);
		int iLocation = c.getColumnIndex(KEY_LOCATION);
		int iEventName = c.getColumnIndex(KEY_EVENTNAME);
		int iPitchSurface = c.getColumnIndex(KEY_PITCHSURFACE);
		int iScoredBy = c.getColumnIndex(KEY_SCOREDBY);

		if (c.getCount() > 0) {
			c.moveToFirst();
			result[0] = c.getString(iRow);
			result[1] = c.getString(iTeam1Name);
			result[2] = c.getString(iTeam2Name);
			result[3] = c.getString(iDate);
			result[4] = c.getString(iLocation);
			result[5] = c.getString(iEventName);
			result[6] = c.getString(iPitchSurface);
			result[7] = c.getString(iScoredBy);
		} else {
			result[0] = "Nothing to return";
		}
		return result;

	}

	public ArrayList<String> getTurnEditInfo(String[] matchID) {
		ArrayList<String> result = new ArrayList<String>();

		String sAdvant;

		String smatch = matchID[0];
		Cursor c = null;

		try {

			c = ourDatabase.query(DATABASE_TABLE2, null, KEY_MATCHID + "=?",
					new String[] { smatch }, null, null, KEY_MATCHID);

		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

		int iKubbTurn = c.getColumnIndex(KEY_TURNNUMBER);
		int iPlayerInkast = c.getColumnIndex(KEY_PLAYERINKAST);
		int iKubbInkast = c.getColumnIndex(KEY_KUBBINKAST);
		int iAdvantage = c.getColumnIndex(KEY_ADVANTAGE);
		int iTeamName = c.getColumnIndex(KEY_TEAMNAME);
		int iThrow1Player = c.getColumnIndex(KEY_THROW1PLAYER);
		int iThrow1 = c.getColumnIndex(KEY_THROW1);
		int iThrow2Player = c.getColumnIndex(KEY_THROW2PLAYER);
		int iThrow2 = c.getColumnIndex(KEY_THROW2);
		int iThrow3Player = c.getColumnIndex(KEY_THROW3PLAYER);
		int iThrow3 = c.getColumnIndex(KEY_THROW3);
		int iThrow4Player = c.getColumnIndex(KEY_THROW4PLAYER);
		int iThrow4 = c.getColumnIndex(KEY_THROW4);
		int iThrow5Player = c.getColumnIndex(KEY_THROW5PLAYER);
		int iThrow5 = c.getColumnIndex(KEY_THROW5);
		int iThrow6Player = c.getColumnIndex(KEY_THROW6PLAYER);
		int iThrow6 = c.getColumnIndex(KEY_THROW6);

		if (c.getCount() > 0) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if (c.getString(iAdvantage) == "True") {
					sAdvant = "A";
				} else {
					sAdvant = " ";
				}
				result.add(c.getString(iKubbTurn) + "^"
						+ c.getString(iTeamName) + "^"
						+ c.getString(iPlayerInkast) + "^"
						+ c.getString(iKubbInkast) + "^" + sAdvant + "^"
						+ c.getString(iThrow1Player) + ":"
						+ c.getString(iThrow1) + "^"
						+ c.getString(iThrow2Player) + ":"
						+ c.getString(iThrow2) + "^"
						+ c.getString(iThrow3Player) + ":"
						+ c.getString(iThrow3) + "^"
						+ c.getString(iThrow4Player) + ":"
						+ c.getString(iThrow4) + "^"
						+ c.getString(iThrow5Player) + ":"
						+ c.getString(iThrow5) + "^"
						+ c.getString(iThrow6Player) + ":"
						+ c.getString(iThrow6));
			}

		} else {
			Toast.makeText(ourContext, "Couldn't find match data",
					Toast.LENGTH_SHORT).show();
		}

		return result;
	}

	public ArrayList<String> getTurnInfo(String[] matchID) {
		ArrayList<String> result = new ArrayList<String>();

		// //////
		// /////
		// /////
		String sAdvant;

		String smatch = matchID[0];
		Cursor c = null;

		try {

			c = ourDatabase.query(DATABASE_TABLE2, null, KEY_MATCHID + "=?",
					new String[] { smatch }, null, null, KEY_MATCHID);

		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

		int iKubbTurn = c.getColumnIndex(KEY_TURNNUMBER);
		int iPlayerInkast = c.getColumnIndex(KEY_PLAYERINKAST);
		int iKubbInkast = c.getColumnIndex(KEY_KUBBINKAST);
		int iAdvantage = c.getColumnIndex(KEY_ADVANTAGE);
		int iTeamName = c.getColumnIndex(KEY_TEAMNAME);
		int iThrow1Player = c.getColumnIndex(KEY_THROW1PLAYER);
		int iThrow1 = c.getColumnIndex(KEY_THROW1);
		int iThrow2Player = c.getColumnIndex(KEY_THROW2PLAYER);
		int iThrow2 = c.getColumnIndex(KEY_THROW2);
		int iThrow3Player = c.getColumnIndex(KEY_THROW3PLAYER);
		int iThrow3 = c.getColumnIndex(KEY_THROW3);
		int iThrow4Player = c.getColumnIndex(KEY_THROW4PLAYER);
		int iThrow4 = c.getColumnIndex(KEY_THROW4);
		int iThrow5Player = c.getColumnIndex(KEY_THROW5PLAYER);
		int iThrow5 = c.getColumnIndex(KEY_THROW5);
		int iThrow6Player = c.getColumnIndex(KEY_THROW6PLAYER);
		int iThrow6 = c.getColumnIndex(KEY_THROW6);

		if (c.getCount() > 0) {

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				if (c.getString(iAdvantage) == "True") {
					sAdvant = "A";
				} else {
					sAdvant = " ";
				}
				result.add("Turn : " + c.getString(iKubbTurn) + " Team : "
						+ c.getString(iTeamName) + " - "
						+ c.getString(iPlayerInkast) + " - "
						+ c.getString(iKubbInkast) + " - " + sAdvant + " "
						+ c.getString(iThrow1Player) + ":"
						+ c.getString(iThrow1) + " "
						+ c.getString(iThrow2Player) + ":"
						+ c.getString(iThrow2) + " "
						+ c.getString(iThrow3Player) + ":"
						+ c.getString(iThrow3) + " "
						+ c.getString(iThrow4Player) + ":"
						+ c.getString(iThrow4) + " "
						+ c.getString(iThrow5Player) + ":"
						+ c.getString(iThrow5) + " "
						+ c.getString(iThrow6Player) + ":"
						+ c.getString(iThrow6));
			}

		} else {
			Toast.makeText(ourContext, "Couldn't find match data",
					Toast.LENGTH_SHORT).show();
		}

		return result;

		// ///////
		// ////
		// ///////

	}

	public int getNumberofPlayers() {
		String[] columns = new String[] { KEY_PLAYERNAME };
		Cursor c = ourDatabase.query(DATABASE_TABLE3, columns, null, null,
				null, null, null);
		int players = c.getCount();
		return players;
	}

	public ArrayList<String> getData() {
		// TODO Auto-generated method stub
		String[] columns = new String[] { KEY_PLAYERNAME, KEY_PLAYERINITIALS };
		Cursor c = ourDatabase.query(DATABASE_TABLE3, columns, null, null,
				null, null, null);
		ArrayList<String> result = new ArrayList<String>();
		int iRow = c.getColumnIndex(KEY_PLAYERNAME);
		int iName = c.getColumnIndex(KEY_PLAYERINITIALS);
		for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			result.add(c.getString(iRow) + " - " + c.getString(iName) + "\n");
		}
		return result;
		// return null;
	}

	public String createFinalData(String matchid) {

		int iT1Players = 1, iT2Players = 1;
		SQLiteDatabase db = ourHelper.getReadableDatabase();
		String sMatchInfo = matchid;
		// Start with Match Information
		String[] columns1 = new String[] { KEY_MATCHID, KEY_TEAM1NAME,
				KEY_TEAM2NAME, KEY_DATE, KEY_LOCATION, KEY_EVENTNAME,
				KEY_PITCHSURFACE, KEY_SCOREDBY, KEY_T1P1, KEY_T1P2, KEY_T1P3,
				KEY_T1P4, KEY_T1P5, KEY_T1P6, KEY_T2P1, KEY_T2P2, KEY_T2P3,
				KEY_T2P4, KEY_T2P5, KEY_T2P6 };
		try {
			Cursor c = db.query(DATABASE_TABLE1, columns1, KEY_MATCHID + "=?",
					new String[] { String.valueOf(matchid) }, null, null, null,
					null);
			if (c != null) {
				c.moveToFirst();
			}

			int iMatchID = c.getColumnIndex(KEY_MATCHID);
			int iTeam1Name = c.getColumnIndex(KEY_TEAM1NAME);
			int iTeam2Name = c.getColumnIndex(KEY_TEAM2NAME);
			int iDate = c.getColumnIndex(KEY_DATE);
			int iLocation = c.getColumnIndex(KEY_LOCATION);
			int iEventName = c.getColumnIndex(KEY_EVENTNAME);
			int iPitchSurface = c.getColumnIndex(KEY_PITCHSURFACE);
			int iScoredBy = c.getColumnIndex(KEY_SCOREDBY);
			int iT1P1 = c.getColumnIndex(KEY_T1P1);
			int iT1P2 = c.getColumnIndex(KEY_T1P2);
			int iT1P3 = c.getColumnIndex(KEY_T1P3);
			int iT1P4 = c.getColumnIndex(KEY_T1P4);
			int iT1P5 = c.getColumnIndex(KEY_T1P5);
			int iT1P6 = c.getColumnIndex(KEY_T1P6);
			int iT2P1 = c.getColumnIndex(KEY_T2P1);
			int iT2P2 = c.getColumnIndex(KEY_T2P2);
			int iT2P3 = c.getColumnIndex(KEY_T2P3);
			int iT2P4 = c.getColumnIndex(KEY_T2P4);
			int iT2P5 = c.getColumnIndex(KEY_T2P5);
			int iT2P6 = c.getColumnIndex(KEY_T2P6);

			// Get Number of players on each team
			if (c.getString(iT1P1).trim().length() > 0) {
				iT1Players = 1;
			}
			if (c.getString(iT1P2).trim().length() > 0) {
				iT1Players = 2;
			}
			if (c.getString(iT1P3).trim().length() > 0) {
				iT1Players = 3;
			}
			if (c.getString(iT1P4).trim().length() > 0) {
				iT1Players = 4;
			}
			if (c.getString(iT1P5).trim().length() > 0) {
				iT1Players = 5;
			}
			if (c.getString(iT1P6).trim().length() > 0) {
				iT1Players = 6;
			}
			if (c.getString(iT2P1).trim().length() > 0) {
				iT2Players = 1;
			}
			if (c.getString(iT2P2).trim().length() > 0) {
				iT2Players = 2;
			}
			if (c.getString(iT2P3).trim().length() > 0) {
				iT2Players = 3;
			}
			if (c.getString(iT2P4).trim().length() > 0) {
				iT2Players = 4;
			}
			if (c.getString(iT2P5).trim().length() > 0) {
				iT2Players = 5;
			}
			if (c.getString(iT2P6).trim().length() > 0) {
				iT2Players = 6;
			}

			// Create first part of Match information
			sMatchInfo = "{{Game\n|Team A=" + c.getString(iTeam1Name)
					+ "\n|Team A Player Count=" + Integer.toString(iT1Players)
					+ "\n|Team B=" + c.getString(iTeam2Name)
					+ "\n|Team B Player Count=" + Integer.toString(iT2Players)
					+ "\n|Start date=" + c.getString(iDate) + "\n|Location="
					+ c.getString(iLocation) + "\n|Event="
					+ c.getString(iEventName) + "\n|Pitch surface="
					+ c.getString(iPitchSurface) + "\n|Scored by="
					+ c.getString(iScoredBy) + "\n}}\n";

			// Add Player info
			for (int x = 0; x < iT1Players; x++) {
				sMatchInfo = sMatchInfo + "{{Game player\n|Team="
						+ c.getString(iTeam1Name);
				switch (x) {
				case 0:
					sInits = getPlayerInitials(c.getString(iT1P1));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P1)
							+ "\n|Initial=" + sInits + "}}\n";
					break;
				case 1:
					sInits = getPlayerInitials(c.getString(iT1P2));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P2)
							+ "\n|Initial=" + sInits + "}}\n";
					break;
				case 2:
					sInits = getPlayerInitials(c.getString(iT1P3));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P3)
							+ "\n|Initial=" + sInits + "}}\n";
					break;
				case 3:
					sInits = getPlayerInitials(c.getString(iT1P4));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P4)
							+ "\n|Initial=" + sInits + "}}\n";
					break;
				case 4:
					sInits = getPlayerInitials(c.getString(iT1P5));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P5)
							+ "\n|Initial=" + sInits + "}}\n";
					break;
				case 5:
					sInits = getPlayerInitials(c.getString(iT1P6));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT1P6)
							+ "\n|Initial=" + sInits + "}}\n";
					break;

				}
			}
			for (int x = 0; x < iT2Players; x++) {
				sMatchInfo = sMatchInfo + "{{Game player\n|Team="
						+ c.getString(iTeam2Name);
				switch (x) {
				case 0:
					sInits = getPlayerInitials(c.getString(iT2P1));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P1)
							+ "\n|Initial=" + sInits + "}}\n";

					break;
				case 1:
					sInits = getPlayerInitials(c.getString(iT2P2));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P2)
							+ "\n|Initial=" + sInits + "}}\n";

					break;
				case 2:
					sInits = getPlayerInitials(c.getString(iT2P3));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P3)
							+ "\n|Initial=" + sInits + "}}\n";

					break;
				case 3:
					sInits = getPlayerInitials(c.getString(iT2P4));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P4)
							+ "\n|Initial=" + sInits + "}}\n";

					break;
				case 4:
					sInits = getPlayerInitials(c.getString(iT2P5));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P5)
							+ "\n|Initial=" + sInits + "}}\n";

					break;
				case 5:
					sInits = getPlayerInitials(c.getString(iT2P6));
					sMatchInfo = sMatchInfo + "\n|Player=" + c.getString(iT2P6)
							+ "\n|Initial=" + sInits + "}}\n";

					break;

				}
				// c.close();
			}

		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

		String[] columns2 = new String[] { KEY_MATCHID, KEY_TURNNUMBER,
				KEY_TEAMNAME, KEY_PLAYERINKAST, KEY_KUBBINKAST, KEY_ADVANTAGE,
				KEY_THROW1PLAYER, KEY_THROW1, KEY_THROW2PLAYER, KEY_THROW2,
				KEY_THROW3PLAYER, KEY_THROW3, KEY_THROW4PLAYER, KEY_THROW4,
				KEY_THROW5PLAYER, KEY_THROW5, KEY_THROW6PLAYER, KEY_THROW6 };

		sMatchInfo = sMatchInfo + "{{Game initialize}}\n";
		try {

			Cursor c = db.query(DATABASE_TABLE2, columns2, KEY_MATCHID + "=?",
					new String[] { String.valueOf(matchid) }, null, null, null,
					null);
			if (c != null) {
				int iPlayerInkast = c.getColumnIndex(KEY_PLAYERINKAST);
				int iKubbInkast = c.getColumnIndex(KEY_KUBBINKAST);
				int iAdvantage = c.getColumnIndex(KEY_ADVANTAGE);
				int iThrow1Player = c.getColumnIndex(KEY_THROW1PLAYER);
				int iThrow1 = c.getColumnIndex(KEY_THROW1);
				int iThrow2Player = c.getColumnIndex(KEY_THROW2PLAYER);
				int iThrow2 = c.getColumnIndex(KEY_THROW2);
				int iThrow3Player = c.getColumnIndex(KEY_THROW3PLAYER);
				int iThrow3 = c.getColumnIndex(KEY_THROW3);
				int iThrow4Player = c.getColumnIndex(KEY_THROW4PLAYER);
				int iThrow4 = c.getColumnIndex(KEY_THROW4);
				int iThrow5Player = c.getColumnIndex(KEY_THROW5PLAYER);
				int iThrow5 = c.getColumnIndex(KEY_THROW5);
				int iThrow6Player = c.getColumnIndex(KEY_THROW6PLAYER);
				int iThrow6 = c.getColumnIndex(KEY_THROW6);

				for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {

					sMatchInfo = sMatchInfo + "{{Game turn\n";

					sMatchInfo = sMatchInfo + "|Kubb throw 1 player="
							+ c.getString(iPlayerInkast).toString() + "\n"
							+ "|Kubb throw 1="
							+ c.getString(iKubbInkast).toString() + "\n"
							+ "|Advantage line="
							+ c.getString(iAdvantage).toString() + "\n"
							+ "|Throw 1 player="
							+ c.getString(iThrow1Player).toString() + "\n"
							+ "|Throw 1=" + c.getString(iThrow1).toString()
							+ "\n" + "|Throw 2 player="
							+ c.getString(iThrow2Player).toString() + "\n"
							+ "|Throw 2=" + c.getString(iThrow2).toString()
							+ "\n" + "|Throw 3 player="
							+ c.getString(iThrow3Player).toString() + "\n"
							+ "|Throw 3=" + c.getString(iThrow3).toString()
							+ "\n" + "|Throw 4 player="
							+ c.getString(iThrow4Player).toString() + "\n"
							+ "|Throw 4=" + c.getString(iThrow4).toString()
							+ "\n" + "|Throw 5 player="
							+ c.getString(iThrow5Player).toString() + "\n"
							+ "|Throw 5=" + c.getString(iThrow5).toString()
							+ "\n" + "|Throw 6 player="
							+ c.getString(iThrow6Player).toString() + "\n"
							+ "|Throw 6=" + c.getString(iThrow6).toString()
							+ "\n}}\n";

				}

			} else {
				Toast.makeText(ourContext, "Couldn't find match data",
						Toast.LENGTH_SHORT).show();
			}

		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

		return sMatchInfo;

	}

	public String getPlayerInitials(String playername) {
		String sPlayerInitials = "";
		String[] columns = new String[] { KEY_PLAYERNAME, KEY_PLAYERINITIALS };
		try {
			// Cursor c = ourDatabase.rawQuery("select 1 from " +
			// DATABASE_TABLE3 +
			// " where " + KEY_PLAYERNAME + "=%s", new String[] {playername});
			Cursor c = ourDatabase.query(DATABASE_TABLE3, columns,
					KEY_PLAYERNAME + "=?", new String[] { playername }, null,
					null, null, null);
			if (c.getCount() > 0) {
				c.moveToFirst();
				int iPlayerInitials = c.getColumnIndex(KEY_PLAYERINITIALS);
				sPlayerInitials = c.getString(iPlayerInitials);
			} else {
				sPlayerInitials = "not found";
			}
		} catch (Exception e) {
			String error = e.toString();
			Dialog d = new Dialog(null);
			d.setTitle("Dang it!");
			TextView tv = new TextView(null);
			tv.setText(error);
			d.setContentView(tv);
			d.show();
		}

		return sPlayerInitials;

	}

	public void deleteRecord(String database, String field, String value) {
		open();
		SQLiteDatabase db = ourHelper.getWritableDatabase();

		if (db.delete(database, field + "='" + value + "'", null) > 0) {
			// Toast.makeText(ourContext, "Deleted player : " + value,
			// Toast.LENGTH_SHORT).show();
		} else {
			// Toast.makeText(ourContext, "Nope...didn't work",
			// Toast.LENGTH_SHORT)
			// .show();
		}

		close();

	}

	public void editPlayer(String oldname, String oldinit, String newname,
			String newinit) {

		SQLiteDatabase db = ourHelper.getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(KEY_PLAYERNAME, newname);
		cv.put(KEY_PLAYERINITIALS, newinit);
		db.update(DATABASE_TABLE3, cv, KEY_PLAYERNAME + "='" + oldname + "'",
				null);

	}

}
