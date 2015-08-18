package crawler.shixing;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import crawler.factory.Loginer;
import crawler.factory.RequestBuilder;
import crawler.factory.Type;

public class ShixingCrawler extends Loginer{
	BufferedWriter out;
	public Loginer login(Type t, String url, String page) {
		RequestBuilder builder = new RequestBuilder(url,null,null,page);
		HttpPost post = builder.buildPost(t);
		try {
			httpClient = HttpClients.custom().build();
			CloseableHttpResponse redirect = httpClient.execute(post);
			HttpEntity myEntity = redirect.getEntity();
			
	    String fulltext = EntityUtils.toString(myEntity,"UTF-8"); 
	    Document doc = Jsoup.parse(fulltext);
	    
	    Elements elements = doc.select("tbody").first().select("tr");
	    for(int i=1; i<elements.size(); ++i) {
	    	String id = elements.get(i).getElementsByAttribute("id").first().attr("id");
	    	String jsonurl = "http://shixin.court.gov.cn/detail?id="+id;
	    	
	    	HttpGet get = builder.buildGet(t, jsonurl);
	    	CloseableHttpResponse json = httpClient.execute(get);
	    	HttpEntity newEntity = json.getEntity();
		    webtext = EntityUtils.toString(newEntity,"UTF-8"); 
	    	//System.out.println(webtext);
	    	crawl();
	    	}
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	@Override
	public Loginer crawl() {
		String[] arr= webtext.split(",");
//		try {
			for(String s:arr) {
				s = s.substring(s.indexOf(":")+1).split("}")[0];
				if(s.contains("\""))
					s = s.split("\"")[1];
				System.out.print(s+"\t");
			}
			System.out.println();
//			out.write("\n");
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		return this;
	}
	public static void main(String[] args) {
		try {
			FileWriter file = new FileWriter("Shixing.txt");
			String url = "http://shixin.court.gov.cn/personMore.do";
			ShixingCrawler sx = new ShixingCrawler();
			sx.out = new BufferedWriter(file);
			for(int i=1; i<107600; ++i)
				sx.login(Type.Shixing,url,Integer.toString(i));
			sx.out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
