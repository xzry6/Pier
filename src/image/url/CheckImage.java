package image.url;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class CheckImage {
	 private static StringBuffer fileInfo;  
	    // dir info  
	 private static StringBuffer dirInfo;  
	    // child file info  
	 private static File[] fm;  
	 
	 
//	 public static void main(String[] args) {
//	  check("C:\\Users\\dell0\\Desktop\\img");
//	 }
	   
	 
	 public static List<String> check(String fileName) {  
		 List<String> list = new ArrayList<String>();
		 File dir = new File(fileName); 
	     String key = ".PNG";  
	     printAllInfo(dir);  
	     System.out.println("/nsearch key : " + key);  
	     System.out.println("search results : ");  
	     for (File file : fm) {  
	     //System.out.println(file.getName().substring(0,12));
	    	 if (file.getName().indexOf(key) >= 0) {  
	    		 if (file.isFile()) {  
	    			 String temp = file.getName().substring(0,12);
	    			 list.add(temp);
	    			 System.out.println("file : " + temp);
	    		 } //else if (file.isDirectory()) {  
	                    //System.out.println("dir : " + file.getName() + "   ");  
	                //}  
	    	 }  
	     }
	     return list;
	 }  
	    /** 
	     * print info of this directory 
	     *  
	     * @param dir 
	     */  
	 private static void printAllInfo(File dir) {  
		 fileInfo = new StringBuffer();  
		 dirInfo = new StringBuffer();  
		 fm = dir.listFiles();  
		 for (File file : fm) {  
			 if (file.isFile()) {  
				 fileInfo.append(file.getName() + "    ");  
			 } else if (file.isDirectory()) {  
				 dirInfo.append(file.getName() + "    ");  
			 }  
		 }  
		 System.out.println(dir.getAbsolutePath());  
		 System.out.println("contains : ");  
		 System.out.println("file ---> " + fileInfo);  
		 System.out.println("dir  ---> " + dirInfo);  
	 }  
}
