package contest.misc;

import java.util.Scanner;

public class Chocolate_Bar {
	static Scanner c = new Scanner(System.in);

	public static void main (String[] args) {
		int num = c.nextInt();
		for (; num > 0; num--) {
			System.out.println(c.nextInt() * c.nextInt() - 1);
		}
	}
}
