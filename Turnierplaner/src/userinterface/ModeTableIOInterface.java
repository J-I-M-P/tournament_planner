package userinterface;

import Prog1Tools.IOTools;
import datatypes.Mode_Table;

public class ModeTableIOInterface {
	public static void IOMenue(Mode_Table modeTable){
		while(true){
			//by team ID / by team name / by place / byLittlePoints / byWin_Losses
			String MenueString =  "-----Tabele-Menue--------\n"
									+ "\n"
					  				+ "1 - sort by teamID\n"
									+ "2 - sort by team name\n"
					  				+ "3 - sort by place\n"
									+ "4 - sort by little points\n"
					  				+ "5 - sort by win/losses\n"
									+ "\n"
					  				+ "0 - velassen\n"
					  				+ "99 - Desktop\n";

			System.out.println(MenueString);
			//byName; byPlace; byLittlePoints; byWinLosses
			switch (IOTools.readInt()) {
			case 1:
//				modeTable.sortBy("
				break;
			case 2:
				modeTable.sortBy("byName");
				modeTable.printTable();
				break;
			case 3:
				modeTable.sortBy("byPlace");
				modeTable.printTable();
				break;
			case 4:
				modeTable.sortBy("byLittlePoints");
				modeTable.printTable();
				break;
			case 5:
				modeTable.sortBy("byWinLosses");
				modeTable.printTable();
				break;
			case 99:
				MainInterface.quitWOSave();
			case 0:
				return;
			}
		}
	}

}
