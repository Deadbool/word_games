package part_1.wg.scrabble;

import part_1.wg.common.Board;
import part_1.wg.common.Game;
import part_1.wg.common.Player;

public class ScrabbleGame extends Game {
	static final public int SCRABBLE_BOARD_SIZE = 15;
	
	// === Constructor ===
	public ScrabbleGame(Player...players) {
		super(players);
		super.board = new Board(ScrabbleGame.SCRABBLE_BOARD_SIZE);
	}
}
