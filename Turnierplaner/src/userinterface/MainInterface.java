package userinterface;

import java.util.ArrayList;
import java.util.Scanner;
import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import datatypes.Player;
import datatypes.Team;
import datatypes.TournamentMode;
import datatypes.TournamentMode_KO;
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
				DBManager.dbManagerIOMenue();
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

				ArrayList<Team> in = new ArrayList<>();
				for (Team team : Import_export_datas.allTeams) {
					in.add(team);
				}
				TournamentMode_KO mode1 = new TournamentMode_KO(in);
//				System.out.println(mode1);
				mode1.generateModePlan();
				
//				Import_export_datas.importTournament2allTournaments2("rsd171109");
//				Import_export_datas.importAllTournamentsFromOverview2_TEMP(false);
//				Import_export_datas.tournamentOldImportNewExport();
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

	
	
	/**
	 * TODO
	 * implement save only when changes
	 */
	public static void saveAndQuit() {
		
		if (IOTools.readInt("save?")==1){
			Import_export_datas.export_players_to_file();
			Import_export_datas.export_teams_to_file();
			Import_export_datas.saveAllActualTournamentData();
		}
		System.out.print("-->Desktop");
		System.exit(0);
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
