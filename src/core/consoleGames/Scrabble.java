package core.consoleGames;

import core.common.Game;
import core.common.Player;
import core.scrabble.ScrabbleGame;

public class Scrabble {
	
	public static void main(String[] args) {
		Player p1 = new ConsolePlayer("Player 1");
		Player p2 = new ConsolePlayer("Player 2");
		
		System.out.println("***** Scrabble *****");
		
		Game scrabble = ScrabbleGame.newGame(p1, p2);
		
		scrabble.launch();
	}
}