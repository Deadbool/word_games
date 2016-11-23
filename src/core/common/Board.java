package core.common;

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
