package part_1.wg.topword;

import java.util.ArrayList;

import part_1.wg.common.Bag;
import part_1.wg.common.Board;
import part_1.wg.common.Cell;
import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Word;

public class TopwordGame extends Game {
	private static final long serialVersionUID = 1L;
	static final public int TOPWORD_BOARD_SIZE = 8;
	static final public int TOPWORD_MAX_STACKED_TILES = 5;
	
	public static TopwordGame newGame(Player...players) {
		return new TopwordGame(players);
	}
	
	// === Constructor ===
	public TopwordGame(Player...players) {
		super(players);
		super.board = new Board(TopwordGame.TOPWORD_BOARD_SIZE);
		super.bag = new Bag("config/topword_bag_fr.txt");
	}
	
	// === Methods ===
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
				player.setScore(player.getScore() + score);
				break;
			} else {
				player.getRack().addAll(word.getTiles());
				
				word = player.askForAWord(board);
				System.out.print("Trying " + word + "... ");
				score = applyWord(word);
			}
		}
	}

	@Override
	public int applyWord(Word word) {
		int score = 0;
		ArrayList<Word> crossingWords = new ArrayList<Word>();
		Word w = null;
		Cell cell;
		boolean in_a_word = false;
		
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
				if (cell.getTopTile().equals(word.getTiles().get(i))) {
					in_a_word = true;
				} else {
					if (cell.count() + 1 > TOPWORD_MAX_STACKED_TILES) {
						System.out.println(word + " cannot be placed here !\n");
						return 0;
					} else
						in_a_word = true;
				}
			}
			
			// Is there a crossing word at this tile ?
			w = board.crossingWord(word, i);
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
			board.putTile(word.getTiles().get(i), word.getRowOfTile(i), word.getColOfTile(i));
		}		
		
		return score;
	}

	@Override
	public int scoreWord(Word word) {
		int score = 0;
		int coeff = 2;
		Cell cell;
		
		for (int i=0; i < word.getTiles().size(); i++) {
			cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)];
			if (cell.count() > 0)
				coeff = 1;
			
			score += 1 + word.getTiles().get(i).getVal();
			word.getTiles().get(i).setVal(0);
		}
		
		return score * coeff;
	}
}
