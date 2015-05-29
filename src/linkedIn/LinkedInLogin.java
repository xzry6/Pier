package linkedIn;

import factory.Loginer;
import factory.Type;

public class LinkedInLogin extends Loginer{
	
	@Override
	public Loginer login(Type t, String url, String user, String pwd) {
		super.login(t, url, user, pwd);
		return this;
	}
	@Override
	public void printf(String filename) {
		super.printf(filename);
	}
	
	public static void main(String[] args) {
		String url = "https://www.linkedin.com/uas/login-submit";
		String user = "xzry6@mail.missouri.edu";
		String pwd = "19911121";
		String filename = "linkedin.1";
		try {
			Loginer l = new LinkedInLogin();
			l.login(Type.LinkedIn, url, user, pwd);
			l.printf(filename);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
