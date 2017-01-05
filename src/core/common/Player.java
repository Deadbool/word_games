package core.common;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Player model for word games, with a name, a score and a rack.
 * 
 * @author Nicolas Guégan
 *
 */

public abstract class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	static final public int RACK_SIZE = 7; // Max letter count on each rack
	
	protected int score;
	protected String name;
	protected ArrayList<Tile> rack;
	
	/**
	 * Constructor.
	 * 
	 * Create a new player with his name with a score of 0 and an empty rack.
	 * 
	 * @param name
	 */
	public Player(String name) {
		this.score = 0;
		this.setName(name);
		this.rack = new ArrayList<Tile>();
	}

	public String toString() {
		return this.name;
	}
	
	/**
	 * Ask the player the word he wants to put on the board.
	 * @param board
	 * @return word, The chosen word.
	 */
	public abstract Word askForAWord(Board board);
	
	/**
	 * Add tiles from a bag to player's rack while the rack is not filled.
	 * @param bag
	 */
	public void draw(Bag bag) {
		while (bag.size() > 0 && rack.size() < Player.RACK_SIZE) {
			Tile t = bag.getTiles().get(Game.RAND.nextInt(bag.size()));
			bag.getTiles().remove(t);
			rack.add(t);
		}
	}
	
	/**
	 * Exchange some tiles of the player's rack with new ones of a bag.
	 * @param bag
	 * @param tiles, The tiles to exchange.
	 * @return true if there are enough tiles in the bag, else false
	 */
	public boolean exchange(Bag bag, ArrayList<Tile> tiles) {
		if (bag.size() < tiles.size())
			return false;
		
		for (int i=0; i < tiles.size(); i++) {
			Tile t = bag.getTiles().get(Game.RAND.nextInt(bag.size()));
			bag.getTiles().remove(t);
			rack.add(t);
		}
		
		for (int i=0; i < tiles.size(); i++) {
			bag.getTiles().add(tiles.get(i));
		}
		
		return true;
	}
	
	/**
	 * Check if the word can be formed with current letters and at this position.
	 * @param word
	 * @param input
	 * @param board
	 * @return true if word and position are ok, else false
	 */
	protected boolean checkWord(Word word, String input, Board board) {
		boolean found;
		ArrayList<Tile> removedTiles = new ArrayList<Tile>();
		Cell cell;
		
		int jokers = 0;
		for (Tile tile : rack) {
			jokers += (tile.equals(Tile.JOKER)) ? 1 : 0;
		}
		
		for (int i=0; i < input.length(); i++) {
			found = false;
			String let = input.substring(i,i+1);
			for (Tile tile : rack) {
				if (tile.getLet().equals(let)) {
					word.addTile(tile);
					removedTiles.add(tile);
					rack.remove(tile);
					found = true;
					break;
				}
			}
			
			if (!found) {
				cell = board.cell(word.getRow() + i*Word.ROW_INC[word.getOrientation()], 
						word.getCol() + i*Word.COL_INC[word.getOrientation()]);
				
				if (cell.count() > 0 && cell.getTopTile().getLet().equals(let)) {
					word.addTile(cell.getTopTile());
				} else if (jokers > 0) {
					--jokers;
					word.addTile(Tile.createUsedJoker(let));
				} else {
					// If we arrive here it's because an input letter is not in rack -> ask again
					rack.addAll(word.getTiles());
					System.out.println("You cannot write " + input + " here !");
					
					return false;
				}
			}
		}
		
		// Searching for upper/lefter tiles
		int r = word.getRowOfTile(-1);
		int c = word.getColOfTile(-1);
		while (board.validPosition(r, c) && board.getGrid()[r][c].count() > 0) {
			word.setRow(r);
			word.setCol(c);
			word.getTiles().add(0, board.getGrid()[r][c].getTopTile());
			r = word.getRowOfTile(-1);
			c = word.getColOfTile(-1);
		}
		
		rack.addAll(removedTiles);
		
		return true;
	}
	
	/**
	 * @return The player's score.
	 */
	public int getScore() {
		return score;
	}
	
	/**
	 * Increase the player's score.
	 * @param val, The value to add to the current score.
	 */
	public void incScore(int val) {
		this.score += val;
	}
	
	/**
	 * @return The player's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Set the player's name.
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return The player's rack.
	 */
	public ArrayList<Tile> getRack() {
		return rack;
	}
}
