package birnbaua.dke_pr_client.test;

import java.net.MalformedURLException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.CourseForGUI;
import birnbaua.dke_pr_client.rest.JerseyHelper;

public class Test {
	
	public static void test() {
		Course c = new Course("test","","","",2.5f,false);
		Course c1 = new Course("test1","","","",2.6f,false);
		List<Course> courses = new LinkedList<>();
		courses.add(c);
		courses.add(c1);
		Gson gson = new Gson();
		String str = gson.toJson(courses, new TypeToken<List<Course>>() {}.getType());
		System.out.println(str);
		List<Course> list = new LinkedList<>();
		try {
			JerseyHelper<Course> jersey = new JerseyHelper<Course>("");
			for(CourseForGUI bc : jersey.getCourses(str)) {
				System.out.print(bc);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		for(Course bc : list) {
			
		}
	}
}
