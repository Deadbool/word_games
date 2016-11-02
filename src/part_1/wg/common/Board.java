package part_1.wg.common;

import java.io.Serializable;

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	// === Attributes ===
	protected int size;
	protected Cell[][] grid;
	
	// === Constructor ===
	public Board(int size) {
		this.size = size;
		this.grid = new Cell[size][size];
		for (int r=0; r < size; r++) {
			for (int c=0; c < size; c++) {
				this.grid[r][c] = new Cell();
			}
		}
	}
	
	// === Methods ===
	public String toString() {
		String s = "";
		
		for (Cell[] row : grid) {
			for (Cell cell : row) {
				s += cell + " ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public void putTile(Tile tile, int row, int col) {
		this.grid[row][col].getStack().add(tile);
	}
	
	public boolean validPosition(int r, int c) {
		return r >= 0 && c >= 0 && r < size && c < size;
	}
	
	public Word crossingWord(Word word, int t) {
		Word cross = new Word(word.getRowOfTile(t), word.getColOfTile(t), 1 - word.getOrientation());
		int c, r;
		
		// Searching for upper/lefter tiles
		r = cross.getRowOfTile(cross.getRow() - 1);
		c = cross.getColOfTile(cross.getCol() - 1);
		while (validPosition(r, c) && grid[r][c].count() > 0) {
			cross.setRow(r);
			cross.setCol(c);
			r = cross.getRowOfTile(cross.getRow() - 1);
			c = cross.getColOfTile(cross.getCol() - 1);
		}
		
		// Adding all under/righter tile to the crossing word
		int i = 0;
		r = cross.getRowOfTile(i);
		c = cross.getColOfTile(i);
		while (validPosition(r, c) && grid[r][c].count() > 0) {
			cross.addTile(grid[r][c].getTopTile());
			++i;
			r = cross.getRowOfTile(i);
			c = cross.getColOfTile(i);
		}
		
		// If length == 1 -> there is no "real" crossing word, just the leter itself
		return (cross.length() > 1) ? cross : null;
	}

	// === Getters & Setters ===
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Cell[][] getGrid() {
		return grid;
	}
	public Cell cell(int r, int c) {
		return grid[r][c];
	}
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
}
