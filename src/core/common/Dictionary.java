package core.common;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Dictionary model storing all available words for word games.
 * 
 * @author Nicolas Guégan
 *
 */

public class Dictionary extends HashSet<String> {
	private static final long serialVersionUID = 1L;
	private static final String DICTIONARY_PATH = "config/dico.txt";
	private static Dictionary dico = new Dictionary();
	
	/**
	 * Constructor.
	 * 
	 * Create the dictionary thanks to a DICTIONARY_PATH.
	 * 
	 * Because it's a singleton, you have to use the Dictionary.get() method to access it.
	 * 
	 */
	private Dictionary() {
		
		try {
			List<String> words = Files.lines(Paths.get(Dictionary.DICTIONARY_PATH)).collect(Collectors.toList());
		
			for (String word : words) {
				this.add(word.toUpperCase());
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @return The single dictionary instance.
	 */
	public static Dictionary get() {
		return dico;
	}
}
