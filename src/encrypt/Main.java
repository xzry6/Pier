package encrypt;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
public class Main
{
	public static void main(String[] args) {
		String original = "`1234567890-=~!@#$%^&*()_+qwertyuiop[]\\\t\n\rQWERTYUIOP{}|asdfghjkl;'ASDFGHJKL:zxcvbnm,\"./ZXCVBNM<>?";
		System.out.println("original key is:\n"+original+"\n");
		String modified = new KeyEncrypt().encrypt(original);
		System.out.println("encrypted key is:\n"+modified+"\n");
		String decrypted = new KeyEncrypt().decrypt(modified);
		System.out.println("decrypted key is:\n"+decrypted+"\n");
	}
	public static void writeInstance(String s, String outputName) throws IOException {
		FileWriter file = new FileWriter(outputName);
		BufferedWriter out = new BufferedWriter(file);
		out.write(s);
		out.close();
	}
	public static String readInstance(String outputName) throws IOException {
		File file = new File(outputName);
    		BufferedReader reader = new BufferedReader(new FileReader(file));

    		String tempString = null;
		tempString = reader.readLine();
    		reader.close();
		return tempString;
	}

}

