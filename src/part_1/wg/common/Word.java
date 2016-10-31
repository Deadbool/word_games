package part_1.wg.common;

import java.util.ArrayList;

public class Word {
	public static final int HORIZONTAL = 0;
	public static final int VERTICAL = 1;
	
	// === Attributes ===
	private ArrayList<Tile> tiles;
	private int row;
	private int col;
	private int orientation;

	// === Constructor ===
	public Word(ArrayList<Tile> tiles, int r, int c, int orientation) {
		this.tiles = tiles;
		this.row = r;
		this.col = c;
		this.orientation = orientation;
	}

	// === Methods ===

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
}
