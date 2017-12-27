package datatypes;

public class gameRound {
	
	int roundID;
	TournamentMode belongs2TournamentMode;
	/**
	 * @return the roundID
	 */
	public int getRoundID() {
		return roundID;
	}
	/**
	 * @param roundID the roundID to set
	 */
	public void setRoundID(int roundID) {
		this.roundID = roundID;
	}
	/**
	 * @return the belongs2TournamentMode
	 */
	public TournamentMode getBelongs2TournamentMode() {
		return belongs2TournamentMode;
	}
	/**
	 * @param belongs2TournamentMode the belongs2TournamentMode to set
	 */
	public void setBelongs2TournamentMode(TournamentMode belongs2TournamentMode) {
		this.belongs2TournamentMode = belongs2TournamentMode;
	}
	
	

}
