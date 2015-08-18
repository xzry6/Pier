package sqlserver;

public class SqlServerLocal extends BasicSqlServer{
	
	private static BasicSqlServer bss = new SqlServerLocal();
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/pier";
	private String user = "sean";
	private String password = "eric";
	
	private SqlServerLocal() {
		
	}
	
	public static BasicSqlServer getInstance() {
		return bss;
	}
	
	public String getDriver() {
		return driver;
	}
	
	public String getUrl() {
		return url;
	}
	
	public String getUser() {
		return user;
	}
	
	public String getPassword() {
		return password;
	}
}
