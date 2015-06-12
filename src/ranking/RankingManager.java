package ranking;

public class RankingManager {
	public static void main(String[] args) {
		StoredProcedure sp = new StoredProcedure();
		try {
			sp.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012");
			//sp.addCollege("C:\\Users\\dell0\\Downloads\\2015中国大学排行榜700强.doc");
			//sp.addCollegeMajor("C:\\Users\\dell0\\Downloads\\2015中国大学专业排名2.doc");
			sp.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
		MajorRankingReader br = new MajorRankingReader();
		br.write("C:\\Users\\dell0\\Downloads\\2015中国大学专业排名2.doc","a.txt");
	}
}
