package part_1.wg.topword;

import java.util.HashSet;

import part_1.wg.common.Bag;
import part_1.wg.common.Letter;

public class TopwordBag extends Bag {
	// === Constructor ===
	public TopwordBag() {
		super.letters = new HashSet<Letter>();
		
		// TODO move this into a configuration file
		String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		int[] counts = new int[] {9,2,2,3,15,2,2,2,8,1,1,5,3,6,6,2,1,6,6,6,6,2,1,11,1,1};
		
		for (int i=0; i < alpha.length(); i++) {
			for (int n=0; n < counts[i]; n++) {
				super.letters.add(new Letter(String.valueOf(alpha.charAt(i)), 1));
			}
		}
	}
}
