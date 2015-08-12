package crawler.linkedin;

import crawler.factory.Loginer;
import crawler.factory.Type;

public class LinkedInLogin extends Loginer{
	
	@Override
	public Loginer login(Type t, String url, String user, String pwd, String additional) {
		super.login(t, url, user, pwd, additional);
		return this;
	}
	@Override
	public void printf(String filename) {
		super.printf(filename);
	}
	
	public static void main(String[] args) {
		String url = "https://www.linkedin.com/uas/login-submit";
		String user = "********";
		String pwd = "******";
		String filename = "linkedin.1";
		try {
			Loginer l = new LinkedInLogin();
			l.login(Type.LinkedIn, url, user, pwd, null);
			l.printf(filename);
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
