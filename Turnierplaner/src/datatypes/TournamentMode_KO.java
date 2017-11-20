package datatypes;

import java.util.ArrayList;

public class TournamentMode_KO extends TournamentMode {

		//super class variables
		//input
		//ArrayList<Team> inputTeams = new ArrayList<>();
		
		
		//output
		//ArrayList<Team> outputTeams = new ArrayList<>();
		
	/*
	 * mode specs
	 * int modeID
	 * 
	 * take list of players w/o sorting
	 * sort list by various sorting specs
	 */
	
	/**
	 * local variables
	 */
	ArrayList<Team> firstHalf = new ArrayList<>();
	ArrayList<Team> secondHalf = new ArrayList<>();
		
	
	/**
	 * standard constructor
	 */
	public TournamentMode_KO(){
		System.out.println("new tournament mode ko - constructor");
	}
	
	public TournamentMode_KO(ArrayList<Team> inputTeams){
		this.inputTeams = inputTeams;
		
	}
	
	public ArrayList<Team> returnTeamList(){
		return this.outputTeams;
	}
	
	
	public void generateModePlan(){
		int middle = inputTeams.size()/2;
		System.out.println("input teams size: " + inputTeams.size());
		System.out.println("middle: " + middle);
		int counter = 0;
		for (Team team : inputTeams) {
			if (counter <= middle){
				firstHalf.add(team);
			} else {
				secondHalf.add(team);
			}
		}
		
		teams2Lines();
	}
	
	public void teams2Lines(){
		System.out.println("first half:");
		for (Team team : firstHalf) {
			System.out.println(team.getTeamName());
		}
		System.out.println("second half:");
		for (Team team : secondHalf) {
			System.out.println(team.getTeamName());
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String out = "TournamentMode_KO [\n";
		for (Team team : inputTeams) {
			out += team.getTeamName() + "\n";
		}
		out += "]\n";
		return  out;
	}
	
	
	
}
