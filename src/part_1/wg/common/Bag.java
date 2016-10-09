package part_1.wg.common;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Bag {
	// === Attributes ===
	protected HashSet<Tile> content;
	
	// === Methods ===
	public String toString() {
		String s = "Bag: ";
		Iterator<Tile> it = this.content.iterator();
		while (it.hasNext())
			s += it.next();
		return s + "\n";
	}
	
	// === Getters & Setters ===
	public HashSet<Tile> getLetters() {
		return content;
	}
	public void setLetters(HashSet<Tile> content) {
		this.content = content;
	}
}
