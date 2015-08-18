package util.sql;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

public class SQLquery {
	
	private StringBuffer head = new StringBuffer();
	private StringBuffer body = new StringBuffer();
	private StringBuffer foot = new StringBuffer();

	public SQLquery(QueryType qt, String table, Map<String,String> map) {
		sethead(qt,table,map);
	}
	
	public SQLquery(QueryType qt, String table, String[] columns, String[] values) {
		sethead(qt,table,columns,values);
	}
	
	public SQLquery(QueryType qt, String table, Map<String,String> map, Map<String,String> restrictmap, String seperator) {
		setfoot(restrictmap,seperator).sethead(qt,table,map);
	}
	
	public SQLquery(QueryType qt, String table, String[] columns, String[] values, Map<String,String> restrictmap, String seperator) {
		setfoot(restrictmap, seperator).sethead(qt,table,columns,values);
	}
	
	public SQLquery(QueryType qt, String table, String[] columns, String[] values, String[] restrictcolumns, String[] restrictvalues, String seperator) {
		setfoot(restrictcolumns, restrictvalues, seperator).sethead(qt,table,columns,values);
	}
	
	public SQLquery(QueryType qt, String table, Map<String,String> map, String[] restrictcolumns, String[] restrictvalues, String seperator) {
		setfoot(restrictcolumns, restrictvalues, seperator).sethead(qt,table,map);
	}
	
	public String getQuery() {
		return new StringBuffer().append(head).append(body).append(foot).toString();
	}
	
	
	private SQLquery sethead(QueryType qt, String table, Map<String,String> map){
		switch(qt) {
			case SELECT: {
				head.append("SELECT * FROM "+table);
				break;
			}
			case DELETE: {
				head.append("DELETE FROM "+table);
				break;
			}
			case INSERT: {
				head.append("INSERT INTO "+table);
				setinsertbody(qt,map);
				break;
			}
			case UPDATE: {
				head.append("UPDATE "+table+" SET ");
				setupdatebody(qt,map);
				break;
			}
		}
		return this;
	}
	
	
	private SQLquery sethead(QueryType qt, String table, String[] columns, String[] values){
		switch(qt) {
			case SELECT: {
				head.append("SELECT ");
				setselectbody(qt,table,columns);
				break;
			}
			case DELETE: {
				head.append("DELETE FROM "+table);
				break;
			}
			case INSERT: {
				head.append("INSERT INTO "+table);
				setinsertbody(qt,columns,values);
				break;
			}
			case UPDATE: {
				head.append("UPDATE "+table+" SET ");
				setupdatebody(qt,columns,values);
				break;
			}
		}
		return this;
	}
	
	
	private void setselectbody(QueryType qt, String table, String[] columns) {
		if(columns==null||columns.length==0) {
			body.append("* FROM "+table);
			return;
		}
		body.append(Arrays.toString(columns))
				.deleteCharAt(0)
				.deleteCharAt(body.length()-1)
				.append(" FROM "+table);
	}
	
	
	private void setupdatebody(QueryType qt, String[] columns, String[] values) {
		if(columns.length!=values.length) {
			body.append("----------------WARNING: Inputs are not in equal size!-------------------");
			return;
		}
		for(int i=0; i<values.length; ++i) {
			String value = values[i]==null||values[i].length()==0||values[i].equalsIgnoreCase("null")?"NULL":"'"+values[i]+"'";
			body.append(columns[i]).append("=").append(value).append(",");
		}
		body.deleteCharAt(body.length()-1);
	}
	
	
	private void setupdatebody(QueryType qt, Map<String,String> map) {
		Iterator<String> i = map.keySet().iterator();
		while(i.hasNext()) {
			String key = i.next();
			String value = map.get(key)==null||map.get(key).length()==0||map.get(key).equalsIgnoreCase("null")?"NULL":"'"+map.get(key)+"'";
			body.append(key).append("=").append(value).append(",");
		}
		body.deleteCharAt(body.length()-1);
	}

	
	private void setinsertbody(QueryType qt, String[] columns, String[] values) {
		if(columns.length!=values.length) {
			body.append("----------------WARNING: Inputs are not in equal size!-------------------");
			return;
		}
		body.append("(").append(Arrays.toString(columns)).deleteCharAt(1).deleteCharAt(body.length()-1).append(") VALUES(");
		for(int i=0; i<values.length; ++i) {
			String value = values[i]==null||values[i].length()==0||values[i].equalsIgnoreCase("null")?"NULL":"'"+values[i]+"'";
			body.append(value).append(",");
		}
		body.deleteCharAt(body.length()-1).append(")");
	}
	
	
	private void setinsertbody(QueryType qt, Map<String,String> map) {
		StringBuffer values = new StringBuffer(" VALUES(");
		Iterator<String> i = map.keySet().iterator();
		body.append("(");
		while(i.hasNext()) {
			String key = i.next();
			String value = map.get(key)==null||map.get(key).length()==0||
						map.get(key).equalsIgnoreCase("null")?
						"NULL":"'"+map.get(key)+"'";
			body.append(key)
					.append(",");
			values.append(value)
						.append(",");
		}
		values.deleteCharAt(values.length()-1)
					.append(")");
		body.deleteCharAt(body.length()-1)
				.append(")")
				.append(values);
	}
	

	private SQLquery setfoot(String[] columns, String[] values, String seperator){
		if(columns.length!=values.length) {
			System.out.println("invalid input");
			return null;
		}
		foot.append(" WHERE ");
		seperator = " "+seperator+" ";
		for(int i=0; i<values.length; ++i) {
			String value = values[i]==null||values[i].length()==0||values[i].equalsIgnoreCase("null")?"NULL":"'"+values[i]+"'";
			foot.append(columns[i]).append("=").append(value);
			if(i!=values.length-1) foot.append(seperator);
		}
		return this;
	}
	
	
	private SQLquery setfoot(Map<String,String> map, String seperator){
		foot.append(" WHERE ");
		seperator = " "+seperator+" ";
		Iterator<String> i = map.keySet().iterator();
		
		while(i.hasNext()) {
			String key = i.next();
			String value = map.get(key)==null||map.get(key).length()==0||
								map.get(key).equalsIgnoreCase("null")?
								"NULL":"'"+map.get(key)+"'";
			foot.append(key)
					.append("=")
					.append(value)
					.append(seperator);
		}
		foot.delete(foot.length()-seperator.length(), foot.length());
		return this;
	}
}
