package part_1.wg.common;

import java.util.ArrayList;

public class DictionaryElement {

	// === Attributes ===
	protected char let;
	protected ArrayList<DictionaryElement> children;
	protected boolean end;
	
	// === Constructor ===
	public DictionaryElement(char let) {
		this.setLet(let);
		this.setChildren(new ArrayList<DictionaryElement>());
		this.end = false;
	}
	
	public DictionaryElement(char let, boolean end) {
		this.setLet(let);
		this.setChildren(new ArrayList<DictionaryElement>());
		this.end = end;
	}
	
	// === Methods ===
	public void addChild(char let) {
		this.children.add(new DictionaryElement(let));
	}
	
	public void addWord(String word, int index) {
		char letter = word.charAt(index);
		for (DictionaryElement e : children) {
			if (e.getLet() == letter) {
				if (index + 1 < word.length())
					e.addWord(word, index + 1);
				else
					e.end = true;
					
				return;
			}
		}
		
		addChild(letter);
		if (index + 1 < word.length())
			children.get(children.size() - 1).addWord(word, index + 1);
		else
			children.get(children.size() - 1).end = true;
	}
	
	public boolean search(String word, int index) {
		char letter = word.charAt(index);
		for (DictionaryElement e : children) {
			if (e.getLet() == letter) {
				if (index + 1 < word.length())
					return e.search(word, index + 1);
				else
					return true;
			}
		}
		
		return false;
	}
	
	public void log(String str) {
		if (children.size() == 0) {
			System.out.println(str + this.let);
		} else {
			if (this.end)
				System.out.println(str + this.let);
			
			for (DictionaryElement e : children) {
				e.log(str + this.let);
			}
		}
	}
	
	public String toString() {
		return String.valueOf(this.let);
	}
	
	// === Getters & Setters
	public char getLet() {
		return let;
	}
	public void setLet(char let) {
		this.let = let;
	}
	public ArrayList<DictionaryElement> getChildren() {
		return children;
	}
	public void setChildren(ArrayList<DictionaryElement> children) {
		this.children = children;
	}
}