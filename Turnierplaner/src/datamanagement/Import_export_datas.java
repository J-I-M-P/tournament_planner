package datamanagement;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
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
					
					System.out.println("imported: " + addP);
					
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
					allTeams.add(addT);
					System.out.println("imported: " + addT);
					setId=addT.getTeamId()+1;
				}
				Team.setNextId(setId);
			} catch (Exception ignored) {}

		}

	}

	/**
	 * 
	 * @param userOKisNeeded true if user has to decide if to import or not
	 */
	@SuppressWarnings("deprecation")
public static void importAllTournamentsFromOverview(boolean userOKisNeeded){
		/**
		 * 3 files: player,teams,tournament
		 */
		boolean verboseIsOn = false;
		
		File databaseFile = new File(dbFileTournamentOverview);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				//skip first line
				r.readLine();
				//read following lines
				while((line = r.readLine()) != null){
					String[] splitLine = line.split(";");
					//tournamentName
					String tourName = splitLine[1];
					//tournamentDBfile
					String tournamentDBFileName = splitLine[4];

					if(verboseIsOn){
						//tournament id
						int tourID = Integer.valueOf(splitLine[0]);
						//date made
						/**
						 * TODO
						 * date format
						 */
						java.sql.Date tourDate = new java.sql.Date(0);
						//date last edit
						System.out.println(" - date last modified: "+splitLine[3]);
						System.out.println(line);
						System.out.println("id: "+tourID);
						System.out.println(" - name: "+tourName);
						System.out.print(" - date made: "+splitLine[2]);
						System.out.println(" - DB file: "+tournamentDBFileName);	
					}
					
					
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
	
	public static void importAllTournamentsFromOverview2_TEMP(boolean userOKisNeeded){
		/**
		 * 3 files: player,teams,tournament
		 */
		boolean verboseIsOn = false;
		
		File databaseFile = new File(dbFileTournamentOverview);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				//skip first line
				r.readLine();
				//read following lines
				while((line = r.readLine()) != null){
					String[] splitLine = line.split(";");
					//tournamentName
					String tourName = splitLine[1];
					//tournamentDBfile
					String tournamentDBFileName = splitLine[4];

					if(verboseIsOn){
						//tournament id
						int tourID = Integer.valueOf(splitLine[0]);
						//date made
						/**
						 * TODO
						 * date format
						 */
						java.sql.Date tourDate = new java.sql.Date(0);
						//date last edit
						System.out.println(" - date last modified: "+splitLine[3]);
						System.out.println(line);
						System.out.println("id: "+tourID);
						System.out.println(" - name: "+tourName);
						System.out.print(" - date made: "+splitLine[2]);
						System.out.println(" - DB file: "+tournamentDBFileName);	
					}
					
					
					if(userOKisNeeded){
						if(IOTools.readInt("import " + tourName + "?")==1){
							importTournament2allTournaments2(tournamentDBFileName);
						}
					}else{
						importTournament2allTournaments2(tournamentDBFileName);
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

	
	public static void exportTournamentOverview(){
		if(Import_export_datas.allTournaments.isEmpty()){
			System.out.println("datenbank leer...abruch");
			return;
		}
//		String filename = "data//tournamentOverview.txt";
		String filename = dbFileTournamentOverview;
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
			writer.write("id;tournamentName;date_made;date_last_edit;tournamentDBfile\n");
			for (Tournament t : allTournaments) {
				writer.write(t.getId() + ";" + t.getTournamentName() + ";" + t.getDateMade() + ";" + t.getDateLastMod() + ";" + t.getFilePathName() + "\n"); 
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
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
				String addTourName = null, addTourFileName = null, addTourLocation = null;
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
						case "location":
							addTourLocation = splitLine[1];
							break;
						case "filePathName":
							addTourFileName = splitLine[1];
							break;
//						case "":
//							break;
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
				tourAdd.setFilePathName(addTourFileName);
				tourAdd.setLocation(addTourLocation);
				
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
	
	public static void importTournament2allTournaments2(String filename){
		String filePathName = "data//"+filename;

		//		System.out.println(filePathName);

		File databaseFile = new File(filePathName);
		if(databaseFile.exists()){
			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				int addTourID = 0;
				long addTourDate_made = 0, addTourDate_l_e=0;
				String addTourName = null, addTourFileName = null, addTourLocation = null;
				boolean isInHeader = false, isInBody = false;

				while((line = r.readLine()) != null){
					if(line.equals("")){//skip empty lines
						continue;
					}
//					System.out.print(line + " --> ");
					if (isInHeader){//if line is in header
						if(line.contains("HEADER END")){
//							System.out.println("header end detected");
							isInHeader = false;
							continue;
						}
						//area for manage header content --------------------------------


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
							case "location":
								addTourLocation = splitLine[1];
								break;
							case "filePathName":
								addTourFileName = splitLine[1];
								break;
								//case "":
								//break;
							default:
								break;
							}//switch
						}//if line contain "="

						//end of area for manage header content -------------------------
					} else if(isInBody){
						if(line.contains("BODY END")){
//							System.out.println("body end detected");
							isInBody = false;
							continue;
						}
						//area for manage body content ----------------------------------

//						System.out.println("body room");

						//end of area for manage body content ---------------------------
					} else if (line.contains("HEAD BEGIN")){
						isInHeader = true;
//						System.out.println("header begin detected");
						continue;
					} else if (line.contains("BODY BEGIN")){
						isInBody = true;
//						System.out.println("body begin detected");
						continue;
					} else {
//						System.out.println("interstellar room *_*");
						continue;
					}
				}//while read line
				//adding tournament to db

				Tournament tourAdd = new Tournament();
				tourAdd.setId(addTourID);
				tourAdd.setTournamentName(addTourName);
				tourAdd.setDateMade(addTourDate_made);
				tourAdd.setDateLastMod(addTourDate_l_e);
				tourAdd.setFilePathName(addTourFileName);
				tourAdd.setLocation(addTourLocation);

				//add tourAdd to all tournaments
				allTournaments.add(tourAdd);
				//increase id counter in datatype 
				Tournament.setNextID(tourAdd.getId()+1);
				/**
				 * TODO
				 * delete verbose
				 */
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

	public static void export_tournaments_to_file(int id){
		/**
		 * 3 files for players, teams and tournament
		 * players and team files are connected to tournament file by name and links
		 */
		
		boolean verboseIsOn = true;
		
		Tournament tournament2export = getTournamentByID(id);
		String filename = "data//" + tournament2export.getFilePathName();
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));

			//data header of tournament
			writer.write(     "main info\n"
							+ "-------------------------------\n"
							+ "id=" + tournament2export.getId() + "\n"
							+ "tournament_name=" + tournament2export.getTournamentName() + "\n"
							+ "date_made=" + tournament2export.getDateMade() + "\n"
							+ "date_last_edit=" + tournament2export.getDateLastMod() + "\n"
							+ "filePathName=" + tournament2export.getFilePathName() + "\n"
							+ "location=" + tournament2export.getLocation());
			
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

		if(verboseIsOn){
			System.out.println("exported: " + tournament2export);
		}
	
		
	}

	public static void export_tournaments_to_file2(int id){
		/**
		 * 3 files for players, teams and tournament
		 * players and team files are connected to tournament file by name and links
		 */
		
		boolean verboseIsOn = true;
		
		Tournament tournament2export = getTournamentByID(id);
		String filename = "data//" + tournament2export.getFilePathName();
		Writer writer = null;
		try {
			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));

			//data header of tournament
			writer.write(     "HEAD BEGIN\n"
							+ "id=" + tournament2export.getId() + "\n"
							+ "tournament_name=" + tournament2export.getTournamentName() + "\n"
							+ "date_made=" + tournament2export.getDateMade() + "\n"
							+ "date_last_edit=" + tournament2export.getDateLastMod() + "\n"
							+ "filePathName=" + tournament2export.getFilePathName() + "\n"
							+ "location=" + tournament2export.getLocation() + "\n"
							+ "HEADER END\n");
			
			//body of tournament - tournament table
			writer.write(     "BODY BEGIN\n"
							+ "\n"
							+ "BODY END");
			
			
			
			
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {writer.close();} catch (Exception ex) {/*ignore*/}
		}

		if(verboseIsOn){
			System.out.println("exported: " + tournament2export);
		}
	
		
	}

	public static Tournament getTournamentByID(int id) {
		for (Tournament t : allTournaments) {
			if (id == t.getId()){
				return t;
			}
		}
		return null;
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
			filename = dbFilePlayer; 
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

	public static void saveAllActualTournamentData() {
		for (Tournament t : allTournaments) {
			export_tournaments_to_file(t.getId());
		}
		exportTournamentOverview();
		
	}

	public static void tournamentOldImportNewExport() {
		importAllTournamentsFromOverview(false);
		for (Tournament t : allTournaments) {
			export_tournaments_to_file2(t.getId());
		}	
		
	}


}
