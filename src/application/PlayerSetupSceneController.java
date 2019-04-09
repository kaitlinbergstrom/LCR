package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PlayerSetupSceneController {
	
	@FXML
	private Button letsPlayButton;

	@FXML
	private RadioButton radioPlayer4;
	
	@FXML
	private RadioButton radioPlayer5;
	
	@FXML
	private RadioButton radioPlayer6;
	
	@FXML
	private RadioButton radioPlayer7;
	
	@FXML
	private RadioButton radioPlayer8;
	
	@FXML
	private RadioButton radioPlayer9;
	
	@FXML
	private RadioButton radioPlayer10;
	
	@FXML
	private TextField playerName1;
	
	@FXML
	private TextField playerName2;
	
	@FXML
	private TextField playerName3;
	
	@FXML
	private TextField playerName4;
	
	@FXML
	private TextField playerName5;
	
	@FXML
	private TextField playerName6;
	
	@FXML
	private TextField playerName7;
	
	@FXML
	private TextField playerName8;
	
	@FXML
	private TextField playerName9;
	
	@FXML
	private TextField playerName10;
	
	@FXML
	private CheckBox playerCheck1;
	
	@FXML
	private CheckBox playerCheck2;
	
	@FXML
	private CheckBox playerCheck3;
	
	@FXML
	private CheckBox playerCheck4;
	
	@FXML
	private CheckBox playerCheck5;
	
	@FXML
	private CheckBox playerCheck6;
	
	@FXML
	private CheckBox playerCheck7;
	
	@FXML
	private CheckBox playerCheck8;
	
	@FXML
	private CheckBox playerCheck9;
	
	@FXML
	private CheckBox playerCheck10;
	
	@FXML
	private Label invalidLabel;
	
	ArrayList<Player> playerList = new ArrayList<Player>();
	
	public LCR theGame;
	
	//When play button is pressed, we check if player setup is valid, then get player info and start the game.
	public void playButtonPressed(ActionEvent e) throws IOException {

		collectPlayerInfo();
		
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("GameScene.fxml"));

		Parent gameSceneRoot = loader.load();
		
		Scene mainGameScene = new Scene(gameSceneRoot);
		
		mainGameScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
		primaryStage.hide();
		primaryStage.setScene(mainGameScene);
		
		String playerListText = "";
		
		
		for(int i = 0; i < theGame.getNumPlayers(); i++) {
			Player x = theGame.playerList.get(i);
			playerListText += x.getName() + " - " + x.getChips() + " chips\n" ;
		}
		
		GameSceneController nextSceneController = loader.<GameSceneController>getController();
		nextSceneController.initData(theGame, playerListText);
		
		primaryStage.setResizable(false);
		primaryStage.show();
		theGame.initialize();
			

	}
	
	//Collects player info and creates game engine
	private void collectPlayerInfo() {
		
		//CREATES GAME ENGINE IF PLAYERS ARE VALIDATED
		theGame = new LCR();
		
		String name;
		
		//Player 1
		if (playerName1.getText().trim().length() == 0) {
			name = "Player 1";
		}else {
			name = playerName1.getText();
		}
		
		theGame.addPlayer(name, !playerCheck1.isSelected(), 0);
		
		//Player 2
		if (playerName2.getText().trim().length() == 0) {
			name = "Player 2";
		}else {
			name = playerName2.getText();
		}
		
		theGame.addPlayer(name, !playerCheck2.isSelected(), 1);
		
		//Player 3
		if (playerName3.getText().trim().length() == 0) {
			name = "Player 3";
		}else {
			name = playerName3.getText();
		}
		
		theGame.addPlayer(name, !playerCheck3.isSelected(), 2);
		
		//Player 4
		if (radioPlayer4.isSelected()) {
			if (playerName4.getText().trim().length() == 0) {
				name = "Player 4";
			}else {
				name = playerName4.getText();
			}
			
			theGame.addPlayer(name, !playerCheck4.isSelected(), 3);
		}
		
		//Player 5
		if (radioPlayer5.isSelected()) {
			if (playerName5.getText().trim().length() == 0) {
				name = "Player 5";
			}else {
				name = playerName5.getText();
			}
			
			theGame.addPlayer(name, !playerCheck5.isSelected(), 4);
		}
		
		//Player 6
		if (radioPlayer6.isSelected()) {
			if (playerName6.getText().trim().length() == 0) {
				name = "Player 6";
			}else {
				name = playerName6.getText();
			}
			
			theGame.addPlayer(name, !playerCheck6.isSelected(), 5);
		}
		
		//Player 7
		if (radioPlayer7.isSelected()) {
			if (playerName7.getText().trim().length() == 0) {
				name = "Player 7";
			}else {
				name = playerName7.getText();
			}
			
			theGame.addPlayer(name, !playerCheck7.isSelected(), 6);
		}
		
		//Player 8
		if (radioPlayer8.isSelected()) {
			if (playerName8.getText().trim().length() == 0) {
				name = "Player 8";
			}else {
				name = playerName8.getText();
			}
			
			theGame.addPlayer(name, !playerCheck8.isSelected(), 7);
		}
		
		//Player 9
		if (radioPlayer9.isSelected()) {
			if (playerName9.getText().trim().length() == 0) {
				name = "Player 9";
			}else {
				name = playerName9.getText();
			}
			
			theGame.addPlayer(name, !playerCheck9.isSelected(), 8);
		}
		
		//Player 10
		if (radioPlayer10.isSelected()) {
			if (playerName10.getText().trim().length() == 0) {
				name = "Player 10";
			}else {
				name = playerName10.getText();
			}
			
			theGame.addPlayer(name, !playerCheck10.isSelected(), 9);
		}
		
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 4
	public void player4Toggle() {
		if (radioPlayer4.isSelected()) {
			playerName4.setVisible(true);
			playerCheck4.setVisible(true);
		}else{
			playerName4.setVisible(false);
			playerCheck4.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 5
	public void player5Toggle() {
		if (radioPlayer5.isSelected()) {
			playerName5.setVisible(true);
			playerCheck5.setVisible(true);
		}else{
			playerName5.setVisible(false);
			playerCheck5.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 6
	public void player6Toggle() {
		if (radioPlayer6.isSelected()) {
			playerName6.setVisible(true);
			playerCheck6.setVisible(true);
		}else{
			playerName6.setVisible(false);
			playerCheck6.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 7
	public void player7Toggle() {
		if (radioPlayer7.isSelected()) {
			playerName7.setVisible(true);
			playerCheck7.setVisible(true);
		}else{
			playerName7.setVisible(false);
			playerCheck7.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 8
	public void player8Toggle() {
		if (radioPlayer8.isSelected()) {
			playerName8.setVisible(true);
			playerCheck8.setVisible(true);
		}else{
			playerName8.setVisible(false);
			playerCheck8.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 9
	public void player9Toggle() {
		if (radioPlayer9.isSelected()) {
			playerName9.setVisible(true);
			playerCheck9.setVisible(true);
		}else{
			playerName9.setVisible(false);
			playerCheck9.setVisible(false);
		}
	}
	
	//Controls visibility of Player Name text fields and NPC toggles for Player 10
	public void player10Toggle() {
		if (radioPlayer10.isSelected()) {
			playerName10.setVisible(true);
			playerCheck10.setVisible(true);
		}else{
			playerName10.setVisible(false);
			playerCheck10.setVisible(false);
		}
	}
	
	//Closing LCR from menu bar
	public void fileCloseClicked() {
		System.exit(0);
	}
	
	
}
