package crawler.ranking;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import util.CSVReader;
import crawler.factory.Loginer;
import crawler.factory.RequestBuilder;
import crawler.factory.Type;

public class NewCollegeScoreCrawler extends Loginer{
	List<String[]> list = new ArrayList<String[]>();
	public Loginer login(Type t, String url, String acc) {
		Document doc = loginAssistant(t,url+acc);
	  Elements elements = doc.select("td").first().select("a");
	  for(Element element:elements) {
		  //Element element = elements.get(0);
	    String nextacc = element.attr("href");
	   	login2(t,url,nextacc);
	    }
		return this;
	}
	
	private void login2(Type t, String url, String acc) {
		Document doc = loginAssistant(t,url+acc);
		Elements elements = doc.select("tbody tbody").first().select("a");
		for(Element element:elements) {
			//Element element = elements.get(0);
		  String nextacc = element.attr("href");
		  String info = element.html();
		  login3(t,url,nextacc,info.substring(info.lastIndexOf(" ")+1));
	    }
	}
	
	private void login3(Type t, String url, String acc, String info) {
		System.out.println("begin on 3rd floor");
		Document doc = loginAssistant(t,url+acc);
		Elements elements = doc.select("tbody tbody tbody tr");
		for(Element e:elements) {
			Elements tdelements = e.select("td");
			String t0 = tdelements.get(0).select("div").first().html();
			t0 = t0.substring(t0.lastIndexOf(";")+1);
			if(tdelements.size()>1) {
				String t1 = tdelements.get(1).html();
				String[] temp = {info,t0,t1};
				list.add(temp);
			}
			String[] temp = {info,t0};
			list.add(temp);
		}
		for(String[] s:list) {
			System.out.println(Arrays.toString(s));
		}
	}
	
	private Document loginAssistant(Type t, String url) {
		HttpGet firstGrab = new RequestBuilder().buildGet(t,url);
		Document doc = new Document("");
		httpClient = HttpClients.custom().build();
		CloseableHttpResponse redirect;
		try {
			redirect = httpClient.execute(firstGrab);
			HttpEntity myEntity = redirect.getEntity();
			String fulltext = EntityUtils.toString(myEntity,"UTF-8"); 
			doc = Jsoup.parse(fulltext);
		} catch (IOException e) {
				// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return doc;
	}
	
	public static void main(String[] args) {
//		NewCollegeScoreCrawler ncsc = new NewCollegeScoreCrawler();
//		String url = "http://www.chinadegrees.cn/webrms/pages/Ranking/";
//		String acc = "xkpmGXZJ.jsp";
//		ncsc.login(Type.NewCollegeScore, url, acc);
//		try {
//			CSVReader.writeInstance(ncsc.list, "newCollegeScore.txt");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		try {
			List<String[]> list = CSVReader.read("newCollegeScore.txt", false, "\t");
			System.out.println(list.size());
			List<String[]> newlist = new ArrayList<String[]>();
			String lastScore = "0";
			for(int i=0; i<list.size(); ++i) {
				String[] s = list.get(i);
				if(s.length!=3) {
					String[] newsa = {s[0],s[1],lastScore};
					newlist.add(newsa);
				}
				else {
					lastScore = s[2];
				}
			}
			CSVReader.writeInstance(newlist, "newCollegeScore2.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
