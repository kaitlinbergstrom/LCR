package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

/*
This is the scene with the table and the game actually happens
*/
public class StartSceneController {
	
		@FXML
		Button startButton;
		
		@FXML
		MenuItem fileClose;
		
		public void startGame(ActionEvent e) throws Exception {			
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("PlayerSetupScene.fxml"));

			Parent playerSetupRoot = loader.load();
			
			Scene playerSetup = new Scene(playerSetupRoot);
			
			playerSetup.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(playerSetup);
			primaryStage.show();

		}
		
		public void fileCloseClicked() {
			System.exit(0);
		}
		
}
