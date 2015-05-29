package image;
import java.sql.*;
import java.util.List;

public class MySqlConnection {
	//private Connection conn;
		
	void connect(String driver, String url, String user, String password, String s, List<String[]> record){
	    
		try { 
	        Class.forName(driver);
	        Connection conn = DriverManager.getConnection(url, user, password);
	        	   
	        if(!conn.isClosed()) 
	        	System.out.println("Succeeded connecting to the Database!");
	        	  
	        storedProcedures(s,record,conn);
	        conn.close();
	    } 
	           
	    catch(ClassNotFoundException e) {
	        System.out.println("Sorry,can`t find the Driver!"); 
	        e.printStackTrace();
	    } 
	           
	    catch(SQLException e) {
	        e.printStackTrace();
	    } 
	           
	    catch(Exception e) {
	        e.printStackTrace();
	    } 
	} 
	
	void storedProcedures(String s, List<String[]> record, Connection conn){
		
		int size = record.get(0).length;
		
		StringBuffer buffer = new StringBuffer("call ").append(s).append("(?");
		for(int i=0; i<size-1; ++i) {
			buffer.append(",?");
		}
		String procedure = buffer.append(')').toString();
		
		try {
			CallableStatement c = conn.prepareCall(procedure);
			System.out.println(record.size());
			for(int i=0; i<record.size(); ++i) {
				String[] temp = record.get(i);
				c.setString(1,temp[0]);  
				c.setString(2,temp[1]);  
				c.setInt(3,Integer.parseInt(temp[2]));  
				c.execute();  
			}
			 
		}
		
		catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
}
