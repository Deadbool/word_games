package part_1.wg.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public abstract class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	static final public int MAX_PLAYER_COUNT = 4;
	
	public static final Scanner STDIN = new Scanner(System.in);
	public static final Random RAND = new Random(1234);
	
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
		Collections.shuffle(players);
		
		// Initial draw
		for (Player player : players) {
			player.draw(bag);
		}
		
		while(true) {
			for (Player player : players)
				System.out.println(player + " (score: " + player.getScore() + ")");
			System.out.println("Tiles left in bag: " + bag.size());
			System.out.println(board);
			
			playTurn(players.get(0));
			
			if (players.get(0).getRack().size() == 0) {
				System.out.println("\n*** Finish ***");
				System.out.println("Leaderboard :");
				
				players.sort(new Comparator<Player>() {
					@Override
					public int compare(Player a, Player b) {
						return b.getScore() - a.getScore();
					}
					
				});
				
				for (int i=0; i < players.size(); i++)
					System.out.println((i+1) + ") " + players.get(i) + " - " + players.get(i).getScore());
				
				break;
			}
			
			// Players rotation
			Player tmp_player = players.get(0);
			players.remove(0);
			players.add(tmp_player);
		}
	}
	
	public abstract void playTurn(Player player);
	
	public abstract int applyWord(Word word);
	
	public abstract int scoreWord(Word word);
	
	public abstract Word crossingWord(Word word, int t);
	
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
