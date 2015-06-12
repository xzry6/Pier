package ranking;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;

public class MajorRankingReader extends MajorReader {
	/** 
     * 
     * @param  
     * fileName must be the full path of the file;
     * @param
     * outputName could be a simple path and it will be stored locally;
     * @return
     * This method could either return a list of all the information or store it to a .txt file.
     * One can ignore the storing procedure by setting outputName null.
     * 
     * @version 2015.06.12 
     * @author Sean
     * 
     */  
	public List<HashMap<String, List<String[]>>> write(String fileName, String outputName) {
		List<String> majorList = read(fileName);
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
		            if(!tr.text().contains("ÅÅ")&&!tr.text().contains("B")&&!tr.text().contains("C")) {
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
		            	String[] temp = tr.text().split("£º")[1].split("")[0].split("¡¢");
		            	for(int a=0; a<temp.length; ++a) {
		            		String[] s = {temp[a]};
		            		Bp.add(s);
		            	}
		            	continue;
		            }
		            if(tr.text().contains("B")) {
		            	String[] temp = tr.text().split("£º")[1].split("")[0].split("¡¢");
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
		if(outputName!=null) {
			try {
				PrintStream ps = new PrintStream(new File(outputName));
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
		return list;
	}

}
