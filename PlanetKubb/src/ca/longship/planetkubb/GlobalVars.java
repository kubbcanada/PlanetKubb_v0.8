package ca.longship.planetkubb;

import android.app.Application;
import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;

public class GlobalVars extends Application {

	
	
	static String sTeam1Name, sTeam2Name, sT1P1In, sT1P2In, sT1P3In, sT1P4In,
			sT1P5In, sT1P6In, sT2P1In, sT2P2In, sT2P3In, sT2P4In, sT2P5In,
			sT2P6In, whoFirst;
	static String[] sTeam1PlayerNames = { "Player 1", "Player 2", "Player 3",
			"Player 4", "Player 5", "Player 6" };
	static String[] sTeam2PlayerNames = { "Player 1", "Player 2", "Player 3",
			"Player 4", "Player 5", "Player 6" };
	static String[] sTeam1PlayerInit = { "", "", "", "", "", "" };
	static String[] sTeam2PlayerInit = { "", "", "", "", "", "" };
	static Integer sTeam1PlayNum, sTeam2PlayNum;
	static String[] sTurnText;
	static Integer iTurnNumber;
	static Integer iTurn = 1;
	static String sCurrentTeam = "Team 1";
	static Boolean bAdvantage = false;
	static Boolean bAdv = false;
	static Integer iInkast = 0, iRethrow = 0, iPenalty = 0;
	static String sTurn1Player = " ", sTurn2Player = " ", sTurn3Player = " ",
			sTurn4Player = " ", sTurn5Player = " ", sTurn6Player = " ";
	static Integer iTurn1Thrown = 0, iTurn2Thrown = 0, iTurn3Thrown = 0,
			iTurn4Thrown = 0, iTurn5Thrown = 0, iTurn6Thrown = 0;
	static String sStartDate, sLocation, sEvent, sPitchSurface, sScoredBy;
	static String submitStringText;
	static int kubbsKnockedDownLastTurn = 0;
	static int iTeam1BaseKubbs = 0, iTeam1FieldKubbs = 0, iTeam2BaseKubbs = 0,
			iTeam2FieldKubbs = 0;

	static String sTurn1Hit = "-", sTurn2Hit = "-", sTurn3Hit = "-",
			sTurn4Hit = "-", sTurn5Hit = "-", sTurn6Hit = "-";
	static String sCurrentTurnFinal, sCurrentTurn;
	static int lastBaseKubbs = 0;
	static int lastFieldKubbs = 0;
	static int t1bh, t2bh, t3bh, t4bh, t5bh, t6bh;
	static int t1fh, t2fh, t3fh, t4fh, t5fh, t6fh;
	static int iTurnEdit;
	static String sMatchID, smatchInfo, sInkast = " ";

	static String sT1P1 = " ", sT1P2 = " ", sT1P3 = " ", sT1P4 = " ",
			sT1P5 = " ", sT1P6 = " ", sT2P1 = " ", sT2P2 = " ", sT2P3 = " ",
			sT2P4 = " ", sT2P5 = " ", sT2P6 = " ";

	public static void resetValues() {
		// / This function will reset all values to defaults

		sTeam1PlayerNames[0] = "Player 1";
		sTeam1PlayerNames[1] = "Player 2";
		sTeam1PlayerNames[2] = "Player 3";
		sTeam1PlayerNames[3] = "Player 4";
		sTeam1PlayerNames[4] = "Player 5";
		sTeam1PlayerNames[5] = "Player 6";
		sTeam2PlayerNames[0] = "Player 1";
		sTeam2PlayerNames[1] = "Player 2";
		sTeam2PlayerNames[2] = "Player 3";
		sTeam2PlayerNames[3] = "Player 4";
		sTeam2PlayerNames[4] = "Player 5";
		sTeam2PlayerNames[5] = "Player 6";
		sTurn1Hit = "-";
		sTurn2Hit = "-";
		sTurn3Hit = "-";
		sTurn4Hit = "-";
		sTurn5Hit = "-";
		sTurn6Hit = "-";
		sCurrentTurnFinal = "";
		sCurrentTurn = "";
		submitStringText = "";
		bAdvantage = false;
		bAdv = false;
		sTurn1Hit = "-";
		sTurn2Hit = "-";
		sTurn3Hit = "-";
		sTurn4Hit = "-";
		sTurn5Hit = "-";
		sTurn6Hit = "-";
		lastBaseKubbs = 0;
		lastFieldKubbs = 0;
		t1bh = 0;
		t2bh = 0;
		t3bh = 0;
		t4bh = 0;
		t5bh = 0;
		t6bh = 0;
		t1fh = 0;
		t2fh = 0;
		t3fh = 0;
		t4fh = 0;
		t5fh = 0;
		t6fh = 0;
		iInkast = 0;
		iRethrow = 0;
		iPenalty = 0;
		iTurn1Thrown = 0;
		iTurn2Thrown = 0;
		iTurn3Thrown = 0;
		iTurn4Thrown = 0;
		iTurn5Thrown = 0;
		iTurn6Thrown = 0;
		kubbsKnockedDownLastTurn = 0;
		iTeam1BaseKubbs = 0;
		iTeam1FieldKubbs = 0;
		iTeam2BaseKubbs = 0;
		iTeam2FieldKubbs = 0;
		sInkast = " ";
		iTurn = 1;
	}

