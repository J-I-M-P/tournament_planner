package userinterface;

import java.nio.channels.ShutdownChannelGroupException;

import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;

public class PlayerIOInterface {
	
	public static void playerIOMenue(){
		while(true){
			String playerDataMenue =  "-----Player IO---------\n"
									+ "\n"
					  				+ "1 - spieler anzeigen\n"
									+ "2 - neuen spielerdaten anlegen\n"
					  				+ "3 - spieler löschen\n"
									+ "4 - spieler editieren\n"
					  				+ "5 - zum team menue\n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(playerDataMenue);
			switch (IOTools.readInt()) {
			case 1:
				showAllPlayers();
				break;
			case 2:
				addNewPlayerDialogue();
				break;
			case 3:
				removePlayerDialogue();
				break;
			case 4:
				editPlayer();
				break;
			case 5:
				TeamIOInterface.teamIOMenue();
				break;
			case 99:
				MainInterface.saveAndQuit();
			case 0:
				return;
			}
		}
		
		
		
	}
	
	public static void addNewPlayerDialogue(){
		/**
		 * TODO
		 * check player in database if import wasnt made
		 * --> no duplicate in id !!!
		 * 
		 * other way: ask for dont import..then create new player database
		 */
		if(Import_export_datas.allPlayers.isEmpty()){
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
	
	public static int removePlayerDialogue(){
		return 0;
		
	}
	
	private static int playerRemoveFunction(){
		return 0;
		
	}
	
	public static void showAllPlayers(){
		if(Import_export_datas.allPlayers.isEmpty()){
			if(IOTools.readChar("keine Daten importiert. importieren? (j/n): ") == 'j'){
				Import_export_datas.import_players_from_file();
			}else{
				/**
				 * TODO
				 * implement this 
				 */
				System.out.println("neue DB wird erstellt\n ACHTUNG muss noch implementiert werden");
			}
			
			
		}
		for (Player p : Import_export_datas.allPlayers) {
			System.out.println(p);
		}
	}
	
	public static int editPlayer(){
		return 0;
	}
	
	public static Player getPlayerByID(int id){
		for (Player p : Import_export_datas.allPlayers) {
			if(p.getPlayerId() == id){
				return p;
			}
		}
		return null;
	}

}