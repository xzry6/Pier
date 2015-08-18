package crawler.manager;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

import sqlserver.SqlConnector;
import sqlserver.SqlServer254;
import crawler.bank.BankNameRegularizationMap;
import util.StoredProcedure;

public class BankNameRegularizationManager implements BasicManager{
	private Map<String,String> map;
	@Override
	public void manage() {
		// TODO Auto-generated method stub
		crawler();
		try {
			operate();
		} catch (Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		map = BankNameRegularizationMap.getMap();
	}

	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		StoredProcedure sp = new StoredProcedure(new SqlConnector(SqlServer254.getInstance()).getConn());
		Iterator<String> i = map.keySet().iterator();
		String[] columns = {"NAME_KEY","NAME_VALUE"};
		int count = 0;
		while(i.hasNext()) {
			String s = i.next();
			String[] values = {s,map.get(s)};
			String restrict = "NAME_KEY='"+s+"'";
			System.out.println(Arrays.toString(values));
			if(!sp.checkInstanceExisted("BANK_NAME_REGULIZATION", "NAME_KEY", restrict)) {
				count++;
				sp.insertInstance("BANK_NAME_REGULIZATION", columns, values);
			}
		}
		System.out.println(count+" instances has been inserted");
	}
	public static void main(String[] args) {
		BankNameRegularizationManager bnrm = new BankNameRegularizationManager();
		bnrm.manage();
	}
}
