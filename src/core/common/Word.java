package core.common;

import java.util.ArrayList;

/**
 * Word representation as a list of tiles, a position on the board and an orientation.
 * 
 * @author Nicolas Guégan
 *
 */

public class Word {
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	public static final int[] ROW_INC = {0, 1};
	public static final int[] COL_INC = {1, 0};
	
	private ArrayList<Tile> tiles;
	private ArrayList<Boolean> news;
	private int row;
	private int col;
	private int orientation;

	/**
	 * Constructor.
	 * 
	 * Create a new empty word with default position and orientation ([0:0] and horizontal)
	 */
	public Word() {
		this.tiles = new ArrayList<Tile>();
		this.news = new ArrayList<Boolean>();
		this.row = 0;
		this.col = 0;
		this.orientation = 0;
	}
	
	/**
	 * Constructor.
	 * 
	 * Create a new empty word with its position and orientation.
	 * @param r, row
	 * @param c, column
	 * @param orientation
	 */
	public Word(int r, int c, int orientation) {
		this.tiles = new ArrayList<Tile>();
		this.news = new ArrayList<Boolean>();
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}
	
	/**
	 * Constructor.
	 * 
	 * Create a new word from a list of tiles with its position and orientation.
	 * @param tiles
	 * @param r
	 * @param c
	 * @param orientation
	 */
	public Word(ArrayList<Tile> tiles, int r, int c, int orientation) {
		this.tiles = tiles;
		this.news = new ArrayList<Boolean>();
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}

	/**
	 * Check the validity of the word by searching it in the dictionary.	
	 * @return true if the word is valid, else false
	 */
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
	
	/**
	 * Add a tile at the end of the word.
	 * @param t, the tile to add
	 */
	public void addTile(Tile t) {
		this.tiles.add(t);
		this.news.add(true);
	}
	
	/**
	 * Add a tile at the end of the word, specifying if
	 * this tile is already present on the board or not.
	 * @param t, the tile to add
	 * @param newTile, false if the tile is already on the board, else true
	 */
	public void addTile(Tile t, boolean newTile) {
		this.tiles.add(t);
		this.news.add(newTile);
	}

	/**
	 * @return The tiles composing the word.
	 */
	public ArrayList<Tile> getTiles() {
		return tiles;
	}
	
	/**
	 * Set the list of tiles of the word.
	 * @param tiles
	 */
	public void setTiles(ArrayList<Tile> tiles) {
		this.tiles = tiles;
	}
	
	/**
	 * @return The row of the word's first tile.
	 */
	public int getRow() {
		return row;
	}
	
	
	/*
	 * Set the row of the word's first tile.
	 */
	public void setRow(int row) {
		this.row = row;
	}
	
	/**
	 * @param The column of the word's first tile.
	 */
	public int getCol() {
		return col;
	}
	
	/*
	 * Set the column of the word's first tile.
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	/**
	 * @return The word's orientation.
	 */
	public int getOrientation() {
		return orientation;
	}
	
	/**
	 * Set the word's orientation.
	 * @param orientation
	 */
	public void setOrientation(int orientation) {
		this.orientation = orientation;
	}
	
	/**
	 * Get the row of a word's particular tile.
	 * @param tileIndex
	 * @return row
	 */
	public int getRowOfTile(int tileIndex) {
		return (orientation == Word.HORIZONTAL) ? row : row + tileIndex;
	}
	
	/**
	 * Get the column of a word's particular tile.
	 * @param tileIndex
	 * @return column
	 */
	public int getColOfTile(int tileIndex) {
		return (orientation == Word.VERTICAL) ? col : col + tileIndex;
	}
	
	/**
	 * @return The number of tiles composing the word.
	 */
	public int length() {
		return this.tiles.size();
	}
	
	/**
	 * Use this function to know if a tile of the world is already on the board or not.
	 * @param i, the tile index in the word
	 * @return false if the tile is already on the board, else true
	 */
	public boolean isNew(int i) {
		return this.news.get(i);
	}
	
	/**
	 * Set a tile of the word as already present on the board.
	 * @param i, the tile index in the word
	 * @param b, false if the tile is already on the board, else true
	 */
	public void setNew(int i, boolean b) {
		this.news.set(i, b);
	}
}
