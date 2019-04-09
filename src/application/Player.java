package application;

public class Player {
	
	public String playerName;
	public int playerChips = 3;
	public int labelPosition;
	public boolean NPC = false;
	
	public Player(String name, boolean npc, int position) {
		this.setName(name);
		this.setNPC(npc);
		this.setLabelPosition(position);
	}
	
	public void setName(String name) {
		// Remove whitespace from name and truncate to 15 characters
		name = name.trim();
		name = name.substring(0, Integer.min(name.length(), 15));
		this.playerName = name;
	}
	
	public void setChips(int chips) {
		this.playerChips = chips;
	}
	
	public void setNPC(boolean isNPC) {
		this.NPC = isNPC;
	}
	
	public void setLabelPosition(int pos) {
		this.labelPosition = pos;
	}
	
	public String getName() {
		return this.playerName;
	}
	
	public int getChips() {
		return this.playerChips;
	}
	
	public boolean getNPC() {
		return this.NPC;
	}
	
	public int getLabelPosition() {
		return this.labelPosition;
	}
	
	public void addChip() {
		this.playerChips += 1;
	}
	
	public void removeChip() {
		this.playerChips -= 1;
	}
	
}