	// Get Team Names

	public static String getGameInfo() {
		return sCurrentTurnFinal;
	}

	public static void setGameInformation(String date, String location,
			String event, String pitch, String scoredby) {
		sStartDate = date;
		sLocation = location;
		sEvent = event;
		sPitchSurface = pitch;
		sScoredBy = scoredby;
		sCurrentTurnFinal = "{{Game\n|Team A=" + sTeam1Name
				+ "\n|Team A Player Count=" + sTeam1PlayNum + "\n|Team B="
				+ sTeam2Name + "\n|Team B Player Count=" + sTeam2PlayNum
				+ "\n|Start date=" + sStartDate + "\n|Location=" + sLocation
				+ "\n|Event=" + sEvent + "\n|Pitch surface=" + sPitchSurface
				+ "\n|Scored by=" + sScoredBy + "\n}}\n";

	}

	public static String getTeam1Name() {
		return sTeam1Name;
	}

	public static String getTeam2Name() {
		return sTeam2Name;
	}

	// Set Team Names

	public static void setTeam1Name(Editable team1Name) {
		sTeam1Name = team1Name.toString();
	}

	public static void setTeam2Name(Editable team2Name) {
		sTeam2Name = team2Name.toString();
	}

	// Get Number of Players per team

	public static Integer getTeam1PlayNum() {

		return sTeam1PlayNum;
	}

	public static Integer getTeam2PlayNum() {
		return sTeam2PlayNum;
	}

	// Set Number of Players per team

	public static void setTeam1PlayNum(Integer team1PlayNum) {
		sTeam1PlayNum = team1PlayNum;
	}

	public static void setTeam2PlayNum(Integer team2PlayNum) {
		sTeam2PlayNum = team2PlayNum;
	}

	// Get Player Names

	public static String getTeam1PlayerNames(int x) {
		return sTeam1PlayerNames[x];

	}

	public static String getTeam2PlayerNames(int x) {
		return sTeam2PlayerNames[x];

	}

	// Update String with the player names

	public static void addToString(String whattoadd) {
		sCurrentTurnFinal = sCurrentTurnFinal + whattoadd;
	}

	// Set Player Names

	public static void setTeam1PlayerNames(String team1PlayerName, int position) {
		sTeam1PlayerNames[position] = team1PlayerName.toString();
		addToString("{{Game player\n|Team=" + sTeam1Name + "\n|Player="
				+ sTeam1PlayerNames[position]);

	}

	public static void setTeam2PlayerNames(String team2PlayerName, int position) {
		sTeam2PlayerNames[position] = team2PlayerName.toString();
		addToString("{{Game player\n|Team=" + sTeam2Name + "\n|Player="
				+ sTeam2PlayerNames[position]);
	}

	// Set Player Initials

	public static void setTeam1PlayerInit(String team1PlayerInit, int position) {
		sTeam1PlayerInit[position] = team1PlayerInit.toString();
		addToString("\n|Initial=" + sTeam1PlayerInit[position] + "\n}}\n");

	}

	public static void setTeam2PlayerInit(String team2PlayerInit, int position) {
		sTeam2PlayerInit[position] = team2PlayerInit.toString();
		addToString("\n|Initial=" + sTeam2PlayerInit[position] + "\n}}\n");
	}

	// Which Team Goes first

	public static String getFirstTeam() {
		return whoFirst;

	}

