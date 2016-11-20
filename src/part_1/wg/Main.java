package part_1.wg;

import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Tile;
import part_1.wg.common.Word;
import part_1.wg.common.ConsolePlayer;
import part_1.wg.common.Dictionary;
import part_1.wg.scrabble.ScrabbleGame;
import part_1.wg.topword.TopwordGame;

public class Main {
	
	public static void main(String[] args) {
		Player p1 = new ConsolePlayer("Player 1");
		Player p2 = new ConsolePlayer("Player 2");
		
		System.out.println("***** Scrabble *****");
		
		Game scrabble = ScrabbleGame.newGame(p1, p2);
		Game topword = TopwordGame.newGame(p1, p2);
		
		topword.launch();
				
		// CONSTITUTIONNALISASSIONS
		/*Word word = new Word();
		word.addTile(new Tile("CONSTITUTIONNALISASSIONS"));
		long start;
		
		start = System.currentTimeMillis();
		System.out.print(word.isValid());
		System.out.println(" - " + (System.currentTimeMillis() - start));
		
		start = System.currentTimeMillis();
		System.out.print(word.isValidTree());
		System.out.println(" - " + (System.currentTimeMillis() - start));*/
	}
}