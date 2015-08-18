package util;

import java.util.Arrays;

import crawler.shixing.ShixingList;

public class TestStringArray {
	public static void main(String[] args) {
//		String[] a = {"aaa:bbb","ccc:ddd"};
//		for(int i=0; i<a.length; ++i) {
//			a[i] = a[i].substring(a[i].indexOf(":")+1);
//			System.out.println(a[i]);
//		}
//		System.out.println(Arrays.toString(a));
//		String s = " 其他有履行能力而拒不履行生效法律文书确定义务\"";
//		s = s.substring(s.indexOf(":")+1);
//		if(s.contains("}"))
//			s = s.split("}")[0];
//		if(s.contains("\""))
//			s = s.split("\"")[1];
//		System.out.print(s);
		String s = "{\"id\":1503431,\"iname\":\"吴发宝\",\"caseCode\":\"(2011)西执字第00545号\",\"age\":32,\"sexy\":\"男\",\"cardNum\":\"3601241983****0092\",\"courtName\":\"南昌市西湖区人民法院\",\"areaName\":\"江西\",\"partyTypeName\":\"580\",\"gistId\":\"(2010)西民二初字第659号\",\"regDate\":\"2011年11月23日\",\"gistUnit\":\"南昌市人西湖区人民法院\",\"duty\":\"一、被告胡江辉于本判决书生效后三日内向原告中国邮政储蓄有限责任公司南昌市分行归款借款本金84406.72元及利息。\n二、被告张志军、吴发宝对上述款项承担连带清偿责任。\n如果未按本判决指定的期间履行给付金钱义务，应当依照《中华人民共和国民事诉讼法》第二百二十九条之规定，加倍支付迟延履行期间的债务利息。\n本案由原告预交的案件受理费2650元，由被告涂胡江辉、张志军、吴发宝承担，跟随上述款一并偿付。\n\",\"performance\":\"全部未履行\",\"disruptTypeName\":\"其他有履行能力而拒不履行生效法律文书确定义务\",\"publishDate\":\"2015年04月27日\"}";
		//String s = "{\"id\":1500580,\"iname\":\"张协文\",\"caseCode\":\"(2015)高法执字第00182号\",\"age\":32,\"sexy\":\"男\",\"cardNum\":\"3707851983****5173\",\"courtName\":\"高密市人民法院\",\"areaName\":\"山东\",\"partyTypeName\":\"580\",\"gistId\":\"（2014）高民初字第2455号\",\"regDate\":\"2015年01月04日\",\"gistUnit\":\"高密市人民法院\",\"duty\":\"被告欠原告329696.41元\",\"performance\":\"全部未履行\",\"disruptTypeName\":\"违反财产报告制度,其他有履行能力而拒不履行生效法律文书确定义务\",\"publishDate\":\"2015年04月27日\"}";
		System.out.println(s.replaceAll("\n", ""));
		ShixingList sx = JsonUtils.json2bean(s.replaceAll("\n", ""),ShixingList.class);
		System.out.println(sx.getAge());
		System.out.println(sx.getAreaName());
		System.out.println(sx.getId());
		System.out.println(sx.getCardNum());
		System.out.println(sx.getCaseCode());
		System.out.println(sx.getCourtName());
		System.out.println(sx.getDisruptTypeName());
		System.out.println(sx.getDuty());
		System.out.println(sx.getGistId());
		System.out.println(sx.getGistUnit());
		System.out.println(sx.getIname());
		System.out.println(sx.getPartyTypeName());
		System.out.println(sx.getPerformance());
		System.out.println(sx.getPublishDate());
		System.out.println(sx.getRegDate());
		System.out.println(sx.getSexy());
		System.out.println(Arrays.toString(sx.info2array()));
}
}
