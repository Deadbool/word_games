package core.common;

import java.io.Serializable;

/**
 * Word games tile representation (letter + value).
 * 
 * @author Nicolas Guégan
 *
 */

public class Tile implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected String letter; // String because of the "Qu" letter of topword
	protected int value; // value of the letter (points)
	
	/**
	 * Constructor.
	 * 
	 * Create a new tile with a letter, setting its value to 0. 
	 * @param let, The tile's letter
	 */
	public Tile(String let) {
		this.letter = let;
		this.value = 0;
	}
	
	/**
	 * Constructor.
	 * 
	 * Create a new tile with a letter and its value.
	 * @param let, Tile's letter
	 * @param val, Tile's value
	 */
	public Tile(String let, int val) {
		this.letter = let;
		this.value = val;
	}
	
	public String toString() {
		return String.valueOf(this.letter);
	}
	
	public boolean equals(Object o) {
		return letter.toUpperCase().equals(((Tile) o).letter.toUpperCase());
	}
	
	/**
	 * @return The tile's letter.
	 */
	public String getLet() {
		return letter;
	}
	
	/**
	 * Set the tile's letter.
	 * @param letter
	 */
	public void setLet(String letter) {
		this.letter = letter;
	}
	
	/**
	 * @return The tile's value.
	 */
	public int getVal() {
		return value;
	}
	
	/**
	 * Set the tile's value.
	 * @param value
	 */
	public void setVal(int value) {
		this.value = value;
	}
}
