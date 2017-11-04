package datatypes;

import java.util.Arrays;

public class Tournament {
	
	private int id;
	private static int nextID = 0;
	private String tournamentName, location, filePathName;
	private long dateMade, dateLastMod;
	private Team[] teams;
	
	public Tournament(){
		
	}
	
	public Tournament(String name){
		this.tournamentName = name;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Tournament [id=" + id + ", tournamentName=" + tournamentName + ", location=" + location + ", dateMade="
				+ dateMade + ", dateLastMod=" + dateLastMod + ", teams=" + Arrays.toString(teams) + "]";
	}

	
	
	

}
