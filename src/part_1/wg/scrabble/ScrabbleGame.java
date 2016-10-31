package part_1.wg.scrabble;

import part_1.wg.common.Bag;
import part_1.wg.common.Board;
import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Word;

public class ScrabbleGame extends Game {
	static final public int SCRABBLE_BOARD_SIZE = 15;
	
	// === Constructor ===
	public ScrabbleGame(Player...players) {
		super(players);
		super.board = new Board(ScrabbleGame.SCRABBLE_BOARD_SIZE);
		super.bag = new Bag("config/scrabble_bag_fr.txt");
	}

	// === Methods ===
	@Override
	public void playTurn(Player player) {
		Word word = player.askForAWord();
		System.out.print("Trying " + word + "... ");
		
		int score = applyWord(word);
		if (score > 0) {
			System.out.println("Well done ! You got " + score + " points.");
			player.draw(bag);
			player.setScore(player.getScore() + score);
		} else {
			System.out.println(" Sorry, your word is invalid or cannot be placed there.");
			player.getRack().addAll(word.getTiles());
		}
	}

	@Override
	public int applyWord(Word word) {
		if (!word.isValid())
			return 0;
				
		// Are all cells empty ?
		for (int i=0; i < word.getTiles().size(); i++) {
			if (board.getGrid()[word.getRowOfTile(i)][word.getColOfTile(i)].count() > 0)
				return 0;
			
			// TODO environment check -> for now we can drop a word anywhere
		}
		
		// Drop the word
		int score = 0;
		for (int i=0; i < word.getTiles().size(); i++) {
			board.putTile(word.getTiles().get(i), word.getRowOfTile(i), word.getColOfTile(i));
			score += word.getTiles().get(i).getVal();
		}
		
		return score;
	}
}
