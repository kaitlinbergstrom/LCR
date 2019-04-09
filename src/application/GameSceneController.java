package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameSceneController {

		public LCR theGame;
		
		@FXML
		MenuItem fileClose;
		
		@FXML
		Button rollButton;
		
		@FXML
		TextArea playerListView;
		
		@FXML
		TextArea gameLog;
		
		@FXML
		Label centerPotLabel;
		
		@FXML
		Label currentPlayerLabel;
		
		@FXML
		Label rollsLabel;
		
		@FXML
		Label rollsRemaining;
		
		@FXML
		Label player1Label;
		
		@FXML
		Label player2Label;
		
		@FXML
		Label player3Label;
		
		@FXML
		Label player4Label;
		
		@FXML
		Label player5Label;
		
		@FXML
		Label player6Label;
		
		@FXML
		Label player7Label;
		
		@FXML
		Label player8Label;
		
		@FXML
		Label player9Label;
		
		@FXML
		Label player10Label;
		
		
		public void initData(LCR game, String text) {
			theGame = game;
			setRollButtonText("Next Turn");
			playerListView.setText(text);
			centerPotLabel.setText(String.valueOf(theGame.center));
			currentPlayerLabel.setText(theGame.playerList.get(theGame.currentPlayer).getName() + "'s turn to roll.");
			refreshPlayerLabels();
		}
		
		
		//refresh game log
		public void refreshGameLog() throws Exception {
			gameLog.setText(gameLog.getText() + theGame.gameMSG);
			gameLog.appendText("");
		}
		
		
		//Updates Player List to show current chip counts
		public void refreshPlayerListText() {
			String playerListText = "";
			
			for(int i = 0; i < theGame.getNumPlayers(); i++) {
				Player x = theGame.playerList.get(i);
				playerListText += x.getName() + " - " + x.getChips() + " chips\n" ;
			}
			
			playerListView.setText(playerListText);
		}
		
		//when the main game button ("ROLL", "Next Turn") is clicked
		public void theRollButton(ActionEvent x) throws Exception {
			
			//if a turn in not in session
			if (!theGame.rollsInSession) {
					//if no rolls in session, but active game, it must be the beginning of the turn
					theGame.beginTurn();
					rollsLabel.setText(Integer.toString(theGame.potentialRolls));
					
					if (theGame.potentialRolls == 0 ) {
						setRollButtonText("End Turn");
					}else {
						setRollButtonText("ROLL");
					}
					
					currentPlayerLabel.setText(theGame.playerList.get(theGame.currentPlayer).getName() + "'s turn to roll.");
					refreshAll();

			}else {
				//if a turn is in progress, roll
				if (theGame.potentialRolls > 0) {
					
					//if NPC has chips, make roll button, roll, otherwise, go to end of turn
					Player roller = theGame.playerList.get(theGame.currentPlayer);
					if (roller.getChips() > 0) {
						theGame.executeRoll(theGame.currentPlayer, roller.getName());
						refreshAll();
					}
					
					if (theGame.potentialRolls == 0 || roller.getChips() < 1) {
						setRollButtonText("End Turn");
					}
	
				}else {
					//if no rolls can happen, click ends turn
					theGame.endTurn(theGame.playerList.get(theGame.currentPlayer).getName());
					setRollButtonText("Next Turn");
					refreshAll();
					currentPlayerLabel.setText("Time for " + theGame.playerList.get(theGame.currentPlayer).getName() + "'s Turn!");
					
				}
				
			}
			// Disable Roll button during NPC turns
			if (theGame.playerList.get(theGame.currentPlayer).getNPC()) {
				rollButton.setText("NPC Turn");
				rollButton.setDisable(true);
			} else {
				rollButton.setDisable(false);
			}
			
			//if end of game switch to winner screen
			if (theGame.checkIfEOG()) {
				endGame(x);
			}else {
				if (theGame.playerList.get(theGame.currentPlayer).getNPC()) {
					Platform.runLater( () -> {
						try {
							npcTurn(x);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
				}
				
			}
			
			
		}
		

		//Runs through an entire NPC's turn
		public void npcTurn(ActionEvent x) throws Exception {
			Thread.sleep(600);
			if (theGame.playerList.get(theGame.currentPlayer).getNPC()) {
				Platform.runLater( () -> {
					try {
						theRollButton(x);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		}
		
		
		//ends game, switches to WinnerScene
		public void endGame(ActionEvent e) throws Exception {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("WinnerScene.fxml"));

			Parent winnerSceneRoot = loader.load();
			
			Scene winnerScene = new Scene(winnerSceneRoot);
			
			winnerScene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			Stage primaryStage = (Stage)((Node)e.getSource()).getScene().getWindow();
			primaryStage.hide();
			primaryStage.setScene(winnerScene);
			
			WinnerSceneController nextSceneController = loader.<WinnerSceneController>getController();
			nextSceneController.initData(theGame);
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		
		//Close game from menu
		public void fileCloseClicked() {
			System.exit(0);
		}
		
		//refresh all UI values on screen
		public void refreshAll() throws Exception {
			rollsLabel.setText(Integer.toString(theGame.potentialRolls));
			refreshPlayerListText();
			refreshGameLog();
			refreshPlayerLabels();
			centerPotLabel.setText(String.valueOf(theGame.center));
			
		}
		
		//Sets text on roll/game button
		public void setRollButtonText(String s) {
			if (!theGame.playerList.get(theGame.currentPlayer).getNPC()) {
				rollButton.setText(s);
			}
		}
		
		
		//Sets labels for player names on game table
		private void refreshPlayerLabels() {
			for (int i = 0; i < theGame.getNumPlayers(); i++) {
				Player x = theGame.playerList.get(i);
				String name = x.getName();
				
				if (x.getLabelPosition() == 0) {
					player1Label.setText(name);
					if (theGame.currentPlayer == 0) {
						player1Label.setTextFill(Color.ORANGE);
					}else {
						player1Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 1) {
					player2Label.setText(name);
					if (theGame.currentPlayer == 1) {
						player2Label.setTextFill(Color.ORANGE);
					}else {
						player2Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 2) {
					player3Label.setText(name);
					if (theGame.currentPlayer == 2) {
						player3Label.setTextFill(Color.ORANGE);
					}else {
						player3Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 3) {
					player4Label.setText(name);
					if (theGame.currentPlayer == 3) {
						player4Label.setTextFill(Color.ORANGE);
					}else {
						player4Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 4) {
					player5Label.setText(name);
					if (theGame.currentPlayer == 4) {
						player5Label.setTextFill(Color.ORANGE);
					}else {
						player5Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 5) {
					player6Label.setText(name);
					if (theGame.currentPlayer == 5) {
						player6Label.setTextFill(Color.ORANGE);
					}else {
						player6Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 6) {
					player7Label.setText(name);
					if (theGame.currentPlayer == 6) {
						player7Label.setTextFill(Color.ORANGE);
					}else {
						player7Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 7) {
					player8Label.setText(name);
					if (theGame.currentPlayer == 7) {
						player8Label.setTextFill(Color.ORANGE);
					}else {
						player8Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 8) {
					player9Label.setText(name);
					if (theGame.currentPlayer == 8) {
						player9Label.setTextFill(Color.ORANGE);
					}else {
						player9Label.setTextFill(Color.WHITE);
					}
				}
				
				if (x.getLabelPosition() == 9) {
					player10Label.setText(name);
					if (theGame.currentPlayer == 9) {
						player10Label.setTextFill(Color.ORANGE);
					}else {
						player10Label.setTextFill(Color.WHITE);
					}
				}
				
			}
		}
}
