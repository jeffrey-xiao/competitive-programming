package contest.other;

import java.util.Scanner;

class A_Game {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int[] coins = new int[scan.nextInt()];
		for (int x = 0; x < coins.length; x++)
			coins[x] = scan.nextInt();

		int[][] table = new int[coins.length][coins.length];

		for (int x = table.length; x >= 0; x--) {
			for (int y = 0, z = table.length - x; y < x; y++, z++) {
				// y and z are the indices -> from y to z
				int i = y + 1 > z - 1 ? 0 : table[y + 1][z - 1];
				int j = y + 2 > z ? 0 : table[y + 2][z];
				int k = z - 2 < y ? 0 : table[y][z - 2];
				table[y][z] = max(coins[y] + min(i, j), coins[z] + min(i, k));

			}

		}
		/*
		 * for(int[] x: table){ for(int y:x) System.out.print(y+" ");
		 */
		System.out.print(table[0][coins.length - 1] + " ");
		System.out.println(Math.min(table[1][coins.length - 1],
				table[0][coins.length - 2]));
	}

	static int max (int a, int b) {
		return a > b ? a : b;
	}

	static int min (int a, int b) {
		return a < b ? a : b;
	}
}