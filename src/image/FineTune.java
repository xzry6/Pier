package image;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FineTune {
	
	public static void main(String[] args){
		
		/*capture keywords from url and store in textName*/
		
	    String url = "http://www.chakahao.com/cardbin/chakahao_abc.html";
	    String textName = "chakahao.txt";
	    Spider.capture(url,textName);
		
		
		
		/*fine tune some of the works then store in another file*/
		
	    String readName = "chakahao.txt";
	    //String writeName = "chakahao2.txt";
	    List<String[]> list = readfile(readName);
	    //writefile(writeName,list);
	    
	    
	    
	    /*connect to mysql and modify the table*/
	    
	    MySqlConnection mysql = new MySqlConnection();
	    mysql.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "bcsql", "Alameda2012","test_insert_bank_BIN",list);
	}
	
	
	
	static List<String[]> readfile(String fileName) {
		
		List<String[]> list = new ArrayList<String[]>();
		Map<String,String> map = creatMap();
		
		try {
			File file = new File(fileName);
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			while((tempString = reader.readLine())!=null){
				
				String[] array = tempString.split("\t");

		    	if(array[0].contains("�ɷ����޹�˾")){
			    	String temp = array[0].replaceAll("�ɷ����޹�˾����", "����");
		    		array[0] = temp;
		    	}
		    	if(map.containsKey(array[0])) {
		    		array[0] = map.get(array[0]);
		    	}
		    	
				list.add(array);
			}
			
			reader.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	
	static void writefile(String fileName, List<String[]> list) {
		try {
			
			FileWriter file = new FileWriter(fileName);
			BufferedWriter out = new BufferedWriter(file);
			for(int i=0; i<list.size(); ++i){
				String[] s = list.get(i);
				out.write(s[0]+'\t'+s[1]+'\t'+s[2]+'\r'+'\n');
			}
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	static Map<String,String> creatMap() {
		
		Map<String,String> map = new HashMap<String,String>();
		map.put("�й���ͨ����","��ͨ����");
		map.put("�й���������","��������");
		map.put("��������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��ͨ����ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("�γ�����ҵ����","��������");
		map.put("������ҵ����","��������");
		map.put("���Ƹ�����ҵ����","��������");
		map.put("�γ���������","��������");
		map.put("Ȫ������ҵ����","Ȫ������");
		map.put("Ӫ������ҵ����","Ӫ������");
		map.put("��������ҵ����","��������");
		map.put("̩¡��������������","�㽭̩¡��ҵ����");
		map.put("������ҵ����","��������ҵ����");
		map.put("��������ҵ����","��������");
		map.put("��ݸ��������","��ݸ����");
		map.put("�е�����ҵ����","�е�����");
		map.put("��������ҵ����","��������");
		map.put("����ˮ��ҵ����","��������");
		map.put("��˳����ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("ƽ������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��������ҵ����","��������");
		map.put("��ɽũ��������","��ɽũ����ҵ����");
		map.put("������ũ����ҵ����","����ũ������");
		map.put("����ũ����ҵ����","����ũ������");
		map.put("����ũ��������","����ũ����ҵ����");
		map.put("�㶫�Ϻ�ũ����ҵ����","�Ϻ�ũ������");
		map.put("��ɽ˳��ũ����ҵ����","˳��ũ������");
		map.put("����ũ��������","����ʡũ��������");
		map.put("����ũ��������","����ʡũ��������");
		map.put("����ũ������","����ʡũ��������");
		map.put("�人ũ������","����ʡũ��������");
		map.put("�����н�ũ�����ú�����������","����ʡũ������������");
		map.put("������ũ����ҵ����","���ս���ũ����ҵ����");
		map.put("����ũ����ҵ����","���ս���ũ����ҵ����");
		map.put("ɽ��ũ����������������","ɽ��ʡũ��������");
		map.put("ɽ��ʡũ������������������","ɽ��ʡũ��������");
		map.put("�żҸ�ũ����ҵ����","�żҸ�ũ����");
		map.put("����ʡũ������������������","����ũ��������");
		map.put("����ũ����ҵ����","����ũ������");
		map.put("���ũ����ҵ����","���ũ������");
		map.put("۴��ũ���������","۴������");
		map.put("����۴��ũ���������","۴������");
		map.put("��ɽ����ˮ��ũ�����ú���������","�㶫ʡũ��������");
		map.put("�ɶ�ũ����ҵ����","�ɶ�ũ������");
		map.put("����ũ��������","����ʡũ������������");
		map.put("����ʡũ������������������","����ʡũ������������");
		map.put("�����»�ũ����ҵ����","�»�ũ������");
		map.put("�⽭ũ��������","�⽭ũ����ҵ����");
		map.put("�㽭ʡũ������������������","�㽭ʡũ�������磨��������)");
		map.put("�麣ũ�����ú�������������","�㶫ʡũ��������");
		map.put("̫��ũ����ҵ����","����̫��ũ����ҵ����");
		map.put("Ң����ũ�����ú�������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("����ũ������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("����ʡũ������������������","�ӱ�ʡũ��������");
		map.put("����ʡũ������������������","�����ź�");
		map.put("����ũ������������������","����ʡũ��������");
		map.put("�½�ά���������ũ����������������","�½�ũ��������");
		map.put("�½�ũ������������������","�½�ũ��������");
		map.put("����ũ������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("�ຣʡũ������������������","�ຣʡũ��������");
		map.put("�㶫ʡũ������������������","�㶫ʡũ��������");
		map.put("���ɹ�������ũ������������ʽ����","���ɹ�ũ��������������");
		map.put("�Ĵ�ʡũ������������������","�Ĵ�ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("����ʡũ������������������","����ʡũ��������");
		map.put("ɽ��ʡũ������������������","ɽ��ʡũ��������");
		map.put("ɽ��ʡũ������������","ɽ��ʡũ��������");
		map.put("������ʡũ������������������","������ʡũ��������");
		map.put("�������޹�˾����","��������");
		map.put("���ǰ��ŷ�������","��������");
		map.put("�������޹�˾����","��������");
		map.put("�������޹�˾����","��������");
		map.put("��չ������޹�˾����","��չ����");
		map.put("��¡���޹�˾����","��¡����");
		map.put("����Ϻ�������޹�˾����","�������");
		map.put("�������޹�˾����","��������");
		map.put("���żλ����޹�˾����","��������");
		map.put("�������޹�˾����","��������");
		map.put("������������","�й�����");
		map.put("AEON��","AEON����");
		map.put("������޹�˾����","�������");
		map.put("����������޹�˾����","��������");
		map.put("������ۣ�����","��������");
		map.put("�¼��´�����","�´�����");
		map.put("������������","��������");
		map.put("����������","��������");
		map.put("�ɶ�����ҵ����","�ɶ�����");
		map.put("��������������","����������");
		map.put("�Ϻ�ũ����ҵ����","�Ϻ�ũ������");
		map.put("����ũ������������������","����ʡũ��������");
		map.put("����ũ������","����ʡũ��������");
		map.put("����ʡũ������������","����ʡũ��������");
		map.put("��������ҵ����","��������");
		
		return map;
	}
}
