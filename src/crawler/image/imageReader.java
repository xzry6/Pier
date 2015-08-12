package crawler.image;

import java.io.BufferedReader;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.InputStreamReader;  
import java.util.ArrayList;  
import java.util.List;  

  

public class imageReader {
	
    private final static String EOL = System.getProperty("line.separator");   
  
    public static String recognizeText(File imageFile) throws Exception {  
        
    	File outputFile = new File(imageFile.getParentFile(), "output");  
        StringBuffer strB = new StringBuffer();  
        
        List<String> cmd = new ArrayList<String>();  
        cmd.add("C:\\Users\\dell0\\Tesseract-OCR\\tesseract");  
        cmd.add("");  
        cmd.add(outputFile.getName());  
        cmd.add("-l");  
        cmd.add("eng");    
        cmd.add("-psm");    
        cmd.add("7");  
        cmd.add("user");    
  
        ProcessBuilder pb = new ProcessBuilder();  
        
        pb.directory(imageFile.getParentFile());  
        cmd.set(1, imageFile.getName());  
        pb.command(cmd);  
        pb.redirectErrorStream(true);  
        Process process = pb.start();  
        
        int w = process.waitFor();  
        if (w == 0) {  
            BufferedReader in = new BufferedReader(new InputStreamReader(  
                    new FileInputStream(outputFile.getAbsolutePath() + ".txt"),  
                    "UTF-8"));  
            String str;  
            while ((str = in.readLine()) != null) 
                strB.append(str).append(EOL);  
            in.close();  
        } else {  
            String msg;  
            switch (w) {  
	            case 1:  
	                msg = "Errors accessing files. There may be spaces in your image's filename.";  
	                break;  
	            case 29:  
	                msg = "Cannot recognize the image or its selected region.";  
	                break;  
	            case 31:  
	                msg = "Unsupported image format.";  
	                break;  
	            default:  
	                msg = "Errors occurred.";  
            }
            throw new RuntimeException(msg);  
        }  
        new File(outputFile.getAbsolutePath() + ".txt").delete();  
        return strB.toString().replaceAll("\\s*", "");  
    }  
    
	public static void main(String[] args) {
		 try {
			 File testDataDir = new File("C://Users/dell0/Desktop/captcha (4).jpg");  
	         System.out.println(testDataDir.exists());
	         String s = recognizeText(testDataDir);
	         System.out.println(s);
	     } catch(Exception e) {
		     e.printStackTrace();  
	     }
	}
}
