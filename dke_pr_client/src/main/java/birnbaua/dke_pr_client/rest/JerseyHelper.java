package birnbaua.dke_pr_client.rest;

import java.net.MalformedURLException;

import com.google.gson.Gson;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyHelper<T> {
	private String url;

	
	public JerseyHelper(String url) throws MalformedURLException {
		this.url = url;
	}
	
	public String get(String param) throws RuntimeException{
		Client client = Client.create();
		WebResource webResource = null;
		if(param != null) {
			webResource = client.resource(url + "?" + param);
		} else {
			webResource = client.resource(url);
		}
		ClientResponse response = webResource.accept("application(json").get(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new RuntimeException("Failed with HTTP code: "+ response.getStatus());
		}
		
		String fetched = response.getEntity(String.class);
		return fetched;
	}
	
	public String get() throws RuntimeException{
		return get(null);
	}
	
	public String post(T t, String param) throws RuntimeException{
		Client client = Client.create();
		WebResource webResource = null;
		if(param != null) {
			webResource = client.resource(url + "?" + param);
		} else {
			webResource = client.resource(url);
		}
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, new Gson().toJson(t));
		if(response.getStatus() != 201) {
			throw new RuntimeException("Failed with HTTP code: "+ response.getStatus());
		}
		return response.getEntity(String.class);
	}
	
	public String post(T t) throws RuntimeException{
		return post(t,null);
	}
}
