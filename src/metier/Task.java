package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Task {
	private int id, list;
	private String title;
	private boolean chekcked;
	private Date created;
	
	public static void addTask(String title, int list) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO tache (title, list) VALUES(?, ?);");
			pst.setString(1, title);
			pst.setInt(2, list);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static void deleteTask(int id) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM tache WHERE id = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static void updateCheck(int id, boolean checked) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("UPDATE tache SET checked = ? WHERE id = ?;");
			pst.setBoolean(1, !checked);
			pst.setInt(2, id);
			pst.executeUpdate();
		} catch(Exception e) { System.err.println(e); }
	}
	
	public static List<Task> getTasks() {
		List<Task> tasks = new ArrayList<Task>();
		try {
			Connection conn = OnceConn.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM tache;");
			
			while(rs.next()) {
				Task task = new Task();
				task.setChekcked(rs.getBoolean("checked"));
				task.setCreated(rs.getDate("created"));
				task.setId(rs.getInt("id"));
				task.setList(rs.getInt("list"));
				task.setTitle(rs.getString("title"));
				
				tasks.add(task);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return tasks;
	}
	
	public static List<Task> getTasksByList(int list) {
		List<Task> tasks = new ArrayList<Task>();
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("SELECT * FROM tache WHERE list = ?");
			pst.setInt(1, list);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				Task task = new Task();
				task.setChekcked(rs.getBoolean("checked"));
				task.setCreated(rs.getDate("created"));
				task.setId(rs.getInt("id"));
				task.setList(rs.getInt("list"));
				task.setTitle(rs.getString("title"));
				
				tasks.add(task);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		
		return tasks;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getList() {
		return list;
	}
	public void setList(int list) {
		this.list = list;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isChekcked() {
		return chekcked;
	}
	public void setChekcked(boolean chekcked) {
		this.chekcked = chekcked;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
}
