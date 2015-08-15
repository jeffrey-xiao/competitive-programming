package ccc;

import java.util.Scanner;

class CCC_2011_J5 {
	public static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int number = scan.nextInt();
		int[] friends = new int[number];
		for (int x = 0; x < number - 1; x++)
			friends[x] = scan.nextInt();
		System.out.println(getWays(friends, number, number));
	}

	public static int getWays (int[] friends, int number, int t) {
		int result = 1;
		for (int x = 0; x < number; x++) {
			if (friends[x] == t)
				result *= (1 + getWays(friends, number, x + 1));
		}
		return result;
	}
}
