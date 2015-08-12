package crawler.ranking;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Range;
import org.apache.poi.hwpf.usermodel.Table;
import org.apache.poi.hwpf.usermodel.TableIterator;
import org.apache.poi.hwpf.usermodel.TableRow;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

public class CollegeRankingReader implements BasicReader{
	/** 
     * 
     * @param  
     * fileName must be the full path of the file;
     * @return
     * This method will return a list of the college information.
     * 
     * 
     * @version 2015.06.12 
     * @author Sean
     * 
     */
	@Override
	public List<String[]> read(String fileName) {
		// TODO Auto-generated method stub
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
		            	if(j==7)temp[2] = tr.getCell(7).text().split("��")[0];
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
	
}
