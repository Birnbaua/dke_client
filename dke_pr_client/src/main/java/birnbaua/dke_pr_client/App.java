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
import birnbaua.dke_pr_client.basics.JsonCourseConverter;
import birnbaua.dke_pr_client.json.JsonHelper;
import birnbaua.dke_pr_client.rest.ConnectionHelper;

/**
 * JavaFX App
 */
public class App extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		try {
			ConnectionHelper connHelp = new ConnectionHelper(new URL("http://90.146.27.135:8080/restdemo/webapi/myresource/getLVA/vl"));
			System.out.println(connHelp.get());
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
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