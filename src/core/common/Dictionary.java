package core.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Dictionary extends HashSet<String> {
	private static final long serialVersionUID = 1L;
	private static final String DICTIONARY_PATH = "config/dico.txt";
	private static Dictionary dico = new Dictionary();
	
	// === Constructor ===
	protected Dictionary() {
		
		try {
			List<String> words = Files.lines(Paths.get(Dictionary.DICTIONARY_PATH)).collect(Collectors.toList());
		
			for (String word : words) {
				this.add(word.toUpperCase());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Dictionary get() {
		return dico;
	}
	
	public void log() {

	}
}
