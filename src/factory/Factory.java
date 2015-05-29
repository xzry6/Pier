package factory;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;

public abstract class Factory {

	void setLinkedIn(HttpRequestBase request){
		
	}
	
	void setLinkedIn(HttpPost request, String user, String pwd){
		
	}
	
	
}
