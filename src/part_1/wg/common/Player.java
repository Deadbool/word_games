package part_1.wg.common;

import java.util.HashSet;

public class Player {
	static public int RACK_SIZE = 7; // Max letter count on each rack
	
	// === Attributes ===
	protected int score;
	protected String name;
	protected HashSet<Letter> rack;
	
	// === Constructor ===
	public Player(String name) {
		this.score = 0;
		this.setName(name);
		this.rack = new HashSet<Letter>();
	}
	
	// === Methods ===
	public String toString() {
		return this.name + " (score: " + this.score + ")";
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
	public HashSet<Letter> getRack() {
		return rack;
	}
	public void setRack(HashSet<Letter> rack) {
		this.rack = rack;
	}
}
