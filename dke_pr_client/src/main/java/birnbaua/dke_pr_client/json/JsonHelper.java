package birnbaua.dke_pr_client.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonHelper {
	
	public static <T> String getJsonFrom(T object) {
		return new Gson().toJson(object);
	}
	
	public static <T> String getJsonFrom(List<T> objects) {
		return new Gson().toJson(objects);
	}
	
	public static <T> T getObjectFrom(String json, Class<T> c) {
		return new Gson().fromJson(json, c);
	}
	
	public static <T> List<T> getObjectsFrom(String json, Class<T> c) {
		return new Gson().fromJson(json, new TypeToken<List<T>>() {}.getType());
	}
}
