package datatypes;
/**
 * superclass for tables 
 * 
 * @author JIMP
 *
 */

import datamanagement.Import_export_datas;


/**
 * master table
 * children
 * 	- 	tournament-table
 * 	-	mode-table
 * 	-	
 * @author JIMP
 *
 */
public class Table {
	int tournamentID;
	Tournament tournamentOnTable;
	
	public Table(int tournamentId){
		this.tournamentID = tournamentId;
		tournamentOnTable = Import_export_datas.getTournamentByID(tournamentID);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "MOA-Table";
	}
	
	
	
	
}
