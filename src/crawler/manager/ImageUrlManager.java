package crawler.manager;

import crawler.image.CheckImage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import util.CSVReader;
import util.StoredProcedure;

public class ImageUrlManager implements BasicManager{
	private List<String> list;
	private String table;
	private String fileName;

	
	public ImageUrlManager getConfigured(String table, String fileName) {
		this.table = table;
		this.fileName = fileName;
		return this;
	}
	
	@Override
	public void manage() {
		// TODO Auto-generated method stub
		getConfigured("BANK_INFO","C:\\Users\\dell0\\Desktop\\img");
		crawler();
		try {
			operate();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		list = CheckImage.check(fileName);
	}

	
	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		StoredProcedure sp = new StoredProcedure(sc.getConn());
		for(int i=0; i<list.size(); ++i) {
			String id = list.get(i);
			String restrict = "ID='"+id+"'";
			String[] key = {"IMG_URL"};
			if(!sp.checkInstanceExisted(table, key[0], restrict)) {
				String[] value = {"http://pierup-images.oss-cn-shenzhen.aliyuncs.com/"+id+".PNG"};
				sp.updateInstance(table, key, value, restrict);
			}
		}
		sc.close();
	}
	
	
	public static void main(String[] args) {
		try {
			List<String[]> list = CSVReader.read("image.txt", false, "\t");
			StoredProcedure sp = new StoredProcedure(new SqlConnector(SqlServer254.getInstance()).getConn());
			String[] columnName = {"IMAGE_URL"};
			for(String[] sa:list) {
				String[] values = {"http://pierup-images.oss-cn-shenzhen.aliyuncs.com/"+sa[0]};
				String restrict = "ID='"+sa[0].split("[.]")[0]+"'";
				sp.updateInstance("BANK_INFO", columnName, values, restrict);
			}
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
