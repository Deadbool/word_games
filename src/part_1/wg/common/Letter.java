package part_1.wg.common;

// TODO composite pattern for words classification from dico.txt

public class Letter {
	
	// === Attributes ===
	private char let; // The "letter"
	private int val; // it's value (points)
	
	// === Constructor ===
	public Letter(char let, int val) {
		this.let = let;
		this.val = val;
	}
	
	// === Methods ===
	public String toString() {
		return String.valueOf(this.let);
	}
	
	// === Getters & Setters ===
	public char getLet() {
		return let;
	}
	public void setLet(char let) {
		this.let = let;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
}
