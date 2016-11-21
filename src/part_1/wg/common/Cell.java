package part_1.wg.common;

import java.io.Serializable;
import java.util.ArrayList;

public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int DOUBLE_LETTER = 2;
	public static final int TRIPLE_LETTER = 3;
	public static final int DOUBLE_WORD = 7;
	public static final int TRIPLE_WORD = 8;
	
	// === Attributes ===
	protected ArrayList<Tile> stack;
	protected int bonus;
	
	// === Constructor ===
	public Cell() {
		this.stack = new ArrayList<Tile>();
		this.bonus = 0;
	}
	
	public Cell(int bonus) {
		this.stack = new ArrayList<Tile>();
		this.bonus = bonus;
	}
	
	public void put(Tile tile) {
		this.stack.add(tile);
	}
	
	public void pick() {
		this.stack.remove(this.stack.size() - 1);
	}
	
	public String toString() {
		return (count() > 0) ? getTopTile().toString() : (bonus > 0) ? String.valueOf(bonus) : ".";
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
	public int getBonus() {
		return bonus;
	}
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
