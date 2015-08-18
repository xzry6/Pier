package crawler.manager;

import java.io.IOException;
import java.util.List;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import util.CSVReader;

public class NewCollegeScoreManager implements BasicManager{
	private List<String[]> list;
	public static void main(String[] args) {
		NewCollegeScoreManager n = new NewCollegeScoreManager();
		n.manage();
	}
	@Override
	public void manage() {
		// TODO Auto-generated method stub
		crawler();
		try {
			operate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		try {
			list = CSVReader.read("newCollegeScore2.txt", false, "\t");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		//sc.insertInstances("COLLEGE_MAJOR_RANK_NEW", list, null, null,"");
	}

}
