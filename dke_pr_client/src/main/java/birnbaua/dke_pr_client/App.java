package birnbaua.dke_pr_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import com.google.gson.Gson;

import birnbaua.dke_pr_client.basics.Course;
import birnbaua.dke_pr_client.basics.Student;
import birnbaua.dke_pr_client.basics.BasicCourse;
import birnbaua.dke_pr_client.json.JsonHelper;
import birnbaua.dke_pr_client.rest.ConnectionHelper;
import birnbaua.dke_pr_client.rest.JerseyHelper;

/**
 * JavaFX App
 */
public class App extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			JerseyHelper<Student> connHelp = new JerseyHelper<>("http://90.146.27.135:8080/restdemo/webapi/meta-service/jku/insertStudent");
			connHelp.post(new Student("Maxd","Mühleer","12345623212311445287230"));
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(RuntimeException e) {
			e.printStackTrace();
		}
		
		
		launch(); //launch javafx application
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane mainBorderPane = (BorderPane) FXMLLoader.load(App.class.getResource("primary.fxml"));
		Scene scene = new Scene(mainBorderPane);
		//scene.getStylesheets().add("main/gui.css");
		System.out.println();
		stage.setScene(scene);
		stage.show();
	}

}