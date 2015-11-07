package contest.ioi;

import java.util.Scanner;

public class IOI_2004_Phidias {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		/*
		 * optimal substructure at every point iterate through all the possible
		 * blocks that fit and choose the max(dp[x-x1][y] + dp[x1][y-y1],
		 * dp[x][y-y1] + dp[x-x1][y1])
		 */
		int width = scan.nextInt();
		int height = scan.nextInt();
		int[][] dp = new int[height + 1][width + 1];
		int[][] slabs = new int[scan.nextInt() + 1][2];// 0 is x, 1 is y
		for (int x = 1; x < slabs.length; x++) {
			slabs[x][0] = scan.nextInt();
			slabs[x][1] = scan.nextInt();
		}
		for (int y = 1; y < dp.length; y++) {
			for (int x = 1; x < dp[y].length; x++) {
				for (int z = 1; z < slabs.length; z++) {
					if (x >= slabs[z][0] && y >= slabs[z][1]) {
						int x1 = slabs[z][0];
						int y1 = slabs[z][1];
						int a = dp[y - y1][x] + dp[y1][x - x1] + x1 * y1;
						int b = dp[y][x - x1] + dp[y - y1][x1] + x1 * y1;
						dp[y][x] = Math.max(Math.max(a, b), dp[y][x]);
					} else {
						int a = dp[y - 1][x];
						int b = dp[y][x - 1];
						dp[y][x] = Math.max(Math.max(a, b), dp[y][x]);

					}
				}
			}
		}
		System.out.println(-dp[height][width] + height * width);
	}
}
