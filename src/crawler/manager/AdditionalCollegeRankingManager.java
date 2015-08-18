package crawler.manager;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import util.CSVReader;

public class AdditionalCollegeRankingManager implements BasicManager {
	private List<String[]> list;
	@Override
	public void manage() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		try {
			list = CSVReader.read("additionCollege.csv", false, ",");List<String[]> newlist = new ArrayList<String[]>();
			SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
			String[] columns = {"STATE_ID"};
			Map<String,String> map = new HashMap<String,String>();
			int count = 0;
			for(String[] s:list) {
				map.put("NAME", s[1]);
				ResultSet rs = sc.selectInstance("CITY",columns, map, "");
				if(rs.next()) {
					count++;
					String temp = rs.getString("STATE_ID");
					String[] newcolumns = {"DESCRIPTION"};
					Map<String,String> newmap = new HashMap<String,String>();
					newmap.put("ID",temp);
					ResultSet nrs = sc.selectInstance("STATE_PROVINCE",newcolumns, newmap, "");
					if(nrs.next()) {
						String[] newtemp = {s[0],nrs.getString("DESCRIPTION")};
						newlist.add(newtemp);
					}
				}
				else newlist.add(s);
			}
			for(String[] s:newlist)
				System.out.println(Arrays.toString(s));
			System.out.println("total instances are： "+list.size());
			System.out.println(count+" instances has been selected");
			CSVReader.writeInstance(newlist, "filteredList.txt");
		} catch (IOException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		list = CSVReader.read("filteredList.csv", false, ",");
		List<String[]> newlist = new ArrayList<String[]>();
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		int count = 0;
		for(String[] s:list) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("NAME", s[0]);
			if(sc.checkExist("COLLEGE_RANKS", map, "")) {
//				String[] columns = {"LEVEL"};
//				String[] values = {s[2]};
//				sc.updateInstance("COLLEGE_RANKS", columns, values, map, "");
				count++;
			} else {
				newlist.add(s);
			}
		}
		System.out.println(newlist.size());
		String[] recordType = {"string","string","string"};
//		sc.procedure("add_yeji_college", newlist, recordType);
		System.out.println("total instances: "+list.size());
		System.out.println("counted instances: "+count);
	}
	
	public static void main(String[] args) {
		AdditionalCollegeRankingManager a = new AdditionalCollegeRankingManager();
		//a.crawler();
		try {
			a.operate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
