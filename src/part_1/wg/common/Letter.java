package part_1.wg.common;

// TODO composite pattern for words classification from dico.txt

public class Letter {
	
	// === Attributes ===
	private String let; // The "letter", String because of the "Qu" letter of topword
	private int val; // it's value (points)
	
	// === Constructor ===
	public Letter(String let, int val) {
		this.let = let;
		this.val = val;
	}
	
	// === Methods ===
	public String toString() {
		return String.valueOf(this.let);
	}
	
	// === Getters & Setters ===
	public String getLet() {
		return let;
	}
	public void setLet(String let) {
		this.let = let;
	}
	public int getVal() {
		return val;
	}
	public void setVal(int val) {
		this.val = val;
	}
}
