package crawler.ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;

public class MajorReader implements BasicReader {
	/** 
     * 
     * @param  
     * fileName must be the full path of the file;
     * @return
     * This method will return a list of the major information.
     * 
     * 
     * @version 2015.06.12 
     * @author Sean
     * 
     */
	@Override
	public List<String> read(String fileName) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String>();
		try {
			File f = new File(fileName);
			FileInputStream fis = new FileInputStream(f);     
	        HWPFDocument doc = new HWPFDocument(fis);
	        Range rang = doc.getRange();
	        for(int i=1; i<rang.numParagraphs(); ++i) {
	        	Paragraph p = rang.getParagraph(i);
	        	if(p.text().contains("TC")) {
	        		list.add(p.text().split(" ")[1].split("")[0]);
	        	}
	        }
	        fis.close(); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
