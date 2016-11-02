package part_1.wg.scrabble;

import java.util.ArrayList;

import part_1.wg.common.Bag;
import part_1.wg.common.Board;
import part_1.wg.common.Cell;
import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Word;

public class ScrabbleGame extends Game {
	private static final long serialVersionUID = 1L;
	static final public int SCRABBLE_BOARD_SIZE = 15;
	
	public static ScrabbleGame newGame(Player...players) {
		return new ScrabbleGame(players);
	}
	
	// === Constructor ===
	protected ScrabbleGame(Player...players) {
		super(players);
		super.board = new Board(ScrabbleGame.SCRABBLE_BOARD_SIZE);
		super.bag = new Bag("config/scrabble_bag_fr.txt");
	}

	// === Methods ===
	@Override
	public void playTurn(Player player) {
		Word word = player.askForAWord(board);
		System.out.print("Trying " + word + "... ");
		
		int score = applyWord(word);
		if (score > 0) {
			System.out.println("Well done ! You got " + score + " points.");
			player.draw(bag);
			player.setScore(player.getScore() + score);
		} else {
			player.getRack().addAll(word.getTiles());
		}
	}

	@Override
	public int applyWord(Word word) {
		int score = 0;
		ArrayList<Word> crossingWords = new ArrayList<Word>();
		Word w = null;
		
		if (!word.isValid()) {
			System.out.println(word + " is not a valid word !");
			return 0;
		}
				
		// Are all cells empty (or used in the word) ?
		for (int i=0; i < word.getTiles().size(); i++) {
			Cell cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)];
			
			if (cell.count() > 0 && !cell.getTopTile().equals(word.getTiles().get(i))) {
				System.out.println(word + " cannot be placed here !");
				return 0;
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
		
		// Drop the word
		for (int i=0; i < word.getTiles().size(); i++) {
			board.putTile(word.getTiles().get(i), word.getRowOfTile(i), word.getColOfTile(i));
		}
		
		score += word.score();
		
		return score;
	}
}
