package birnbaua.dke_pr_client.json;

import java.lang.reflect.Type;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonHelper {
	
	public static <T> String getJsonFrom(List<T> objects, Class<T> c) {
		
		//TODO
		return null;
	}
	
	public static <T> List<T> getObjectsFrom(String json, Class<T> c) {
		Type listType = new TypeToken<List<T>>() {}.getType();
		return new Gson().fromJson(json,listType);
	}
}
