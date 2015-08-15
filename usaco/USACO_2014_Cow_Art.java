package usaco;

import java.util.Scanner;

public class USACO_2014_Cow_Art {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		char[][] grid = new char[scan.nextInt()][];
		char[][] grid1 = new char[grid.length][];
		for (int x = 0; x < grid.length; x++) {
			String s = scan.next();
			grid[x] = s.toCharArray();
			grid1[x] = s.toCharArray();
		}
		int RGB = 0;

		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if (grid[x][y] == 'B') {
					remove(grid, x, y, 'B', ' ');
					RGB++;
				} else if (grid[x][y] == 'R') {
					remove(grid, x, y, 'R', ' ');
					RGB++;
				} else if (grid[x][y] == 'G') {
					remove(grid, x, y, 'G', ' ');
					RGB++;
				}
			}
		}
		int other = 0;
		for (int x = 0; x < grid.length; x++) {
			for (int y = 0; y < grid.length; y++) {
				if (grid1[x][y] == 'B') {
					remove(grid1, x, y, 'B', ' ');
					other++;
				} else if (grid1[x][y] == 'R' || grid1[x][y] == 'G') {
					remove(grid1, x, y, 'R', 'G');
					other++;
				}

			}
		}
		System.out.printf("%d %d", RGB, other);
	}

	private static void remove (char[][] grid, int x, int y, char c, char c2) {
		if (x < 0 || y < 0 || x >= grid.length || y >= grid.length)
			return;
		if (grid[x][y] != c && grid[x][y] != c2)
			return;
		grid[x][y] = '.';
		remove(grid, x + 1, y, c, c2);
		remove(grid, x - 1, y, c, c2);
		remove(grid, x, y + 1, c, c2);
		remove(grid, x, y - 1, c, c2);
	}

}
