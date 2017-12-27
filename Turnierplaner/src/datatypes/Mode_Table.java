package datatypes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import datamanagement.config;
import userinterface.DBManager;

public class Mode_Table extends Table {

	/**
	 * row-keys
	 * @param tournamentId
	 */
	private enum row_keys{
		TEAM_ID, TEAM_NAME, GAMES, WIN_LOSSES, LITTLE_POINTS, PLACE
	}


	
	/**
	 * fields in table row
	 * team-name - 	games - w/l -	points -	place 
	 * the team		3		+2 		+22			3 
	 */
	
	
	ArrayList<String> liste = new ArrayList<>();
	ArrayList<HashMap<row_keys, String>> modeTable = new ArrayList<>();
	
	/**
	 * comparators for row comparison by team ID / by team name / by place / byLittlePoints / byWin_Losses  
	 */
	//by team ID
		Comparator<HashMap<row_keys, String>> compRowByTeamID = new Comparator<HashMap<row_keys,String>>() {
			
			@Override
			public int compare(HashMap<row_keys, String> arg0, HashMap<row_keys, String> arg1) {
				return arg0.get(row_keys.TEAM_ID).compareTo(arg1.get(row_keys.TEAM_ID));
			}
		};
	
	//by team name
	Comparator<HashMap<row_keys, String>> compRowByTeamName = new Comparator<HashMap<row_keys,String>>() {
		
		@Override
		public int compare(HashMap<row_keys, String> arg0, HashMap<row_keys, String> arg1) {
			return arg0.get(row_keys.TEAM_NAME).compareTo(arg1.get(row_keys.TEAM_NAME));
		}
	};
	
	//by place
	Comparator<HashMap<row_keys, String>> compRowByPlace = new Comparator<HashMap<row_keys,String>>() {
		
		@Override
		public int compare(HashMap<row_keys, String> arg0, HashMap<row_keys, String> arg1) {
			return arg0.get(row_keys.PLACE).compareTo(arg1.get(row_keys.PLACE));
		}
	};
	
	//byLittlePoints
	Comparator<HashMap<row_keys, String>> compRowByLittlePoints = new Comparator<HashMap<row_keys,String>>() {

		@Override
		public int compare(HashMap<row_keys, String> arg0, HashMap<row_keys, String> arg1) {
			return Integer.compare(Integer.parseInt(arg1.get(row_keys.LITTLE_POINTS)),Integer.parseInt(arg0.get(row_keys.LITTLE_POINTS)));
		}
	};
	
	//byWin_Losses
		Comparator<HashMap<row_keys, String>> compRowByWin_Losses = new Comparator<HashMap<row_keys,String>>() {

			@Override
			public int compare(HashMap<row_keys, String> arg0, HashMap<row_keys, String> arg1) {
				return Integer.compare(Integer.parseInt(arg1.get(row_keys.WIN_LOSSES)),Integer.parseInt(arg0.get(row_keys.WIN_LOSSES)));
			}
		};
	/**
	 * TODO more comparators - multi level comparison eg. 1.by game points 2. by little points
	 */
	
	public void test(){
//		liste.add("hans");
//		liste.add("franz");
//		liste.sort(null);
		
		HashMap<row_keys, String> add = new HashMap<>();
		add.put(row_keys.TEAM_NAME, "test");
		add.put(row_keys.LITTLE_POINTS, "23");
		System.out.println(add);
		
		HashMap<row_keys, String> add2 = new HashMap<>();
		add2.put(row_keys.TEAM_NAME, "test2");
		add2.put(row_keys.LITTLE_POINTS, "231");
		System.out.println(add2);
		
		HashMap<row_keys, String> add3 = new HashMap<>();
		add3.put(row_keys.TEAM_NAME, "adler");
		add3.put(row_keys.LITTLE_POINTS, "6");
		System.out.println(add3);
		
		modeTable.add(add);
		modeTable.add(add2);
		modeTable.add(add3);
		
		System.out.println(modeTable);
		
		
		
		modeTable.sort(compRowByTeamName);
		System.out.println(modeTable);
		
	}
		
	/**
	 * constructor
	 * @param tournamentId
	 */
	public Mode_Table(int tournamentId) {
		super(tournamentId);
		// TODO Auto-generated constructor stub
	}
	
	
	/**
	 * 
	 * @param how2compare byName; byPlace; byLittlePoints; byWinLosses; t.b.d.
	 */
	public void sortBy(String how2compare){
		switch (how2compare) {
		case "byName":
			modeTable.sort(compRowByTeamName);
			break;
		case "byPlace":
			modeTable.sort(compRowByPlace);
			break;
		case "byLittlePoints":
			modeTable.sort(compRowByLittlePoints);
			break;
		case "byWinLosses":
			modeTable.sort(compRowByWin_Losses);
			break;
		default:
			break;
		}
	}
	
	public void addTeam(int teamID){
		/**
		 * TODO verbose
		 */
//		System.out.println("try2add team");
		
		Team addTeam = DBManager.getTeamByID(teamID);
		
		/**
		 * TODO verbose
		 */
//		System.out.println("team found: " + addTeam);
		
		HashMap<row_keys, String> addTeamRow = new HashMap<>();
		
//		System.out.println("hashmap before adding team= " + addTeamRow);
		
		//add team ID
		addTeamRow.put(row_keys.TEAM_ID, String.valueOf(addTeam.getTeamId()));
		//add team name
		addTeamRow.put(row_keys.TEAM_NAME, addTeam.getTeamName());
		
		/**
		 * fields in table row
		 * team-name - 	games - w/l -	points -	place 
		 * the team		3		+2 		+22			3
		 */
		addTeamRow.put(row_keys.GAMES, String.valueOf(0));
		addTeamRow.put(row_keys.WIN_LOSSES, String.valueOf(0));
		addTeamRow.put(row_keys.LITTLE_POINTS, String.valueOf(0));
		addTeamRow.put(row_keys.PLACE, String.valueOf(0));
		
		
		/**
		 * TODO random little points for debug phase
		 * maybe create a function to randomly fill the match results
		 */
		
		//add little points
//		addTeamRow.put(row_keys.LITTLE_POINTS, String.valueOf((int)Math.round(Math.random()*25)));
		
		
//		System.out.println("hashmap after team= " + addTeamRow);
		
		if(!(modeTable.add(addTeamRow))){
			System.err.println("cant add");
		} else {
//			System.out.println("added team");
		}
	}
	
