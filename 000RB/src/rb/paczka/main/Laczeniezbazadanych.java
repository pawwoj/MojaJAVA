package rb.paczka.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Laczeniezbazadanych {

	public static Connection Connect() {
		try {
			String url = "jdbc:mysql://serwer1936875.home.pl/31886298_rb?useSSL=false";
			String user = "31886298_rb";
			String password = "password";

			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection(url, user, password);
			return conn;

		} catch (ClassNotFoundException | SQLException ex) {
			Logger.getLogger(Laczeniezbazadanych.class.getName()).log(Level.SEVERE, null, ex);
		}
		return null;
	}
	
}
