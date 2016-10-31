package part_1.wg.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;

public class Player {
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
		String r = this.name + " (score: " + this.score + ") has this rack : ";
		Iterator<Tile> it = this.rack.iterator();
		while (it.hasNext())
			r += it.next();
		return r;
	}
	
	public Word askForAWord() {
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		int r = 0;
		int c = 0;
		int orientation = Word.HORIZONTAL;
		
		// Here we ask to the player for a word
		tiles.add(rack.get(0));
		tiles.add(rack.get(1));
		tiles.add(rack.get(2));
		
		for (Tile tile : tiles) {
			rack.remove(tile);
		}
		
		return new Word(tiles, r, c, orientation);
	}
	
	public void draw(Bag bag) {
		Random rand = new Random(System.currentTimeMillis());
		while (rack.size() < Player.RACK_SIZE) {
			Tile t = bag.getLetters().get(rand.nextInt(bag.size()));
			bag.getLetters().remove(t);
			rack.add(t);
		}
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
