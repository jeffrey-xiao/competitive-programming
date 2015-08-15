package woburn;

import java.util.Scanner;

public class Woburn_Challenge_Smallest_Latin_Square3 {
	static int[][] finalGrid = new int[0][0];
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		int[][] grid = new int[n][n];

		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++) {
				grid[x][y] = y + 1;
			}

		for (int x = 1; x < n; x++)
			for (int y = 0; y < n; y++)
				for (int z = y + 1; z < n; z++)
					if (isDup(grid, x, y, grid[x][y])) {
						swap(grid, x, y, z);
						for (int x1 = 0; x1 < n; x1++) {
							for (int y1 = 0; y1 < n; y1++)
								System.out.print(grid[x1][y1] + " ");
							System.out.println();
						}
						System.out.println();
					}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++)
				System.out.print(grid[x][y] + " ");
			System.out.println();
		}

	}

	private static void swap (int[][] grid, int x, int y1, int y2) {
		int temp = grid[x][y1];
		grid[x][y1] = grid[x][y2];
		grid[x][y2] = temp;

	}

	public static boolean isDup (int[][] grid, int x, int y, int n) {
		for (int z = 0; z < y; z++) {
			if (grid[x][z] == n) {
				System.out.println(x + " " + y + " " + grid[x][y] + " " + n);

				return true;
			}
		}
		for (int z = 0; z < x; z++) {
			if (grid[z][y] == n) {
				System.out.println(grid[x][y] + " " + n);
				return true;
			}
		}
		return false;
	}

}
