package part_1.wg.common;

import java.util.ArrayList;

public abstract class Game {
	static final public int MAX_PLAYER_COUNT = 4;
	
	// === Attributes ===
	protected ArrayList<Player> players;
	protected Board board;
	protected Bag bag;

	// === Constructor ===
	public Game(Player... players) {
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			if (this.players.size() >= MAX_PLAYER_COUNT)
				break;
			this.players.add(p);
		}
	}
	
	// === Methods ===
	public void launch() {
		// Initial draw
		for (Player player : players) {
			player.draw(bag);
		}
		
		while(true) {
			playTurn(players.get(0));
			
			// Win ?
			if (true)
				break;
			
			// Players rotation
			Player tmp_player = players.get(0);
			players.remove(0);
			players.add(tmp_player);
		}
	}
	
	public abstract void playTurn(Player player);
	
	public abstract int applyWord(Word word);
	
	// === Getters & Setters
	public ArrayList<Player> getPlayers() {
		return players;
	}
	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}
	public Board getBoard() {
		return board;
	}
	public void setBoard(Board board) {
		this.board = board;
	}
	public Bag getBag() {
		return bag;
	}
	public void setBag(Bag bag) {
		this.bag = bag;
	}
}
