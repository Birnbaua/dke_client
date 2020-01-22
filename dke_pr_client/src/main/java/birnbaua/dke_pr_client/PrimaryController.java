package birnbaua.dke_pr_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.controlsfx.control.textfield.TextFields;

import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.Study;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.javafx.CustomView;
import birnbaua.dke_pr_client.rest.ConnectionHelper;
import birnbaua.dke_pr_client.rest.RestCall;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML private ChoiceBox<Study> studies;
    @FXML private ChoiceBox<University> uni;
    @FXML private TextField searchMyCourses;
    @FXML private TableView<Course> myCourses;
    @FXML private TextField searchCoursesRest;
    @FXML private Button refreshSearchResults;
    @FXML private TableView<Course> courses;
    @FXML private Text name;
    @FXML private Text matrNr;
    @FXML private Text university;
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private Button login;
    @FXML private Button create;
    @FXML private Button delete;
    @FXML private CheckBox voCheckBox;
    @FXML private CheckBox ueCheckBox;
    @FXML private CheckBox prCheckBox;
    @FXML private CheckBox ksCheckBox;
    @FXML private Spinner<Double> minEcts;
    @FXML private Spinner<Double> maxEcts;
    @FXML private Button saveMyCourses;
    private RestCall rest;
    private ObservableList<Course> myCoursesList = FXCollections.observableArrayList();

    @FXML
    void onSaveMyCourses() {
    	
    }
    
    @FXML
    void onSaveCourses() {

    }
    
    @FXML
    void onMouseClickedStudies() {
    	
    }
    
    @FXML
    void onRefresh() {
    	courses.getItems().clear();
    	courses.getItems().addAll(rest.getCourses(searchCoursesRest.getText()));
    	//System.out.println(searchCoursesRest.getText());
    	//courses.getItems().addAll(rest.getCourses("vl"));
    }
    
    @FXML
    void initialize() {
    	
    	Properties properties = new Properties();
    	try {
    		properties.load(new FileInputStream(new File(App.class.getResource("settings.properties").getPath()).getAbsolutePath()));
			
		} catch (IOException e) {e.printStackTrace();} 
    	rest = new RestCall(properties);
    	
    	CustomView.applyFilter(myCourses, myCoursesList, searchMyCourses, courseTableSetup(myCourses));
		//TextFields.bindAutoCompletion(searchMyCourses, myCoursesList);
    	courseTableSetup(courses);
    	
    	searchCoursesRest.setOnKeyPressed(value -> {
    		if(value.getCode().equals(KeyCode.ENTER)) {
    			onRefresh();
    		}
    	});
    	
    	myCoursesList.add(new Course("weihnachtsmensa","6969","max muehler","drangln",6,false));
    	this.uni.getItems().addListener((ListChangeListener<University>)(x) ->{
    		if(this.uni.getItems().size() > 0) {
    			setStudentDisable(false);
    		} else {
    			setStudentDisable(true);
    		}
    	});
    	
    	this.name.textProperty().addListener((a,o,n) -> {
    		if(n.length() > 0 && n.charAt(0) == '<') {
    			setDisableMyCourses(true);
    		} else if(n.length() > 0) {
    			setDisableMyCourses(false);
    		} else {
    			setDisableMyCourses(true);
    		}
    	});
    	disableComponentsAtStartUp();
    }
    
    private void disableComponentsAtStartUp() {
    	setDisableMyCourses(true);
    	setStudentDisable(true);
    }
    
    private void setDisableMyCourses(boolean disable) {
    	this.myCourses.setDisable(disable);
    	this.searchMyCourses.setDisable(disable);
    	this.saveMyCourses.setDisable(disable);
    }
    
    private void setStudentDisable(boolean disable) {
    	this.first_name.setDisable(disable);
    	this.last_name.setDisable(disable);
    	this.login.setDisable(disable);
    	this.studies.setDisable(disable);
    }
   
    @SuppressWarnings("unchecked")
	private TableColumn<Course,?>[] courseTableSetup(TableView<Course> table) {
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
    	return table.getColumns().toArray(new TableColumn[table.getColumns().size()]);
    }
}
