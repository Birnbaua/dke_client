package birnbaua.dke_pr_client.basics;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseForGUI {
	private SimpleStringProperty name;
	private SimpleStringProperty id;
	private SimpleStringProperty lector;
	private SimpleStringProperty type;
	private SimpleFloatProperty ects;
	private SimpleBooleanProperty isEnrolledBy;
	
	public CourseForGUI(String title, String id, String lector, String type, float ects, boolean isEnrolledBy) {
		this.name = new SimpleStringProperty(title);
		this.id = new SimpleStringProperty(id);
		this.lector = new SimpleStringProperty(lector);
		this.type = new SimpleStringProperty(type);
		this.ects = new SimpleFloatProperty(ects);
		this.isEnrolledBy = new SimpleBooleanProperty(isEnrolledBy);
	}
	
	public CourseForGUI(String name) {
		this(name," - "," - "," - ",0,false);
	}
	
	public CourseForGUI() {
		this(" - "," - "," - "," - ",0,false);
	}
	

	public SimpleStringProperty getName() {
		return name;
	}

	public void setName(SimpleStringProperty name) {
		this.name = name;
	}

	public SimpleStringProperty getId() {
		return id;
	}

	public void setId(SimpleStringProperty id) {
		this.id = id;
	}

	public SimpleStringProperty getLector() {
		return lector;
	}

	public void setLector(SimpleStringProperty lector) {
		this.lector = lector;
	}

	public SimpleStringProperty getType() {
		return type;
	}

	public void setType(SimpleStringProperty type) {
		this.type = type;
	}

	public SimpleBooleanProperty getIsEnrolledBy() {
		return isEnrolledBy;
	}

	public void setIsEnrolledBy(SimpleBooleanProperty isEnrolledBy) {
		this.isEnrolledBy = isEnrolledBy;
	}

	public SimpleFloatProperty getEcts() {
		return ects;
	}

	public void setEcts(SimpleFloatProperty ects) {
		this.ects = ects;
	}
	
	public Course toCourse() {
		return new Course(name.get(),id.get(),lector.get(),type.get(),ects.get(),isEnrolledBy.get());
	}
}
