package factory;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;

public class PostFactory extends Factory{
	private List<NameValuePair> list;
	public PostFactory(Type cookie, HttpPost request, String user, String pwd){
		switch(cookie) {
			case LinkedIn: setLinkedIn(request,user,pwd);
		}
	}
	@Override
	void setLinkedIn(HttpPost request, String user, String pwd){
		list = new ArrayList<NameValuePair>();
		list.add(new BasicNameValuePair("client_n","718602494:212332562:346061556"));
		list.add(new BasicNameValuePair("client_output","-222948414"));
		list.add(new BasicNameValuePair("client_r",user+":718602494:212332562:346061556"));
		list.add(new BasicNameValuePair("client_ts","1432723389667"));
		list.add(new BasicNameValuePair("client_v","1.0.1"));
		list.add(new BasicNameValuePair("csrfToken","ajax:4384913756679411556"));
		list.add(new BasicNameValuePair("fromEmail",""));
		list.add(new BasicNameValuePair("isJsEnabled","true"));
		list.add(new BasicNameValuePair("loginCsrfParam","e53ff59a-9c5e-4d6a-8d8b-e91a721be3e5"));
		list.add(new BasicNameValuePair("session_key",user));
		list.add(new BasicNameValuePair("session_password",pwd));
		list.add(new BasicNameValuePair("session_redirect",null));
		list.add(new BasicNameValuePair("signin","Sign In"));
		list.add(new BasicNameValuePair("sourceAlias","0_7r5yezRXCiA_H0CRD8sf6DhOjTKUNps5xGTqeX8EEoi"));
		list.add(new BasicNameValuePair("source_app",null));
		list.add(new BasicNameValuePair("trk",null));
		try {
			request.setEntity(new UrlEncodedFormEntity(list, "UTF-8"));
		} catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
	}
	

}
