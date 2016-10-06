package part_1.wg;

import part_1.wg.common.Letter;

public class Main {

	public static void main(String[] args) {
		Letter[] word = new Letter[] {new Letter('a', 0), new Letter('b', 0), new Letter('c', 0)};
		
		for (Letter l : word) {
			System.out.print(l);
		}
	}
}