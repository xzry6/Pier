package image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.*;
import org.jsoup.examples.HtmlToPlainText;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Spider {
		
	static void capture(String url, String textName){
		//connect Jsoup
		try {
			Document doc = Jsoup.connect(url).get();

			//capture keywords
		    Elements links = doc.getElementsByAttributeValueEnding("href", "html");
		    
		    int size = links.size();
			String[][] record = new String[size][3];
		    System.out.println("Links:"+ links.size());
		    
			for (int i=0; i<size; ++i) {
				Element link = links.get(i);
			    String s = search(link).substring(1);
			    System.out.println(i);
			    System.out.println(s.substring(s.indexOf("：")+1, s.indexOf((char) 10)));
			    record[i][0] = s.substring(s.indexOf("：")+1, s.indexOf((char) 10));
			    record[i][1] = s.substring(s.indexOf("是")+1, s.indexOf("贷"));
			    record[i][2] = s.substring(s.indexOf("为")+1, s.indexOf(" ")-1);
			}
			//write into file
			FileWriter file = new FileWriter(textName);
			BufferedWriter out = new BufferedWriter(file);
			for(int i=0; i<record.length; ++i){
				out.write(record[i][1]+'\t'+record[i][0]+'\t'+record[i][2]+'\r'+'\n');
			}
			out.close();
		} 
		
		catch(IOException e) {
			e.printStackTrace();
		} 
		
		catch(Exception e){
	    	e.printStackTrace();
	    }
	}
	
	static List<String[]> captureCardType(String url, String textName){
		//connect Jsoup
		List<String[]> list = new ArrayList<String[]>();
		try {
			Document doc = Jsoup.connect(url).get();

			//capture keywords
		    Elements links = doc.getElementsByAttributeValueEnding("href", "html");
		    
		    int size = links.size();
				String[][] record = new String[size][3];
		    System.out.println("Links:"+ links.size());
		    
			for (int i=0; i<size; ++i) {
				Element link = links.get(i);
			    String s = searchCardType(link).substring(1);
			    //System.out.println(s);
			    //System.out.println(s.substring(0, s.indexOf("开")));
			    record[i][0] = s.substring(0, s.indexOf("开"));
			    String temp = s.substring(s.indexOf("是")+1, s.indexOf((char) 10));
			    record[i][1] = temp.substring(0,temp.length()-3);
			    record[i][2] = temp.substring(temp.length()-3,temp.length());
			    System.out.println(record[i][0]+"\t"+record[i][1]+"\t"+record[i][2]);
			    list.add(record[i]);
			}
			//write into file
//			FileWriter file = new FileWriter(textName);
//			BufferedWriter out = new BufferedWriter(file);
//			for(int i=0; i<record.length; ++i){
//				out.write(record[i][1]+'\t'+record[i][0]+'\t'+record[i][2]+'\r'+'\n');
//			}
//			out.close();
		} 
		
		catch(IOException e) {
			e.printStackTrace();
		} 
		
		catch(Exception e){
	    	e.printStackTrace();
	    }
		return list;
	}
	
	static String search(Element link) throws IOException {
		
		if(link==null) return null;
		String url = link.attr("abs:href");
		Document doc = Jsoup.connect(url).get();
		System.out.println(doc.html());
		Elements links = doc.select("div.chalist");
    	
		HtmlToPlainText formatter = new HtmlToPlainText();
		String plainText = formatter.getPlainText(links.get(0));
		
		return plainText;
	}
	
	
static String searchCardType(Element link) throws IOException {
		
		if(link==null) return null;
		String url = link.attr("abs:href");
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("div.chalist > p");
		HtmlToPlainText formatter = new HtmlToPlainText();
		
		String plainText = formatter.getPlainText(links.get(1));
		return plainText;
	}
	
	public static void main(String[] args) {
		  String url = "http://www.chakahao.com/cardbin/chakahao_abc.html";
	    String textName = "/usr/local/tmp/chakahao.txt";
	    Spider.captureCardType(url,textName);
		
		
		
	}
}
