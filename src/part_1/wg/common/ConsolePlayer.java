package part_1.wg.common;

public class ConsolePlayer extends Player {	
	private static final long serialVersionUID = 1L;

	// === Constructor ===
	public ConsolePlayer(String name) {
		super(name);
	}
	
	public Word askForAWord(Board board) {
		Word word;
		boolean stop, found;
		String input;
		Cell cell;
		int maxSize;
		
		do {
			stop = true;
			word = new Word();
	
			System.out.println("Rack: " + rack);
			
			System.out.print("Row ? "); word.setRow(Integer.parseInt(Game.STDIN.nextLine()));
			System.out.print("Column ? "); word.setCol(Integer.parseInt(Game.STDIN.nextLine()));
			System.out.print("Orientation ? "); word.setOrientation(Integer.parseInt(Game.STDIN.nextLine()));
			
			System.out.print("Available cells:");
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
			
			for (int i=0; i < input.length(); i++) {
				found = false;
				String let = input.substring(i,i+1);
				for (Tile tile : rack) {
					if (tile.getLet().equals(let)) {
						word.addTile(tile);
						rack.remove(tile);
						found = true;
						break;
					}
				}
				
				if (!found) {
					cell = board.cell(word.getRow() + i*Word.ROW_INC[word.getOrientation()], 
							word.getCol() + i*Word.COL_INC[word.getOrientation()]);
					
					if (cell.count() > 0 && cell.getTopTile().getLet().equals(let)) {
						word.addTile(cell.getTopTile());
					} else {
						// If we arrive here it's because an input letter is not in rack -> ask again
						stop = false;
						rack.addAll(word.getTiles());
						System.out.println("You cannot write " + input + " here !");
						break;
					}
				}
			}
			
		} while (!stop);
		
		// Searching for upper/lefter tiles
		int r = word.getRowOfTile(-1);
		int c = word.getColOfTile(-1);
		while (board.validPosition(r, c) && board.getGrid()[r][c].count() > 0) {
			word.setRow(r);
			word.setCol(c);
			word.getTiles().add(0, board.getGrid()[r][c].getTopTile());
			r = word.getRowOfTile(-1);
			c = word.getColOfTile(-1);
		}
					
		return word;
	}
}
