package part_1.wg;

import part_1.wg.common.Bag;

public class Main {

	public static void main(String[] args) {
		Bag scr_bag = new Bag("config/scrabble_bag_fr.txt");
		Bag top_bag = new Bag("config/topword_bag_fr.txt");
		System.out.println(scr_bag);
		System.out.println(top_bag);
	}
}