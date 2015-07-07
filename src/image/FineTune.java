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
	    mysql.connect("com.mysql.jdbc.Driver", "jdbc:mysql://192.168.1.254:3306/PIER_CN", "******", "******","test_insert_bank_BIN",list);
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

		    	if(array[0].contains("股份有限公司")){
			    	String temp = array[0].replaceAll("股份有限公司银行", "银行");
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
		map.put("中国交通银行","交通银行");
		map.put("中国招商银行","招商银行");
		map.put("无锡市商业银行","江苏银行");
		map.put("苏州市商业银行","江苏银行");
		map.put("南通市商业银行","江苏银行");
		map.put("常州市商业银行","江苏银行");
		map.put("淮安市商业银行","江苏银行");
		map.put("徐州市商业银行","江苏银行");
		map.put("扬州市商业银行","江苏银行");
		map.put("盐城市商业银行","江苏银行");
		map.put("镇江市商业银行","江苏银行");
		map.put("连云港市商业银行","江苏银行");
		map.put("盐城商行银行","江苏银行");
		map.put("泉州市商业银行","泉州银行");
		map.put("营口市商业银行","营口银行");
		map.put("嘉兴市商业银行","嘉兴银行");
		map.put("泰隆城市信用社银行","浙江泰隆商业银行");
		map.put("威海商业银行","威海市商业银行");
		map.put("桂林市商业银行","桂林银行");
		map.put("东莞商行银行","东莞银行");
		map.put("承德市商业银行","承德银行");
		map.put("遵义市商业银行","贵州银行");
		map.put("六盘水商业银行","贵州银行");
		map.put("安顺市商业银行","贵州银行");
		map.put("邯郸市商业银行","邯郸银行");
		map.put("平凉市商业银行","甘肃银行");
		map.put("白银市商业银行","甘肃银行");
		map.put("上饶市商业银行","上饶银行");
		map.put("昆山农信社银行","昆山农村商业银行");
		map.put("常熟市农村商业银行","常熟农商银行");
		map.put("常熟农村商业银行","常熟农商银行");
		map.put("深圳农信社银行","深圳农村商业银行");
		map.put("广东南海农村商业银行","南海农商银行");
		map.put("佛山顺德农村商业银行","顺德农商银行");
		map.put("昆明农联社银行","云南省农村信用社");
		map.put("湖北农信社银行","湖北省农村信用社");
		map.put("湖北农信银行","湖北省农村信用社");
		map.put("武汉农信银行","湖北省农村信用社");
		map.put("徐州市郊农村信用合作联社银行","江苏省农村信用联合社");
		map.put("江阴市农村商业银行","江苏江阴农村商业银行");
		map.put("江阴农村商业银行","江苏江阴农村商业银行");
		map.put("山东农村信用联合社银行","山东省农村信用社");
		map.put("山东省农村信用社联合社银行","山东省农村信用社");
		map.put("张家港农村商业银行","张家港农商行");
		map.put("福建省农村信用社联合社银行","福建农村信用社");
		map.put("北京农村商业银行","北京农商银行");
		map.put("天津农村商业银行","天津农商银行");
		map.put("鄞州农村合作银行","鄞州银行");
		map.put("宁波鄞州农村合作银行","鄞州银行");
		map.put("佛山市三水区农村信用合作社银行","广东省农村信用社");
		map.put("成都农村商业银行","成都农商银行");
		map.put("江苏农信社银行","江苏省农村信用联合社");
		map.put("江苏省农村信用社联合社银行","江苏省农村信用联合社");
		map.put("江门新会农村商业银行","新会农商银行");
		map.put("吴江农商行银行","吴江农村商业银行");
		map.put("浙江省农村信用社联合社银行","浙江省农村信用社（合作银行)");
		map.put("珠海农村信用合作社联社银行","广东省农村信用社");
		map.put("太仓农村商业银行","江苏太仓农村商业银行");
		map.put("尧都区农村信用合作社联社银行","云南省农村信用社");
		map.put("贵州省农村信用社联合社银行","贵州省农村信用社");
		map.put("湖南省农村信用社联合社银行","湖南省农村信用社");
		map.put("江西农信联合社银行","江西省农村信用社");
		map.put("河南省农村信用社联合社银行","河南省农村信用社");
		map.put("河南省农村信用社联合社银行","河北省农村信用社");
		map.put("陕西省农村信用社联合社银行","陕西信合");
		map.put("广西农村信用社联合社银行","广西省农村信用社");
		map.put("新疆维吾尔自治区农村信用社联合银行","新疆农村信用社");
		map.put("新疆农村信用社联合社银行","新疆农村信用社");
		map.put("吉林农信联合社银行","吉林省农村信用社");
		map.put("安徽省农村信用社联合社银行","安徽省农村信用社");
		map.put("海南省农村信用社联合社银行","海南省农村信用社");
		map.put("青海省农村信用社联合社银行","青海省农村信用社");
		map.put("广东省农村信用社联合社银行","广东省农村信用社");
		map.put("内蒙古自治区农村信用社联合式银行","内蒙古农村信用社联合社");
		map.put("四川省农村信用社联合社银行","四川省农村信用社");
		map.put("甘肃省农村信用社联合社银行","甘肃省农村信用社");
		map.put("辽宁省农村信用社联合社银行","辽宁省农村信用社");
		map.put("山西省农村信用社联合社银行","山西省农村信用社");
		map.put("山西省农村信用社银行","山西省农村信用社");
		map.put("黑龙江省农村信用社联合社银行","黑龙江省农村信用社");
		map.put("东亚有限公司银行","东亚银行");
		map.put("东亚澳门分行银行","东亚银行");
		map.put("花旗有限公司银行","花旗银行");
		map.put("大新有限公司银行","大新银行");
		map.put("星展香港有限公司银行","星展银行");
		map.put("永隆有限公司银行","永隆银行");
		map.put("香港上海汇丰有限公司银行","汇丰银行");
		map.put("恒生有限公司银行","恒生银行");
		map.put("中信嘉华有限公司银行","中信银行");
		map.put("创兴有限公司银行","创兴银行");
		map.put("中银信用银行","中国银行");
		map.put("AEON信","AEON永旺");
		map.put("大丰有限公司银行","大丰银行");
		map.put("渣打香港有限公司银行","渣打银行");
		map.put("渣打（香港）银行","渣打银行");
		map.put("新加坡大华银行","新大华银行");
		map.put("澳门永亨银行","永亨银行");
		map.put("东亚银银行","东亚银行");
		map.put("成都市商业银行","成都银行");
		map.put("哈尔滨商行银行","哈尔滨银行");
		map.put("上海农村商业银行","上海农商银行");
		map.put("湖南农村信用社联合社银行","湖南省农村信用社");
		map.put("湖南农信银行","湖南省农村信用社");
		map.put("安徽省农村信用社银行","安徽省农村信用社");
		map.put("商丘市商业银行","商丘银行");
		
		return map;
	}
}
