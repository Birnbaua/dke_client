package birnbaua.dke_pr_client.rest;

import java.net.MalformedURLException;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class JerseyHelper<T> {
	private String url;

	
	public JerseyHelper(String url) throws MalformedURLException {
		this.url = url;
	}
	
	/**
	 * Performes a GET on the URL given in the Constructor. The parameter is a query parameter and should be given in a format like: 'param=true'
	 * @param param Query parameter
	 * @return
	 * @throws RuntimeException if statuscode != 200
	 */
	public String get(String param) throws RuntimeException{
		Client client = Client.create();
		WebResource webResource = null;
		if(param != null) {
			webResource = client.resource(url + "?" + param);
		} else {
			webResource = client.resource(url);
		}
		ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
		
		if(response.getStatus() != 200) {
			throw new RuntimeException("Failed with HTTP code: "+ response.getStatus());
		}
		
		String fetched = response.getEntity(String.class);
		return fetched;
	}
	
	/**
	 * Performes a GET on the URL given in the Constructor.
	 * @return
	 * @throws RuntimeException RuntimeException if statuscode != 200
	 */
	public String get() throws RuntimeException{
		return get(null);
	}
	
	/**
	 * Performes a Post on the URL given in the Constructor. The parameter is a query parameter and should be given in a format like: 'param=true'
	 * @param t An object of T.
	 * @param param Query parameter
	 * @return
	 * @throws RuntimeException RuntimeException if statuscode != 201
	 */
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
	
	/**
	 * Performes a Post on the URL given in the Constructor.
	 * @param t An object of T.
	 * @return
	 * @throws RuntimeException RuntimeException if statuscode != 201
	 */
	public String post(T t) throws RuntimeException{
		return post(t,null);
	}
	
	/**
	 * 
	 * @param json
	 * @return
	 */
	public List<T> getObjects(String json) {
		return new Gson().fromJson(json, new TypeToken<List<T>>() {}.getType());
	}
}
