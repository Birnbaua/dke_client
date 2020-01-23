package birnbaua.dke_pr_client;

import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.University;
import birnbaua.dke_pr_client.rest.RestCall;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class UserController {
    @FXML private TextField firstName;
    @FXML private TextField lastName;
    @FXML private TextField id;
    @FXML private Button save;
    
    private RestCall rest = null;
    private University uni = null;
    
    public void setInformation(@SuppressWarnings("exports") RestCall rest, University uni) {
    	this.rest = rest;
    	this.uni = uni;
    }
    
    @FXML
    void onSave() {
    	rest.createStudent(new Student(this.firstName.getText(),this.lastName.getText(),this.id.getText()), this.uni);
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
