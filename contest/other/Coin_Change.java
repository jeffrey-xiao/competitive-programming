package contest.other;

import java.util.Scanner;

public class Coin_Change {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int amount = scan.nextInt();
		int numOfCoins = scan.nextInt();
		int[][] table = new int[amount + 1][2];
		for (int x = 1; x < table.length; x++)
			table[x][0] = -5000;
		int[] coins = new int[numOfCoins + 1];
		for (int x = 1; x < coins.length; x++)
			coins[x] = scan.nextInt();

		for (int x = 1; x <= numOfCoins; x++) {
			for (int y = amount; y >= coins[x]; y--) {
				for (int z = 1; z <= y / coins[x]; z++) {
					int b = table[y][0];
					int a = coins[x] * z + table[y - coins[x] * z][0];
					if (a > b) {
						table[y][1] = table[y - coins[x] * z][1] + z;
						table[y][0] = a;
					} else if (a == b)
						table[y][1] = min(table[y - coins[x] * z][1] + z,
								table[y][1]);
					if (table[y][0] < 0) {
						table[y][0] = -5000;
						table[y][1] = 0;
					}
				}
			}
			/*
			 * for(int[] a: table)
			 * System.out.printf("%3d,%3d    ",a[0]<-1?0:a[0],a[1]);
			 * System.out.println();
			 */
		}

		System.out.println(table[amount][1]);
	}

	public static int min (int a, int b) {
		return a < b ? a : b;
	}

	public static int max (int a, int b) {
		return a < b ? a : b;
	}
}
