package birnbaua.dke_pr_client;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import birnbaua.dke_pr_client.basics.CourseForGUI;
import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.Study;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.javafx.CustomView;
import birnbaua.dke_pr_client.rest.RestCall;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML private ChoiceBox<Study> studies;
    @FXML private ChoiceBox<University> uni;
    @FXML private TextField searchMyCourses;
    @FXML private TableView<CourseForGUI> myCourses;
    @FXML private TextField searchCoursesRest;
    @FXML private Button refreshSearchResults;
    @FXML private TableView<CourseForGUI> courses;
    @FXML private Text name;
    @FXML private Text matrNr;
    @FXML private Text university;
    @FXML private Text uniDesc;
    @FXML private TextField first_name;
    @FXML private TextField last_name;
    @FXML private TextField matrikelNr;
    @FXML private Button login;
    @FXML private Button create;
    @FXML private Button delete;
    @FXML private CheckBox voCheckBox;
    @FXML private CheckBox ueCheckBox;
    @FXML private CheckBox prCheckBox;
    @FXML private CheckBox ksCheckBox;
    @FXML private CheckBox pjCheckBox;
    @FXML private Spinner<Integer> ects;
    @FXML private Button saveMyCourses;
    @FXML private Button saveCourses;
    private RestCall rest;
    private Student student;
    private ObservableList<CourseForGUI> myCoursesList = FXCollections.observableArrayList();

    @FXML
    void onSaveMyCourses() {
    	List<CourseForGUI> list = new LinkedList<>();
    	for(CourseForGUI c : this.myCoursesList) {
    		if(c.getIsEnrolledBy().get() == false) {
    			list.add(c);
    		}
    	}
    	this.rest.deleteStudentCourseRelation(student, list, this.uni.getValue());
    	this.myCoursesList.clear();
    	this.myCoursesList.addAll(this.rest.getCoursesOfStudent(student, uni.getValue()));
    	this.saveCourses.setStyle("-fx-background-color: whitesmoke;");
    }
    
    @FXML
    void onSaveCourses() {
    	List<CourseForGUI> list = new LinkedList<>();
    	for(CourseForGUI c : this.courses.getItems()) {
    		if(c.getIsEnrolledBy().get() == true) {
    			list.add(c);
    		}
    	}
    	this.rest.postStudentCourseRelation(student, list, this.uni.getValue());
    	this.myCoursesList.clear();
    	this.myCoursesList.addAll(this.rest.getCoursesOfStudent(student, uni.getValue()));
    	this.saveCourses.setStyle("-fx-background-color: whitesmoke;");
    }
    
    @FXML
    void onMouseClickedStudies() {
    	
    }
    
    @FXML
    void onLogin() {
    	this.uni.setDisable(true);
    	setDisableMyCourses(false);
    	this.name.setText(first_name.getText() + " " + last_name.getText());
    	this.matrNr.setText(this.matrikelNr.getText());
    	this.myCoursesList.clear();
    	this.student = new Student(this.first_name.getText(), this.last_name.getText(), this.matrikelNr.getText());
    	this.myCoursesList.addAll(this.rest.getCoursesOfStudent(student, this.uni.getValue()));
    }
    
    @FXML
    void onLogout() {
    	this.uni.setDisable(false);
    	this.myCoursesList.clear();
    	this.courses.getItems().clear();
    	this.student = null;
    	this.first_name.setText("");
    	this.last_name.setText("");
    	this.matrikelNr.setText("");
    	this.name.setText("<name>");
    }
    
    @FXML
    void onCreate() {
    	UserController userController = null;
    	Parent root;
    	FXMLLoader loader;
    	try {
    		loader = new FXMLLoader(UserController.class.getResource("EditUser.fxml"));
    		root = loader.load();
    		userController = loader.getController();
    		userController.setInformation(this.rest,this.uni);
    		Stage stage = new Stage();
    		stage.setTitle("USER");
    		stage.setScene(new Scene(root));
    		stage.showAndWait();
    		Student student = userController.getStudent();
    		this.first_name.setText(student.getFIRSTNAME());
    		this.last_name.setText(student.getLASTNAME());
    		this.matrikelNr.setText(student.getMATRNR());
    		
    		onLogin();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}
    }
    
    /*
     * is not going to be used
     */
    /*
    @FXML
    void onDelete() {
    	this.rest.deleteStudentCourseRelation(student, this.rest.getCoursesOfStudent(student, this.uni.getValue()), this.uni.getValue());
    	this.rest.deleteStudent(student, uni.getValue());
    }
    */
    
    @FXML
    void onRefresh() {
    	courses.getItems().clear();
    	if(this.ksCheckBox.isSelected()) {
        	courses.getItems().addAll(rest.getCourses(uni.getValue(), searchCoursesRest.getText(), "ks", null, null, ects.getValue()));
    	}
    	if(this.voCheckBox.isSelected()) {
    		courses.getItems().addAll(rest.getCourses(uni.getValue(), searchCoursesRest.getText(), "vo", null, null, ects.getValue()));
    	}
    	if(this.ueCheckBox.isSelected()) {
    		courses.getItems().addAll(rest.getCourses(uni.getValue(), searchCoursesRest.getText(), "ue", null, null, ects.getValue()));
    	}
    	if(this.prCheckBox.isSelected()) {
    		courses.getItems().addAll(rest.getCourses(uni.getValue(), searchCoursesRest.getText(), "pr", null, null, ects.getValue()));
    	}
    	if(this.pjCheckBox.isSelected()) {
    		courses.getItems().addAll(rest.getCourses(uni.getValue(), searchCoursesRest.getText(), "pj", null, null, ects.getValue()));
    	}
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
    	
    	this.courses.getItems().addListener((ListChangeListener<CourseForGUI>) (a) ->{
    		a.next();
    		for(CourseForGUI c : a.getAddedSubList()) {
    			setListener(c,this.saveCourses);
    		}
    	});
    	
    	this.myCoursesList.addListener((ListChangeListener<CourseForGUI>) (a) ->{
    		a.next();
    		for(CourseForGUI c : a.getAddedSubList()) {
    			setListener(c,this.saveMyCourses);
    		}
    	});
    	
    	this.uni.valueProperty().addListener((a,o,n) -> {
    		if(n.getName().equalsIgnoreCase("all")) {
    			setStudentDisable(true);
    		} else {
    			setStudentDisable(false);
    			this.university.setText(n.getName());
    			this.uniDesc.setText(this.uni.getValue().getName());
    			/*
    			 * studies request
    			 */
    			//this.studies.getItems().addAll(this.rest.getAllStudies(n));
    		}
    	});
    	
    	
    	this.studies.valueProperty().addListener((a,o,n) -> {
    		if(n.getName().equalsIgnoreCase("none")) {
    			/*
    			 * TODO get all courses of selected university
    			 */
    		} else {
    			/*
    			 * TODO get all courses of selected uni and study
    			 */
    		}
    	});
    	
    	
    	this.name.textProperty().addListener((a,o,n) -> {
    		if(n.length() > 0 && n.charAt(0) == '<') {
    			this.courses.getColumns().get(courses.getColumns().size()-1).setVisible(false);
    			setDisableMyCourses(true);
    		} else if(n.length() > 0) {
    			setDisableMyCourses(false);
    			this.courses.getColumns().get(courses.getColumns().size()-1).setVisible(true);
    		} else {
    			this.courses.getColumns().get(courses.getColumns().size()-1).setVisible(false);
    			setDisableMyCourses(true);
    		}
    	});
    	
    	this.uni.getItems().add(new University("all"));
    	this.uni.getSelectionModel().selectFirst();
    	this.studies.getItems().add(new Study("none"));
    	this.studies.getSelectionModel().selectFirst();
    	
    	//fetch all Unis of Meta Serivce
    	this.uni.getItems().addAll(this.rest.getAllUniversities());
    	this.ects.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(-1, 15));
    	this.ects.getValueFactory().setValue(-1);
    	
    	disableComponentsAtStartUp();
    }
    
    private void disableComponentsAtStartUp() {
		this.courses.getColumns().get(courses.getColumns().size()-1).setVisible(false);
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
    	this.matrikelNr.setDisable(disable);
    	this.login.setDisable(disable);
    	this.studies.setDisable(disable);
    }
    
    private void setListener(CourseForGUI course, Button button) {
    	course.getIsEnrolledBy().addListener((a,o,n) -> {
    		button.setStyle("-fx-background-color: tomato; ");
    	});
    }
   
    @SuppressWarnings("unchecked")
	private TableColumn<CourseForGUI,?>[] courseTableSetup(TableView<CourseForGUI> table) {
    	TableColumn<CourseForGUI,String> title = new TableColumn<>("Title");
    	TableColumn<CourseForGUI,String> id = new TableColumn<>("Course ID");
    	TableColumn<CourseForGUI,String> type = new TableColumn<>("Type");
    	TableColumn<CourseForGUI,Number> ects = new TableColumn<>("Ects");
    	TableColumn<CourseForGUI,String> lector = new TableColumn<>("Lector");
    	TableColumn<CourseForGUI,Boolean> isEnrolledBy = new TableColumn<>("Enrolled");
    	
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
