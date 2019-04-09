package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class WinnerSceneController {
	
	public LCR theGame;

	@FXML
	Label winnerLabel;
	
	@FXML
	Button newGameButton;
	
	@FXML
	Button closeGameButton;
	
	@FXML
	MenuItem fileClose;
	
	public void initData(LCR game) {
		this.theGame = game;
		theGame.identifyWinner();
		winnerLabel.setText(theGame.gameMSG);
	}
	
	//resets application
	public void newGameButtonClicked(ActionEvent e) throws Exception{
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("StartScene.fxml"));

		Parent mainSceneRoot = loader.load();
		
		Scene mainScene = new Scene(mainSceneRoot);
		
		mainScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		
		primaryStage.hide();
		primaryStage.setScene(mainScene);
		primaryStage.show();
		
	}
	
	public void fileCloseClicked() {
		System.exit(0);
	}
	
}
