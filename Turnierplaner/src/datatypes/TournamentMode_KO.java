package datatypes;

import java.util.ArrayList;

import javax.rmi.ssl.SslRMIClientSocketFactory;

import Prog1Tools.IOTools;
import userinterface.MainInterface;


/**
 * 
 * KO(easy) mode for tournament 
 * 
 * wishlist
 * --------
 * 
 * tourMode1_KO.prepare(teamlist/table)
 * 		- generateModePlan
 * 			- push teamlist in matches of first round
 * 				- as they come
 * 				- random
 * 				- by lottery ticket
 * 				- handicap
 * 				- age
 * 				- etc
 * 		- generateRounds (maybe just take matchmatrix[x][] as rounds. progress from x to x+1 ... )
 * 		- generateModeInfos4Teams
 * 				- lists where to play when vs. whom
 * 					- time table
 * 					- field list
 * 					- etc
 * tourModel1_KO.startMode()
 * 		- processRounds()
 * 			- iterate rounds
 * 				- generate/show 
 * 					- matches
 * 					- time table
 * 					- field list per round
 * 					- call for teams
 * 					 etc
 * 			- calculate 
 * 				- live table
 * 				-
 * tourMode1_KO.closeUp()
 * 		- generate 
 * 			- tables
 * 			- statistics
 * 			- team list for possible following rounds (some teams are out after 1 mode)
 * 
 *  
 * 
 */
public class TournamentMode_KO extends TournamentMode {

		//super class variables
		//input list before mode = old table
		//ArrayList<Team> inputTeams = new ArrayList<>();
		
		//output list after mode = new table
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
//	Mode_Table 
	int teamsInFirstRound;
	int amountOfMatchesInFirstRound;
	int amountOfMatchesInActualRound;
	int modeRounds;
	int actualRound;
		
	
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
	
	/**
	 * tourMode1_KO.prepare(teamlist/table)
	 * 		- generateModePlan
	 * 		- generateRounds
	 * 		- generateModeInfos4Teams
	 * 				- lists where to play when vs. whom
	 * 					- time table
	 * 					- field list
	 * 					- etc
	 */
	public void prepare(){  //(teamlist/table)
		//generateModePlan
		this.generateModePlan();
		System.out.println("prepared mode plan");
		//generateRounds
		this.generateRounds();
		System.out.println("prepared rounds");
		// 		- generateModeInfos4Teams
		
		// 				- lists where to play when vs. whom
		// 					- time table
		// 					- field list
		
		if(IOTools.readInt("start mode? ") == 1 ){
			this.startMode();
		}else{
			return;
		}
	}
	
	
	

	/**
	 * tourModel1_KO.startMode()
	 * 		- processRounds()
	 * 			- iterate rounds
	 * 				- generate/show 
	 * 					- matches
	 * 					- time table
	 * 					- field list per round
	 * 					- call for teams
	 * 					 etc
	 * 			- calculate 
	 * 				- live table
	 * 				-
	 */
	public void startMode(){
		System.out.println("start " + actualRound+1 + " round of actual tournament mode");
		System.out.println("display what to play..infos...etc");
		System.out.println("wait for game input");
		this.showRoundDialogue();
		
		
	}
	
	

	/**
	 * tourMode1_KO.closeUp()
	 * 		- generate 
	 * 			- tables
	 * 			- statistics
	 * 			- team list for possible following rounds (some teams are out after 1 mode)
	 */
	public void closeMode(){
		
	}
	
