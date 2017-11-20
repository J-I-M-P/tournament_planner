package datatypes;
/**
 * superclass for tables 
 * 
 * @author JIMP
 *
 */

import java.util.ArrayList;

import datamanagement.Import_export_datas;
import userinterface.DBManager;
import userinterface.TournamentIOInterface;

public class Table {
	int tournamentID;
	Tournament tournamentOnTable;
	
	public Table(int id){
		this.tournamentID = id;
		tournamentOnTable = Import_export_datas.getTournamentByID(tournamentID);
	}
	
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Table";
	}
	
	
	
	
}
