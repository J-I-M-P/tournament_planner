package userinterface;

import java.util.Scanner;
import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import obsolet.DataIO;
import starter.StarterClass;

public class MainInterface {
	
	
	
	public static void showWelcomeMessage(){
		String welcomeMessage =   "Willkommen beim Turniermanager\n"
								+ "------------------------------";
		System.out.println(welcomeMessage);
	}
	
	
/**
 * TODO	
 */
	public static void clearScreen(){
		//system.
	}
	
	public static void mainInterfaceMenue(){
		String mainMenueText =	"-----Hauptmenue----------\n"
							  + "\n"
							  + "1 - Import / Export - Datenbanken\n"		 
							  + "2 - Turnierdaten anpassen\n"
							  + "3 - Spielerdaten anpassen\n"
							  + "4 - Teamdaten anpassen\n"
							  + "5 - set verbose\n"
							  + "6 - test\n"
							  + "0 - verlassen";

		while(true){

			System.out.println(mainMenueText);

			switch (IOTools.readInt()) {
			case 1://Import / Export - Datenbanken
				DBManager.dbManagerMenue();
				break;
			case 2://Turnierdaten anpassen
				TournamentIOInterface.tournamentIOMenue();
				break;
			case 3://Spielerdaten anpassen
				PlayerIOInterface.playerIOMenue();
				break;
			case 4://Teamdaten anpassen
				TeamIOInterface.teamIOMenue();
				break;
			case 5:
				StarterClass.verboselevel = IOTools.readInt("verbose (1-3): "); 
				break;
			case 6:	
//				Import_export_datas.importTournament2allTournaments2("rsd171109");
//				Import_export_datas.importAllTournamentsFromOverview2_TEMP(false);
				Import_export_datas.tournamentOldImportNewExport();
//				importtest();
//				System.out.println(Import_export_datas.getTournamentByID(IOTools.readInt("id: ")));
//				Import_export_datas.importTournament2allTournaments(IOTools.readString("filename: "));
				break;
			case 0:
				saveAndQuit();	
				return;
			}
		}
	}

	
	public static void saveAndQuit() {
		if (IOTools.readInt("save?")==1){
			
		}
		System.out.print("-->Desktop");
		System.exit(0);
	}


	/**
	 * TODO
	 * delete test method
	 */
	private static void importtest() {
		String name = IOTools.readLine("neuer turniername: ");
		if (name.equals("")){
			System.out.println("leer");
		}else {
			System.out.println("inhalt");
		}
	}


	//---------------------data base management--------------------------------------------------------------------------------------------
	
	public static void gotoDbMenue(){
		String dbMenueText =  "-----Datenbankmenue------\n"
				  			+ "\n"
				  			+ "1 - playerDB file anpassen\n"
							+ "2 - tournamentDB file anpassen\n";
		
		System.out.println(dbMenueText);
		
	}
	
	//---------------------tournament management-------------------------------------------------------------------------------------------
	
	public static void gotoTournamentMenue(){
		
	}

	
	//---------------------player management-----------------------------------------------------------------------------------------------
	
	
	
	
	
	//---------------------team management--------------------------------------------------------------------------------------------------
	
	public static void gotoTeamMenue(){
		
	}


	public static void startImport() {
		Import_export_datas.import_players_from_file();
		Import_export_datas.import_teams_from_file();
		Import_export_datas.importAllTournamentsFromOverview(false);
	}
	
	 
	

}
