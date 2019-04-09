package application;

import java.util.ArrayList;

public class LCR {
	
	//List of players, # of players, and center pot
		public ArrayList<Player> playerList = new ArrayList<Player>();
		public int center = 0;
		public int currentPlayer = 0;
		public int potentialRolls = 0;
		public String gameMSG;
		
		public boolean rollsInSession = false;

		public LCR() {}
		
		//Roll 6-sided die. (L, C, R, DOT, DOT, DOT)
		public String rollDie() {
			int value = (int)(Math.random() * 6) + 1;
			
			switch (value) {
				case 1:
					return "L";
				case 2:
					return "C";
				case 3:
					return "R";
				default:
					return "DOT";
			}
		}
		
		//Moves chips according to current player and their roll
		public void moveChip(String die, int player) {
			
			String playerName = playerList.get(player).getName();
			
			switch (die) {
				case "L":
					//if player is at the start of the ArrayList, add chip to index 'players - 1'
					if (player == 0) {
						playerList.get(this.getNumPlayers() - 1).addChip();
					}else {
						playerList.get(player - 1).addChip();
					}
					playerList.get(player).removeChip();
					
					gameMSG += playerName + " slides a chip left.\n";
					break;
				case "C":
					center++;
					playerList.get(player).removeChip();
					gameMSG += playerName + " adds a chip to the pot.\n";
					break;
				case "R":
					//if player is at the end of the ArrayList, add chip to index 0
					if (player == this.getNumPlayers() - 1) {
						playerList.get(0).addChip();
					}else {
						playerList.get(player + 1).addChip();
					}
					playerList.get(player).removeChip();
					gameMSG += playerName + " slides a chip right.\n";
					break;
				case "DOT":
					//do nothing
					gameMSG += playerName + " holds on to the chip!\n";
					break;
				default:
					//do nothing, not a valid input
					break;
			}
			
		}
		
		//if end of game, get winner's name and deliver winner message
		public void identifyWinner() {
			gameMSG = "";
			String winner = null;
			int chips = 0;
			
			for (int i = 0; i < playerList.size(); i++) {
				if (playerList.get(i).getChips() != 0) {
					winner = playerList.get(i).getName();
					chips = playerList.get(i).getChips();
				}
			}
			
			gameMSG += winner + " has won the game!\n\n";
			gameMSG += "Ending up with a final chip count of " + chips + ",\n";
			gameMSG += winner + " takes home a total of " + (chips + center) + " chips!\n";
			
		}
		
		//Test for game-end scenario where only 1 player has chips.
		public boolean checkIfEOG() {
			int playersWithChips = 0;
			
			for (int i = 0; i < playerList.size(); i++) {
				if (playerList.get(i).getChips() != 0) {
					playersWithChips++;
				}
			}
			
			//if more than 1 player has chips, return false. Otherwise, game is over, return true.
			if (playersWithChips > 1) {
				return false;
			}
			
			return true;
		}

		// Add player
		public void addPlayer(String name, boolean npc, int pos) {
			this.playerList.add(new Player(name, npc, pos));
		}

		// Get the number of active players
		public int getNumPlayers() {
			return this.playerList.size();
		}

		// Set some variables for the start of a game
		public void initialize() {
			potentialRolls = 0;
			rollsInSession = false;
			this.center = 0;
			currentPlayer = 0;
			for (Player p : this.playerList) {
				p.setChips(3);
			}
		}

		//Changed methods 
		
		//At the start of player's turn
		public void beginTurn() {
			Player playerTurn = playerList.get(currentPlayer);
			potentialRolls = playerTurn.getChips();
			String playerName = playerTurn.getName();
			int playerChips = playerTurn.getChips();
			
			rollsInSession = true;
			
			gameMSG = "";
			gameMSG = "It is " + playerName + "'s turn.\n";
			gameMSG += playerName + "'s chip count: " + playerChips + ".\n";
				
		}
		
		//Rolls a single die. 
		public void executeRoll(int playerIndex, String playerName) {
			String roll = rollDie();
			gameMSG ="";
			gameMSG += playerName + " rolls " + roll + ".\n";
			moveChip(roll, playerIndex);
			potentialRolls--;
			
		}
		
		//Runs at the end of a player's turn. 
		public void endTurn(String playerName) {	
			gameMSG = "";
			gameMSG += playerName + "'s turn is over.\n\n";
			rollsInSession = false;
			currentPlayer++;
			if (currentPlayer >= this.getNumPlayers()) {
				currentPlayer = 0;
			}
			
		}
		

}
