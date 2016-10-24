package part_1.wg.common;

import java.util.HashSet;
import java.util.Iterator;

public class Player {
	static final public int RACK_SIZE = 7; // Max letter count on each rack
	
	// === Attributes ===
	protected int score;
	protected String name;
	protected HashSet<Tile> rack;
	
	// === Constructor ===
	public Player(String name) {
		this.score = 0;
		this.setName(name);
		this.rack = new HashSet<Tile>();
	}
	
	// === Methods ===
	public String toString() {
		String r = this.name + " (score: " + this.score + ") has this rack : ";
		Iterator<Tile> it = this.rack.iterator();
		while (it.hasNext())
			r += it.next();
		return r;
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
	public HashSet<Tile> getRack() {
		return rack;
	}
	public void setRack(HashSet<Tile> rack) {
		this.rack = rack;
	}
}
