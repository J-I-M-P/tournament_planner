package userinterface;

import Prog1Tools.IOTools;

public class IOInterface {
	public static void IOMenue(){
		while(true){
			String MenueString =  "-----IO-Menue--------\n"
									+ "\n"
					  				+ "1 - \n"
									+ "2 - \n"
					  				+ "3 - \n"
									+ "4 - \n"
					  				+ "5 - \n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(MenueString);
			switch (IOTools.readInt()) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 99:
				MainInterface.saveAndQuit();
			case 0:
				return;
			}
		}
	}
}
