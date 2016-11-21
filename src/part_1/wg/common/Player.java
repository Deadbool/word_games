package part_1.wg.common;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	static final public int RACK_SIZE = 7; // Max letter count on each rack
	
	// === Attributes ===
	protected int score;
	protected String name;
	protected ArrayList<Tile> rack;
	
	// === Constructor ===
	public Player(String name) {
		this.score = 0;
		this.setName(name);
		this.rack = new ArrayList<Tile>();
	}
	
	// === Methods ===
	public String toString() {
		return this.name;
	}
	
	public abstract Word askForAWord(Board board);
	
	public void draw(Bag bag) {
		while (bag.size() > 0 && rack.size() < Player.RACK_SIZE) {
			Tile t = bag.getLetters().get(Game.RAND.nextInt(bag.size()));
			bag.getLetters().remove(t);
			rack.add(t);
		}
	}
	
	public boolean exchange(Bag bag, ArrayList<Tile> tiles) {
		if (bag.size() < tiles.size())
			return false;
		
		for (int i=0; i < tiles.size(); i++) {
			Tile t = bag.getLetters().get(Game.RAND.nextInt(bag.size()));
			bag.getLetters().remove(t);
			rack.add(t);
		}
		
		bag.getLetters().addAll(tiles);
		this.rack.removeAll(tiles);
		
		return true;
	}
	
	// === Getters & Setters ===
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incScore(int bonus) {
		this.score += bonus; // could be a malus if bonus < 0
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Tile> getRack() {
		return rack;
	}
	public void setRack(ArrayList<Tile> rack) {
		this.rack = rack;
	}
}
