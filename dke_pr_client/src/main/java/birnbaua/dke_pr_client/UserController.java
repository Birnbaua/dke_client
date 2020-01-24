package birnbaua.dke_pr_client;

import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.rest.RestCall;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserController {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField id;
    @FXML private Button save;
    @FXML private ChoiceBox<University> universities;
    
    private RestCall rest = null;
    private Student student = null;
    
    public void setInformation(@SuppressWarnings("exports") RestCall rest, ChoiceBox<University> universities) {
    	this.rest = rest;
    	this.universities.getItems().addAll(universities.getItems());
    	this.universities.setValue(universities.getValue());
    	this.universities.valueProperty().addListener((a,o,n) -> {
    		universities.setValue(n);
    	});
    }
    
    @FXML
    void onSave(ActionEvent event) {
    	Student st = new Student(this.firstName.getText(),this.lastName.getText(),this.id.getText());
    	rest.createStudent(st,this.universities.getValue());
    	this.student = st;
    	Node  source = (Node)  event.getSource(); 
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    public Student getStudent() {
    	return student;
    }
    
    @FXML
    void initialize() {
    	this.firstName.textProperty().addListener((a,o,n) -> {
    		this.save.setStyle("-fx-background-color: tomato; ");
    	});
    	
    	this.lastName.textProperty().addListener((a,o,n) -> {
    		this.save.setStyle("-fx-background-color: tomato; ");
    	});
    	
    	this.id.textProperty().addListener((a,o,n) -> {
    		this.save.setStyle("-fx-background-color: tomato; ");
    	});
    }
    
}
