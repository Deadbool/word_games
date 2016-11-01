package part_1.wg.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Word {
	public static final String DICTIONARY_PATH = "config/dico.txt";
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int[] ROW_INC = {0, 1};
	public static final int[] COL_INC = {1, 0};
	
	// === Attributes ===
	private ArrayList<Tile> tiles;
	private int row;
	private int col;
	private int orientation;

	// === Constructor ===
	public Word() {
		this.tiles = new ArrayList<Tile>();
		this.row = 0;
		this.col = 0;
		this.orientation = 0;
	}
	
	public Word(int r, int c, int orientation) {
		this.tiles = new ArrayList<Tile>();
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}
	
	public Word(ArrayList<Tile> tiles, int r, int c, int orientation) {
		this.tiles = tiles;
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}

	// === Methods ===
	public boolean isValid() {
		boolean ok = false;
		String str = this.toString();
		
		try {
			List<String> words = Files.lines(Paths.get(Word.DICTIONARY_PATH)).collect(Collectors.toList());
			ok = words.contains(str);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return ok;
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Tile tile : tiles) {
			str.append(tile.getLet());
		}
		return str.toString();
	}

	// === Getters & Setters ===
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	public int getRow() {
		return row;
	}
	public void setRow(int row) {
		this.row = row;
	}
	public int getCol() {
		return col;
	}
	public void setCol(int col) {
		this.col = col;
	}
	public int getOrientation() {
		return orientation;
	}
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	public int getRowOfTile(int tileIndex) {
		return (orientation == Word.HORIZONTAL) ? row : row + tileIndex;
	}
	public int getColOfTile(int tileIndex) {
		return (orientation == Word.VERTICAL) ? col : col + tileIndex;
	}
	public void addTile(Tile t) {
		this.tiles.add(t);
	}
}
