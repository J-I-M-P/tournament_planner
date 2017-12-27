package obsolet;

import java.io.*;
import java.util.ArrayList;

import Prog1Tools.IOTools;
import datatypes.Player;
//import userinterface.ClientInterface;

public class DataIO {
	
	
	
	static ArrayList<Player> allPlayers = new ArrayList<Player>();
	
	
	
	public static void addPlayer2List(Player player){
		allPlayers.add(player);
	}

	


	/**
	 * read data for 
	 * 		tournament
	 * 		player
	 * 
	 * from files
	 * 
	 * 
	 */
	public static void readPlayerDbFromFile(String filename) {
		
		
		File databaseFile = new File(filename);
		int setId = 0;
		if(databaseFile.exists()){

			try(BufferedReader r = new BufferedReader(new FileReader(databaseFile))){
				String line;
				while((line = r.readLine()) != null){
					String[] fields = line.split(";");
					//check if syntax fits
					if(fields.length < 3){
						continue;
					}
					// create player (if possible)
					
//					System.out.println(fields[0] +" - "+ fields[1] +" - "+ fields[2]);
					Player addP = new Player(fields[2]);
					addP.setID(Integer.parseInt(fields[0]));
					addP.setSurename(fields[1]);
					addPlayer2List(addP);
					setId=addP.getPlayerId()+1;
				}
				Player.setNextId(setId);
			} catch (Exception ignored) {}

		}
	}
	
	public static void writeToFile(String whatToWrite){
//		String filename = "";
//		switch (whatToWrite) {
//		case "player":
//			filename = ClientInterface.getDbFilePlayer(); 
//			break;
//		}
//		Writer writer = null;
//
//		try {
//			writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename), "utf-8"));
//			switch (whatToWrite) {
//			case "player":
//				for (Player player : allPlayers) {
//					writer.write(player.getPlayerId() + ";" + player.getSurename() + ";" + player.getFirstName() + "\n");
//				}
//				break;
//			}
////			writer.write("Something");
//		} catch (IOException ex) {
//			// report
//		} finally {
//			try {writer.close();} catch (Exception ex) {/*ignore*/}
//		}
	}

}
