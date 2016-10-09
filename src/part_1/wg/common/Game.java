package part_1.wg.common;

import java.util.ArrayList;

public abstract class Game {
	// === Attributes ===
	protected ArrayList<Player> players;
	protected Board board;
	
	// === Constructor ===
	public Game(Player... players) {
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			this.players.add(p);
		}
	}
	
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
}
