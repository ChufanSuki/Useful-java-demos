package com.allen;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExampleAccess {
	
	static String account = MyDBInfo.MYSQL_USERNAME;
	static String password = MyDBInfo.MYSQL_PASSWORD;
	static String server = MyDBInfo.MYSQL_DATABASE_SERVER;
	static String database = MyDBInfo.MYSQL_DATABASE_NAME;

	public static void main(String[] args) {
		
		try {
			// make sure that java knows about the driver class.
			Class.forName("com.mysql.cj.jdbc.Driver");
			// create a connect
			// url: jdbc:mysql://localhost:3306
			// must be placed within a try catch block with a catch for SQLException
			Connection con = DriverManager.getConnection
				( "jdbc:mysql://" + server, account ,password);
			// Statement object which can be used to execute standard SQL statements
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
			stmt.executeQuery("USE " + database);
			ResultSet rs = stmt.executeQuery("SELECT * FROM metropolises");

// Sample Access Looking for Specific Item
            // indexing is not zero-based
//			rs.absolute(3);
//			System.out.println(rs.getString("metropolis"));
			
// Sample Loop Access
//			while(rs.next()) {
//				String s = rs.getString("metropolis");
//				int i = rs.getInt("population");
//				System.out.println(s + "\t" + i);
//			}
			// Use executeUpdate for UPDATE, INSERT, and DELETE MySQL statements.
			stmt.executeUpdate(
					"DELETE FROM metropolises WHERE continent = \"North America\"");
			con.close();
			
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}


	}

}
