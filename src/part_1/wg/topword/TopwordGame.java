package part_1.wg.topword;

import part_1.wg.common.Bag;
import part_1.wg.common.Board;
import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Word;

public class TopwordGame extends Game {
	static final public int TOPWORD_BOARD_SIZE = 8;
	
	// === Constructor ===
	public TopwordGame(Player...players) {
		super(players);
		super.board = new Board(TopwordGame.TOPWORD_BOARD_SIZE);
		super.bag = new Bag("config/topword_bag_fr.txt");
	}
	
	// === Methods ===
	@Override
	public void playTurn(Player player) {
		
	}

	@Override
	public boolean applyWord(Word word) {
		return false;
	}
}
