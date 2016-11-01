package part_1.wg.common;

import java.util.ArrayList;

public class Cell {
	// === Attributes ===
	protected ArrayList<Tile> stack;
	
	// === Constructor ===
	public Cell() {
		// Create an empty stack of tiles
		this.stack = new ArrayList<Tile>();
	}
	
	public String toString() {
		return (count() > 0) ? getTopTile().toString() : ".";
	}
	
	// === Getters & Setters
	public ArrayList<Tile> getStack() {
		return stack;
	}
	public int count() {
		return this.stack.size();
	}
	public Tile getTopTile() {
		return (stack.size() > 0) ? stack.get(stack.size()-1) : null;
	}
	public void setStack(ArrayList<Tile> stack) {
		this.stack = stack;
	}
}
