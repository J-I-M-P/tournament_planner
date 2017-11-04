package starter;

import userinterface.MainInterface;

public class StarterClass {

	/**
	 * TODO
	 * integrate in all functions 
	 * verboselevel 
	 * 0 = no further information while running
	 * 1 = some more information
	 * 2 = 
	 * 3 = all information
	 */
	public static int verboselevel = 0;
	
	
	public static void main(String[] args) {
			MainInterface.showWelcomeMessage();
			MainInterface.mainInterfaceMenue();
	}
}
