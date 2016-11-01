package part_1.wg.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	static final public int MAX_PLAYER_COUNT = 4;
	
	public static Game loadGame(String path) {
		Game game = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (Game) ois.readObject();
			ois.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return game;
	}
	
	// === Attributes ===
	protected ArrayList<Player> players;
	protected Board board;
	protected Bag bag;

	// === Constructor ===
	protected Game(Player... players) {
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
			System.out.println(board);
			playTurn(players.get(0));
			
			// Players rotation
			Player tmp_player = players.get(0);
			players.remove(0);
			players.add(tmp_player);
		}
	}
	
	public abstract void playTurn(Player player);
	
	public abstract int applyWord(Word word);
	
	public boolean save(String path){
		try {
			FileOutputStream fout = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(fout);
			oos.writeObject(this);
			oos.close();
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
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
	public Bag getBag() {
		return bag;
	}
	public void setBag(Bag bag) {
		this.bag = bag;
	}
}