	public static void setFirstTeam(String firstTeam) {
		whoFirst = firstTeam;
		sCurrentTeam = whoFirst;
	}

	// Turn information

	public static void setTurnText(String turnInfo, int turnNumber) {
		sTurnText[turnNumber] = turnInfo;

	}

	public static String getTurnText(int turnNumber) {
		return sTurnText[turnNumber];

	}

	public static Integer getTurnNumber() {
		return iTurnNumber;
	}

	public static void setTurnNumber(int turnNumber) {
		iTurnNumber = turnNumber;
		sCurrentTurn = turnNumber + ". ";
	}

	public static void setTeam() {

		if (sCurrentTeam == sTeam1Name) {
			sCurrentTeam = sTeam2Name;
		} else {
			sCurrentTeam = sTeam1Name;
		}



	}

	public static String getCurrentTeam() {
		return sCurrentTeam;
	}

	public static Boolean getAdvantage() {
		return bAdvantage;
	}

	public static void setAdvantage(Boolean advantage) {
		bAdvantage = advantage;
	}

	public static void setCurrentTurnString() {
		sCurrentTurn = iTurnNumber + ". ";
		if (iInkast > 0) {
			sCurrentTurn = sCurrentTurn + iInkast + "i";
		}
		if (iRethrow > 0) {
			sCurrentTurn = sCurrentTurn + iRethrow + "r";
		}
		if (iPenalty > 0) {
			sCurrentTurn = sCurrentTurn + iPenalty + "p ";
		}
		if (bAdvantage) {
			sCurrentTurn = sCurrentTurn + "a ";
		}

		// Turn 1

		if (sTurn1Player != " " || sTurn1Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn1Player + ":";
		}
		if (iTurn1Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn1Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn1Hit + " ";

		// Turn 2

		if (sTurn2Player != " " || sTurn2Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn2Player + ":";
		}
		if (iTurn2Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn2Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn2Hit + " ";

		// Turn 3

		if (sTurn3Player != " " || sTurn3Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn3Player + ":";
		}
		if (iTurn3Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn3Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn3Hit + " ";

		// Turn 4

		if (sTurn4Player != " " || sTurn4Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn4Player + ":";
		}
		if (iTurn4Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn4Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn4Hit + " ";

		// Turn 5

		if (sTurn5Player != " " || sTurn5Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn5Player + ":";
		}
		if (iTurn5Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn5Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn5Hit + " ";

		// Turn 6

		if (sTurn6Player != " " || sTurn6Player != null) {
			sCurrentTurn = sCurrentTurn + sTurn6Player + ":";
		}
		if (iTurn6Thrown > 0) {
			sCurrentTurn = sCurrentTurn + iTurn6Thrown;
		}
		sCurrentTurn = sCurrentTurn + sTurn6Hit + " ";
	}

	public static String getCurrentTurnString() {
		return sCurrentTurn;
	}

	public static void setInkast(Integer inkast, Integer rethrow,
			Integer penalty) {

		iInkast = inkast;
		iRethrow = rethrow;
		iPenalty = penalty;

	}

	public static String getTeam1PlayerInitials(int x) {

		return sTeam1PlayerInit[x];

	}

	public static String getTeam2PlayerInitials(int x) {

		return sTeam2PlayerInit[x];

	}

	public static void initializeNewTurn() {

		sCurrentTurn = " ";
		iTurnNumber = 1;
		t1bh = 0;
		t2bh = 0;
		t3bh = 0;
		t4bh = 0;
		t5bh = 0;
		t6bh = 0;
		t1fh = 0;
		t2fh = 0;
		t3fh = 0;
		t4fh = 0;
		t5fh = 0;
		t6fh = 0;
		bAdv = false;
		sInkast = " ";

	}

	public static void setKubbsKnockedDown(int field, int base) {
		switch (iTurnNumber) {
		case 1:
			t1bh = base;
			t1fh = field;
			break;
		case 2:
			t2bh = base;
			t2fh = field;
			break;
		case 3:
			t3bh = base;
			t3fh = field;
			break;
		case 4:
			t4bh = base;
			t4fh = field;
			break;
		case 5:
			t5bh = base;
			t5fh = field;
			break;
		case 6:
			t6bh = base;
			t6fh = field;
			break;

		}

		kubbsKnockedDownLastTurn = t1bh + t1fh + t2bh + t2fh + t3bh + t3fh
				+ t4bh + t4fh + t5bh + t5fh + t6bh + t6fh;
		lastBaseKubbs = base;
		lastFieldKubbs = field;

	}

	public static void setKubbsToZero() {
		kubbsKnockedDownLastTurn = 0;
	}

	public static int getKubbsKnockedDown() {
		return kubbsKnockedDownLastTurn;
	}

	public static void setFieldKubbs(int field) {
		if (sCurrentTeam == sTeam1Name) {
			iTeam1FieldKubbs = field;
		} else {
			iTeam2FieldKubbs = field;
		}
	}

	public static void setBaseKubbs(int base) {
		if (sCurrentTeam == sTeam1Name) {
			iTeam1BaseKubbs = base;
		} else {
			iTeam2BaseKubbs = base;
		}

	}

	public static int getFieldKubbsLeft() {
		if (sCurrentTeam == sTeam1Name) {
			return iTeam1FieldKubbs;
		} else {
			return iTeam2FieldKubbs;
		}

	}

	public static int getBaseKubbsLeft() {
		if (sCurrentTeam == sTeam1Name) {
			return iTeam1BaseKubbs;
		} else {
			return iTeam2BaseKubbs;
		}

	}

	public static void initializegame() {
		iTeam1FieldKubbs = 0;
		iTeam2FieldKubbs = 0;
		iTeam1BaseKubbs = 5;
		iTeam2BaseKubbs = 5;

	}

	public static void resetLastTurn() {
		if (sCurrentTeam == sTeam1Name) {
			iTeam1FieldKubbs += lastFieldKubbs;
			iTeam1BaseKubbs += lastBaseKubbs;
		} else {
			iTeam2FieldKubbs += lastFieldKubbs;
			iTeam2BaseKubbs += lastBaseKubbs;
		}
		lastBaseKubbs = 0;
		lastFieldKubbs = 0;
	}

	
	public static void createInkastString() {
		if (iInkast > 0) {
			sInkast = sInkast + iInkast + "i";
		}
		if (iRethrow > 0) {
			sInkast = sInkast + iRethrow + "r";
		}
		if (iPenalty > 0) {
			sInkast = sInkast + iPenalty + "p ";
		}
		
	}
	
	public static void createThrow1String() {
		// Add Throw 1 data
		addToString("|Throw 1 player=" + sTurn1Player + "\n");

		if (t1bh == 0) {
			if (t1fh == 0) {
				addToString("|Throw 1=-\n");
				sTurn1Hit = "-";
			} else if (t1fh == 1) {
				addToString("|Throw 1=f\n");
				sTurn1Hit = "f";
			} else {
				addToString("|Throw 1=" + t1fh + "f\n");
				sTurn1Hit = t1fh + "f";
			}
		} else if (t1bh == 1) {
			if (t1fh == 0) {
				addToString("|Throw 1=b\n");
				sTurn1Hit = "b";
			} else if (t1fh == 1) {
				addToString("|Throw 1=bf\n");
				sTurn1Hit = "bf";
			} else {
				addToString("|Throw 1=b" + t1fh + "f\n");
				sTurn1Hit = "b" + t1fh + "f";
			}
		} else {
			if (t1fh == 0) {
				addToString("|Throw 1=" + t1bh + "b\n");
				sTurn1Hit = t1bh + "b";
			} else if (t1fh == 1) {
				addToString("|Throw 1=" + t1bh + "bf\n");
				sTurn1Hit = t1bh + "bf";
			} else {
				addToString("|Throw 1=" + t1bh + "b" + t1fh + "f\n");
				sTurn1Hit = t1bh + "b" + t1fh + "f";
			}
		}
	}

	public static void createThrow2String() {
		// Add Throw 2 data
		addToString("|Throw 2 player=" + sTurn2Player + "\n");

		if (t2bh == 0) {
			if (t2fh == 0) {
				addToString("|Throw 2=-\n");
				sTurn2Hit = "-";
			} else if (t2fh == 1) {
				addToString("|Throw 2=f\n");
				sTurn2Hit = "f";
			} else {
				addToString("|Throw 2=" + t2fh + "f\n");
				sTurn2Hit = t2fh + "f";
			}
		} else if (t2bh == 1) {
			if (t2fh == 0) {
				addToString("|Throw 2=b\n");
				sTurn2Hit = "b";
			} else if (t2fh == 1) {
				addToString("|Throw 2=bf\n");
				sTurn2Hit = "bf";
			} else {
				addToString("|Throw 2=b" + t2fh + "f\n");
				sTurn2Hit = "b" + t2fh + "f";
			}
		} else {
			if (t2fh == 0) {
				addToString("|Throw 2=" + t2bh + "b\n");
				sTurn2Hit = t2bh + "b";
			} else if (t2fh == 1) {
				addToString("|Throw 2=" + t2bh + "bf\n");
				sTurn2Hit = t2bh + "bf";
			} else {
				addToString("|Throw 2=" + t2bh + "b" + t2fh + "f\n");
				sTurn2Hit = t2bh + "b" + t2fh + "f";
			}
		}
		
	}

	public static void createThrow3String() {
		// Add Throw 3 Data
		addToString("|Throw 3 player=" + sTurn3Player + "\n");

		if (t3bh == 0) {
			if (t3fh == 0) {
				addToString("|Throw 3=-\n");
				sTurn3Hit = "-";
			} else if (t3fh == 1) {
				addToString("|Throw 3=f\n");
				sTurn3Hit = "f";
			} else {
				addToString("|Throw 3=" + t3fh + "f\n");
				sTurn3Hit = t3fh + "f";
			}
		} else if (t3bh == 1) {
			if (t3fh == 0) {
				addToString("|Throw 3=b\n");
				sTurn3Hit = "b" ;
			} else if (t3fh == 1) {
				addToString("|Throw 3=bf\n");
				sTurn3Hit = "bf";
			} else {
				addToString("|Throw 3=b" + t3fh + "f\n");
				sTurn3Hit = "b" + t3fh + "f";
			}
		} else {
			if (t3fh == 0) {
				addToString("|Throw 3=" + t3bh + "b\n");
				sTurn3Hit = t3bh + "b" ;
			} else if (t3fh == 1) {
				addToString("|Throw 3=" + t3bh + "bf\n");
				sTurn3Hit = t3bh + "bf";
			} else {
				addToString("|Throw 3=" + t3bh + "b" + t3fh + "f\n");
				sTurn3Hit = t3bh + "b" + t3fh + "f";
			}
		}
	}

	public static void createThrow4String() {
		// Add Throw 4 Data
		addToString("|Throw 4 player=" + sTurn4Player + "\n");

		if (t4bh == 0) {
			if (t4fh == 0) {
				addToString("|Throw 4=-\n");
				sTurn4Hit = "-";
			} else if (t4fh == 1) {
				addToString("|Throw 4=f\n");
				sTurn4Hit = "f";
			} else {
				addToString("|Throw 4=" + t4fh + "f\n");
				sTurn4Hit = t4fh + "f";
			}
		} else if (t4bh == 1) {
			if (t4fh == 0) {
				addToString("|Throw 4=b\n");
				sTurn4Hit = "b" ;
			} else if (t4fh == 1) {
				addToString("|Throw 4=bf\n");
				sTurn4Hit = "bf";
			} else {
				addToString("|Throw 4=b" + t4fh + "f\n");
				sTurn4Hit = "b" + t4fh + "f";
			}
		} else {
			if (t4fh == 0) {
				addToString("|Throw 4=" + t4bh + "b\n");
				sTurn4Hit = t4bh + "b" ;
			} else if (t4fh == 1) {
				addToString("|Throw 4=" + t4bh + "bf\n");
				sTurn4Hit = t4bh + "bf";
			} else {
				addToString("|Throw 4=" + t4bh + "b" + t4fh + "f\n");
				sTurn4Hit = t4bh + "b" + t4fh + "f";
			}
		}
	}

	public static void createThrow5String() {
		// Add Throw 5 Data
		addToString("|Throw 5 player=" + sTurn5Player + "\n");

		if (t5bh == 0) {
			if (t5fh == 0) {
				addToString("|Throw 5=-\n");
				sTurn5Hit = "-";
			} else if (t5fh == 1) {
				addToString("|Throw 5=f\n");
				sTurn5Hit = "f";
			} else {
				addToString("|Throw 5=" + t5fh + "f\n");
				sTurn5Hit = t5fh + "f";
			}
		} else if (t5bh == 1) {
			if (t5fh == 0) {
				addToString("|Throw 5=b\n");
				sTurn5Hit = "b" ;
			} else if (t5fh == 1) {
				addToString("|Throw 5=bf\n");
				sTurn5Hit = "bf";
			} else {
				addToString("|Throw 5=b" + t5fh + "f\n");
				sTurn5Hit = "b" + t5fh + "f";
			}
		} else {
			if (t5fh == 0) {
				addToString("|Throw 5=" + t5bh + "b\n");
				sTurn5Hit = t5bh + "b";
			} else if (t5fh == 1) {
				addToString("|Throw 5=" + t5bh + "bf\n");
				sTurn5Hit = t5bh + "bf";
			} else {
				addToString("|Throw 5=" + t5bh + "b" + t5fh + "f\n");
				sTurn5Hit = t5bh + "b" + t5fh + "f";
			}
		}
	}

	public static void createThrow6String() {
		// Add Throw 6 Data
		addToString("|Throw 6 player=" + sTurn6Player + "\n");

		if (t6bh == 0) {
			if (t6fh == 0) {
				addToString("|Throw 6=-\n");
				sTurn6Hit = "-";
			} else if (t6fh == 1) {
				addToString("|Throw 6=f\n");
				sTurn6Hit = "f";
			} else {
				addToString("|Throw 6=" + t6fh + "f\n");
				sTurn6Hit =  t6fh + "f";
			}
		} else if (t6bh == 1) {
			if (t6fh == 0) {
				addToString("|Throw 6=b\n");
				sTurn6Hit = "b" ;
			} else if (t6fh == 1) {
				addToString("|Throw 6=bf\n");
				sTurn6Hit = "bf";
			} else {
				addToString("|Throw 6=b" + t6fh + "f\n");
				sTurn6Hit = "b" + t6fh + "f";
			}
		} else {
			if (t6fh == 0) {
				addToString("|Throw 6=" + t6bh + "b\n");
				sTurn6Hit = t6bh + "b" ;
			} else if (t6fh == 1) {
				addToString("|Throw 6=" + t6bh + "bf\n");
				sTurn6Hit = t6bh + "bf";
			} else {
				addToString("|Throw 6=" + t6bh + "b" + t6fh + "f\n");
				sTurn6Hit = t6bh + "b" + t6fh + "f";
			}
		}
		addToString("}}\n");

	}

	public static void addPlayerName(String playername) {
		switch (iTurnNumber) {
		case 1:
			sTurn1Player = playername;
			break;
		case 2:
			sTurn2Player = playername;
			break;
		case 3:
			sTurn3Player = playername;
			break;
		case 4:
			sTurn4Player = playername;
			break;
		case 5:
			sTurn5Player = playername;
			break;
		case 6:
			sTurn6Player = playername;
			break;
		}

	}

	public static boolean getAdv() {
		if (sCurrentTeam == sTeam1Name) {
			if (iTeam2FieldKubbs > 0) {
				bAdv = true;
			}
		} else {
			if (iTeam1FieldKubbs > 0) {
				bAdv = true;
			}
		}

		return bAdv;

	}

	public static void setMatchID(String matchid) {
		sMatchID = matchid;
	}

	public static void setTurnEdit(int turn) {
		iTurnEdit = turn;
	}

	public static void varsNotNull() {
		if (sTeam1Name == null) {
			sTeam1Name = " ";
		}
		if (sTeam2Name == null) {
			sTeam2Name = " ";
		}
		if (sStartDate == null) {
			sStartDate = " ";
		}
		if (sLocation == null) {
			sLocation = " ";
		}
		if (sEvent == null) {
			sEvent = " ";
		}
		if (sPitchSurface == null) {
			sPitchSurface = " ";
		}
		if (sScoredBy == null) {
			sScoredBy = " ";
		}
		if (sT1P1 == null) {
			sT1P1 = " ";
		}
		if (sT1P2 == null) {
			sT1P2 = " ";
		}
		if (sT1P3 == null) {
			sT1P3 = " ";
		}
		if (sT1P4 == null) {
			sT1P4 = " ";
		}
		if (sT1P5 == null) {
			sT1P5 = " ";
		}
		if (sT1P6 == null) {
			sT1P6 = " ";
		}
		if (sT2P1 == null) {
			sT2P1 = " ";
		}
		if (sT2P2 == null) {
			sT2P2 = " ";
		}
		if (sT2P3 == null) {
			sT2P3 = " ";
		}
		if (sT2P4 == null) {
			sT2P4 = " ";
		}
		if (sT2P5 == null) {
			sT2P5 = " ";
		}
		if (sT2P6 == null) {
			sT2P6 = " ";
		}

	}

	
	
	
}