	private HashMap<row_keys, String> getRowByTeamID(int teamID){
		for (HashMap<row_keys, String> row : modeTable) {
			if(Integer.valueOf(row.get(row_keys.TEAM_ID)) == teamID){ // to int and vergleich
				return row;
			}
		}
		return null;
	}
	
	/**
	 * fields in table row
	 * team-name - 	games - w/l -	points -	place 
	 * the team		3		+2 		+22			3
	 */
	private void pushInRow(int teamID, int deltaGames, int deltaW_L, int deltaPoints){
		//get row out of modeTable
//		HashMap<row_keys, String> row = getRowByTeamID(teamID);
		HashMap<row_keys, String> row = modeTable.remove(modeTable.indexOf(getRowByTeamID(teamID)));
		/**
		 * TODO row.merge / row.compute --> bifunction???
		 */
		//update row date
		int originGames = Integer.valueOf(row.get(row_keys.GAMES));
		int originW_L = Integer.valueOf(row.get(row_keys.WIN_LOSSES));
		int originPoints = Integer.valueOf(row.get(row_keys.LITTLE_POINTS));
		row.put(row_keys.GAMES, String.valueOf(originGames+deltaGames));
		row.put(row_keys.WIN_LOSSES, String.valueOf(originW_L+deltaW_L));
		row.put(row_keys.LITTLE_POINTS, String.valueOf(originPoints+deltaPoints));
		//put updated row in modeTable
		modeTable.add(row);
	}
	
	public void progressMatch(Match match){
//		System.out.println("einmal progressMatch");
		System.out.println(match);
		System.out.println("generate manipulation variables");
		//variables for row push
		int deltaGames = 1;
		int deltaW_L_A = 0;
		int deltaW_L_B = 0;
		int deltaPointsA = 0;
		int deltaPointsB = 0;
		boolean teamAisWILDCARD = match.getTeamA().isWILDCARD();
		boolean teamBisWILDCARD = match.getTeamB().isWILDCARD();
		//deltaPoints
		if (teamAisWILDCARD){
			deltaPointsB = match.getPointsTeamB();
		}else if (teamBisWILDCARD){
			deltaPointsA = match.getPointsTeamA();
		} else {
			deltaPointsA = match.getPointsTeamA() - match.getPointsTeamB();
			deltaPointsB = match.getPointsTeamB() - match.getPointsTeamA();
		}
		//deltaGames
		switch (match.getMatchResult()) {
		case WIN_A:
			if (teamBisWILDCARD){
				deltaW_L_A = config.pointsWhenWinMatch;
				break;
			} else {
				deltaW_L_A = config.pointsWhenWinMatch;
				deltaW_L_B = config.pointsWhenLooseMatch;
			}
			break;
		case WIN_B:
			if (teamAisWILDCARD){
				deltaW_L_B = config.pointsWhenWinMatch;
				break;
			} else {
				deltaW_L_A = config.pointsWhenLooseMatch;
				deltaW_L_B = config.pointsWhenWinMatch;
			}			
			break;
		case DRAWN:
			if (teamAisWILDCARD){
				deltaW_L_B = config.pointsWhenDrawMatch;
				break;
			} else if (teamBisWILDCARD){
				deltaW_L_A = config.pointsWhenDrawMatch;
				break;
			} else {
				deltaW_L_A = config.pointsWhenDrawMatch;
				deltaW_L_B = config.pointsWhenDrawMatch;
			}
			break;
		default:
			break;
		}
		System.out.println("write match to table");
		if(teamAisWILDCARD){
			pushInRow(match.getTeamB().getTeamId(), deltaGames, deltaW_L_B, deltaPointsB);
		}else if (teamBisWILDCARD){
			pushInRow(match.getTeamA().getTeamId(), deltaGames, deltaW_L_A, deltaPointsA);
		}else {
			pushInRow(match.getTeamA().getTeamId(), deltaGames, deltaW_L_A, deltaPointsA);	
			pushInRow(match.getTeamB().getTeamId(), deltaGames, deltaW_L_B, deltaPointsB);
		}
	}

	public void printTable(){ 

		//old
//		System.out.println(modeTable);
		
		//new
		String printHead = String.format("\n%-21s %-7s %-12s %-14s %-6s \n", 
														"TeamName",
														"Spiele",
														"Spielpunkte",
														"Kleine Punkte",
														"Platz");
		System.out.print(printHead);
		for (HashMap<row_keys, String> row : modeTable) {
			String printLine = String.format("%-21s %-7s %-12s %-14s %-6s \n", 
														row.get(row_keys.TEAM_NAME),
														row.get(row_keys.GAMES),
														row.get(row_keys.WIN_LOSSES),
														row.get(row_keys.LITTLE_POINTS),
														row.get(row_keys.PLACE));
			System.out.print(printLine);
		}
		
	}
	
	
	

}
