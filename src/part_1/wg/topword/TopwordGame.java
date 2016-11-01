package part_1.wg.topword;

import part_1.wg.common.Bag;
import part_1.wg.common.Board;
import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Word;

public class TopwordGame extends Game {
	private static final long serialVersionUID = 1L;
	static final public int TOPWORD_BOARD_SIZE = 8;
	
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
		
	}

	@Override
	public int applyWord(Word word) {
		return 0;
	}
}
