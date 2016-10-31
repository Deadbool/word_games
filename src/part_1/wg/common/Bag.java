package part_1.wg.common;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Bag {
	static private final String CONFIG_FILES_SEPARATOR = ":";
	static private final int INDEX_FOR_LETTER = 0;
	static private final int INDEX_FOR_COUNT = 1;
	static private final int INDEX_FOR_VALUE = 2;
	
	// === Attributes ===
	protected ArrayList<Tile> content;
	
	// Constructor
	public Bag() {
		this.content = new ArrayList<Tile>();
	}
	
	public Bag(String config_file_path) {
		this.content = new ArrayList<Tile>();
		try {
			List<String> lines = Files.lines(Paths.get(config_file_path)).collect(Collectors.toList());
			
			// Skip the first line which is a comment
			lines.remove(0);
			
			for (String l : lines) {
				String[] fields = l.split(CONFIG_FILES_SEPARATOR);
				try {
					int count = Integer.parseInt(fields[INDEX_FOR_COUNT]);
					int value = Integer.parseInt(fields[INDEX_FOR_VALUE]);
					
					for (int i=0; i < count; i++) {
						this.content.add(new Tile(fields[INDEX_FOR_LETTER], value));
					}
					
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// === Methods ===
	public String toString() {
		String s = "Bag of " + this.content.size() + " tiles : ";
		Iterator<Tile> it = this.content.iterator();
		while (it.hasNext())
			s += it.next();
		return s;
	}
	
	// === Getters & Setters ===
	public ArrayList<Tile> getLetters() {
		return content;
	}
	public void setLetters(ArrayList<Tile> content) {
		this.content = content;
	}
	public int size() {
		return this.content.size();
	}
}
