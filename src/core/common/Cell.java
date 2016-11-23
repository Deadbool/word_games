package core.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Cell that will stack tiles on a word games board. 
 * 
 * @author Nicolas Guégan
 *
 */

public class Cell implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final int DOUBLE_LETTER = 2;
	public static final int TRIPLE_LETTER = 3;
	public static final int DOUBLE_WORD = 7;
	public static final int TRIPLE_WORD = 8;
	
	protected ArrayList<Tile> stack;
	protected int bonus;
	
	/**
	 * Constructor.
	 * 
	 * Create an empty and normal cell (without bonus).
	 */
	public Cell() {
		this.stack = new ArrayList<Tile>();
		this.bonus = 0;
	}
	
	/**
	 * Create an empty cell with a bonus.
	 * @param bonus, the bonus of the cell: double/triple letter/word
	 */
	public Cell(int bonus) {
		this.stack = new ArrayList<Tile>();
		this.bonus = bonus;
	}
	
	/**
	 * Put a tile on top of cell's stack.
	 * @param tile, the tile to put
	 */
	public void put(Tile tile) {
		this.stack.add(tile);
	}
	
	/**
	 * Remove the top tile of the cell's stack.
	 */
	public void pick() {
		this.stack.remove(this.stack.size() - 1);
	}
	
	public String toString() {
		return (count() > 0) ? getTopTile().toString() : (bonus > 0) ? String.valueOf(bonus) : ".";
	}
	
	/**
	 * @return The cell's stack.
	 */
	public ArrayList<Tile> getStack() {
		return stack;
	}
	
	/**
	 * @return The number of tiles in the cell's stack.
	 */
	public int count() {
		return this.stack.size();
	}
	
	/**
	 * @return The top tile of the cell's stack.
	 */
	public Tile getTopTile() {
		return (stack.size() > 0) ? stack.get(stack.size()-1) : null;
	}
	
	/**
	 * @return The cell's bonus.
	 */
	public int getBonus() {
		return bonus;
	}
	
	/**
	 * @param bonus
	 */
	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
