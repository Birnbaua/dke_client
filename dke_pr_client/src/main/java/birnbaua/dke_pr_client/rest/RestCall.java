package birnbaua.dke_pr_client.rest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import birnbaua.dke_pr_client.basics.CourseForGUI;
import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.Study;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.basics.enrolled.StudentCourseRelation;
import birnbaua.dke_pr_client.json.JsonHelper;

public class RestCall {
	private final Properties properties;
	
	public RestCall(Properties properties) {
		this.properties = properties;
	}
	
	public List<CourseForGUI> getCourses(University uni, String search, String coursetype, String lector, String id, int ects) {
		JerseyHelper<CourseForGUI> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
			 	 	 properties.get("courses").toString().replace("\"", "") + "?" +
			 	 	 "uni=" + uni.getName() +
			 	 	 "&search=" + search + 
			 	 	 (id == null ? "": ("&id=" + id)) +
			 	 	 "&coursetype=" + coursetype +
			 	 	 (ects < 0 ? "":"&ects=" + ects);
		String response = null;
		System.out.println("GET: " + uri);
		try {
			jersey = new JerseyHelper<CourseForGUI>(uri);
			response = jersey.get();
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (RuntimeException e) {e.printStackTrace();}
		return jersey.getCourses(response);
	}
	
	/**
	 * 
	 * @param params
	 * @return
	 */
	public List<CourseForGUI> getCourses(String...params) {
		URL url = null;
		try {
			url = new URL(String.format("%s%s", properties.get("server").toString().replace("\"", ""),properties.get("courses").toString().replace("\"", "")));
		} catch (MalformedURLException e) {e.printStackTrace();}
		ConnectionHelper connHelp = new ConnectionHelper(url);
		JsonHelper<CourseForGUI> json = new JsonHelper<>();
		return json.getCoursesFrom(json.parseList(connHelp.get(params)));
	}
	
	public String deleteStudent(Student student, University uni) {
		String response = null;
		JerseyHelper<Student> jersey = null;
		/*
		 * TODO anpassen der uri
		 */
		String uri = properties.get("server").toString().replace("\"", "") + 
			 	 	 properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()) + 
			 	 	 "/courseRegistration";
		System.out.println("DELETE: " + uri);
		try {
			jersey = new JerseyHelper<>(uri);
			response = jersey.delete(student);
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (RuntimeException e) {e.printStackTrace();}
		
		return response;
	}
	
	/**
	 * 
	 * @return a list of all universities listed in the meta-service.
	 */
	public List<University> getAllUniversities(){
		JerseyHelper<University> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
			 	 	 properties.get("university").toString().replace("\"", "").replace("{uni}", "uni");
		String response = null;
		System.out.println("GET: " + uri);
		try {
			jersey = new JerseyHelper<University>(uri);
			response = jersey.get();
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (RuntimeException e) {e.printStackTrace();}
		return jersey.getUniversities(response);
	}
	
	/**
	 * 
	 * @param student Student to be inserted
	 * @param uni University in with the student should be created.
	 * @return Response of the POST.
	 */
	public String createStudent(Student student, University uni) {
		String uri = properties.get("server").toString().replace("\"", "") + 
					 properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()) + 
					 "/insertStudent";
		String response = null;
		System.out.println("POST: " + uri);
		try {
			JerseyHelper<Student> jersey = new JerseyHelper<>(uri);
			response = jersey.post(student);
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (RuntimeException e) {e.printStackTrace();}
		return response;
	}
	
	/**
	 * 
	 * @param uni
	 * @return
	 */
	public List<Study> getAllStudies(University uni){
		JerseyHelper<Study> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
				 	 properties.get("university").toString().replace("\"", "").replace("{uni}", "studies");
		String response = null;
		System.out.println("GET: " + uri);
		try {
			jersey = new JerseyHelper<Study>(uri);
			response = jersey.get();
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch (RuntimeException e) {e.printStackTrace();}
		return jersey.getObjects(response);
	}
	
	/**
	 * 
	 * @param student Current Student of the session.
	 * @param courses List of courses the student wants to be enrolled in.
	 * @param uni University with offers these courses.
	 * @return a list of Responses of the courses.
	 */
	public List<String> postStudentCourseRelation(Student student, List<CourseForGUI> courses, University uni) {
		List<String> response = new LinkedList<>();
		JerseyHelper<StudentCourseRelation> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
			 	 	 properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()) + 
			 	 	 "/courseRegistration";
		System.out.println("POST: " + uri);
		for(CourseForGUI course : courses) {
			try {
				jersey = new JerseyHelper<>(uri);
				response.add(jersey.post(new StudentCourseRelation(student, course)));
			} catch (MalformedURLException e) {e.printStackTrace();
			} catch (RuntimeException e) {e.printStackTrace();}
		}
		return response;
	}
	
	/**
	 * 
	 * @param student Current Student of the session.
	 * @param courses List of courses the student wants to leave.
	 * @param uni University with offers these courses.
	 * @return a list of Responses of the courses.
	 */
	public List<String> deleteStudentCourseRelation(Student student, List<CourseForGUI> courses, University uni) {
		List<String> response = new LinkedList<>();
		JerseyHelper<StudentCourseRelation> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
			 	 	 properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()) + 
			 	 	 "/courseRegistration";
		System.out.println("DELETE: " + uri);
		for(CourseForGUI course : courses) {
			try {
				jersey = new JerseyHelper<>(uri);
				response.add(jersey.delete(new StudentCourseRelation(student, course)));
			} catch (MalformedURLException e) {e.printStackTrace();
			} catch (RuntimeException e) {e.printStackTrace();}
		}
		return response;
	}
	
	/**
	 * 
	 * @param student Current Student of the session.
	 * @param uni University of the student.
	 * @return a list of courses in which the student is enrolled.
	 */
	public List<CourseForGUI> getCoursesOfStudent(Student student, University uni){
		JerseyHelper<CourseForGUI> jersey = null;
		String uri = properties.get("server").toString().replace("\"", "") + 
				 	 properties.get("university").toString().replace("\"", "").replace("{uni}", "uni");
		System.out.println(uri);
		try {
			jersey = new JerseyHelper<CourseForGUI>(uri);
		} catch (MalformedURLException e) {e.printStackTrace(); return null;}
		return jersey.getCourses(jersey.get());
	}
	
	
	
	/*
	 * 
	 */
	public String createStudy(Study study, University uni) {
		String response = null;
		try {
			JerseyHelper<Study> connHelp = new JerseyHelper<>(String.format("%s%s", properties.get("server").toString().replace("\"", ""),
					properties.get("university").toString().replace("\"", "").replace("{uni}", uni.getName()))+"/insertStudy");
			response = connHelp.post(study);
		} catch (MalformedURLException e) {e.printStackTrace();
		} catch(RuntimeException e) {e.printStackTrace();}
		return response;
	}
}
