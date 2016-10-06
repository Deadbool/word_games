package part_1.wg;

import part_1.wg.common.Bag;
import part_1.wg.topword.TopwordBag;

public class Main {

	public static void main(String[] args) {
		Bag bag = new TopwordBag();
		
		System.out.println(bag);
		System.out.println(bag.getLetters().size());
	}
}