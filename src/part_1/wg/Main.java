package part_1.wg;

import java.util.ArrayList;

import part_1.wg.common.Game;
import part_1.wg.common.Player;
import part_1.wg.common.Tile;
import part_1.wg.common.Word;
import part_1.wg.scrabble.ScrabbleGame;

public class Main {

	public static void main(String[] args) {
		Player p1 = new Player("Player 1");
		Player p2 = new Player("Player 2");
		
		System.out.println("***** Scrabble *****");
		
		Game scrabble = new ScrabbleGame(p1, p2);
		
		scrabble.launch();
		
		System.out.println("\n>> T1 <<");
		System.out.println(scrabble.getBag());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(scrabble.getBoard());
		
		/*ArrayList<Tile> tiles = new ArrayList<Tile>();
		tiles.add(new Tile("A", 1));
		tiles.add(new Tile("H", 4));
		Word word = new Word(tiles, 0, 0, Word.HORIZONTAL);
		
		System.out.print(word+" ");
		System.out.println((word.isValid()) ? "is valid." : "is not valid");*/
		
		System.out.println("\n>> T2 <<");
		System.out.println(scrabble.getBag());
		System.out.println(p1);
		System.out.println(p2);
		System.out.println(scrabble.getBoard());
	}
}