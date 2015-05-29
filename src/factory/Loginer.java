package factory;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public abstract class Loginer {
	protected String url;
	protected String user;
	protected String pwd;
	protected CloseableHttpClient httpClient;
	protected CloseableHttpResponse response;
	
	public Loginer(String url, String user, String pwd) {
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		httpClient = HttpClients.custom().build();
	}
	
	public Loginer login() {
		HttpPost post = init();
		try {
			CloseableHttpResponse redirect = httpClient.execute(post);
			//System.out.println("getStatusCode:"+response.getStatusLine().getStatusCode());
			String url = redirect.getHeaders("Location")[0].getValue();
			HttpRequestBase get = redirect(url);
			this.response = httpClient.execute(get);
			redirect.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return this;
	}
	
	public void printf(String filename) {
		HttpEntity myEntity = response.getEntity();  
        try {
	        String resString = EntityUtils.toString(myEntity,"UTF-8"); 
	        PrintStream ps = new PrintStream(new File(filename));
	        ps.println(resString);
	        ps.flush();
	        ps.close();
	        httpClient.close();
	        response.close();
        } catch(IOException e) {
        	e.printStackTrace();
        } 
	}
	
	protected abstract HttpPost init();
	
	protected abstract HttpGet redirect(String url);
}
