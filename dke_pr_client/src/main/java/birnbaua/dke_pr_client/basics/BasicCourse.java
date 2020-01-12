package birnbaua.dke_pr_client.basics;

public class BasicCourse {
	private String name;
	private String id;
	private String lector;
	private String type;
	private float ects;
	private boolean isEnrolledBy;
	
	public BasicCourse(String name, String id, String lector, String type, float ects, boolean isEnrolledBy) {
		this.name = name;
		this.id = id;
		this.lector = lector;
		this.type = type;
		this.ects = ects;
		this.isEnrolledBy = isEnrolledBy;
	}
	
	public static Course toCourse(BasicCourse c) {
		return new Course(c.name,c.id,c.lector,c.type,c.ects,c.isEnrolledBy);
	}
}
