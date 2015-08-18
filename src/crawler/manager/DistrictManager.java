package crawler.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import util.CSVReader;
import util.StoredProcedure;

public class DistrictManager implements BasicManager{
	private Map<String, List<String>> map;
	private String table;
	private String fileName;
	
	public DistrictManager getConfigured(String table, String fileName) {
		this.fileName = fileName;
		this.table = table;
		return this;
	}
	@Override
	public void manage() {
		// TODO Auto-generated method stub
		getConfigured("DISTRICT","Macau.csv");
//		crawler();
		crawlerSPECIAL();
		try {
//			operate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		try {
			map = CSVReader.read(fileName,true,",",2,3);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void crawlerSPECIAL() {
		try {
			map = new HashMap<String,List<String>>();
			List<String[]> list = CSVReader.read(fileName, false, ",");
			for(String[] s:list) {
				List<String> temp = new ArrayList<String>(Arrays.asList(s));
				String header = temp.remove(0);
				map.put(header, temp);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void operate() throws Exception{
		// TODO Auto-generated method stub
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		StoredProcedure sp = new StoredProcedure(sc.getConn());
		String[] columnName = {"ID","NAME"};
//		String restrict = "STATE_ID='"+code+"'";
		List<String[]> list = sp.readInstance("CITY", columnName, null, null);
		String[] columns = {"CITY_ID","NAME","SORT"};
		int count = 0;
		for(String[] ss:list) {
			if(map.containsKey(ss[1])) {
				System.out.println(ss[0]+"\t"+ss[1]);
				List<String> temp = map.get(ss[1]);
				for(int i=0; i<temp.size(); ++i) {
					String[] values = {ss[0],temp.get(i),Integer.toString(i+1)};
					sp.insertInstance(table,columns,values);
					count++;
				}
			}
		}
		System.out.println(count+" instances has been inserted");
		sc.close();
	}

	public static void main(String[] args) {
		 DistrictManager dm = new DistrictManager();
		 dm.manage();
	}
}
