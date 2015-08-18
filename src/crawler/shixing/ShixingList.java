package crawler.shixing;

import java.util.ArrayList;
import java.util.List;

public class ShixingList {
	private String id;
	private String iname;
	private String caseCode;
	private String age;
	private String sexy;
	private String cardNum;
	private String courtName;
	private String areaName;
	private String partyTypeName;
	private String gistId;
	private String regDate;
	private String gistUnit;
	private String duty;
	private String performance;
	private String disruptTypeName;
	private String publishDate;
	
	public ShixingList() {
		
	}
	
	public ShixingList(String id,String iname,String caseCode,String age,String sexy,String cardNum,String courtName,
				String areaName,String partyTypeName,String gistId,String regDate,String gistUnit,String duty,String performance,
				String disruptTypeName, String publishDate) {
		this.id = id;
		this.iname = iname;
		this.caseCode = caseCode;
		this.age = age;
		this.sexy = sexy;
		this.cardNum = cardNum;
		this.courtName = courtName;
		this.areaName = areaName;
		this.partyTypeName = partyTypeName;
		this.gistId = gistId;
		this.regDate = regDate;
		this.gistUnit = gistUnit;
		this.duty = duty;
		this.performance = performance;
		this.disruptTypeName = disruptTypeName;
		this.publishDate = publishDate;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public void setIname(String iname) {
		this.iname = iname;
	}
	
	public void setCaseCode(String caseCode) {
		this.caseCode = caseCode;
	}
	
	public void setAge(String age) {
		this.age = age;
	}
	
	public void setSexy(String sexy) {
		this.sexy = sexy;
	}
	
	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
	
	public void setCourtName(String courtName) {
		this.courtName = courtName;
	}
	
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	
	public void setPartyTypeName(String partyTypeName) {
		this.partyTypeName = partyTypeName;
	}
	
	public void setGistId(String gistId) {
		this.gistId = gistId;
	}
	
	public void setRegDate(String regDate) {
		this.regDate = regDate;
	}
	
	public void setGistUnit(String gistUnit) {
		this.gistUnit = gistUnit;
	}
	
	public void setDuty(String duty) {
		this.duty = duty;
	}
	
	public void setPerformance(String performance) {
		this.performance = performance;
	}
	
	public void setDisruptTypeName(String disruptTypeName) {
		this.disruptTypeName = disruptTypeName;
	}
	
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	
	public String getId() {
		return id;
	}
	
	public String getIname() {
		return iname;
	}

	public String getCaseCode() {
		return caseCode;
	}
	
	public String getAge() {
		return age;
	}
	
	public String getSexy() {
		return sexy;
	}
	
	public String getCardNum() {
		return cardNum;
	}
	
	public String getCourtName() {
		return courtName;
	}
	
	public String getAreaName() {
		return areaName;
	}
	
	public String getPartyTypeName() {
		return partyTypeName;
	}
	
	public String getGistId() {
		return gistId;
	}
	
	public String getRegDate() {
		return regDate;
	}
	
	public String getGistUnit() {
		return gistUnit;
	}
	
	public String getDuty() {
		return duty;
	}
	
	public String getPerformance() {
		return performance;
	}
	
	public String getDisruptTypeName() {
		return disruptTypeName;
	}
	
	public String getPublishDate() {
		return publishDate;
	}
	
	public String[] info2array() {
		List<String> list = new ArrayList<String>();
		list.add(id);
		list.add(iname);
		list.add(caseCode);
		list.add(age);
		list.add(sexy);
		list.add(cardNum);
		list.add(courtName);
		list.add(areaName);
		list.add(partyTypeName);
		list.add(gistId);
		list.add(regDate);
		list.add(gistUnit);
		list.add(duty);
		list.add(performance);
		list.add(disruptTypeName);
		list.add(publishDate);
		return list.toArray(new String[16]);
	}
}
