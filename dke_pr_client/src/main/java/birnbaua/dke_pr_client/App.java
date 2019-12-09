package birnbaua.dke_pr_client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(); //launch javafx application
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// TODO Auto-generated method stub
		BorderPane mainBorderPane = (BorderPane) FXMLLoader.load(App.class.getResource("primary.fxml"));
		Scene scene = new Scene(mainBorderPane);
		//scene.getStylesheets().add("main/gui.css");
		stage.setScene(scene);
		stage.show();
	}

}