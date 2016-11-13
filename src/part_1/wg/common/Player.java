package part_1.wg.common;

import java.io.Serializable;
import java.util.ArrayList;

import part_1.wg.Main;

public class Player implements Serializable {
	private static final long serialVersionUID = 1L;
	static final public int RACK_SIZE = 7; // Max letter count on each rack
	
	// === Attributes ===
	protected int score;
	protected String name;
	protected ArrayList<Tile> rack;
	
	// === Constructor ===
	public Player(String name) {
		this.score = 0;
		this.setName(name);
		this.rack = new ArrayList<Tile>();
	}
	
	// === Methods ===
	public String toString() {
		return this.name;
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
			
			System.out.print("Row ? "); word.setRow(Integer.parseInt(Main.STDIN.nextLine()));
			System.out.print("Column ? "); word.setCol(Integer.parseInt(Main.STDIN.nextLine()));
			System.out.print("Orientation ? "); word.setOrientation(Integer.parseInt(Main.STDIN.nextLine()));
			
			System.out.print("Available cells:");
			maxSize = Player.RACK_SIZE;
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
			
			System.out.print("Word ? "); input = Main.STDIN.nextLine().toUpperCase();
			
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
	
	public void draw(Bag bag) {
		while (bag.size() > 0 && rack.size() < Player.RACK_SIZE) {
			Tile t = bag.getLetters().get(Main.RAND.nextInt(bag.size()));
			bag.getLetters().remove(t);
			rack.add(t);
		}
	}
	
	// === Getters & Setters ===
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public void incScore(int bonus) {
		this.score += bonus; // could be a malus if bonus < 0
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Tile> getRack() {
		return rack;
	}
	public void setRack(ArrayList<Tile> rack) {
		this.rack = rack;
	}
}
