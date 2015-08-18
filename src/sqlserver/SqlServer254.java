package sqlserver;

public class SqlServer254 extends BasicSqlServer{

	private static BasicSqlServer bss = new SqlServer254();
	
	private String driver = "com.mysql.jdbc.Driver";
	private String url = "*******";
	private String user = "*******";
	private String password = "*******";
	
	private SqlServer254() {
		
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
