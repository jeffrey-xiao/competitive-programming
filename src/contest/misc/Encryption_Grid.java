package contest.misc;

import java.util.Scanner;

public class Encryption_Grid {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int columns = scan.nextInt();
		while (columns != 0) {
			String s = scan.next();
			int counter = 0;
			char[][] grid = new char[(int) Math.ceil(s.length() / 1.0 / columns)][columns];
			for (int x = 0; x < grid.length; x++) {
				if (x % 2 == 0) {
					for (int y = 0; y < grid[0].length; y++) {
						grid[x][y] = s.charAt(counter);
						counter++;
					}
				} else {
					for (int y = grid[0].length - 1; y >= 0; y--) {
						grid[x][y] = s.charAt(counter);
						counter++;
					}
				}
			}
			for (int x = 0; x < grid[0].length; x++) {
				for (int y = 0; y < grid.length; y++) {
					System.out.print(grid[y][x]);
				}
			}
			System.out.println();
			columns = scan.nextInt();
		}
	}
}
