package datatypes;

import java.util.ArrayList;

import javax.rmi.ssl.SslRMIClientSocketFactory;

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
	ArrayList<Team> firstRoundInputTeams = new ArrayList<>();
	Match[][] matchMatrix;
	int teamsInFirstRound;
	int amountOfMatchesInFirstRound;
		
	
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
		
		/**
		 * - split in halfs and match
		 * 		{1,2,3,4,5,6} {7,8,9,10,11,12} 
		 * 		-> [1,7] [2,8] ...
		 * 		-> [1,12] [2,11 ...
		 * - match by rules
		 * 		{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15}
		 * 		-> [1,7] [2,8] ...
		 * 		-> [1,12] [2,11] ...
		 *-------> rule 4 ko (I .. iterations 4 planning | X .. wildcard if in last iteration)
		 *			game	1 		2		3		4		5		6		7		8
		 *			I		[1,X]	[2,X]	[3,X]	[4,X]	[5,X]	[6,X]	[7,X]	[8,X]
		 *			II		[1,9]	[2,X]	[3,10]	[4,X]	[5,11]	[6,X]	[7,12]	[8,X]
		 *	r1		III		[1,9]	[2,13]	[3,10]	[4,14]	[5,11]	[6,15]	[7,12]	[8,X]
		 *						|	|			 |	|			 |	|			 |	|
		 *						\	/			 \	/			 \	/			 \	/
		 *						  V				   V			   V			   V
		 *	r2					[1,13]			[10,4]			 [5,6]			[12,8]
		 *							\			 /					\			 /
		 *	r3							[1,4]							 [6,12]
		 *									\							 /
		 *	r4 = final								   [1,6]
		 *			-> games for ko per round
		 *				n	teams				games in first round
		 *				1	2-4					4
		 *				2	5-8					8
		 *				n	2^n+1 - 2^(n+1)		2^(n+1)
		 *			
		 *
		 */
//		System.out.println("[start generatemodeplan]input teams size: " + inputTeams.size());
		//amount of teams 4 ko planning
		int teamAmount = inputTeams.size();
		
//		int teamAmount = 17;
		
		if(teamAmount <2){
			System.out.println("to less teams 4 tournament");
			return;
		}
		int rounds = 1;
		int twoEx2 = 1;
		int twoEx2p1 = 2;
		while(!((teamAmount >= twoEx2)&&(teamAmount < twoEx2p1))){
			twoEx2 *= 2;
			twoEx2p1 = twoEx2*2;
			rounds++;
		}
		teamsInFirstRound = twoEx2p1;
		amountOfMatchesInFirstRound = twoEx2p1/2;
		
		//set size of team filling list and fill with input teams
		
//		System.out.println("teams: " + teamAmount + " - games: " + amountOfGamesInFirstRound + " - rounds: " + rounds);
		
		/**
		 * match[rounds][game in first round]
		 * input teams: 12
		 * -->
		 * 	rounds: 4
		 * 	games in first round: 8 (16 teams --> 4 joker)
		 *        rounds 	 games in fst rd
		 * 	match[4]   		[16]
		 * 	match[0..3]		[0..15]
		 */
		System.out.println("generate match matrix - teams:" + inputTeams.size() + "rounds:" + rounds + "MifR:" + amountOfMatchesInFirstRound);
		matchMatrix = new Match[rounds][amountOfMatchesInFirstRound];
		int matchPerRoundCounter = amountOfMatchesInFirstRound;
		for(int r=0; r < rounds; r++){
//			System.out.println("[generate matches]round: " + (r+1));
			for(int m=0;m<matchPerRoundCounter;m++){
				//generate match
				matchMatrix[r][m] = new Match();
				//set matchID
				matchMatrix[r][m].setMatchID((r+1)*100+(m+1));
			}
			matchPerRoundCounter /= 2;
		}
		
		//input player
		fillMatchMatrix();
		
		/**
		 * TODO delete
		 * test output match matrix
		 */
		matchPerRoundCounter = amountOfMatchesInFirstRound;
		for(int r=0; r < rounds; r++){
			for(int m=0;m<matchPerRoundCounter;m++){
				System.out.print(matchMatrix[r][m]);
				if(m<(matchPerRoundCounter-1)){
					System.out.print("_");
				}
			}
			System.out.println();
			matchPerRoundCounter /= 2;
		}
		
		
		/*old
		System.out.println("middle: " + middle);
		int counter = 0;
		for (Team team : inputTeams) {
			System.out.println("c: " + counter + " - m: " + middle);
			if (counter < middle){
				System.out.println("-->fisrst");
				firstHalf.add(team);
			} else {
				System.out.println("-->second");
				secondHalf.add(team);
			}
			counter++; 
		}
		teams2Lines();
		end old*/
	}
	
	private void fillMatchMatrix(){
//		System.out.println("matchmatrix[0].length: " + matchMatrix[0].length);
//		System.out.println("inputteams.size: " + inputTeams.size());
		/**
		 * TODO
		 * rework match test
		 */
//		if(matchMatrix[0].length != inputTeams.size()){
//			System.out.println("input teams size - teams first round mismatch");
//			return;
//		}
		
		/**
		 * 0			1		2		3		4		5		6		7
		 * 0 	1		2 3		4 5		...
		 * 2*n	2*n+1
		 */
		
		for(int i = 0; i<matchMatrix[0].length;i++){
			if (inputTeams.get(2*i) != null){
				matchMatrix[0][i].setTeamA(inputTeams.get(2*i));
			}else{
				matchMatrix[0][i].setTeamA(new Team("WILDCARD"));
			}
			if(i<(matchMatrix[0].length-1)){
				if (inputTeams.get(2*i+1) != null){
					matchMatrix[0][i+1].setTeamB(inputTeams.get(2*i+1));
				}else{
					matchMatrix[0][i+1].setTeamB(new Team("WILDCARD"));
				}
			}
		}
	}
	
	public static void teamAmounts2games(){
		for(int teamAmount = 2;teamAmount < 50;teamAmount++ ){
			int twoEx2 = 1;
			int twoEx2p1 = 2;
			int rounds = 1;
			while(!((teamAmount > twoEx2)&&(teamAmount <= twoEx2p1))){
				twoEx2 *= 2;
				twoEx2p1 = twoEx2*2;
				rounds++;
			}
			int amountOfGamesInFirstRound = twoEx2p1/2;
			System.out.println("teams: " + teamAmount + " - games: " + amountOfGamesInFirstRound + " - rounds: " + rounds);
		}
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