	private void generateModePlan(){
		
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
		int roundsInMode = 1;
		int twoEx2 = 1;
		int twoEx2p1 = 2;
		while(!((teamAmount >= twoEx2)&&(teamAmount < twoEx2p1))){
			twoEx2 *= 2;
			twoEx2p1 = twoEx2*2;
			roundsInMode++;
		}
		teamsInFirstRound = twoEx2p1;
		amountOfMatchesInFirstRound = twoEx2p1/2;
		amountOfMatchesInActualRound = amountOfMatchesInFirstRound;
		modeRounds = roundsInMode;
		actualRound = 0;
		
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
//		System.out.println("generate match matrix - teams:" + inputTeams.size() + "rounds:" + rounds + "MifR:" + amountOfMatchesInFirstRound);
		matchMatrix = new Match[roundsInMode][amountOfMatchesInFirstRound];
		int matchPerRoundCounter = amountOfMatchesInFirstRound;
		for(int r=0; r < roundsInMode; r++){
//			System.out.println("[generate matches]round: " + (r+1));
			for(int m=0; m < matchPerRoundCounter;m++){
				//generate match
				matchMatrix[r][m] = new Match();
				//set matchID
				matchMatrix[r][m].setMatchID((r+1)*100+(m+1));
			}
			
//			System.out.println("round: " + r + "matchPerRoundCounter before next round: " + matchPerRoundCounter);
			matchPerRoundCounter /= 2; //8..4..2..1
//			System.out.println("matchPerRoundCounter after next round: " + matchPerRoundCounter + "\n");
		}
		
		//input player
		fillMatchMatrix1stRound();
		
		/**
		 * TODO delete
		 * test output match matrix
		 */
//		matchPerRoundCounter = amountOfMatchesInFirstRound;
//		for(int r=0; r < roundsInMode; r++){
//			for(int m=0;m<matchPerRoundCounter;m++){
//				if(matchMatrix[r][m] != null){
//					System.out.print(matchMatrix[r][m]);	
//				}
//			}
//			matchPerRoundCounter /= 2;
//		}
		
		
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
	
	private void fillMatchMatrix1stRound(){
//		System.out.println("matchmatrix[0].length: " + matchMatrix[0].length);
//		System.out.println("inputteams.size: " + inputTeams.size());
		/**
		 * TODO
		 * rework match test - if necessary
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
//		System.out.println("matrix start");
//		System.out.print("teams: ");
//		for (Team team : inputTeams) {
//			System.out.print(team.getTeamName() + " - ");
//		}
//		System.out.println();
		
		
		//iteration 0
		System.out.println("iteration 0 - prepare for insertion");
//		System.out.print("matches: ");
//		for (Match match : matchMatrix[0]) {
//			System.out.print(match + " - ");
//		}
//		System.out.println();
		
		//iteration 1 - teams A
		int teamCounter = 0;								//start use first team in inputTeam list
		for (Match match : matchMatrix[0]) {
			if (teamCounter < inputTeams.size()){
				match.setTeamA(inputTeams.get(teamCounter));
				teamCounter++;
			}else{
				match.setTeamA(new Team("WILDCARD"));
			}
			
		}
		System.out.println("iteration 1 - Teams A");
//		System.out.print("matches: ");
//		for (Match match : matchMatrix[0]) {
//			System.out.print(match + " - ");
//		}
//		System.out.println();
//		System.out.println("rest teams - teamcounter:" + teamCounter + " - teamsize:" + inputTeams.size());
//		for (int i = teamCounter;i < inputTeams.size();i++){
//			System.out.print(inputTeams.get(i) + " - ");
//		}
//		System.out.println();
		

		//iteration 2  - every second team B
		int skipper = 0;								//don't reset teamCounter / skipper to skip every 2nd match
		for (Match match : matchMatrix[0]) {
			if (teamCounter < inputTeams.size()){
				if(skipper == 0){
					match.setTeamB(inputTeams.get(teamCounter));
					teamCounter++;
					skipper++;
				} else if ( skipper ==1){
					skipper = 0;
					continue;
				}
				
			}else{
				if(match.getTeamB() == null){
					match.setTeamB(new Team("WILDCARD"));
				}
				
			}
			
		}
		System.out.println("iteration 2 - every 2nd Team B");
//		System.out.print("matches: ");
//		for (Match match : matchMatrix[0]) {
//			System.out.print(match + " - ");
//		}
//		System.out.println();
//		System.out.println("rest teams");
//		if(teamCounter >= inputTeams.size()){
//			System.out.print("no more teams");
//		}
//		for (int i = teamCounter;i<inputTeams.size();i++){
//			System.out.print(inputTeams.get(i) + " - ");
//		}
//		System.out.println();
		
		//iteration 3 - fill gaps
		for (Match match : matchMatrix[0]) {
			if (teamCounter < inputTeams.size()){					//there are more teams in list AND
				if(match.getTeamB() == null){						//team is not set
					match.setTeamB(inputTeams.get(teamCounter));
					teamCounter++;
				}
			}else{													//there are NO more teams in list AND
				if(match.getTeamB() == null){						//team is not set
					match.setTeamB(new Team("WILDCARD"));
				}
			}														//skip if team is set
		}
		System.out.println("iteration 3 - filled gaps");
//		System.out.print("matches: ");
//		for (Match match : matchMatrix[0]) {
//			System.out.print(match + " - ");
//		}
//		System.out.println();
	}
	
	/**
	 * generates rounds from matchmatrix
	 * status: not used
	 */
	private void generateRounds() {
		System.out.println("prepare rounds");
		System.out.println("rounds: " + modeRounds);
		for (int i=0; i<modeRounds;i++){
			
		}
		
	}
	
	private void jump2NextRound(){
		System.out.println("next round abfrage\n Runde abschliessen ...");
		this.finalizeActualRound();
		System.out.println("von runde: " + ((actualRound++)+1) + " zu runde: " + (actualRound+1));
		/**
		 *generate new matches
		 *					0		1		2		3		4		5		6		7
		 *					101 	102		103		104		105		106		107		108
		 * 	r1		III		[1,9]	[2,13]	[3,10]	[4,14]	[5,11]	[6,15]	[7,12]	[8,X]
		 *						|	|			 |	|			 |	|			 |	|
		 *						\	/			 \	/			 \	/			 \	/
		 *						  V				   V			   V			   V
		 *matchmatrix[r][x]		0				1				 2				3
		 *						201				202				 203			204
		 *	r2					[1,13]			[10,4]			 [5,6]			[12,8]
		 *							\			 /					\			 /
		 *								0								 1
		 *								301								 302
		 *	r3							[1,4]							 [6,12]
		 *									\							 /
		 *									  ----------V----------------
		 *											   401
		 *	r4 = final								   [1,6]
		 */
		//take first 2 matches and put winners in next match
		//if one team is WILDCARD => other team is winner
		System.out.println("prepare next matches");
		//set new amount of matches this round (actual amount /2)
		amountOfMatchesInActualRound /= 2;
		this.generateMatches4Round(actualRound);
		
	}
	
	private void generateMatches4Round(int generate4Round) {
		if(generate4Round <= 0){
			System.out.println("nicht in erster runde / rundenzaehler fehler");
			return;
		}
		
		System.out.println("prepare for round:" + (actualRound+1) + " actual matches: " + amountOfMatchesInActualRound);
		
		for(int i=0; i < amountOfMatchesInActualRound;i++){
			/**
			 * 	
			 *  
			 * end of array != 8!!
			 */
			matchMatrix[generate4Round][i].setTeamA(matchMatrix[generate4Round-1][i*2].getWinner());
			matchMatrix[generate4Round][i].setTeamB(matchMatrix[generate4Round-1][i*2+1].getWinner());
		}
		
	}

	private void showRoundDialogue() {
		while(true){
			String roundMenue =  "-----Round IO---------\n"
									+ "\n"
					  				+ "1 - aktuelle runden info anzeigen\n"
									+ "2 - live runde\n"
					  				+ "3 - ergebnis eingabe\n"
									+ "4 - runde abschließen\n"
					  				+ "5 - nächste runde\n"
					  				+ "6 - live tabelle\n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(roundMenue);
			switch (IOTools.readInt()) {
			case 1:
				this.showActualRoundInfo();
				break;
			case 2:
				this.updateMatchesInActualRound();
				this.showActualRoundInfo();
				break;
			case 3:
				this.scoreInput();
				break;
			case 4:
				this.finalizeActualRound();
				break;
			case 5:
				this.jump2NextRound();
				break;
			case 6:
				this.generateTable();
				this.showTable();
				break;
			case 99:
				MainInterface.quitWOSave();
				break;
			case 0:
				return;
			}
		}
	}
	
	
	
	/* (non-Javadoc)
	 * @see datatypes.TournamentMode#generateTable()
	 */
	@Override
	protected void generateTable() {
		super.generateTable();
		
		/**
		 * fields in table
		 * team-name - 	games - w/l -	points -	place 
		 * the team		3		+2 		+22			3
		 */
		
		/**
		 * TODO datatype mode-table
		 */
		
	}

	private void scoreInput() {
		for (Match m1 : matchMatrix[actualRound]) {
			if(m1 != null){
				System.out.print(m1);
			}
		}
		int m_in = IOTools.readInt("match? (xxx / 101)");
		boolean noMatch = true;
		for (Match m2 : matchMatrix[actualRound]) {
			if(m2 != null){
				if (m2.getMatchID() == m_in){
					m2.setPointsTeamA(IOTools.readInt(m2.getTeamA().getTeamName() + " Points: "));
					m2.setPointsTeamB(IOTools.readInt(m2.getTeamB().getTeamName() + " Points: "));
					noMatch = false;
				}
			}
		}
		if(noMatch){
			System.out.println("no such match");
		}
	}

	private void updateMatchesInActualRound() {
		for (Match m : matchMatrix[actualRound]) {
			if(m != null){
				m.progress();
			}
		}
	}

	private void finalizeActualRound() {
		//progress all matches in actual round
		for (Match m : matchMatrix[actualRound]) {
			if(m != null){
				m.progress();
				System.out.print("match: " + m.getMatchID() + " winner: ");
				if(m.getWinner() == null){
					System.out.println("NO WINNER");
				} else {
					System.out.println(m.getWinner().getTeamName());
				}
			}
		}
		
	}

	private void showActualRoundInfo() {

		//		System.out.println("ID\tteamA\t\tteamB");
		//		System.out.println("aktuelle runde: " + actualRound);
		long mNumber;
		for (Match m : matchMatrix[actualRound]) {
			if(m != null){
//				m.progress();
				//			System.out.println(m.getMatchID() + "\t" + m.getTeamA().getTeamName() + "\t\t" + m.getTeamB().getTeamName());
				mNumber = m.getMatchID() - (((long)m.getMatchID()/100)*100);
//				System.out.printf("R%d-M%d (%03d) %s[%12s]A %s B[%s]\n", actualRound+1,mNumber,m.getInternalID(),m.getMatchResult(),m.getTeamA().getTeamName(),m.getScore(),m.getTeamB().getTeamName());
				System.out.printf("R%d-M%d: ", actualRound+1,mNumber);
				System.out.print(m);
			}
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
	

	
	/**
	 * OBSOLET
	 */
	
	
//	private static void teamAmounts2games(){
//		for(int teamAmount = 2;teamAmount < 50;teamAmount++ ){
//			int twoEx2 = 1;
//			int twoEx2p1 = 2;
//			int rounds = 1;
//			while(!((teamAmount > twoEx2)&&(teamAmount <= twoEx2p1))){
//				twoEx2 *= 2;
//				twoEx2p1 = twoEx2*2;
//				rounds++;
//			}
//			int amountOfGamesInFirstRound = twoEx2p1/2;
//			System.out.println("teams: " + teamAmount + " - games: " + amountOfGamesInFirstRound + " - rounds: " + rounds);
//		}
//	}
	
//	public void teams2Lines(){
//		System.out.println("first half:");
//		for (Team team : firstHalf) {
//			System.out.println(team.getTeamName());
//		}
//		System.out.println("second half:");
//		for (Team team : secondHalf) {
//			System.out.println(team.getTeamName());
//		}
//	}
	
}
