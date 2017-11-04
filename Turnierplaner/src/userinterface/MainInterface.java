package userinterface;

import java.util.Scanner;
import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import obsolet.DataIO;
import starter.StarterClass;

public class MainInterface {
	
	
	
	public static void showWelcomeMessage(){
		String welcomeMessage = "Willkommen beim Turniermanager";
		
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
				Import_export_datas.import_tournament_overview_from_file(true);
				break;
			case 0:
				System.out.println("by\n");	
				return;
			}
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
	
	 
	

}
