package crawler.bank;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import util.CSVReader;
import util.StoredProcedure;

public class Operater  extends StoredProcedure{


	public Operater(Connection conn) throws SQLException {
		super(conn);
		// TODO Auto-generated constructor stub
	}

	private void select() throws SQLException, ClassNotFoundException, IOException {
		BankNameRegularizationMap.mapping(list,0);
		list = sp.checkNcountNull("BANK_BIN", list, "BIN", 5, null);
		sp.writeInstance(list, "additionalnotMatchBin.txt");
	}
	
	private void insert() {
//		int[] columns = {1,0,4};
		int[] columns = {0,5,4};
		list = sp.select(list, columns);
		String[] ss = {"String","String","int"};
		sp.procedure("test_insert_bank_BIN",list,ss);
	}
	
	
	
	private void update() throws SQLException, IOException {
		String[] columnName = {"CARD_NAME","CARD_TYPE"};
		int count = 0;
		for(String[] s:list) {
			String[] values = {s[2],s[3]};
			String restrict = "BIN="+s[5];
			System.out.println(Arrays.toString(values));
			System.out.println(restrict);
			if(!sp.checkInstanceExisted("BANK_BIN", "CARD_NAME", restrict)) {
				sp.updateInstance("BANK_BIN", columnName, values, restrict);
				count++;
			}
		}
		System.out.println(count+" instances has been updated");
	}
	
	
	
	private void updateCode() throws SQLException, IOException {
		String[] columnName = {"CODE"};
		int count = 0;
		for(String[] s:list) {
			String[] values = {s[0]};
			String restrict = "BANK_NAME='"+s[1]+"'";
			if(!sp.checkInstanceExisted("BANK_INFO", "CODE", restrict))
				count++;
			sp.updateInstance("BANK_INFO", columnName, values, restrict);
		}
		System.out.println(count+" instances has been updated");
	}
	
	
	private void updateCodeViaBin() throws SQLException, IOException {
		String[] columnName = {"CODE"};
		int count = 0;
		for(String[] s:list) {
			String[] values = {s[1]};
			String restrict = "ID=(select BANK_ID from BANK_BIN WHERE BIN='"+s[5]+"')";
			System.out.println(Arrays.toString(values));
			System.out.println(restrict);
			if(!sp.checkInstanceExisted("BANK_INFO", "CODE", restrict))
				count++;
			sp.updateInstance("BANK_INFO", columnName, values, restrict);
		}
		System.out.println(count+" instances has been updated");
	}
	
	
	
	private List<String[]> getCode(List<String[]> list, int columnIndex) throws IOException {
		List<String[]> ll = new ArrayList<String[]>();
		for(String[] sa:list) {
			String name = sa[columnIndex];
			if(name.contains("(")) {
				String[] temp = {name.substring(0,name.indexOf("(")),name.substring(name.indexOf("(")+1,name.indexOf(")"))};
				ll.add(temp);
			}
		}
		BankNameRegularizationMap.mapping(list,0);
		CSVReader.writeInstance(list, "bankCode.txt");
		return ll;
		
	}
}
