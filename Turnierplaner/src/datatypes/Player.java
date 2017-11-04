package datatypes;


/**
 * @author JIMP
 *
 */
public class Player {
	
	private int playerId, age;
	private String firstName, sureName, adress, tel;
	private double handicap;
	private char male;
	
/**
 * data types to store lists for used teams, tournaments, 
 * with links to place in table of tournament	
 */
	
	//private arraylist usedTeams = new arraylist();
	//private arraylist visitedTournaments = new arraylist();
	
	
/**/
	
	static int nextID = 0;
	
	public static void setNextId(int id){
		nextID = id;
	}
	
	
	
	public Player(int age, String tel, String firstName, String surename, String adress, double handicap, char male) {
		this.age = age;
		this.tel = tel;
		this.firstName = firstName;
		this.sureName = surename;
		this.adress = adress;
		this.handicap = handicap;
		this.male = male;
		this.playerId = nextID++;
	}
	
	public Player(String firstName){
		this(0,"", firstName, "", "", 0.0, (char) 0);
	}
	
	public void setID(int id){
		this.playerId = id;
	}
	
	
	
	
	
	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @return the age
	 */
	public int getAge() {
		return age;
	}



	/**
	 * @param age the age to set
	 */
	public void setAge(int age) {
		this.age = age;
	}



	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}



	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the surename
	 */
	public String getSurename() {
		return sureName;
	}



	/**
	 * @param surename the surename to set
	 */
	public void setSurename(String surename) {
		this.sureName = surename;
	}



	/**
	 * @return the adress
	 */
	public String getAdress() {
		return adress;
	}



	/**
	 * @param adress the adress to set
	 */
	public void setAdress(String adress) {
		this.adress = adress;
	}



	/**
	 * @return the handicap
	 */
	public double getHandicap() {
		return handicap;
	}



	/**
	 * @param handicap the handicap to set
	 */
	public void setHandicap(double handicap) {
		this.handicap = handicap;
	}



	/**
	 * @return the male
	 */
	public char getMale() {
		return male;
	}



	/**
	 * @param male the male to set
	 */
	public void setMale(char male) {
		this.male = male;
	}



	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		return super.equals(arg0);
	}
	@Override
	public String toString() {
		return "Player[ID: " + this.getPlayerId() + " - " + this.getFirstName() + " - " + this.getSurename() +"]"; 
	}
	
}
