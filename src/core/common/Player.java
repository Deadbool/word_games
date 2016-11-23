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
		
		bag.getTiles().addAll(tiles);
		this.rack.removeAll(tiles);
		
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
