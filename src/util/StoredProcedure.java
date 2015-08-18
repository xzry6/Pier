package util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import sqlserver.SqlServerLocal;


public class StoredProcedure {
	private Connection conn;
	private Statement stmt;
	
	
	public StoredProcedure(Connection conn) throws SQLException {
		this.conn = conn;
		stmt = conn.createStatement();
	}
	
	
	public void close() throws SQLException {
		stmt.close();
		conn.close();
	}
	
	public boolean excuteQuery(String query) {
		boolean bool = stmt.equals(query); 
		return bool;
	}
	
	public static void main(String[] args) {
		try {
			SqlConnector sc = new SqlConnector(SqlServerLocal.getInstance());
			StoredProcedure sp = new StoredProcedure(sc.getConn());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public boolean insertInstance(String tableName, String[] columnName, String[] values) throws SQLException {
		if(values==null||values.length==0||values.length!=columnName.length) return false;
		StringBuffer sb1 = new StringBuffer("INSERT INTO "+tableName+"(");
		StringBuffer sb2 = new StringBuffer(") VALUES(");
		for(String column:columnName) 
			sb1.append(column+",");
		sb1.deleteCharAt(sb1.length()-1);
		for(String value:values) 
			sb2.append("'"+value+"',");
		sb2.deleteCharAt(sb2.length()-1);
		
		String query = sb1.append(sb2).append(")").toString();
//		System.out.println(query);
		 
		boolean bool = stmt.execute(query); 
		return bool;
	}
	
	
	
	public boolean insertInstance(String tableName, Map<String, String> map) throws SQLException {
		if(map.size()==0) return false;
		StringBuffer sb1 = new StringBuffer("INSERT INTO "+tableName+"(");
		StringBuffer sb2 = new StringBuffer(") VALUES(");
		Iterator<String> i = map.keySet().iterator();
		Object key = i.next();
		Object value = map.get(key);
		sb1.append(key);
		sb2.append(value);
		while(i.hasNext()) {
			key = i.next();
			value = map.get(key);
			sb1.append(","+key);
			sb2.append(","+value);
		}
		String query = sb1.append(sb2).append(")").toString();
		//System.out.println(query);; 
		boolean bool = stmt.execute(query); 
		return bool;
	}
	
	
	
	public boolean updateInstance(String tableName, String[] columnName, String[] values, String restrict) throws SQLException {
		if(values==null||values.length==0||values.length!=columnName.length) return false;
		StringBuffer sb1 = new StringBuffer("UPDATE "+tableName+" SET "+columnName[0]+"='"+values[0]+"'");
		for(int i=1; i<columnName.length; ++i) {
			sb1.append(","+columnName[i]+"='"+values[i]+"'");
		}
		String query = sb1.toString();

		if(restrict!=null&&restrict.length()!=0) 
			query+=" WHERE "+restrict;
		
//		System.out.println(query);; 
		boolean bool = stmt.execute(query); 
		return bool;
		
	}
	
	
	
	public boolean updateInstance(String tableName, Map<String, String> map, String restrict) throws SQLException {
		if(map.size()==0) return false;
		StringBuffer sb1 = new StringBuffer("UPDATE "+tableName+" SET ");
		
		Iterator<String> i = map.keySet().iterator();
		Object key = i.next();
		Object value = map.get(key);
		sb1.append(key+"="+value);
		while(i.hasNext()) {
			key = i.next();
			value = map.get(key);
			sb1.append(","+key+"="+value);
		}
		String query = sb1.toString();

		if(restrict!=null&&restrict.length()!=0)
			query+=" WHERE "+restrict;
//		System.out.println(query);; 
		boolean bool = stmt.execute(query); 
		return bool;
		
	}
	
	
	
	public List<String[]> readInstance(String tableName, String[] columnName, String restrict, String outputName) throws SQLException, IOException {
		
		List<String[]> list = new ArrayList<String[]>();
		
		String query = "SELECT * FROM "+tableName;
		if(restrict!=null&&restrict.length()!=0)
			query += " WHERE "+restrict;
		ResultSet rs = stmt.executeQuery(query);
		
		while(rs.next()) {
			String[] temp = new String[columnName.length];
			for(int i=0; i<columnName.length; ++i) {
				temp[i] = rs.getString(columnName[i]);
				System.out.print(temp[i]+"\t");
			}
			list.add(temp);
			System.out.println();
	    }   
		if(outputName!=null&&outputName.length()!=0) {
			writeInstance(list,outputName);
		}
		
		return list;
	}
	
public boolean checkInstanceExisted(String tableName, String columnName, String restrict) throws SQLException, IOException {
				
		String query = "SELECT * FROM "+tableName;
		if(restrict!=null&&restrict.length()!=0)
			query += " WHERE "+restrict;
		ResultSet rs = stmt.executeQuery(query);
		if(rs.next()) {
			if(rs.getString(columnName)==null) 
       	return false;
      else {
        System.out.println(columnName+" already exists!");
        return true;
      		}
		}
		return false;
	}
	
	public List<String[]> checkNcountNull(String tableName, List<String[]> list, String columnName, int indexNum, String outputName) throws SQLException, IOException {
		int f = 0;
		List<String[]> ll = new ArrayList<String[]>();
		for(String[] sa:list) {
			String s = sa[indexNum];
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+"='"+s+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(!rs.next()) {
				ll.add(sa);
				f++;
			}
		}
		System.out.println(f);
		if(outputName!=null&&outputName.length()!=0)
			writeInstance(ll,outputName);
		return ll;
	}
	
	public List<String[]> checkNcountExisted(String tableName, List<String[]> list, String columnName, int indexNum, String outputName) throws SQLException, IOException {
		int t = 0;
		List<String[]> ll = new ArrayList<String[]>();
		for(String[] sa:list) {
			String s = sa[indexNum];
			String query = "SELECT * FROM "+tableName+" WHERE "+columnName+"='"+s+"'";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next()) {
				ll.add(sa);
				t++;
			}
		}
		System.out.println(t);
		if(outputName!=null&&outputName.length()!=0)
			writeInstance(ll,outputName);
		return ll;
	}
	
	public void procedure(String s, List<String[]> record, String[] recordType){
		for(String[] sa:record) {
			if(sa.length!=recordType.length) return;
		}
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
				for(int j=0; j<size; ++j) {
					System.out.println(temp[j]);
					if(recordType[j]!="int")
						c.setString(j+1, temp[j]);
					else
						c.setInt(j+1, Integer.parseInt(temp[j]));
				}
				c.execute();  
			}
			 
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}

	public void writeInstance(List<String[]> list, String outputName) throws IOException {
		FileWriter file = new FileWriter(outputName);
		BufferedWriter out = new BufferedWriter(file);
		for(String[] sa:list){
			for(String s:sa) {
				out.write(s+'\t');
			}
			out.write('\n');
		}
		out.close();
	}
	
	public List<String[]> select(List<String[]> list, int[] columns) {
		List<String[]> ll = new ArrayList<String[]>();
		for(String[] sa:list) {
			String[] stringArray = new String[columns.length];
			for(int i=0; i<columns.length; ++i) 
				stringArray[i] = sa[columns[i]];
			ll.add(stringArray);
		}
		return ll;
	}
}
