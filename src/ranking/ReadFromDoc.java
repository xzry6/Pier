package ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;


import org.apache.poi.poifs.filesystem.POIFSFileSystem;


public class ReadFromDoc {
	public static List<String[]> read(String fileName) {
		List<String[]> list = new ArrayList<String[]>(); 
		try{
		    FileInputStream in = new FileInputStream(fileName);
		    POIFSFileSystem pfs = new POIFSFileSystem(in);   
		    HWPFDocument hwpf = new HWPFDocument(pfs);   
		    Range range = hwpf.getRange();
		    TableIterator it = new TableIterator(range);
		     
		    while (it.hasNext()) {   
		        Table tb = (Table) it.next();   
		          
		        for (int i = 0; i < tb.numRows(); i++) {   
		            TableRow tr = tb.getRow(i);   
		            
		            String[] temp = new String[6];
		            for (int j = 0; j < tr.numCells(); j++) {
		            	if(j==4||j==8) continue;
		            	if(j==1)temp[0] = tr.getCell(1).text().split("")[0];
		            	if(j==2)temp[1] = tr.getCell(2).text().split("")[0];
		            	if(j==7)temp[2] = tr.getCell(7).text().split("星")[0];
		            	if(j==0)temp[3] = tr.getCell(0).text().split("")[0];
		            	if(j==3)temp[4] = tr.getCell(3).text().split("")[0];
		            	if(j==5)temp[5] = tr.getCell(5).text().split(" ")[0];
		            }
		            if(temp[0]!=null) {
		                list.add(temp);
		            }
		        }
		    }
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	
	public static List<String> readMajor(String fileName){
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
	
	
	public static void readMajorRank(String fileName) {
		List<String> majorList = readMajor(fileName);
		List<HashMap<String, List<String[]>>> list = new ArrayList<HashMap<String, List<String[]>>>();
		try {
			File f = new File(fileName);
			FileInputStream fis = new FileInputStream(f);     
	        HWPFDocument doc = new HWPFDocument(fis);
	        Range rang = doc.getRange();
	       
	        TableIterator it = new TableIterator(rang);
		    int count = 0;
		    while (it.hasNext()) {   
		    	count++;
		        Table tb = (Table) it.next(); 
		        HashMap<String, List<String[]>> hash = new HashMap<String, List<String[]>>();
	            List<String[]> Ap = new ArrayList<String[]>();
	            List<String[]> A = new ArrayList<String[]>();
	            List<String[]> Bp = new ArrayList<String[]>();
	            List<String[]> B = new ArrayList<String[]>();
		        for (int i = 0; i < tb.numRows(); i++) {   
		            TableRow tr = tb.getRow(i);   
		            if(!tr.text().contains("排")&&!tr.text().contains("B")&&!tr.text().contains("C")) {
            			String[] s = new String[2];
		            	for (int j = 0; j < tr.numCells(); j++) {
		            		String tc = tr.getCell(j).text();
		            		if(!tc.equals("")) {
			            		tc = tc.split("")[0].split(" ")[0];
			            		if(j==0||j==3||j==6) {
			            			s = new String[2];
			            			s[0] = tc;
			            		}
			            		if(j==1||j==4||j==7) s[1] = tc;
			            		if(j==2||j==5||j==8) {
			            			if(tc.equals("A+")) Ap.add(s);
			            			if(tc.equals("A")) A.add(s);
			            		}
		            		}
				        }
		            	continue;
		            }
		            if(tr.text().contains("B+")) {
		            	String[] temp = tr.text().split("：")[1].split("")[0].split("、");
		            	for(int a=0; a<temp.length; ++a) {
		            		String[] s = {temp[a]};
		            		Bp.add(s);
		            	}
		            	continue;
		            }
		            if(tr.text().contains("B")) {
		            	String[] temp = tr.text().split("：")[1].split("")[0].split("、");
		            	for(int a=0; a<temp.length; ++a) {
		            		String[] s = {temp[a]};
		            		B.add(s);
		            	}
		            	continue;
		            }
		        }
		        hash.put("A+", Ap);
		        hash.put("A", A);
		        hash.put("B+", Bp);
		        hash.put("B", B);
		        list.add(hash);
		    }
		    System.out.println(count);
	        fis.close(); 
		} catch(IOException e) {
			e.printStackTrace();
		}
		try {
			PrintStream ps = new PrintStream(new File("OUTPUT.txt"));
			for(int i=0; i<list.size(); ++i) {
				HashMap<String, List<String[]>> hash = list.get(i);
				List<String[]> Ap = hash.get("A+");
				for(int n=0; n<Ap.size(); ++n)
					ps.println(majorList.get(i)+"\tA+\t"+Ap.get(n)[1]+"\t"+Ap.get(n)[0]);
				List<String[]> A = hash.get("A");
				for(int n=0; n<A.size(); ++n)
					ps.println(majorList.get(i)+"\tA\t"+A.get(n)[1]+"\t"+A.get(n)[0]);
				List<String[]> Bp = hash.get("B+");
				for(int n=0; n<Bp.size(); ++n)
					ps.println(majorList.get(i)+"\tB+\t"+Bp.get(n)[0]+"\tnull");
				List<String[]> B = hash.get("B");
				for(int n=0; n<B.size(); ++n)
					ps.println(majorList.get(i)+"\tB\t"+B.get(n)[0]+"\tnull");
			}
	        ps.flush();
	        ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//read("C:\\Users\\dell0\\Downloads\\2015中国大学排行榜700强.doc");
		//readMajor("C:\\Users\\dell0\\Downloads\\2015中国大学专业排名2.doc");
		readMajorRank("C:\\Users\\dell0\\Downloads\\2015中国大学专业排名2.doc");
	}
}
