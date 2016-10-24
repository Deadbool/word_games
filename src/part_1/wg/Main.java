package part_1.wg;

import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.scrabble.ScrabbleGame;
import part_1.wg.topword.TopwordGame;

public class Main {

	public static void main(String[] args) {
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		
		System.out.println("Scrabble :");
		Game scrabble = new ScrabbleGame(p1, p2);
		System.out.println(scrabble.getBag());
		System.out.println(scrabble.getBoard());
		
		System.out.println("\nTopword :");
		Game topword = new TopwordGame(p1, p2);
		System.out.println(topword.getBag());
		System.out.println(topword.getBoard());
	}
}