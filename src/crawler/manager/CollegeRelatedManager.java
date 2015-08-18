package crawler.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import util.CSVReader;

public class CollegeRelatedManager implements BasicManager{
	private List<String[]> list;
	@Override
	public void manage() {
		// TODO Auto-generated method stub
	}

	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		try {
			
			list = CSVReader.read("filtered_major.csv", false, ",");
			for(String s:list.get(0))
				System.out.println(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void operate() throws Exception {
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		List<String[]> l = new ArrayList<String[]>();
		for(String s:list.get(0)) {
			Map<String,String> map = new HashMap<String,String>();
			map.put("NAME", s);
			if(!sc.checkExist("COLLEGE_MAJORS", map, "")) {
					String[] temp = {s};
					l.add(temp);
			}
		}
		for(String[] ss:l) 
			System.out.println(Arrays.toString(ss));
		String[] recordType = {"string"};
		sc.procedure("add_college_major", l, recordType);
		System.out.println(list.get(0).length);
		System.out.println(l.size()+" instances has been inserted");
	}
	
	public static void main(String[] args) {
		CollegeRelatedManager crm = new CollegeRelatedManager();
		crm.crawler();
//		try {
//			crm.operate();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

}
