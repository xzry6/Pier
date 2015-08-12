package crawler.ranking;

import java.sql.*;
import java.util.List;

public class StoredProcedure {
	private Connection conn;
	//connect to mysql
	public void connect(String driver, String url, String user, String password) throws ClassNotFoundException, SQLException {
	    Class.forName(driver);
	    conn = DriverManager.getConnection(url, user, password);
	    if(!conn.isClosed()) 
	    	System.out.println("Succeeded connecting to the Database!");
	}
	//close connection
	public void close() throws SQLException {
        conn.close();
    } 
	
	
	//add columns to college
	public void addCollege(String fileName) throws SQLException {
		String procedure = "call add_college(?,?,?,?,?,?)";
		@SuppressWarnings("unchecked")
		List<String[]> list = ReaderFactory.readCollegeRanking().read(fileName);
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
	}
	
	
	// add college majors
	public void addCollegeMajor(String fileName) throws SQLException {
		String procedure = "call add_college_major(?)";
		@SuppressWarnings("unchecked")
		List<String> list = ReaderFactory.readMajor().read(fileName);
		CallableStatement c = conn.prepareCall(procedure);
		for(int i=0; i<list.size(); ++i) {
			c.setString(1, list.get(i));
			c.execute();
		}
	}
        
}
