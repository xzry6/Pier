package image.url;

import java.util.List;


public class UpdateURL {
	public static void main(String[] args) {
		List<String> list = CheckImage.check("C:\\Users\\dell0\\Desktop\\img");
		//stored procedure
		StoredURL sp = new StoredURL();
		try {
			sp.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
			for(int i=0; i<list.size(); ++i) {
				String id = list.get(i);
				sp.insertInstance("BANK_INFO", id);
			}
			
			sp.close();
		}
		catch(Exception e) {
			e.printStackTrace();;
		}
		
		
		
		
	}
}
