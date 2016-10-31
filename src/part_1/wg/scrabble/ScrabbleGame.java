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
		
		if (applyWord(word)) {
			player.draw(bag);
		} else {
			player.getRack().addAll(word.getTiles());
		}
	}

	@Override
	public boolean applyWord(Word word) {
		return false;
	}
}
