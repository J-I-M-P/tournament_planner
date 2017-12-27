package datatypes;

import java.util.ArrayList;

import datamanagement.Import_export_datas;

/**
 * @author JIMP
 * 
 * input: table or list of teams before tournament mode
 * output: table/list of teams after tournament mode
 * 
 * mode ID:
 * 
 * 1 - ko
 * 2 - double ko
 * 3 - jeder gegen jeden
 * 4 - 
 *
 * 
 *						 -Tournament-Mode-
 *						------------------- 
 * id  	team-id	/		/				  /		 / sorted list
 * 1  	3		/		/ mode			  /		 /		- groups
 * 2  	4		/ ---> 	/ rounds		  / ---> /
 * 3  	5		/		/ calculations	  /		 /
 * 4  	9		/		/				  /		 /
 *
 */





public class TournamentMode {
	
	//class variables
	
	//tournament
	Tournament tournament4ThisMode;
	//input
	ArrayList<Team> inputTeams = new ArrayList<>();
	
	/**
	 * TODO teams2Use - maybe there will be more teams added during tournament
	 * implemented in tournamentMode_KO
	 */
	ArrayList<Team> teamsInTournament = new ArrayList<>();
	
	//output
	ArrayList<Team> outputTeams = new ArrayList<>();
	
	//mode specs
	int modeID;
	
	//mode calculations
	Mode_Table modeTable;
	

	/**
	 * standard constructor
	 * TODO implement id for mode
	 * @param tournamentID 
	 * 
	 */
	public TournamentMode(int tournamentID){
//		System.out.println("new tournament mode - constructor");
//		System.out.println("input teams: " + inputTeams);
//		System.out.println("output teams: " + outputTeams);
		
		for (Tournament t : Import_export_datas.allTournaments) {
			if(t.getId() == tournamentID){
				tournament4ThisMode = t;
				modeTable = new Mode_Table(tournamentID);
			}
		}
				
				
	}
	
	/**
	 * for every mode the same --> generating table has to has the same output
	 * shows round table
	 * filter table
	 */
	
	
	
	/**
	 * use matchmatrix to generate table
	 */
	protected void generateTable() {
		if(modeTable == null){
			System.err.println("tournamentmode:generateTable:no modetable");
			return;
		}
		System.out.println("table " + modeTable + " exsists");
		
		//add all teams
		for (Team team : teamsInTournament) {
			modeTable.addTeam(team.getTeamId());
			/**
			 * fields in table row
			 * team-name - 	games - w/l -	points -	place 
			 * the team		3		+2 		+22			3
			 *  
			 * TODO push initial values: games = 0; win/lost = 0; little points(points) = 0; place = 0 
			 */
		}
		modeTable.sortBy("byName");
		System.out.println("generated table for mode");
		modeTable.printTable();
	}

	protected void showTable() {
		modeTable.printTable();
	}

	protected void updateTable(){

	}

	protected void generateOutputTeamList(){
		
	}
}
