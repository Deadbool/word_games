package part_1.wg.common;

public class Board {
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
				Tile tile = cell.getTopTile();
				s += ((tile != null) ? tile : ".") + " ";
			}
			s += "\n";
		}
		
		return s;
	}
	
	public void putTile(Tile tile, int row, int col) {
		this.grid[row][col].getStack().add(tile);
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
