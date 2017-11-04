package userinterface;

import java.nio.channels.ShutdownChannelGroupException;

import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import datatypes.Team;

public class TeamIOInterface {
	
	public static void teamIOMenue(){
		while(true){
			String teamDataMenue =  "-----Team IO---------\n"
									+ "\n"
					  				+ "1 - team anzeigen\n"
									+ "2 - neues team anlegen\n"
					  				+ "3 - team löschen\n"
									+ "4 - spieler zu team hinzufuegen\n"
					  				+ "5 - \n"
					  				+ "6 - zum spieler menue\n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(teamDataMenue);
			switch (IOTools.readInt()) {
			case 1:
				showAllTeams();
				break;
			case 2:
				addNewTeamDialogue();
				break;
			case 3:
				removeTeamDialogue();
				break;
			case 4:
				addPlayer2TeamDialogue();
				break;
			case 5:
				
				break;
			case 99:
				System.out.print("-->Desktop");
				System.exit(0);
			case 0:
				return;
			}
		}
		
		
		
	}
	
	public static void addNewTeamDialogue(){
		/**
		 * TODO
		 * check player in database if import wasnt made
		 * --> no duplicate in id !!!
		 * 
		 * other way: ask for dont import..then create new player database
		 */
		if(Import_export_datas.allTeams.isEmpty()){
			//System.out.println("leer");
			Import_export_datas.import_players_from_file();
		}
		System.out.println("Neuen Spieler anlegen:");
		String firstname 	= IOTools.readLine("Vorname:");
		String surename 	= IOTools.readLine("Nachname:");
		Player addP = new Player(firstname);
		addP.setSurename(surename);
		Import_export_datas.allPlayers.add(addP);
	}
	
	public static int removeTeamDialogue(){
		return 0;
		
	}
	
	private static void addPlayer2TeamDialogue(){
//		for (Team team : Import_export_datas.allTeams) {
//			
//		}
		System.out.println(Import_export_datas.allTeams);
	}
	
	private static int teamRemoveFunction(){
		return 0;
		
	}
	
	public static void showAllTeams(){
		if(Import_export_datas.allTeams.isEmpty()){
			System.out.println("keine Daten importiert. importieren?/n");
			//Import_export_datas.import_players_from_file();
		}
		for (Team t : Import_export_datas.allTeams) {
			System.out.println(t);
		}
	}

}