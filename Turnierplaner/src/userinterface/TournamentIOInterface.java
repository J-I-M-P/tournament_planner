package userinterface;

import java.nio.channels.ShutdownChannelGroupException;

import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import datatypes.Team;
import datatypes.Tournament;

public class TournamentIOInterface {
	
	public static void tournamentIOMenue(){
		while(true){
			String teamDataMenue =  "-----Turnier IO---------\n"
									+ "\n"
					  				+ "1 - Alle Turniere anzeigen\n"
									+ "2 - \n"
					  				+ "3 - neues Turnier starten\n"
									+ "4 - turnier anlegen\n"
					  				+ "5 - Team Menue\n"
					  				+ "6 - Spieler Menue\n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(teamDataMenue);
			switch (IOTools.readInt()) {
			case 1:
				showAllTournaments();
				break;
			case 2:
				break;
			case 3:
				startNewTournament();
				break;
			case 4:
				createNewTournament();
				break;
			case 5:
				TeamIOInterface.teamIOMenue();
				break;
			case 6:
				PlayerIOInterface.playerIOMenue();
				break;
			case 99:
				System.out.print("-->Desktop");
				System.exit(0);
			case 0:
				return;
			}
		}
		
		
		
	}
	
	public static void createNewTournament() {
		System.out.println("turnier anlegen");
		//import overview to get actual tournament id
		System.out.println("import aktuelle turnierliste");
		Import_export_datas.import_tournament_overview_from_file(false);
		
		System.out.println("turnierdaten");
		//set tournament id
		/**
		 * TODO
		 * better way
		 */
		int tAddID = Tournament.getNextID();
		Tournament.setNextID(tAddID+1);
		
		//get tournament data
		String tAddName = IOTools.readString("turniername: ");
		long tAddDateCreate = IOTools.readLong("datum: ");
		/**
		 * TODO
		 * whole file path
		 */
		String tAddFileName = IOTools.readString("filename: ");
		String tAddLocation = IOTools.readString("ort: ");
		
		//generate new tournament
		Tournament tAdd = new Tournament();
		tAdd.setId(tAddID);
		tAdd.setTournamentName(tAddName);
		tAdd.setDateMade(tAddDateCreate);
		/**
		 * TODO
		 * whole file path 
		 */
		tAdd.setFilePathName(tAddFileName);
		tAdd.setLocation(tAddLocation);
		//import in all tournaments
		Import_export_datas.allTournaments.add(tAdd);
		//set as actual tournament 
		System.out.println(tAddName + " wird als aktuelles Turnier gesetzt.");
		Import_export_datas.actualTournament = tAdd;
		
		//player
		System.out.println("spielerdaten");
		
		
		//team
		System.out.println("teamdaten");
		
		
	}

	public static void startNewTournament() {
		System.out.println("starte neues turnier");
		createNewTournament();
		
	}

	public static void showAllTournaments(){
		for (Tournament t : Import_export_datas.allTournaments) {
			System.out.println(t);
		}
	}
	
	
}