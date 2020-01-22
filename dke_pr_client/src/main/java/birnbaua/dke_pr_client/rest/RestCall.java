package birnbaua.dke_pr_client.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Properties;

import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.json.JsonHelper;

public class RestCall {
	private final Properties properties;
	
	public RestCall(Properties properties) {
		this.properties = properties;
	}
	
	public List<Course> getCourses() {
		return getCourses((String[]) null);
	}
	
	public List<Course> getCourses(String...params) {
		URL url = null;
		try {
			url = new URL(String.format("%s%s", properties.get("server").toString().replace("\"", ""),properties.get("courses").toString().replace("\"", "")));
		} catch (MalformedURLException e) {e.printStackTrace();}
		ConnectionHelper connHelp = new ConnectionHelper(url);
		JsonHelper<Course> json = new JsonHelper<>();
		//return json.getObjectsFrom(json.parseList(connHelp.get(params)), Course.class);
		return json.getCoursesFrom(json.parseList(connHelp.get(params)));
	}
	
	public List<University> getUniversities(){
		JerseyHelper<University> jersey = null;
		try {
			jersey = new JerseyHelper<University>(String.format("%s%s", properties.get("server").toString().replace("\"", ""),properties.get("university").toString().replace("\"", "")));
		} catch (MalformedURLException e) {e.printStackTrace(); return null;}
		return jersey.getObjects(jersey.get());
	}
	
	public String createStudent(Student student, University uni) {
		String response = null;
		try {
			JerseyHelper<Student> connHelp = new JerseyHelper<>(String.format("%s%s", properties.get("server").toString().replace("\"", ""),
					properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()))+"/insertStudent");
			response = connHelp.post(student);
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch(RuntimeException e) {e.printStackTrace();}
		return response;
	}
}
