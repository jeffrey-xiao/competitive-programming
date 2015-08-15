package ccc;

import java.util.Scanner;

public class CCC_1996_C {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int[][] cases = new int[scan.nextInt()][2];
		for (int x = 0; x < cases.length; x++) {
			cases[x][0] = scan.nextInt();
			cases[x][1] = scan.nextInt();
		}
		for (int[] x : cases) {
			System.out.println("The bit patterns are");
			printPatterns(x[0], x[1], "");
			System.out.println();
		}
	}

	private static void printPatterns (int i, int j, String s) {
		if (i == 0 && j == 0) {
			System.out.println(s);
			return;
		} else if (i == 0 && j != 0 || j < 0)
			return;
		for (int x = 1; x >= 0; x--) {
			if (x == 1)
				printPatterns(i - 1, j - 1, s + "1");
			else
				printPatterns(i - 1, j, s + "0");
		}

	}
}
