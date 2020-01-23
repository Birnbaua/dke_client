package birnbaua.dke_pr_client.basics.enrolled;

import birnbaua.dke_pr_client.basics.CourseForGUI;
import birnbaua.dke_pr_client.basics.Student;

public class StudentCourseRelation {
	private Student student;
	private CourseForGUI course;
	
	public StudentCourseRelation(Student student, CourseForGUI course) {
		this.setStudent(student);
		this.setCourse(course);
	}
	
	public StudentCourseRelation() {
		
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public CourseForGUI getCourse() {
		return course;
	}

	public void setCourse(CourseForGUI course) {
		this.course = course;
	}
	

}
