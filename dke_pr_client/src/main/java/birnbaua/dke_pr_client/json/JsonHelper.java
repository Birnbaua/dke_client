package birnbaua.dke_pr_client.json;

import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import birnbaua.dke_pr_client.basics.CourseForGUI;

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
	
	public List<CourseForGUI> getCoursesFrom(String json){
		List<CourseForGUI> courses = new LinkedList<>();
		for(String str : json.split(",")) {
			str = str.replace("[", "");
			str = str.replace("]", "");
			str = str.replace("{", "");
			str = str.replace("}", "");
			courses.add(new CourseForGUI(str.split(":")[1]));
		}
		return courses;
		//return new Gson().fromJson(json, new TypeToken<List<Course>>() {}.getType());
	}
	
	public String parseList(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for(String str : list) {
			builder.append(str);
		}
		return builder.toString();
	}
	
}
