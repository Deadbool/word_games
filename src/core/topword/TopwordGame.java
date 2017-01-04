package core.topword;

import java.util.ArrayList;

import core.common.Bag;
import core.common.Board;
import core.common.Cell;
import core.common.Game;
import core.common.Player;
import core.common.Tile;
import core.common.Word;

/**
 * TopWord game model composed of players, a board and a bag of tiles.
 * 
 * @author Nicolas Guégan
 *
 */

public class TopwordGame extends Game {
	private static final long serialVersionUID = 1L;
	static final public int TOPWORD_BOARD_SIZE = 8;
	static final public int TOPWORD_MAX_STACKED_TILES = 5;
	
	/**
	 * Constructor.
	 * 
	 *  Create a new Scrabble game from a list of players.
	 * @param players
	 */
	public TopwordGame(Player...players) {
		super(players);
		super.board = new Board(TopwordGame.TOPWORD_BOARD_SIZE);
		super.bag = new Bag("config/topword_bag_fr.txt");
	}
	
	/**
	 * Play the turn of a player.
	 * @param player
	 */
	@Override
	public void playTurn(Player player) {
		System.out.println("=== "+player+" ===");
		
		Word word = player.askForAWord(board);
		System.out.print("Trying " + word + "... ");
		
		int score = applyWord(word);
		while (true) {
			if (score > 0) {
				System.out.println("Well done ! You got " + score + " points.\n");
				player.draw(bag);
				player.incScore(score);
				break;
			} else {				
				word = player.askForAWord(board);
				System.out.print("Trying " + word + "... ");
				score = applyWord(word);
			}
		}
	}

	/**
	 * Put a word on the board after checking it's validity.
	 * @param word
	 * @return The score for this turn.
	 */
	@Override
	public int applyWord(Word word) {
		int score = 0;
		ArrayList<Word> crossingWords = new ArrayList<Word>();
		Word w = null;
		Cell cell;
		boolean in_a_word = false;
		boolean newTiles = false;
		
		if (!word.isValid()) {
			System.out.println(word + " is not a valid word !\n");
			return 0;
		}
				
		// Check validity of position?
		boolean centered = false;
		
		for (int i=0; i < word.getTiles().size(); i++) {
			cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)];
			
			if ((word.getRowOfTile(i)==3 && (word.getColOfTile(i)==3 || word.getColOfTile(i)==4)) ||
					(word.getRowOfTile(i)==4 && (word.getColOfTile(i)==3 || word.getColOfTile(i)==4)))
				centered = true;
			
			if (cell.count() > 0) {
				in_a_word = true;
				
				if (cell.getTopTile().equals(word.getTiles().get(i))) {
					word.setNew(i, false);
				} else {
					if (cell.count() + 1 > TOPWORD_MAX_STACKED_TILES) {
						System.out.println(word + " cannot be placed here !\n");
						return 0;
					}
					
					newTiles = true;
				}
			} else {
				newTiles = true;
			}
			
			// Is there a crossing word at this tile ?
			w = this.crossingWord(word, i);
			if (w != null) {
				// Is this crossing word valid ?
				if (w.isValid()) {
					crossingWords.add(w);
				} else {
					System.out.println(w + " is not a valid word !");
					return 0;
				}
			}
		}
		
		if (!newTiles) {
			System.out.println("You have to use new tiles !");
			return 0;
		}
		
		if (!in_a_word && !centered) {
			System.out.println(word + " cannot be placed here !");
			return 0;
		}
		
		// Increase score
		score += this.scoreWord(word);
		for (Word wo : crossingWords) {
			score += this.scoreWord(wo);
		}
		
		// Drop the word
		for (int i=0; i < word.getTiles().size(); i++) {
			Tile tile = word.getTiles().get(i);
			board.putTile(tile, word.getRowOfTile(i), word.getColOfTile(i));
			if (word.isNew(i)) {
				players.get(0).getRack().remove(tile);
			}
		}		
		
		return score;
	}

	/**
	 * Compute the score of a word on the current board.
	 * @param word
	 * @return score
	 */
	@Override
	public int scoreWord(Word word) {
		int score = 0;
		int coeff = 2;
		Cell cell;
		int news = 0;
		
		for (int i=0; i < word.getTiles().size(); i++) {
			cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)];
			if (cell.count() > 0)
				coeff = 1;
			
			news += (word.isNew(i)) ? 1 : 0;
			
			score += ((word.isNew(i)) ? cell.count() : 0)
					+ 1 + word.getTiles().get(i).getVal();
			word.getTiles().get(i).setVal(0);
		}
		
		return score * coeff + ((news < Player.RACK_SIZE) ? 0 : 50);
	}
	
	/**
	 * Detect the crossing word for a given tile of a word.
	 * @param word
	 * @param t, The index of the tile to check.
	 * @return The crossing word if there is one, else null
	 */
	@Override
	public Word crossingWord(Word word, int t) {
		int c, r;
		r = word.getRowOfTile(0);
		c = word.getColOfTile(0);
		Word cross = new Word(word.getRowOfTile(t), word.getColOfTile(t), 1 - word.getOrientation());
		
		if (this.board.validPosition(r, c)) {
			if (this.board.getGrid()[r][c].count() > 0 && word.getTiles().get(t).equals(this.board.getGrid()[r][c].getTopTile()))
				return null;
			this.board.getGrid()[r][c].put(word.getTiles().get(t));
		}
		
		// Searching for upper/lefter tiles
		r = cross.getRowOfTile(-1);
		c = cross.getColOfTile(-1);
		while (this.board.validPosition(r, c) && this.board.getGrid()[r][c].count() > 0) {
			cross.setRow(r);
			cross.setCol(c);
			r = cross.getRowOfTile(-1);
			c = cross.getColOfTile(-1);
		}
		
		// Adding all under/righter tile to the crossing word
		int i = 0;
		r = cross.getRowOfTile(i);
		c = cross.getColOfTile(i);
		while (this.board.validPosition(r, c)) {
			if (r == word.getRowOfTile(t) && c == word.getColOfTile(t))
				cross.addTile(word.getTiles().get(t));
			else {
				if (this.board.getGrid()[r][c].count() > 0)
					cross.addTile(this.board.getGrid()[r][c].getTopTile());
				else
					break;
			}
			
			++i;
			r = cross.getRowOfTile(i);
			c = cross.getColOfTile(i);
		}
		
		r = word.getRowOfTile(0);
		c = word.getColOfTile(0);		
		if (this.board.validPosition(r, c)) {
			this.board.getGrid()[r][c].pick();
		}
		
		// If length == 1 -> there is no "real" crossing word, just the leter itself
		return (cross.length() > 1) ? cross : null;
	}
}
