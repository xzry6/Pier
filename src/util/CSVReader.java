package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CSVReader {
	/** 
     * 
     * @param  
     * fileName must be the full path of the file;
     * @param  
     * keyID is the column of the key in map;
     * @param  
     * valueID is the column of the value in map;
     * @param  
     * ifHeader, is the column of the value in map;
     * @return
     * return a map of two attributes from the .csv.
     * 
     * 
     * @version 2015.06.12 
     * @author Sean
     * 
     */
	public static Map<String, List<String>> read(String fileName, boolean ifHeader, String splitChar, int keyID, int valueID) throws IOException {
		if(fileName==null||fileName.length()==0) return null;
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		
		File file = new File(fileName);
    BufferedReader reader = new BufferedReader(new FileReader(file));
    String tempString = null;
    if(ifHeader)
    	tempString = reader.readLine();
    while ((tempString = reader.readLine()) != null) {
    	String[] sa = tempString.split(splitChar);
    	System.out.println(sa[keyID]+"\t"+sa[valueID]);
    	String key = sa[keyID];
    	List<String> list = new ArrayList<String>();
    	if(map.containsKey(key))
    			list = map.get(key);
    	list.add(sa[valueID]);
    	map.put(key, list);
    	}
    reader.close();
		return map;
	}
	/** 
     * 
     * @param  
     * fileName must be the full path of the file;
     * @param  
     * keyID is the column of the key in map;
     * @param  
     * valueID is the column of the value in map;
     * @param  
     * ifHeader, is the column of the value in map;
     * @return
     * return a map of two attributes from the .csv.
     * 
     * 
     * @version 2015.06.12 
     * @author Sean
     * 
     */
	public static List<String[]> read(String fileName, boolean ifHeader, String splitChar) throws IOException {
		if(fileName==null||fileName.length()==0) return null;
		List<String[]> list = new ArrayList<String[]>();
		
		File file = new File(fileName);
    BufferedReader reader = new BufferedReader(new FileReader(file));

    String tempString = null;
    if(ifHeader)
    	tempString = reader.readLine();
    while ((tempString = reader.readLine()) != null) {
    	String[] sa = tempString.split(splitChar);
    	for(String s:sa) {
    		System.out.print(s+"\t");
    		}
    	System.out.println();
    	list.add(sa);
    	}
    reader.close();
		return list;
	}
	
	public static void main(String[] args) {
		String fileName = "/home/pier/district.csv";
		try {
			Map<String, List<String>> map = read(fileName,true,",",2,3);
			Iterator<String> i = map.keySet().iterator();
			while(i.hasNext()) {
				String key = i.next();
				System.out.println(key+": ");
				List<String> list = map.get(key);
				for(String s:list) {
					System.out.print(s+"\t");
				}
				System.out.println();
			}
			System.out.println(map.size());
//			List<String[]> list = read(fileName,true,",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	public static void writeInstance(List<String[]> list, String outputName) throws IOException {
		FileWriter file = new FileWriter(outputName);
		BufferedWriter out = new BufferedWriter(file);
		for(String[] sa:list){
			for(String s:sa) {
				out.write(s+'\t');
			}
			out.write('\n');
		}
		out.close();
	}
}
