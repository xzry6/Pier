package crawler.ranking;

import java.util.List;
/** 
 * 
 * @param  
 * fileName must be the full path of the file.
 * @version 2015.06.12 
 * @author Sean
 * 
 */  
public interface BasicReader {
	public List read(String fileName);
}
