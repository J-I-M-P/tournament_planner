package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DataBaseTest {
	
	public static void test(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//		DriverManager.registerDriver(new );
		} catch (Exception e) {
			System.out.println("  --  class not found " + e);
		}


		Connection con;
		Statement stmt;
		ResultSet rSet;
		try {
			String url = "jdbc:mysql://localhost:3306/test" 
					+ "?verifyServerCertificate=false" 
					+ "&useSSL=false" 
					+ "&requireSSL=false";
			con = DriverManager.getConnection(url, "jimp", "150381");
			stmt = con.createStatement();

			String sqlQuery = "SELECT * from first";
			rSet = stmt.executeQuery(sqlQuery);

			while(rSet.next()){
				System.out.println(rSet.getString(1));
			}
			stmt.close();
			con.close();
		} catch (Exception e) {
			System.out.println("fehler bei tabellenabfrage");
		}
	}
	
	
}

