package datatypes;

public class Match {
	
	public enum result{
		WIN_A, DRAW, WIN_B
	}
	
	private long matchID;
	private Team teamA, teamB, refery;
	private Team winner, looser;
	private int field;
	private int pointsTeamA, pointsTeamB;
	private result matchResult;
	
	public Match(){
		
	}
	
	public Match(Team teamA, Team teamB){
		this.teamA = teamA;
		this.teamB = teamB;
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
	 * generates winner, looser, result, 
	 */
	public void progress(){
		if(teamA == null | teamB == null){
			System.out.println("unzureichende eingaben - keine berechnung möglich");
			return;
		}
		matchResult = pointsTeamA>pointsTeamB?result.WIN_A:(pointsTeamA<pointsTeamB?result.WIN_B:result.DRAW);
		switch (matchResult) {
		case WIN_A:
			winner = teamA;
			looser = teamB;
			break;
		case WIN_B:
			winner = teamB;
			looser = teamA;
			break;
		case DRAW:
			
			break;
		default:
			break;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		//generate String
		String out = "Match[ID=" + matchID + "-";
		if (teamA != null){
			out += teamA.getTeamName(); 
		}else{
			out += "NN";
		}
		out += "<>";
		if (teamB != null){
			out += teamB.getTeamName(); 
		}else{
			out += "NN";
		}
		out += "]";
		return  out;
	}
	
	

}
