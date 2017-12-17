package datatypes;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import userinterface.DBManager;

public class Mode_Table extends Table {

	/**
	 * row-keys
	 * @param tournamentId
	 */
	private enum row_keys{
		TEAM_NAME, GAMES, WIN_LOSSES, LITTLE_POINTS, PLACE
	}


	
	/**
	 * fields in table row
	 * team-name - 	games - w/l -	points -	place 
	 * the team		3		+2 		+22			3 
	 */
	
	
	ArrayList<String> liste = new ArrayList<>();
	ArrayList<HashMap<row_keys, String>> modeTable = new ArrayList<>();
	
	/**
	 * comparators for row comparison
	 */
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
			return Integer.compare(Integer.parseInt(arg0.get(row_keys.LITTLE_POINTS)), Integer.parseInt(arg1.get(row_keys.LITTLE_POINTS)));
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
	 * @param how2compare byName;byPlace;byLittlePoints;t.b.d.
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
		
		addTeamRow.put(row_keys.TEAM_NAME, addTeam.getTeamName());
		
		/**
		 * TODO random liitle points
		 * maybe create a function to randomly fill the match results
		 */
		addTeamRow.put(row_keys.LITTLE_POINTS, String.valueOf((int)Math.round(Math.random()*25)));
		
//		System.out.println("hashmap after team= " + addTeamRow);
		
		if(!(modeTable.add(addTeamRow))){
			System.err.println("cant add");
		} else {
//			System.out.println("added team");
		}
	}
	
	public void printTable(){
		//old
//		System.out.println(modeTable);
		
		//new
		String printHead = String.format("%-10s %-10s \n", 
														"TeamName",
														"Kleine Punkte");
		System.out.print(printHead);
		for (HashMap<row_keys, String> row : modeTable) {
			String printLine = String.format("%-10s %-10s \n", 
														row.get(row_keys.TEAM_NAME),
														row.get(row_keys.LITTLE_POINTS));
			System.out.print(printLine);
		}
		
	}
	
	
	

}
