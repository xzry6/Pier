package crawler.manager;

import java.io.IOException;
import java.util.List;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import sqlserver.SqlServerLocal;
import util.CSVReader;

public class ShixingManager implements BasicManager{
	private List<String[]> list;
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
			list = CSVReader.read("Shixing.txt", false, "\t");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		sc.insertInstances("BLACKLIST", list, "unequalList.txt");
	}

	public static void main(String[] args) {
		ShixingManager sx = new ShixingManager();
		sx.manage();
	}
}
