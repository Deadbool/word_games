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
		boolean newTiles = false;
		
		if (!word.isValid()) {
			System.out.println(word + " is not a valid word !\n");
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
					word.setNew(i, false);
				} else {
					System.out.println(word + " cannot be placed here !\n");
					return 0;
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
		for (Word wo : crossingWords) {
			score += this.scoreWord(wo);
		}
		score += this.scoreWord(word);
		
		// Clear bonus
		for (int i=0; i < word.getTiles().size(); i++) {
			board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)].setBonus(0);
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
		Cell cell;
		int coeff = 1;
		int bonus = 0;
		
		for (int i=0; i < word.getTiles().size(); i++) {
			bonus = 1;
			cell = board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)]; 
			
			if (cell.getBonus() > 0) {
				if (cell.getBonus() <= Cell.TRIPLE_LETTER) {
					bonus = cell.getBonus();
				} else {
					coeff *= cell.getBonus() - 5;
				}
			}
			
			score += word.getTiles().get(i).getVal() * bonus;
		}
		
		return score * coeff;
	}
	
	@Override
	public Word crossingWord(Word word, int t) {
		if (this.board.getGrid()[word.getRowOfTile(t)][word.getColOfTile(t)].count() > 0)
			return null;
		
		Word cross = new Word(word.getRowOfTile(t), word.getColOfTile(t), 1 - word.getOrientation());
		int c, r;
		
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
		
		// If length == 1 -> there is no "real" crossing word, just the leter itself
		return (cross.length() > 1) ? cross : null;
	}
}
