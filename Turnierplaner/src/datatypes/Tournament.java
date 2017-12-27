package datatypes;

import java.util.Arrays;
import java.util.GregorianCalendar;

import Prog1Tools.IOTools;
import datamanagement.Import_export_datas;
import userinterface.TournamentIOInterface;

public class Tournament {
	
	private int id;
	private static int nextID = 0;
	private String tournamentName, location, filePathName;
	private long dateMade, dateLastMod;
	private GregorianCalendar timeStampMade, timeStampLastModified;
	private Team[] teams;
	
	public Tournament(){
		
	}
	
	public Tournament(String name){
		this.tournamentName = name;
		this.timeStampMade = new GregorianCalendar();
	}
	
	

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	

	/**
	 * @return the nextID
	 */
	public static int getNextID() {
		return nextID;
	}

	/**
	 * @param nextID the nextID to set
	 */
	public static void setNextID(int nextID) {
		Tournament.nextID = nextID;
	}

	/**
	 * @return the tournamentName
	 */
	public String getTournamentName() {
		return tournamentName;
	}

	/**
	 * @param tournamentName the tournamentName to set
	 */
	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}
	
	

	/**
	 * @return the filePathName
	 */
	public String getFilePathName() {
		return filePathName;
	}

	/**
	 * @param filePathName the filePathName to set
	 */
	public void setFilePathName(String filePathName) {
		this.filePathName = filePathName;
	}

	/**
	 * @return the dateMade
	 */
	public long getDateMade() {
		return dateMade;
	}

	/**
	 * @param dateMade the dateMade to set
	 */
	public void setDateMade(long dateMade) {
		this.dateMade = dateMade;
	}

	/**
	 * @return the dateLastMod
	 */
	public long getDateLastMod() {
		return dateLastMod;
	}

	/**
	 * @param dateLastMod the dateLastMod to set
	 */
	public void setDateLastMod(long dateLastMod) {
		this.dateLastMod = dateLastMod;
	}
	
	

	/**
	 * @return the timeStampMade
	 */
	public GregorianCalendar getTimeStampMade() {
		return timeStampMade;
	}

	/**
	 * @return the location
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * @param location the location to set
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * @return the teams
	 */
	public Team[] getTeams() {
		return teams;
	}

	/**
	 * @param teams the teams to set
	 */
	public void setTeams(Team[] teams) {
		this.teams = teams;
	}



	public static void editTournament() {
		System.out.println("turnier editieren");
		System.out.println("-----------------");
		TournamentIOInterface.showAllTournaments();
		Tournament editT = Import_export_datas.getTournamentByID(IOTools.readInt("id: "));

		String filename = IOTools.readLine("filename (" + editT.getFilePathName() + "): ");
		if (filename.equals("")){
			editT.setFilePathName(editT.getFilePathName());
		}else{
			editT.setFilePathName(filename);
		}
		System.out.println("set filename to: " + editT.getFilePathName());
		
		
		editT.setTournamentName(IOTools.readString("name (" + editT.getTournamentName() + "): "));
		
		editT.setLocation(IOTools.readString("location (" + editT.getLocation() + "): "));
		
		editT.setDateMade(IOTools.readLong("date made (" + editT.getDateMade() + "): "));
		/**
		 * TODO
		 * later automatically
		 */
		editT.setDateLastMod(IOTools.readLong("date last mod (" + editT.getDateLastMod() + "): "));
		
		/**
		 * TODO
		 * teams
		 */
//		private Team[] teams;
		
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tournament [id=" + id + ", tournamentName=" + tournamentName + ", location=" + location
				+ ", filePathName=" + filePathName + ", dateMade=" + dateMade + ", dateLastMod=" + dateLastMod
				+ ", teams=" + Arrays.toString(teams) + "]";
	}
	
	

//	Time 
	
//	SimpleDateFormat formatter = new SimpleDateFormat ("dd.MM.yyyy, HH:mm:ss ");
//	Date currentTime = new Date();
//	out.println("Zeit und Datum : " + formatter.format(currentTime));
	
	

}
