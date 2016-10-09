package part_1.wg.common;

public class Board {
	private static boolean exist = false;
	
	// === Attributes ===
	private int size;
	private Letter[][] grid;
	
	// === Constructor ===
	private Board(int size) {
		this.setSize(size);
		this.setGrid(new Letter[size][size]);
	}
	
	// === Factory ===
	public static Board create(int size) {
		if (size > 1 && !Board.exist) {
			Board.exist = true;
			return new Board(size);
		} else
			return null;
	}

	// === Getters & Setters ===
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	public Letter[][] getGrid() {
		return grid;
	}
	public void setGrid(Letter[][] grid) {
		this.grid = grid;
	}
}
