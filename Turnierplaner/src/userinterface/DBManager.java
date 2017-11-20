package userinterface;

import javax.swing.plaf.synth.SynthSpinnerUI;

import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import datatypes.Team;
import obsolet.DataIO;

public class DBManager {
	
	public static void dbManagerIOMenue(){
		while(true){
			String playerDataMenue =  "-----DB Manager-------------------------------------------------------------------------------------------------\n"
									+ "-----PlayerDB---------                      -----TeamDB---------                       -----TurnierDB----------        \n"
									+ "\n"
					  				+ "01 - spielerdaten einlesen von Datenbank    06 - teamdaten einlesen von Datenbank      11 - Turnierdaten einlesen\n"
									+ "02 - spieler anzeigen                       07 - team anzeigen                         12 - Turnierdaten anzeigen\n"
					  				+ "03 - spieler DB auf Konsistenz prüfen       08 - team DB auf Konsistenz prüfen         13 - turnierübersicht exportieren\n"
									+ "04 - spieler in datenbak schreiben          09 - teams in datenbak schreiben           14 - turniere und übersicht speichern\n"
					  				+ "05 - spieler in DB suchen                   10 - team in DB suchen                     15 - \n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(playerDataMenue);
			switch (IOTools.readInt()) {
			case 1:
				Import_export_datas.import_players_from_file();
				break;
			case 2:
				/**
				 * TODO
				 * read players direct from db. not from intern data
				 */
				if(Import_export_datas.allPlayers.isEmpty()){
					System.out.println("DB leer...Daten werden eingelsen...\n");
					Import_export_datas.import_players_from_file();
				}
				for (Player p : Import_export_datas.allPlayers) {
					System.out.println(p);
				}
				break;
			case 3:
				
				break;
			case 4:
				Import_export_datas.export_players_to_file();
				break;
			case 6:
				Import_export_datas.import_teams_from_file();
				break;
			case 7:
				/**
				 * TODO
				 * read teams direct from db. not from intern data
				 */
				if(Import_export_datas.allTeams.isEmpty()){
					System.out.println("DB leer...Daten werden eingelsen...\n");
					Import_export_datas.import_teams_from_file();;
				}
				for (Team t : Import_export_datas.allTeams) {
					System.out.println(t);
				}
				break;
			case 8:
				//team create
				break;
			case 9:
				Import_export_datas.export_teams_to_file();
				break;
			case 11:
				Import_export_datas.importAllTournamentsFromOverview(false);
				break;
			case 12:
				TournamentIOInterface.showAllTournaments();
				break;
			case 13:
				Import_export_datas.exportTournamentOverview();
				break;
			case 14:
				Import_export_datas.saveAllActualTournamentData();
				break;
			case 15:
				break;
			case 99:
				MainInterface.saveAndQuit();
			case 0:
				return;
			}
		}
		
		
		
	}
	
	
	
	/**
	 * check DB for integrity 
	 * all players have right amount of data
	 * if not  maybe fix this automatically
	 */
	private void checkDataBaseIntegrity(){
		
	}
	
}
