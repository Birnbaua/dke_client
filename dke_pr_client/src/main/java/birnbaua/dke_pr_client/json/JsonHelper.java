package birnbaua.dke_pr_client.json;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonHelper<T> {
	
	public String getJsonFrom(T object) {
		return new Gson().toJson(object);
	}
	
	public String getJsonFrom(List<T> objects) {
		return new Gson().toJson(objects);
	}
	
	public T getObjectFrom(String json, Class<T> c) {
		return new Gson().fromJson(json, c);
	}
	
	public List<T> getObjectsFrom(String json, Class<T> c) {
		return new Gson().fromJson(json, new TypeToken<List<T>>() {}.getType());
	}
	
	public String parseList(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for(String str : list) {
			builder.append(str);
		}
		return builder.toString();
	}
}
