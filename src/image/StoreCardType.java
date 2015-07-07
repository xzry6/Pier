package image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import exchange.StoredProcedure;

public class StoreCardType extends StoredProcedure{
	public boolean updateInstance(String tableName, String[] instance) throws SQLException {
		boolean bool = false;
		Statement stmt = conn.createStatement(); 
//		if(checkNull(tableName, instance, stmt)) {
			String query = "UPDATE "+tableName+" SET CARD_NAME='"+instance[1]+"', CARD_TYPE='"+instance[2]+"' WHERE BIN="+instance[0];
			System.out.println(query);
			bool = stmt.execute(query); 
			System.out.println(bool);
//		}
		stmt.close();
		return bool;
	}
	private boolean checkNull(String tableName, String[] instance, Statement stmt) throws SQLException {
		if(instance.length==0) return false;
		String query = "SELECT * FROM "+tableName+" WHERE BIN="+instance[0];
		ResultSet rs = stmt.executeQuery(query);
		if (rs.next()) {   
	        if(rs.getString("CARD_NAME")==null) 
	        	return true;
	        else {
	        	System.out.println("CARD_NAME already exists!");
	        	return false;
	        }
	    }   
		return false;
	}
	public static void main(String[] args) {
		
		String url = "http://www.chakahao.com/cardbin/chakahao_abc.html";
	  String textName = "/usr/local/tmp/chakahao.txt";
	  List<String[]> list = Spider.captureCardType(url,textName);
	  
		try {
			StoreCardType sp = new StoreCardType();
			sp.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "*****", "*****");
			for(int i=0; i<list.size(); ++i) {
				sp.updateInstance("BANK_BIN", list.get(i));
			  }
		
			sp.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}
