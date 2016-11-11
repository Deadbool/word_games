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
		
		// Bonus cells
		int center = 7;
		int bonus = 0;
		
		board.getGrid()[center][center].setBonus(Cell.DOUBLE_WORD);
		for (int i=1; i < center+1; i++) {
						
			if (i == 1) {
				bonus = Cell.DOUBLE_LETTER;
			} else if (i == 2)
				bonus = Cell.TRIPLE_LETTER;
			else if (i == 4) {
				bonus = Cell.DOUBLE_LETTER;
				board.getGrid()[center][center + i].setBonus(bonus);
				board.getGrid()[center][center - i].setBonus(bonus);
				board.getGrid()[center + i][center].setBonus(bonus);
				board.getGrid()[center - i][center].setBonus(bonus);
			} else if (i == center) {
				bonus = Cell.TRIPLE_WORD;
				board.getGrid()[center][center + i].setBonus(bonus);
				board.getGrid()[center][center - i].setBonus(bonus);
				board.getGrid()[center + i][center].setBonus(bonus);
				board.getGrid()[center - i][center].setBonus(bonus);
			} else
				bonus = Cell.DOUBLE_WORD;
			
			board.getGrid()[center + i][center + i].setBonus(bonus);
			board.getGrid()[center + i][center - i].setBonus(bonus);
			board.getGrid()[center - i][center + i].setBonus(bonus);
			board.getGrid()[center - i][center - i].setBonus(bonus);
		}
		
		bonus = Cell.DOUBLE_LETTER;
		board.getGrid()[center + 1][center + 5].setBonus(bonus);
		board.getGrid()[center + 1][center - 5].setBonus(bonus);
		board.getGrid()[center - 1][center + 5].setBonus(bonus);
		board.getGrid()[center - 1][center - 5].setBonus(bonus);

		board.getGrid()[center + 5][center + 1].setBonus(bonus);
		board.getGrid()[center - 5][center + 1].setBonus(bonus);
		board.getGrid()[center + 5][center - 1].setBonus(bonus);
		board.getGrid()[center - 5][center - 1].setBonus(bonus);
		
		board.getGrid()[center + 4][center + 7].setBonus(bonus);
		board.getGrid()[center + 4][center - 7].setBonus(bonus);
		board.getGrid()[center - 4][center + 7].setBonus(bonus);
		board.getGrid()[center - 4][center - 7].setBonus(bonus);

		board.getGrid()[center + 7][center + 4].setBonus(bonus);
		board.getGrid()[center - 7][center + 4].setBonus(bonus);
		board.getGrid()[center + 7][center - 4].setBonus(bonus);
		board.getGrid()[center - 7][center - 4].setBonus(bonus);
		
		bonus = Cell.TRIPLE_LETTER;
		board.getGrid()[center + 2][center + 6].setBonus(bonus);
		board.getGrid()[center + 2][center - 6].setBonus(bonus);
		board.getGrid()[center - 2][center + 6].setBonus(bonus);
		board.getGrid()[center - 2][center - 6].setBonus(bonus);

		board.getGrid()[center + 6][center + 2].setBonus(bonus);
		board.getGrid()[center - 6][center + 2].setBonus(bonus);
		board.getGrid()[center + 6][center - 2].setBonus(bonus);
		board.getGrid()[center - 6][center - 2].setBonus(bonus);
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
		Cell cell;
		boolean in_a_word = false;
		
		if (!word.isValid()) {
			System.out.println(word + " is not a valid word !");
			return 0;
		}
				
		// Check validity of position?
		boolean centered = false;
		
		for (int i=0; i < word.getTiles().size(); i++) {
			cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)];
			
			if (word.getRowOfTile(i)==7 && word.getColOfTile(i)==7)
				centered = true;
			
			if (cell.count() > 0) {
				if (cell.getTopTile().equals(word.getTiles().get(i))) {
					in_a_word = true;
				} else {
					System.out.println(word + " cannot be placed here !");
					return 0;
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
		
		if (!in_a_word && crossingWords.size() == 0 && !centered) {
			System.out.println(word + " cannot be placed here !");
			return 0;
		}
		
		// Clear bonus
		for (int i=0; i < word.getTiles().size(); i++) {
			board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)].setBonus(0);
		}
		
		// Drop the word
		for (int i=0; i < word.getTiles().size(); i++) {
			board.putTile(word.getTiles().get(i), word.getRowOfTile(i), word.getColOfTile(i));
		}
		
		score += word.score(this.board);
		
		// Clear bonus and check if the word is at an available place
		
		
		return score;
	}
}
