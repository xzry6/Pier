package linkedIn;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class LinkedInSpider {
	public static void main(String[] args) {
		String url = "https://www.linkedin.com/uas/login-submit";
		
		HttpClientBuilder builder = HttpClients.custom();
		CloseableHttpClient httpClient = builder.build();
		
		HttpPost post = new HttpPost(url);
		set(post);
		List<NameValuePair> list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("client_n","718602494:212332562:346061556"));
		list.add(new BasicNameValuePair("client_output","-222948414"));
		list.add(new BasicNameValuePair("client_r","xzry6@mail.missouri.edu:718602494:212332562:346061556"));
		list.add(new BasicNameValuePair("client_ts","1432723389667"));
		list.add(new BasicNameValuePair("client_v","1.0.1"));
		list.add(new BasicNameValuePair("csrfToken","ajax:4384913756679411556"));
		list.add(new BasicNameValuePair("fromEmail",""));
		list.add(new BasicNameValuePair("isJsEnabled","true"));
		list.add(new BasicNameValuePair("loginCsrfParam","e53ff59a-9c5e-4d6a-8d8b-e91a721be3e5"));
		list.add(new BasicNameValuePair("session_key","xzry6@mail.missouri.edu"));
		list.add(new BasicNameValuePair("session_password","19911121"));
		list.add(new BasicNameValuePair("session_redirect",null));
		list.add(new BasicNameValuePair("signin","Sign In"));
		list.add(new BasicNameValuePair("sourceAlias","0_7r5yezRXCiA_H0CRD8sf6DhOjTKUNps5xGTqeX8EEoi"));
		list.add(new BasicNameValuePair("source_app",null));
		list.add(new BasicNameValuePair("trk",null));
		System.out.println(post.getClass().toString()=="class org.apache.http.client.methods.HttpPost");
		try {
			
			
			post.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));  
			CloseableHttpResponse response = httpClient.execute(post);
						
			System.out.println("getStatusCode:"+response.getStatusLine().getStatusCode());
			if(302==response.getStatusLine().getStatusCode()){
				//System.out.println(response);
				Header[] hs = response.getHeaders("Location");
				Header[] cookie = response.getHeaders("Set-Cookie");
//				String li_at=null;
//				for(Header h:cookie){
//					//System.out.println("=========="+h.getValue());
//					if(h.getValue().contains("li_at")){
//						li_at=h.getValue().split("li_at=")[1];
//						System.out.println("li_at:"+li_at);
//						break;
//					}
//				}
				
				System.out.println("Location:"+hs[0].getValue());
				HttpRequestBase redirect = new HttpGet(hs[0].getValue());
				

				redirect.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
				redirect.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//*/
				redirect.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
				redirect.setHeader("Accept-Encoding","gzip, deflate");
				redirect.setHeader("Host","www.linkedin.com");
				redirect.setHeader("Connection","keep-alive");
				redirect.setHeader("Cookie", "lang=\"v=2&lang=en-us\"; JSESSIONID=\"ajax:4384913756679411556\"; bcookie=\"v=2&e53ff59a-9c5e-4d6a-8d8b-e91a721be3e5\"; lidc=\"b=VB63:g=181:u=1:i=1432723205:t=1432809605:s=AQE3VtuzlPp9-eOWQ4iFe6NGm0Aoc-o5\"; visit=\"v=1&M\"; L1e=390560fd; L1c=f30616b; __utma=226841088.1536829439.1432629938.1432629938.1432723302.2; __utmc=226841088; __utmz=226841088.1432723302.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=226841088.guest; _lipt=0_1-m7N8hG_p9JyMYw312193Dzhv80mFhF-C37B2_uiGeC-2vTRqdAjqWolR_r8z7RTY3Q5s8Tl6vtU19Rlkj3mVTL8uI7ct4KLOhWkYRlg-hc5e9kTPeDwnVSPHYUjdk5-xv7xfs1Mo1M7UWW6jMvdp9dgTMU2TBk_5oDNlUR6icL2Tv1fozxN4o65YeU_7IBEck0xxMUWLJbna_mp5OpG2l0TELPoDD7jSN3jVjQbil7yH_z8CK8J0iUrHl-H4UXb3QrwoVOBDQx4XUNub_6ld; share_setting=PUBLIC; sdsc=1%3A1SZM1shxDNbLt36wZwCgPgvN58iw%3D; oz_props_fetch_size1_318216681=1; __utmb=226841088.6.10.1432723302; __utmt=1; li_at=AQECARL3mekA_4bbAAABTZT3ZF4AAAFNlWVBXk2PI0i-lyH6Ea4mOLU9W6AK-GDnD7ITeo6BfSRDpHmz5cG6997b8iwsLA29x0wWPuNer73E1ihfpsIWhj7BKIOd5t1R-qomuNcoRdhcp2NhRB4MJKo");//+li_at);
	            CloseableHttpResponse response2 = httpClient.execute(redirect);
	            System.out.println("Redirect:"+ response2.getStatusLine().getStatusCode());
	            
	            try {  
	                HttpEntity myEntity = response2.getEntity();  
	                System.out.println(myEntity.getContentType());  
	                System.out.println(myEntity.getContentLength());  
	                  
//	                String resString = EntityUtils.toString(myEntity,"UTF-8"); 
//	                PrintStream ps = new PrintStream(new File("linkedin_resp.log"));
//	                ps.println(resString);
//	                ps.flush();
//	                ps.close();
	            }catch(Exception e){
	            	e.printStackTrace();
	            }
			}
		} catch(Exception e) {
			
			e.printStackTrace();
		}
		
	}
	public static void set(HttpRequestBase request) {
		request.setHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
		request.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");//*/
		request.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.2; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0");
		request.setHeader("Accept-Encoding","gzip, deflate");
		request.setHeader("Host","www.linkedin.com");
		request.setHeader("Referer","https://www.linkedin.com/nhome/");
		request.setHeader("Connection","keep-alive");
		request.setHeader("Cookie", "lang=\"v=2&lang=en-us\"; JSESSIONID=\"ajax:4384913756679411556\"; bcookie=\"v=2&e53ff59a-9c5e-4d6a-8d8b-e91a721be3e5\"; bscookie=\"v=1&2015052608445162a22d18-1607-4777-851f-6bdf51d9a4fdAQFrksZwkq0q5BHOXTN7S2hx_pGmMOMd\"; lidc=\"b=VB63:g=181:u=1:i=1432723205:t=1432809605:s=AQE3VtuzlPp9-eOWQ4iFe6NGm0Aoc-o5\"; visit=\"v=1&M\"; L1e=390560fd; L1c=f30616b; __utma=226841088.1536829439.1432629938.1432629938.1432723302.2; __utmc=226841088; __utmz=226841088.1432723302.2.2.utmcsr=baidu|utmccn=(organic)|utmcmd=organic; __utmv=226841088.guest; _lipt=0_1-m7N8hG_p9JyMYw312193Dzhv80mFhF-C37B2_uiGeC-2vTRqdAjqWolR_r8z7RTY3Q5s8Tl6vtU19Rlkj3mVTL8uI7ct4KLOhWkYRlg-hc5e9kTPeDwnVSPHYUjdk5-xv7xfs1Mo1M7UWW6jMvdp9dgTMU2TBk_5oDNlUR6icL2Tv1fozxN4o65YeU_7IBEck0xxMUWLJbna_mp5OpG2l0TELPoDD7jSN3jVjQbil7yH_z8CK8J0iUrHl-H4UXb3QrwoVOBDQx4XUNub_6ld; share_setting=PUBLIC; sdsc=1%3A1SZM1shxDNbLt36wZwCgPgvN58iw%3D; oz_props_fetch_size1_318216681=1; leo_auth_token=\"GST:8OJ1tPAUz6DlBs-1oRbuR-z7t2PgM7pP8miuLoKI1PDoHuV5_JRz-m:1432723325:2730a708c93d5c43281a5a22bf44eb2e89a9bd90\"; __utmb=226841088.6.10.1432723302; __utmt=1");
	}
	
	
}
