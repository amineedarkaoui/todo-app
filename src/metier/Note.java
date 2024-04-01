package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Note {
	private int id;
	private String title, text;
	private boolean pin;
	private Date created;
	
	public static void addNote(String title, String text) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO note (title, text) VALUES(?, ?);");
			pst.setString(1, title);
			pst.setString(2, text);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static void deleteNote(int id) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM note WHERE id = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static List<Note> getNotes() {
		List<Note> notes = new ArrayList<Note>();
		try {
			Connection conn = OnceConn.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM note;");
			
			while(rs.next()) {
				Note note = new Note();
				note.setCreated(rs.getDate("created"));
				note.setId(rs.getInt("id"));
				note.setText(rs.getString("text"));
				note.setTitle(rs.getString("title"));
				note.setPin(rs.getBoolean("pin"));
				
				notes.add(note);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return notes;
	}
	
	public static Note getNote(int id) {
		Note note = new Note();
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM note WHERE id=?;");
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				note.setCreated(rs.getDate("created"));
				note.setId(rs.getInt("id"));
				note.setText(rs.getString("text"));
				note.setTitle(rs.getString("title"));
				note.setPin(rs.getBoolean("pin"));
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return note;
	}
	
	public static void updateNote(Note note) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("UPDATE note SET title = ?, text = ? WHERE id = ?;");
			pst.setString(1, note.getTitle());
			pst.setString(2, note.getText());
			pst.setInt(3, note.getId());
			pst.executeUpdate();
		} catch(Exception e) { System.err.println(e); }
	}
	
	public static void updatePin(int id, boolean pinned) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("UPDATE note SET pin = ? WHERE id = ?;");
			pst.setBoolean(1, !pinned);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch(Exception e) { System.err.println(e); }
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isPin() {
		return pin;
	}
	public void setPin(boolean pin) {
		this.pin = pin;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String toString() {
		int maxLen = 160;
		String displayText = "";
		if (text!=null)
			displayText = (text.length()>maxLen)? text.substring(0, maxLen)+"..." : text;
		return displayText;
	}
	
}
