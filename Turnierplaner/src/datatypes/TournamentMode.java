package datatypes;

import java.util.ArrayList;

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
 * 3 - 
 *
 * 
 * 
 * id  	team-id	/		/				/		/ sorted list
 * 1  	3		/		/ mode			/		/		- groups
 * 2  	4		/ ---> 	/ rounds		/ --->	/
 * 3  	5		/		/ calculations	/		/
 * 4  	9		/		/				/		/
 *
 */





public class TournamentMode {
	
	//class variables
	//input
	ArrayList<Team> inputTeams = new ArrayList<>();
	
	
	//output
	ArrayList<Team> outputTeams = new ArrayList<>();
	
	//mode specs
	int modeID;
	

	public TournamentMode(){
//		System.out.println("new tournament mode - constructor");
//		System.out.println("input teams: " + inputTeams);
//		System.out.println("output teams: " + outputTeams);
	}
}
