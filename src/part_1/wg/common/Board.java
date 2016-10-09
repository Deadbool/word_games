package part_1.wg.common;

public class Board {
	// === Attributes ===
	protected int size;
	protected Cell[][] grid;
	
	// === Constructor ===
	public Board(int size) {
		this.size = size;
		this.grid = new Cell[size][size];
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
	public void setGrid(Cell[][] grid) {
		this.grid = grid;
	}
}
