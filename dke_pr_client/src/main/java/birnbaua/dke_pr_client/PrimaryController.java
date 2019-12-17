package birnbaua.dke_pr_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;


import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.rest.ConnectionHelper;
import birnbaua.dke_pr_client.rest.RestCall;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML private ChoiceBox<University> studies;
    @FXML private TextField searchMyCourses;
    @FXML private TableView<Course> myCourses;
    @FXML private TextField searchCoursesRest;
    @FXML private Button refreshSearchResults;
    @FXML private TableView<Course> courses;
    @FXML private Text name;
    @FXML private Text matrNr;
    @FXML private Text university;
    private RestCall rest;

    @FXML
    void onSaveMyCourses() {
    	
    }
    
    @FXML
    void onSaveCourses() {

    }
    
    @FXML
    void onRefresh() {
    	courses.getItems().clear();
    	courses.getItems().addAll(rest.getCourses(searchCoursesRest.getText()));
    	System.out.println(searchCoursesRest.getText());
    	//courses.getItems().addAll(rest.getCourses("vl"));
    }
    
    @FXML
    void initialize() {
    	Properties properties = new Properties();
    	try {
    		properties.load(new FileInputStream(new File(App.class.getResource("settings.properties").getPath()).getAbsolutePath()));
			
		} catch (IOException e) {e.printStackTrace();} 
    	rest = new RestCall(properties);
    	courseTableSetup(myCourses);
    	courseTableSetup(courses);
    	searchCoursesRest.setOnKeyPressed(value -> {
    		if(value.getCode().equals(KeyCode.ENTER)) {
    			onRefresh();
    		}
    	});
    	myCourses.getItems().add(new Course("weihnachtsmensa","6969","max muehler","drangln",6,false));
    }
   
    @SuppressWarnings("unchecked")
	private void courseTableSetup(TableView<Course> table) {
    	TableColumn<Course,String> title = new TableColumn<>("Title");
    	TableColumn<Course,String> id = new TableColumn<>("Course ID");
    	TableColumn<Course,String> type = new TableColumn<>("Type");
    	TableColumn<Course,Number> ects = new TableColumn<>("Ects");
    	TableColumn<Course,String> lector = new TableColumn<>("Lector");
    	TableColumn<Course,Boolean> isEnrolledBy = new TableColumn<>("Enrolled");
    	
    	table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    	table.setEditable(true);
    	isEnrolledBy.setEditable(true);
    	title.setPrefWidth(50);
    	id.setPrefWidth(50);
    	type.setPrefWidth(50);
    	ects.setPrefWidth(50);
    	lector.setPrefWidth(50);
    	isEnrolledBy.setPrefWidth(50);
    	
    	title.setCellValueFactory(value -> value.getValue().getName());
    	id.setCellValueFactory(value -> value.getValue().getId());
    	type.setCellValueFactory(value -> value.getValue().getType());
    	ects.setCellValueFactory(value -> value.getValue().getEcts());
    	lector.setCellValueFactory(value -> value.getValue().getLector());
    	isEnrolledBy.setCellValueFactory(value -> value.getValue().getIsEnrolledBy());
    	
    	isEnrolledBy.setCellFactory(CheckBoxTableCell.forTableColumn(isEnrolledBy));
    	table.getColumns().addAll(title,id,type,ects,lector,isEnrolledBy);
    }
}
