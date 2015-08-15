package ioi;

import java.util.Scanner;

public class IOI_2014_Square {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int size = scan.nextInt();
		int[][] grid = new int[size + 1][size + 1];
		for (int x = 1; x < size + 1; x++)
			for (int y = 1; y < size + 1; y++)
				grid[x][y] = scan.nextInt();
		int[][] table = new int[size + 1][size + 1];
		int max = 0;
		int num = 1;
		for (int x = 1; x < size + 1; x++) {
			for (int y = 1; y < size + 1; y++) {
				if (grid[x][y] == 1)
					table[x][y] = Math.min(
							Math.min(table[x][y - 1], table[x - 1][y]),
							table[x - 1][y - 1]) + 1;
				if (table[x][y] > max) {
					max = table[x][y];
					num = 1;
				} else if (table[x][y] == max)
					num++;
			}
		}
		System.out.println(max * num);
	}

}
