package part_1.wg.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Dictionary {
	private static final String DICTIONARY_PATH = "config/dico.txt";
	private static Dictionary dico = new Dictionary();
	
	// === Attributes ===
	protected ArrayList<DictionaryElement> roots;
	
	// === Constructor ===
	protected Dictionary() {
		this.roots = new ArrayList<DictionaryElement>();
		
		try {
			List<String> words = Files.lines(Paths.get(Dictionary.DICTIONARY_PATH)).collect(Collectors.toList());
		
			for (String word : words) {
				addWord(word.toUpperCase());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void addWord(String word) {
		char letter = word.charAt(0);
		for (DictionaryElement e : roots) {
			if (e.getLet() == letter) {
				if (1 < word.length())
					e.addWord(word, 1);
				else
					e.end = true;
				
				return;
			}
		}
		
		roots.add(new DictionaryElement(letter));
		if (1 < word.length())
			roots.get(roots.size() - 1).addWord(word, 1);
		else
			roots.get(roots.size() - 1).end = true;
	}
	
	public static Dictionary get() {
		return dico;
	}
	
	public boolean search(String word) {
		char letter = word.charAt(0);
		for (DictionaryElement e : roots) {
			if (e.getLet() == letter) {
				if (1 < word.length())
					return e.search(word, 1);
				else
					return true;
			}
		}
		
		return false;
	}
	
	public void log() {
		for (DictionaryElement e : roots) {
			e.log("");
		}
	}
}
