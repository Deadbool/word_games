package core.common;

import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 
 * Game words bag model.
 * 
 * Players will draw their tile here.
 * 
 * @author Nicolas Guégan
 *
 */

public class Bag implements Serializable {
	private static final long serialVersionUID = 1L;

	private static final String CONFIG_FILES_SEPARATOR = ":";
	private static final int INDEX_FOR_LETTER = 0;
	private static final int INDEX_FOR_COUNT = 1;
	private static final int INDEX_FOR_VALUE = 2;
	
	protected ArrayList<Tile> tiles;
	
	/**
	 * Constructor.
	 * 
	 * Create an empty word game bag.
	 */
	public Bag() {
		this.tiles = new ArrayList<Tile>();
	}

	/**
	 * Constructor.
	 * 
	 * Create a word game bag thanks to a configuration file.
	 * @param config_file_path
	 */
	public Bag(String config_file_path) {
		this.tiles = new ArrayList<Tile>();
		try {
			List<String> lines = Files.lines(Paths.get(config_file_path)).collect(Collectors.toList());

			// Skip the first line which is a comment
			lines.remove(0);

			for (String l : lines) {
				String[] fields = l.split(CONFIG_FILES_SEPARATOR);
				try {
					int count = Integer.parseInt(fields[INDEX_FOR_COUNT]);
					int value = Integer.parseInt(fields[INDEX_FOR_VALUE]);

					for (int i = 0; i < count; i++) {
						this.tiles.add(new Tile(fields[INDEX_FOR_LETTER], value));
					}

				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String toString() {
		String s = "Bag of " + this.tiles.size() + " tiles : ";
		Iterator<Tile> it = this.tiles.iterator();
		while (it.hasNext())
			s += it.next();
		return s;
	}

	/**
	 * @return Tiles in the bag.
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}

	/**
	 * @return The number of tiles in the bag.
	 */
	public int size() {
		return this.tiles.size();
	}
}
