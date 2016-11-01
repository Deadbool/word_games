package part_1.wg.common;

// TODO composite pattern for words classification from dico.txt

public class Tile {
	
	// === Attributes ===
	protected String letter; // String because of the "Qu" letter of topword
	protected int value; // value of the letter (points)
	
	// === Constructor ===
	public Tile(String let) {
		this.letter = let;
		this.value = 0;
	}
	
	public Tile(String let, int val) {
		this.letter = let;
		this.value = val;
	}
	
	// === Methods ===
	public String toString() {
		return String.valueOf(this.letter);
	}
	
	public boolean equals(Object o) {
		return letter.equals(((Tile) o).letter);
	}
	
	// === Getters & Setters ===
	public String getLet() {
		return letter;
	}
	public void setLet(String letter) {
		this.letter = letter;
	}
	public int getVal() {
		return value;
	}
	public void setVal(int value) {
		this.value = value;
	}
}
