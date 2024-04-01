package metier;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TodoList {
	private int id;
	private Date created;
	private String title;
	private boolean top;
	
	public static void addList(String title) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("INSERT INTO list (title) VALUES(?);");
			pst.setString(1, title);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static void deleteList(int id) {
		try {
			Connection conn = OnceConn.getConn();
			PreparedStatement pst = conn.prepareStatement("DELETE FROM list WHERE id = ?;");
			pst.setInt(1, id);
			pst.executeUpdate();
		} catch(Exception e) { System.out.println(e); }
	}
	
	public static List<TodoList> getLists() {
		List<TodoList> lists = new ArrayList<TodoList>();
		try {
			Connection conn = OnceConn.getConn();
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * FROM list;");
			
			while (rs.next()) {
				TodoList list = new TodoList();
				list.setCreated(rs.getDate("created"));
				list.setTop(rs.getBoolean("top"));
				list.setId(rs.getInt("id"));
				list.setTitle(rs.getString("title"));
				
				lists.add(list);
			}
		} catch(Exception e) { System.err.println(e); }
		
		return lists;
	}
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date date) {
		this.created = date;
	}
	public String getTitle() {
		return title;
	}
	public String toString() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isTop() {
		return top;
	}
	public void setTop(boolean top) {
		this.top = top;
	}
	
}
