package crawler.manager;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import crawler.exchange.BOCcrawler;
import crawler.factory.Type;
import util.StoredProcedure;
import sqlserver.SqlConnector;
import sqlserver.SqlServer254;

public class ExchangeManager implements BasicManager{
	private Map<String,String> map;
	private Type t;
	private String url;
	private String table;
	private String date;
	
	public ExchangeManager getConfigured(Type t, String url, String date, String table) {
		this.t = t;
		this.url = url;
		this.date = date;
		this.table = table;
		return this;
	}
	
	@Override
	public void crawler() {
		// TODO Auto-generated method stub
		BOCcrawler l = new BOCcrawler();
		l.login(t, url, date);
		l.crawl();
		map = l.getMap();
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			String temp = i.next();
			System.out.println(temp+"\t"+map.get(temp));
		}
			
	}
	@Override
	public void operate() throws Exception {
		// TODO Auto-generated method stub
		SqlConnector sc = new SqlConnector(SqlServer254.getInstance());
		StoredProcedure sp = new StoredProcedure(sc.getConn());
		sp.insertInstance(table, map);
		sc.close();
	}

	@Override
	public void manage() {
		// TODO Auto-generated method stub
		getConfigured(Type.BOC,"http://srh.bankofchina.com/search/whpj/search.jsp","2015-07-03","US_DOLLAR_EXCHANGE_RATE").crawler();
		try {
			operate();
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	public void manage(List<String> list) {
		for(String s:list) {
			getConfigured(Type.BOC,"http://srh.bankofchina.com/search/whpj/search.jsp",s,"US_DOLLAR_EXCHANGE_RATE").crawler();
			try {
				operate();
			} catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	private List<String> createDateList(String beginDate, String endDate) throws ParseException {
		List<String> list = new ArrayList<String>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date nbDate = df.parse(beginDate);
		Date neDate = df.parse(endDate);
		while(nbDate.before(neDate)) {
			nbDate.setDate(nbDate.getDate()+1);
			list.add(df.format(nbDate));
		}
		return list;
	}
	
	public static void main(String[] args) {
		ExchangeManager em = new ExchangeManager();
		try {
			em.manage(em.createDateList("2015-07-03", "2015-07-14"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
