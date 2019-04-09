package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Parent mainRoot = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
			
			Scene startScreen = new Scene(mainRoot, 800, 600);
			
			startScreen.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("LCR GUI ver 0.1.3");
			primaryStage.setScene(startScreen);
			primaryStage.setResizable(false);
			primaryStage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
