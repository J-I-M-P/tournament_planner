package datatypes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import datamanagement.Import_export_datas;

public class Team {
	
	private int teamId;
	private String teamName;
	private ArrayList<Player> playersInTeam = new ArrayList<>();
	private boolean isWILDCARD = false;

	static int nextID = 0;

	public static void setNextId(int id){
		nextID = id;
	}

	/**
	 * standard constructor
	 */
	public Team() {
//		System.out.println("team:standardconstructor");
//		this.teamId = 0;
//		this.teamName = null;
//		this.playersInTeam = null;
	}

	/**
	 * @param teamName
	 * @param addPlayersInConstrucor
	 */
	public Team(String teamName, ArrayList<Player> addPlayersInConstrucor) {
		this.teamName = teamName;
		this.playersInTeam.addAll(addPlayersInConstrucor);
		this.teamId = nextID++;
	}
	
	public Team (String teamName){
//		System.out.println("created team by name");
//		System.out.println("team name: "+teamName);
		this.teamName = teamName;
		if(teamName.equals("WILDCARD")){
			this.isWILDCARD = true;
		}
		this.playersInTeam = null;
		this.teamId = nextID++;
//		this(teamName, null);
		
	}


	

	
	


	/**
	 * @return the isWILDCARD
	 */
	public boolean isWILDCARD() {
		return isWILDCARD;
	}

	/**
	 * @param isWILDCARD the isWILDCARD to set
	 */
	public void setWILDCARD(boolean isWILDCARD) {
		this.isWILDCARD = isWILDCARD;
	}

	/**
	 * @return the playersInTeam
	 */
	public ArrayList<Player> getPlayersInTeam() {
		return playersInTeam;
	}



	/**
	 * @param playersInTeam the playersInTeam to set
	 */
	public void setPlayersInTeam(ArrayList<Player> setPlayersInTeam) {
		playersInTeam.addAll(setPlayersInTeam);
	}

	public void addPlayerByID(int id){
		for (Player p : Import_export_datas.allPlayers) {
			if ((""+p.getPlayerId()).equals(id)){
				playersInTeam.add(p);
			}
		}
	}

	/**
	 * @return the teamId
	 */
	public int getTeamId() {
		return teamId;
	}



	/**
	 * @return the teamName
	 */
	public String getTeamName() {
		return teamName;
	}



	/**
	 * @param teamName the teamName to set
	 */
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}



	public void setTeamId(int teamID) {
		this.teamId=teamID;
	}

	/**
	 * TODO
	 * complete and use
	 */
	public void generateRandomTeam(){
		
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String playersInTeamString ="";
		if((playersInTeam == null) || playersInTeam.isEmpty()){
			
		}else {
			for (Player p : playersInTeam) {
				playersInTeamString += p.toString();
			}
		}
		
		return "Team [ID: " + teamId + " - " + teamName + " - " + playersInTeamString + "]";
	}
}
