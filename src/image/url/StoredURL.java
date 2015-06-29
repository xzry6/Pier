package image.url;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import exchange.StoredProcedure;

public class StoredURL extends StoredProcedure{

	public boolean insertInstance(String tableName, String bankID) throws SQLException {
		Statement stmt = conn.createStatement();
		boolean bool = false;
		if(checkNull(tableName,bankID,stmt)) {
			String query = "UPDATE "+tableName+" SET IMAGE_URL='http://pierup-images.oss-cn-shenzhen.aliyuncs.com/"+bankID+".PNG' WHERE ID='"+bankID+"'";
			//System.out.println(query);
			bool = stmt.execute(query); 
		}
		stmt.close();
		return bool;
	}
	
	public boolean checkNull(String tableName, String bankID, Statement stmt) throws SQLException {
		if(bankID==null||bankID.length()==0) {
			System.out.println("BANK ID is invalid!");
			return false;
		}
		String s = "SELECT * FROM "+tableName+" WHERE ID='"+bankID+"'";
		//String s = "SELECT * FROM "+tableName+" WHERE ID LIKE 'BI%'";
		ResultSet rs = stmt.executeQuery(s);
//		ResultSetMetaData rsmd = rs.getMetaData();   
//	    int columnCount = rsmd.getColumnCount(); 
//		int rowCount = rs.getRow();
		if (rs.next()) {   
	        if(rs.getString("IMAGE_URL")==null) 
	        	return true;
	        else {
	        	System.out.println("IMAGE_URL already exists!");
	        	return false;
	        }
	    }   
		return false;
	}
	
	public static void main(String[] args) {
		try {
			StoredURL sp = new StoredURL();
			sp.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
			sp.insertInstance("BANK_INFO","BI0000000001");
			sp.close();
		}
		catch(Exception e) {
			e.printStackTrace();;
		}
		
		
	}

}
