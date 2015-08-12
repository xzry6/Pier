package crawler.factory;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;

public class RequestBuilder {
	private String url;
	private String user;
	private String pwd;
	private String additional;
	public RequestBuilder() {
		
	}
	public RequestBuilder(String url, String user, String pwd, String additional){
		this.url = url;
		this.user = user;
		this.pwd = pwd;
		this.additional = additional;
	}
	public HttpPost buildPost(Type t) {
		PostFactory post = new PostFactory();
		CookieFactory header = new CookieFactory();
		return header.set(t,post.set(t,url,user,pwd,additional).getPost()).getPost();
	}
	public HttpGet buildGet(Type t, String url) {
		CookieFactory header = new CookieFactory();
		HttpGet get = new HttpGet(url);
		return header.set(t, get).getGet();
	}
}
