package part_1.wg.common;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Bag {
	// === Attributes ===
	protected HashSet<Letter> letters;
	
	// === Methods ===
	public String toString() {
		String s = "Bag: ";
		Iterator<Letter> it = this.letters.iterator();
		while (it.hasNext())
			s += it.next();
		return s + "\n";
	}
	
	// === Getters & Setters ===
	public HashSet<Letter> getLetters() {
		return letters;
	}
	public void setLetters(HashSet<Letter> letters) {
		this.letters = letters;
	}
}
