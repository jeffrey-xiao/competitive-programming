package ccc;

import java.util.Scanner;

public class CCC_2014_Stage_2_Troyangles {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		char[][] table = new char[scan.nextInt()][];
		scan.nextLine();
		for (int x = 0; x < table.length; x++)
			table[x] = scan.nextLine().toCharArray();
		int[][] solution = new int[table.length][table[0].length];
		for (int x = table.length - 1; x >= 0; x--) {
			for (int y = 0; y < table[x].length; y++) {
				if (table[x][y] == '#')
					solution[x][y]++;
				if (table[x][y] == '#' && x + 1 < table.length
						&& y + 1 < table[x].length && y - 1 >= 0
						&& table[x + 1][y] == '#' && table[x + 1][y + 1] == '#'
						&& table[x + 1][y - 1] == '#')
					solution[x][y] += Math.min(Math.min(solution[x + 1][y],
							solution[x + 1][y + 1]), solution[x + 1][y - 1]);
			}
		}
		int sum = 0;
		for (int[] x : solution) {
			for (int y : x) {
				// System.out.print(y + " ");
				sum += y;
			}
			// System.out.println();
		}
		System.out.println(sum);
	}
}
