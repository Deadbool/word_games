package part_1.wg;

import java.util.Random;
import java.util.Scanner;

import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.scrabble.ScrabbleGame;

public class Main {
	public static final Scanner STDIN = new Scanner(System.in);
	public static final Random RAND = new Random(1234);

	public static void main(String[] args) {
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		
		System.out.println("***** Scrabble *****");
		
		Game scrabble = ScrabbleGame.newGame(p1, p2);
		
		/*p1.setScore(10);
		scrabble.save("saves/test.wg");
		scrabble = Game.loadGame("saves/test.wg");*/
		
		scrabble.launch();
	}
}