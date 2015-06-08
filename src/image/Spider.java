package image;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
			    System.out.println(s.substring(s.indexOf("£º")+1, s.indexOf((char) 10)));
			    record[i][0] = s.substring(s.indexOf("£º")+1, s.indexOf((char) 10));
			    record[i][1] = s.substring(s.indexOf("ÊÇ")+1, s.indexOf("´û"));
			    record[i][2] = s.substring(s.indexOf("Îª")+1, s.indexOf(" ")-1);
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
	
	
	
	static String search(Element link) throws IOException {
		
		if(link==null) return null;
		
		String url = link.attr("abs:href");
		Document doc = Jsoup.connect(url).get();
		Elements links = doc.select("div.chalist");
    	
		HtmlToPlainText formatter = new HtmlToPlainText();
		String plainText = formatter.getPlainText(links.get(0));
		
		return plainText;
	}
}
