package core.consoleGames;

import java.util.ArrayList;

import core.common.Board;
import core.common.Cell;
import core.common.Game;
import core.common.Player;
import core.common.Tile;
import core.common.Word;

/**
 * Word game player based on console interactions.
 * 
 * @author Nicolas Guégan
 *
 */

public class ConsolePlayer extends Player {	
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor.
	 * 
	 * Create a new ConsolePlayer with a his name.
	 * 
	 * @param name
	 */
	public ConsolePlayer(String name) {
		super(name);
	}
	
	/**
	 * Ask the player the word he wants to put on the board.
	 * @param board
	 * @return word, The chosen word.
	 */
	public Word askForAWord(Board board) {
		Word word;
		String input;
		int maxSize;
		Cell cell;
		
		do {
			word = new Word();
	
			System.out.println("Rack: " + rack);
			
			System.out.print("Row ? "); word.setRow(Integer.parseInt(Game.STDIN.nextLine()));
			System.out.print("Column ? "); word.setCol(Integer.parseInt(Game.STDIN.nextLine()));
			System.out.print("Orientation ? "); word.setOrientation(Integer.parseInt(Game.STDIN.nextLine()));
			
			System.out.print("Available cells: ");
			maxSize = ConsolePlayer.RACK_SIZE;
			for (int i=0; i < maxSize; i++) {
				if (!board.validPosition(word.getRow() +i*Word.ROW_INC[word.getOrientation()],
				                        word.getCol() + i*Word.COL_INC[word.getOrientation()]))
					break;
				
				cell = board.cell(word.getRow() +i*Word.ROW_INC[word.getOrientation()],
						word.getCol() + i*Word.COL_INC[word.getOrientation()]);
				System.out.print(" " + cell);
				
				if (cell.count() > 0)
					++maxSize;
			}
			System.out.println("");
			
			System.out.print("Word ? "); input = Game.STDIN.nextLine().toUpperCase();
			
		} while (!checkWord(word, input, board));
					
		return word;
	}
}
