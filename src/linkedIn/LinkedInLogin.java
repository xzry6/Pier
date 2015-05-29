package linkedIn;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

import factory.CookieFactory;
import factory.Loginer;
import factory.PostFactory;
import factory.Type;

public class LinkedInLogin extends Loginer{
	public LinkedInLogin(String url, String user, String pwd) {
		super(url, user, pwd);
	}
	
	protected HttpPost init() {
		HttpPost post = new HttpPost(url);
		new CookieFactory(Type.LinkedIn, post);
		new PostFactory(Type.LinkedIn, post, user, pwd);
		return post;
	}
	
	protected HttpGet redirect(String url) {
		HttpGet get = new HttpGet(url);
		new CookieFactory(Type.LinkedIn, get);
		return get;
	}

	
	public static void main(String[] args) {
		String url = "https://www.linkedin.com/uas/login-submit";
		String user = "xzry6@mail.missouri.edu";
		String pwd = "19911121";
		String filename = "linkedin.log";
		try {
			new LinkedInLogin(url,user,pwd).login().printf(filename);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
