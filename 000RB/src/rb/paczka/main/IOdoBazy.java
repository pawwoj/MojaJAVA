package rb.paczka.main;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class IOdoBazy {
//	public void zapiszDoBazy() {
	public void zapiszDoBazySkladnik(int kodskladnik2, String nazwa, String jednostka, String opis,
			String ktoodpowiada) {

		try {
			Connection conne = Laczeniezbazadanych.Connect();

			String query = "INSERT INTO `skladnik2` (`kodskladnik2`, `nazwa`, `jednostka`, `opis`, `ktoodpowiada`)"
					+ "VALUES (?,?,?,?,?)";
			PreparedStatement preparedStmt = conne.prepareStatement(query);
			preparedStmt.setInt(1, kodskladnik2);
			preparedStmt.setString(2, nazwa);
			preparedStmt.setString(3, jednostka);
			preparedStmt.setString(4, opis);
			preparedStmt.setString(5, ktoodpowiada);
			preparedStmt.execute();
			conne.close();

		} catch (Exception el) {
			System.out.println("Ups. coœ posz³o nie tak.");
			System.out.println(el);

		}
	}

	public void zapiszDoBazyProdukt(int kodbom2, String nazwa, String jednostka, String opis) {

		try {
			Connection conne = Laczeniezbazadanych.Connect();

			String query = "INSERT INTO `bom2` (`kodbom2`, `nazwa`, `jednostka`, `opis`)" + "VALUES (?,?,?,?)";
			PreparedStatement preparedStmt = conne.prepareStatement(query);
			preparedStmt.setInt(1, kodbom2);
			preparedStmt.setString(2, nazwa);
			preparedStmt.setString(3, jednostka);
			preparedStmt.setString(4, opis);

			preparedStmt.execute();
			conne.close();
			System.out.println("zapis bok udany");

		} catch (Exception el) {
			System.out.println("Ups. coœ posz³o nie tak.");
			System.out.println(el);

		}
	}

	public void zapiszDoBazySkladnikDlaBom(int kodbom2, int kodskladnik2, int ilosc) {

		try {
			Connection conne = Laczeniezbazadanych.Connect();

			String query = "INSERT INTO `bomiskladnik2` (`bom2`, `skladnik2`, `ilosc`)" + "VALUES (?,?,?)";
			PreparedStatement preparedStmt = conne.prepareStatement(query);
			preparedStmt.setInt(1, kodbom2);
			preparedStmt.setInt(2, kodskladnik2);
			preparedStmt.setInt(3, ilosc);

			preparedStmt.execute();
			conne.close();
			System.out.println("zapis udany");

		} catch (Exception el) {
			System.out.println("Ups. coœ posz³o nie tak.");
			System.out.println(el);

		}
	}

	public void wczytajProdukISkladniki() {

		try {
			Connection conne = Laczeniezbazadanych.Connect();
			Statement stmt;
			stmt = conne.createStatement(); 
			ResultSet rss = stmt.executeQuery("SELECT skladnik2.*,bomiskladnik2.ilosc "
			+"FROM skladnik2 "
			+"JOIN bomiskladnik2 "
			+"ON skladnik2.kodskladnik2 = bomiskladnik2.skladnik2 "
			+"WHERE bomiskladnik2.bom2 = 48 ");
					
				
			System.out.println("z bazy danych");
			while (rss.next()) {
				System.out.println(rss.getInt(1) + "  " + rss.getString(2) + "  " 
			+ rss.getString(3) + "  " + rss.getString(4) 
			+ "  " + rss.getString(5)+ "  " + rss.getInt(6));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
