package core.common;

import java.util.ArrayList;

public class Word {
	public static final String DICTIONARY_PATH = "config/dico.txt";
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int[] ROW_INC = {0, 1};
	public static final int[] COL_INC = {1, 0};
	
	// === Attributes ===
	private ArrayList<Tile> tiles;
	private ArrayList<Boolean> news;
	private int row;
	private int col;
	private int orientation;

	// === Constructor ===
	public Word() {
		this.tiles = new ArrayList<Tile>();
		this.news = new ArrayList<Boolean>();
		this.row = 0;
		this.col = 0;
		this.orientation = 0;
	}
	
	public Word(int r, int c, int orientation) {
		this.tiles = new ArrayList<Tile>();
		this.news = new ArrayList<Boolean>();
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}
	
	public Word(ArrayList<Tile> tiles, int r, int c, int orientation) {
		this.tiles = tiles;
		this.news = new ArrayList<Boolean>();
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}

	// === Methods ===
	
	public boolean isValid() {
		return Dictionary.get().contains(this.toString());
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder();
		for (Tile tile : tiles) {
			str.append(tile.getLet());
		}
		return str.toString().toUpperCase();
	}
	
	public void addTile(Tile t) {
		this.tiles.add(t);
		this.news.add(true);
	}
	
	public void addTile(Tile t, boolean newTile) {
		this.tiles.add(t);
		this.news.add(newTile);
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
	public int length() {
		return this.tiles.size();
	}
	public ArrayList<Boolean> getNews() {
		return news;
	}
	public void setNews(ArrayList<Boolean> news) {
		this.news = news;
	}
	public boolean isNew(int i) {
		return this.news.get(i);
	}
	public void setNew(int i, boolean b) {
		this.news.set(i, b);
	}
}
