package birnbaua.dke_pr_client;

import java.io.IOException;

import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.University;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    @FXML private ChoiceBox<University> studies;
    @FXML private TextField searchMyCourses;
    @FXML private TableView<Course> myCourses;
    @FXML private Text name;
    @FXML private Text matrNr;
    @FXML private Text university;

    @FXML
    void initialize() {
    	courseTableSetup(myCourses);
    }
    
    private void courseTableSetup(TableView<Course> table) {
    	TableColumn<Course,String> title = new TableColumn<>("Title");
    	TableColumn<Course,String> id = new TableColumn<>("Course ID");
    	TableColumn<Course,String> type = new TableColumn<>("Type");
    	TableColumn<Course,String> ects = new TableColumn<>("Ects");
    	TableColumn<Course,String> lector = new TableColumn<>("Lector");
    	TableColumn<Course,Boolean> isEnrolledBy = new TableColumn<>("Enrolled");
    	
    	table.setEditable(true);
    	isEnrolledBy.setEditable(true);
    	
    	
    	
    }
}
