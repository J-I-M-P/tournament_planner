package datamanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import Prog1Tools.IOTools;
import datatypes.Player;
import datatypes.Team;
import datatypes.Tournament;
import starter.StarterClass;
import userinterface.PlayerIOInterface;

public class Import_export_datas {

	/**
	 * class to handle imports and export of player, team and tournament DBs to or from a txt or xlx file
	 */

	private static String dbFilePlayer = "data\\player.txt";
	/**
	 * TODO
	 * delete
	 */
	private static String dbFilePlayerTesting = "data\\player1.txt";
	/**
	 * 
	 */
	private static String dbFileTeam = "data\\team.txt";
	private	static String dbFileTournamentOverview = "data\\tournamentOverview.txt";

	public static ArrayList<Player> allPlayers = new ArrayList<Player>();
	public static ArrayList<Team> allTeams = new ArrayList<Team>();
	public static ArrayList<Tournament> allTournaments = new ArrayList<Tournament>();

	public static Tournament actualTournament = new Tournament();

	public static String getDbFilePlayer(){
		return dbFilePlayer;
	}

	/**
	 * TODO
	 * import
	 * 		-check for no duplicate id!!/read id from file and check if consistent
	 * 		-check for file status
	 */

	public static void import_players_from_file(){
		File databaseFile = new File(dbFilePlayer);
		/**
		 * TODO
		 * look for id consistent checking
		 */
		int setId = 0;
		if(databaseFile.exists()){

			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				r.readLine();
				while((line = r.readLine()) != null){
					String[] fields = line.split(";");
					/**
					 * TODO
					 * check if syntax fits better way
					 */

					if(fields.length < 3){
						continue;
					}
					// create player (if possible)
					Player addP = new Player(fields[2]);
					addP.setID(Integer.parseInt(fields[0]));
					addP.setSurename(fields[1]);
					allPlayers.add(addP);
					setId=addP.getPlayerId()+1;
				}
				Player.setNextId(setId);
			} catch (Exception ignored) {}

		}

	}

	/**
	 * TODO
	 * read only to display data
	 */
	public static void read_from_file(){

	}


	/**
	 * optimize doubled code for import of player/teams/tournament
	 */
	public static void import_teams_from_file(){
		File databaseFile = new File(dbFileTeam);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				int setId=0;
				
				r.readLine();
				while((line = r.readLine()) != null){
					
					//collection type for players
					ArrayList<Player> addPlayerList = new ArrayList<>();
					
					//split 
					String[] splitLine = line.split(";");
					int id = Integer.valueOf(splitLine[0]);
					String teamName = splitLine[1];
					for (int i = 2; i < splitLine.length; i++) {
						int playerID = Integer.valueOf(splitLine[i]);
						addPlayerList.add(PlayerIOInterface.getPlayerByID(playerID));
					}
					
					// create team (if possible)

					Team addT = new Team(teamName,addPlayerList);
					System.out.println(addT);
					
					allTeams.add(addT);
					setId=addT.getTeamId()+1;
				}
				Team.setNextId(setId);
			} catch (Exception ignored) {}

		}

	}

	@SuppressWarnings("deprecation")
	public static void import_tournament_overview_from_file(boolean userOKisNeeded){
		/**
		 * 3 files: player,teams,tournament
		 */
		File databaseFile = new File(dbFileTournamentOverview);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				//skip first line
				r.readLine();
				//read following lines
				while((line = r.readLine()) != null){
					String[] splitLine = line.split(";");
					//tournament id
					int tourID = Integer.valueOf(splitLine[0]);
					System.out.println("id: "+tourID);
					//tournamentName
					String tourName = splitLine[1];
					System.out.println(" - name: "+tourName);
					//date made
					
					/**
					 * TODO
					 * date format
					 */
					
					System.out.print(" - date made: "+splitLine[2]);
					java.sql.Date tourDate = new java.sql.Date(0);
					
					//date last edit
					System.out.println(" - date last modified: "+splitLine[3]);
					
					//tournamentDBfile
					String tournamentDBFileName = splitLine[4];
					System.out.println(" - DB file: "+tournamentDBFileName);
					
					if(userOKisNeeded){
						if(IOTools.readInt("import " + tourName + "?")==1){
							importTournament2allTournaments(tournamentDBFileName);
						}
					}else{
						importTournament2allTournaments(tournamentDBFileName);
					}
					
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	
	/**
	 * import one tournament to list of tournaments in ram
	 * @param filename of tournament file in //data
	 */
	public static void importTournament2allTournaments(String filename){
		String filePathName = "data//"+filename;
		
//		System.out.println(filePathName);

		File databaseFile = new File(filePathName);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				//skip first line
				r.readLine();
				//read following lines
				int addTourID = 0;
				long addTourDate_made = 0, addTourDate_l_e=0;
				String addTourName = null;
				while((line = r.readLine()) != null){
					if (line.contains("=")){
						String[] splitLine = line.split("=");
						switch (splitLine[0]) {
						case "id":
							addTourID = Integer.valueOf(splitLine[1]);
							break;
						case "tournament_name":
							addTourName = splitLine[1];
							break;
						case "date_made":
							 addTourDate_made = Long.valueOf(splitLine[1]);
							break;
						case "date_last_edit":
							 addTourDate_l_e = Long.valueOf(splitLine[1]);
							break;
						default:
							break;
						}
					}
				}
				
				Tournament tourAdd = new Tournament();
				tourAdd.setId(addTourID);
				tourAdd.setTournamentName(addTourName);
				tourAdd.setDateMade(addTourDate_made);
				tourAdd.setDateLastMod(addTourDate_l_e);
				
				//add tourAdd to all tournaments
				allTournaments.add(tourAdd);
				//increase id counter in datatype 
				Tournament.setNextID(tourAdd.getId()+1);
				
				
				System.out.println("imported: "+tourAdd);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	static public void export_players_to_file(){
		writeToFile("player");
	}

	static public void export_teams_to_file(){
		/**
		 * 2 files
		 * player file additional created and connected by filename and links
		 */
	}

	private void export_tournaments_to_file(){
		/**
		 * 3 files for players, teams and tournament
		 * players and team files are connected to tournament file by name and links
		 */
	}

	private static void writeToFile(String whatToWrite){
		switch (StarterClass.verboselevel){
		case 1:
			break;
		case 2:
			break;
		case 3:
			System.out.println("writing...");
			break;
		}
		String filename = "";//Initialize what to do variable
		switch (whatToWrite) {
		case "player"://if player take player DB
			filename = dbFilePlayerTesting; 
			break;
		}
		Writer writer = null;

		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
			switch (whatToWrite) {//how to write
			case "player":
				/**
				 * TODO
				 * backup player DB --> write new one --> replace old one
				 */
				if (StarterClass.verboselevel > 1){
					/**
					 * lastindex is maybe accessible by iterator for allPlayers
					 */
					writer.write("  lastindex:" + allPlayers.lastIndexOf(allPlayers) + "\n");

				}
				/**
				 * TMP get last index
				 */
				writer.write("ID;Nachname;Vorname;Alter;Geschlecht;Handicap;Adresse;Telefon");
				int maxId = 0;
				for (Player player : allPlayers){
					maxId = player.getPlayerId();
				}
				for (Player player : allPlayers) {
					writer.write(player.getPlayerId() + ";" + player.getSurename() + ";" + player.getFirstName());
					if (StarterClass.verboselevel > 1){
						writer.write("  index:" + allPlayers.indexOf(player));
					}

					/**
					 * TODO
					 * if more data in DB set new line
					 */
					if (player.getPlayerId() < maxId){
						writer.write("\n");
					}


				}
				break;
			}
			//			writer.write("Something");
		} catch (IOException ex) {
			// report
		} finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}
	}


}
