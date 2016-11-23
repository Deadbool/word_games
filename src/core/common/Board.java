package core.common;

import java.io.Serializable;

/**
 * Word games board representation.
 * 
 * It's composed of cells where players will drop tiles.
 * 
 * @author Nicolas Guégan
 *
 */

public class Board implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int size;
	protected Cell[][] grid;
	

	/**
	 * Constructor.
	 * 
	 * Create a square board.
	 * @param size
	 */
	public Board(int size) {
		this.size = size;
		this.grid = new Cell[size][size];
		for (int r=0; r < size; r++) {
			for (int c=0; c < size; c++) {
				this.grid[r][c] = new Cell();
			}
		}
	}
	
	/**
	 * toString()
	 */
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
	
	/**
	 * Put a tile on a cell of the board.
	 * @param tile, the tile to put
	 * @param row, the targeted cell's row
	 * @param col, the targeted cell's column
	 */
	public void putTile(Tile tile, int row, int col) {
		this.grid[row][col].getStack().add(tile);
	}
	
	/**
	 * Check if a position is available on the board.
	 * @param r, row
	 * @param c, column
	 * @return true if position is valid, else false
	 */
	public boolean validPosition(int r, int c) {
		return r >= 0 && c >= 0 && r < size && c < size;
	}

	/**
	 * @return The size of the board.
	 */
	public int getSize() {
		return size;
	}
	
	/**
	 * @return The board's cells grid.
	 */
	public Cell[][] getGrid() {
		return grid;
	}
	
	/**
	 * Get the cell at the wanted position.
	 * @param r, row
	 * @param c, column
	 * @return The wanted cell if position is valid, else null
	 */
	public Cell cell(int r, int c) {
		return validPosition(r, c) ? grid[r][c] : null;
	}
}
