package crawler.manager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import sqlserver.SqlServerLocal;
import crawler.bank.BankNameRegularizationMap;
import util.CSVReader;
import util.StoredProcedure;

public class AdditionalBinManager implements BasicManager{
	private List<String[]> list;
	private StoredProcedure sp;
	

	public static void main (String[] args) {
		AdditionalBinManager abm = new AdditionalBinManager();
		abm.manage();
	}

	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		//sp = new StoredProcedure(new SqlConnector(new SqlServer254()).getConn());
//		CSVReader.read("CODE.TXT",false,"\t");
//		Map<String, List<String>> map = CSVReader.read(fileName,false,"\t",0,1);
//		BankNameRegularizationMap.mapping(list,1);
//		select();
//		insert();
//		update();
//		updateCode();
//		updateCodeViaBin();
	}
	
	@Override
	public void manage() {
		// TODO Auto-generated method stub
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
		try {
			SqlConnector sc = new SqlConnector(SqlServerLocal.getInstance());
			StoredProcedure sp = new StoredProcedure(sc.getConn());
			String[] colums = {"card_bin","bank_name","card_name","card_type","card_digits"};
			list = sp.readInstance("bank_card_bin",colums,null,"additionalBin.txt");
			sc.close();
		} catch (ClassNotFoundException | SQLException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	

}
