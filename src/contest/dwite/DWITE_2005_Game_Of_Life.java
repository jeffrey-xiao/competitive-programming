package contest.dwite;

import java.util.Scanner;

public class DWITE_2005_Game_Of_Life {
	static Scanner scan = new Scanner(System.in);
	static final int[][] moves = { {-1, 1}, {0, 1}, {1, 1}, {-1, 0}, {1, 0}, {-1, -1}, {0, -1}, {1, -1}};

	public static void main (String[] args) {
		char[][] grid = new char[scan.nextInt()][];
		scan.nextLine();
		for (int x = 0; x < grid.length; x++)
			grid[x] = scan.nextLine().toCharArray();
		for (int x = 1; x <= 100; x++) {
			int alive = 0;
			char[][] newgrid = new char[grid.length][grid[0].length];
			for (int y = 0; y < grid.length; y++) {
				for (int z = 0; z < grid[y].length; z++) {
					int neighbours = countNeighbours(y, z, grid);
					if (neighbours == 3 || neighbours == 2 && grid[y][z] == 'X') {
						newgrid[y][z] = 'X';
						alive++;
					} else
						newgrid[y][z] = '.';
				}
			}
			grid = newgrid;
			if (x == 1 || x == 5 || x == 10 || x == 50 || x == 100)
				System.out.println(alive);

		}
	}

	private static int countNeighbours (int x, int y, char[][] grid) {
		int neighbours = 0;
		for (int[] i : moves) {
			if (i[0] + x >= 0 && i[0] + x < grid.length && i[1] + y >= 0 && i[1] + y < grid[0].length && grid[x + i[0]][y + i[1]] == 'X')
				neighbours++;

		}
		return neighbours;
	}
}
