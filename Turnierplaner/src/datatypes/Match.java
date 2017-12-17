package datatypes;

import java.text.Format;

import Prog1Tools.IOTools;

public class Match {
	
	public enum result{
		WIN_A, DRAWN, WIN_B
	}
	
	private long matchID;
	private Team teamA, teamB, refery;
	private Team winner, looser;
	private int field;
	private int pointsTeamA, pointsTeamB;
	private String score;
	private result matchResult;
	
	private int internalID;
	private static int nextInternalID; 
	
	public Match(){
		this.internalID = nextInternalID++;
	}
	
	public Match(Team teamA, Team teamB){
		this.teamA = teamA;
		this.teamB = teamB;
		this.internalID = nextInternalID++;
	}
	
	

	/**
	 * @return the internalID
	 */
	public int getInternalID() {
		return internalID;
	}

	/**
	 * @return the matchID
	 */
	public long getMatchID() {
		return matchID;
	}

	/**
	 * @param matchID the matchID to set
	 */
	public void setMatchID(long matchID) {
		this.matchID = matchID;
	}

	/**
	 * @return the teamA
	 */
	public Team getTeamA() {
		return teamA;
	}

	/**
	 * @param teamA the teamA to set
	 */
	public void setTeamA(Team teamA) {
		this.teamA = teamA;
	}

	/**
	 * @return the teamB
	 */
	public Team getTeamB() {
		return teamB;
	}

	/**
	 * @param teamB the teamB to set
	 */
	public void setTeamB(Team teamB) {
		this.teamB = teamB;
	}

	/**
	 * @return the refery
	 */
	public Team getRefery() {
		return refery;
	}

	/**
	 * @param refery the refery to set
	 */
	public void setRefery(Team refery) {
		this.refery = refery;
	}

	/**
	 * @return the field
	 */
	public int getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(int field) {
		this.field = field;
	}

	/**
	 * @return the pointsTeamA
	 */
	public int getPointsTeamA() {
		return pointsTeamA;
	}

	/**
	 * @param pointsTeamA the pointsTeamA to set
	 */
	public void setPointsTeamA(int pointsTeamA) {
		this.pointsTeamA = pointsTeamA;
	}

	/**
	 * @return the pointsTeamB
	 */
	public int getPointsTeamB() {
		return pointsTeamB;
	}

	/**
	 * @param pointsTeamB the pointsTeamB to set
	 */
	public void setPointsTeamB(int pointsTeamB) {
		this.pointsTeamB = pointsTeamB;
	}

	/**
	 * @return the winner
	 */
	public Team getWinner() {
		return winner;
	}

	/**
	 * @return the looser
	 */
	public Team getLooser() {
		return looser;
	}

	/**
	 * @return the matchResult
	 */
	public result getMatchResult() {
		return matchResult;
	}
	
	
	
	/**
	 * @return the score
	 */
	public String getScore() {
		return score;
	}

	
	
	/**
	 * TODO maybe create a function to randomly fill the match results
	 */
	/**
	 *
	 * generates winner, looser, result, 
	 *
	 * 
	 * @param mode 1-by score / 2-random mode / 3-by lottery 
	 */
	public void progress(int mode){
		
		if(teamA == null || teamB == null){
			System.out.println("match/progress()/unzureichende eingaben - keine berechnung möglich - teamA oder teamB = null");
			if(teamA == null){
				System.out.println("teamA == null");
			}
			if (teamB == null){
				System.out.println("teamB == null");
			}
			return;
		}
		
		
		//determine result - depending on mode
		switch (mode) {
		case 1:  //by points
			if (pointsTeamA == pointsTeamB){
				/**
				 * TODO maybe ask user / at beginning in tournament options
				 */
				this.progress(2);
			}else {
				//if no ko
//				matchResult = pointsTeamA>pointsTeamB?result.WIN_A:(pointsTeamA<pointsTeamB?result.WIN_B:result.DRAWN);
				matchResult = pointsTeamA>pointsTeamB?result.WIN_A:result.WIN_B;
			}
			
			break;
		case 2: //random - no drawn
			if(Math.random() < 0.5){
				matchResult = result.WIN_A;
			} else {
				matchResult = result.WIN_B;
			}
			break;
		case 3:
			char in = IOTools.readChar(this + " - Gewinner (a/b)?_\n");
			matchResult = in =='a'?result.WIN_A:result.WIN_B;
			break;
		default:
			break;
		}
		
		//when WILDCARDs are present
		if(teamA.isWILDCARD()){
			matchResult=result.WIN_B;
		}else if (teamB.isWILDCARD()){
			matchResult=result.WIN_A;
		}
		
		//set winner and looser team
		switch (matchResult) {
		case WIN_A:
			winner = teamA;
			looser = teamB;
			break;
		case WIN_B:
			winner = teamB;
			looser = teamA;
			break;
		case DRAWN:
			//K.O. ==> no draw!
			break;
		default:
			break;
		}
		
		//determine score
		score = String.format("(%02d - %02d)", this.pointsTeamA,this.pointsTeamB);
	
		
	}
	
	public void progress(){
		this.progress(1);	
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//generate String
		String out = String.format("%03d(%03d) %s[%15s]A %s B[%-20s]\n", this.matchID,this.internalID,this.matchResult,this.teamA.getTeamName(),this.score,this.teamB.getTeamName());
		return  out;
	}
	
	

}
