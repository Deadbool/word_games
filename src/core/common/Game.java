package core.common;

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

/**
 * Word game model usable for Scrabble and Topword games.
 * 
 * @author nico
 *
 */

public abstract class Game implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final int MAX_PLAYER_COUNT = 4;
	
	public static final Scanner STDIN = new Scanner(System.in);
	public static final Random RAND = new Random(System.currentTimeMillis());
	
	protected ArrayList<Player> players;
	protected Board board;
	protected Bag bag;
	protected boolean loaded;

	/**
	 * Constructor.
	 * 
	 * Create a new game with some players.
	 * @param players, The players
	 */
	protected Game(Player... players) {
		this.players = new ArrayList<Player>();
		for (Player p : players) {
			if (this.players.size() >= MAX_PLAYER_COUNT)
				break;
			this.players.add(p);
		}
		this.loaded = false;
	}
	
	/**
	 * Load a saved game from a ".wg" file.
	 * @param path, The game file
	 * @return The loaded game
	 */
	public static Game loadGame(String path) {
		Game game = null;
		
		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream ois = new ObjectInputStream(fis);
			game = (Game) ois.readObject();
			ois.close();
			fis.close();
			
			game.loaded = true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return game;
	}
	
	/**
	 * Start the game.
	 */
	public void start() {
		
		if (!loaded) {
			Collections.shuffle(players);
			
			// Initial draw
			for (Player player : players) {
				player.draw(bag);
			}
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
	
	/**
	 * Play the turn of a player.
	 * @param player
	 */
	public abstract void playTurn(Player player);
	
	/**
	 * Put a word on the board after checking it's validity.
	 * @param word
	 * @return The score for this turn.
	 */
	public abstract int applyWord(Word word);
	
	/**
	 * Compute the score of a word on the current board.
	 * @param word
	 * @return score
	 */
	public abstract int scoreWord(Word word);
	
	/**
	 * Detect the crossing word for a given tile of a word.
	 * @param word
	 * @param t, The index of the tile to check.
	 * @return The crossing word if there is one, else null
	 */
	public abstract Word crossingWord(Word word, int t);
	
	/**
	 * Save the game in a ".wg" file.
	 * @param path, The path of the save file.
	 * @return true if the save success, else false.
	 */
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
	
	/**
	 * @return The players list.
	 */
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/**
	 * @return The game's board.
	 */
	public Board getBoard() {
		return board;
	}
	
	/**
	 * @return The game's bag.
	 */
	public Bag getBag() {
		return bag;
	}
}

