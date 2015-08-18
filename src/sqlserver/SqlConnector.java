package sqlserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import util.sql.*;
public class SqlConnector {
	
	private Connection conn;
	private Statement stmt;
	/** 
     * Obtain the connection of the SQL server;
     * @version 2015.07.09 
     * @author Sean
     */
	public Connection getConn() {
		return conn;
	}
	/** 
     * Connect to a SQL server;
     * @param
     * driver such as com.mysql.jdbc.Driver;
     * @param
     * url should choose a database. For example, jdbc:mysql://xxx.xxx.xxx.xxx/xxx;
     * @param
     * user user name. Please make sure the user who must has grants to operate the database;
     * @param
     * password user password;
     * @version 2015.07.09 
     * @author Sean
	 * @return 
     */
	public SqlConnector(BasicSqlServer sql) throws ClassNotFoundException, SQLException {
			String driver = sql.getDriver();
	    Class.forName(driver);
	    conn = DriverManager.getConnection(sql.getUrl(), sql.getUser(), sql.getPassword());
	    if(!conn.isClosed()) 
	    	System.out.println("Succeeded connecting to the Database!");
	    stmt = conn.createStatement();
	}
	/** 
     * Close the connection of the SQL server;
     * @version 2015.07.09 
     * @author Sean
     */
	public void close() throws SQLException {
		stmt.close();
		conn.close();
    } 
	
	public boolean checkExist(String table, Map<String,String> restrictmap, String seperator) throws SQLException {
		ResultSet rs =  stmt.executeQuery(new SQLquery(QueryType.SELECT,table,null,restrictmap,seperator).getQuery());
		if(rs.next()) return true;
		else return false;
	}
	
	public ResultSet selectInstance(String table, String[] columns, Map<String,String> restrictmap, String seperator) throws SQLException {
		return stmt.executeQuery(new SQLquery(QueryType.SELECT,table,columns,null,restrictmap,seperator).getQuery());
	}
	
	public boolean insertInstance(String table, String[] columns, String[] values) throws SQLException {
		return stmt.execute(new SQLquery(QueryType.INSERT,table,columns,values).getQuery());
	}
	
	public boolean insertInstance(String table, Map<String,String> map) throws SQLException {
		return stmt.execute(new SQLquery(QueryType.INSERT,table,map).getQuery());
	}
	
	public int insertInstances(String table, List<String[]> list, String outputName) throws IOException{
		return insertInstances(table,list,outputName,null,null);
	}
	
	public int insertInstances(String table, List<String[]> list, String outputName, Map<String,String> restrictMap, String seperator) throws IOException{
		if(!check(list)) return 0;
		String[] columns = list.get(0);
		int count = 0;
		for(int i=1; i<list.size(); ++i) {
			String[] temp = list.get(i);
			try {
				System.out.println(new SQLquery(QueryType.INSERT,table,columns,list.get(i)).getQuery());
				if(restrictMap==null||!checkExist(table,restrictMap,seperator)) {
					boolean bool = stmt.execute(new SQLquery(QueryType.INSERT,table,columns,list.get(i)).getQuery());
					if(!bool) count++;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				FileWriter file = new FileWriter(outputName);
				BufferedWriter out = new BufferedWriter(file);
				for(String s:temp) 
					out.write(s+'\t');
				out.write('\n');
				out.close();
			}
		}
		System.out.println(count+" instances has been inserted");
		return count;
	}
	
	public boolean updateInstance(String table, String[] columns, String[] values, String[] restrictcolumns, String[] restrictvalues, String seperator) throws SQLException {
		return stmt.execute(new SQLquery(QueryType.UPDATE,table,columns,values,restrictcolumns,restrictvalues,seperator).getQuery());
	}
	
	public boolean updateInstance(String table, String[] columns, String[] values, Map<String,String> restricts, String seperator) throws SQLException {
		return stmt.execute(new SQLquery(QueryType.UPDATE,table,columns,values,restricts,seperator).getQuery());
	}
	
	private boolean check(List<String[]> list) {
		if(list==null||list.size()<=1) return false;
		return true;
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
	
	public void procedure(String s, List<String[]> record, String[] recordType){
		for(String[] sa:record) {
			if(sa.length!=recordType.length) {
				System.out.println(Arrays.toString(sa));
				System.out.println("size is not equal");
				return;
			}
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
			int count = 0;
			for(int i=0; i<record.size(); ++i) {
				String[] temp = record.get(i);
				for(int j=0; j<size; ++j) {
					System.out.println(temp[j]);
					if(recordType[j]!="int")
						c.setString(j+1, temp[j]);
					else
						c.setInt(j+1, Integer.parseInt(temp[j]));
				}
				boolean bool = c.execute();
				if(bool) count++;
			}
			 System.out.println(count+" instances is true");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}	
	}
}



