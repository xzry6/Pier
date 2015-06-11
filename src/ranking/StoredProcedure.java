package ranking;

import java.sql.*;
import java.util.List;

public class StoredProcedure {
	private Connection conn;
	//connect to mysql
	private void connect(String driver, String url, String user, String password) {
		try { 
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url, user, password);
	        	   
	        if(!conn.isClosed()) 
	        	System.out.println("Succeeded connecting to the Database!");
		}
	    catch(Exception e) {
	        e.printStackTrace();
	    } 
	}
	//close connection
	private void close() throws SQLException {
        conn.close();
    } 
	//add columns to college
	public void addCollege() {
		try {
			connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
			String procedure = "call add_college(?,?,?,?,?,?)";
			List<String[]> list = ReadFromDoc.read("C:\\Users\\dell0\\Downloads\\2015中国大学排行榜700强.doc");
			CallableStatement c = conn.prepareCall(procedure);
			for(int i=0; i<list.size(); ++i) {
				String[] temp = list.get(i);
				c.setString(1, temp[0]);
				c.setString(2, temp[1]);
				c.setInt(3, Integer.parseInt(temp[2]));
				c.setInt(4, Integer.parseInt(temp[3]));
				c.setString(5, temp[4]);
				c.setFloat(6, Float.parseFloat(temp[5]));
				c.execute();  
			}
			close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void addCollegeMajor() {
		try {
			connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
			String procedure = "call add_college_major(?)";
			List<String> list = ReadFromDoc.readMajor("C:\\Users\\dell0\\Downloads\\2015中国大学专业排名2.doc");
			CallableStatement c = conn.prepareCall(procedure);
			for(int i=0; i<list.size(); ++i) {
				c.setString(1, list.get(i));
				c.execute();
			}
			close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)  {
		StoredProcedure sp = new StoredProcedure();
		//sp.addCollege();
		sp.addCollegeMajor();
	}
        
}
