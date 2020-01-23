package birnbaua.dke_pr_client.basics;

public class Course {
	private String name;
	private String id;
	private String lector;
	private String type;
	private float ects;
	private boolean isEnrolledBy;
	
	public Course(String name, String id, String lector, String type, float ects, boolean isEnrolledBy) {
		this.name = name;
		this.id = id;
		this.lector = lector;
		this.type = type;
		this.ects = ects;
		this.isEnrolledBy = isEnrolledBy;
	}
	
	public static CourseForGUI toCourse(Course c) {
		return new CourseForGUI(c.name,c.id,c.lector,c.type,c.ects,c.isEnrolledBy);
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s, ECTS: %f", name, ects);
	}
}
