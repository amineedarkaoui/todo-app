package metier;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.Timer;



public class RappelOperation {
	
	public static ResultSet getTodayRappel() { // get count of rappel
		Connection connection = OnceConn.getConn();
		ResultSet rs = null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT COUNT(id) as number FROM rappel WHERE CAST(date AS DATE) = ?");
			ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	public static ResultSet getProgrameeRappel() {
		Connection connection = OnceConn.getConn();
		ResultSet rs = null;
		try {
			PreparedStatement ps = connection.prepareStatement("SELECT COUNT(id) as number FROM rappel");
			rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}
	
	public static void ajouterRappel(String titre, String text, Date date) {
		Connection connection = OnceConn.getConn();
		
		try {
			PreparedStatement ps = connection.prepareStatement("INSERT INTO rappel (title, text, date) VALUES (?,?,?)");
			ps.setString(1, titre);
			ps.setString(2, text);
			ps.setTimestamp(3, new java.sql.Timestamp(date.getTime()));
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	 public static ResultSet getRappelsAujourdhui() {
		 Connection connection = OnceConn.getConn();
			ResultSet rs = null;
			try {
				PreparedStatement ps = connection.prepareStatement("SELECT *  FROM rappel WHERE CAST(date AS DATE) = ?");
				ps.setDate(1, java.sql.Date.valueOf(LocalDate.now()));
				rs = ps.executeQuery();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		 return rs;
	 }

}
