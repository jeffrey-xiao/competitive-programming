package usaco;

import java.util.Arrays;
import java.util.Scanner;

class USACO_2011_Umbrella_for_Cows {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int[] cows = new int[scan.nextInt()];
		int[] stalls = new int[scan.nextInt()];
		for (int x = 0; x < cows.length; x++)
			cows[x] = scan.nextInt();
		Arrays.sort(cows);
		for (int x = 0; x < stalls.length; x++)
			stalls[x] = scan.nextInt();
		for (int x = stalls.length - 2; x >= 0; x--)
			if (stalls[x] > stalls[x + 1])
				stalls[x] = stalls[x + 1];
		int[] dp = new int[cows.length];
		dp[0] = stalls[0];

		for (int x = 1; x < cows.length; x++) {
			dp[x] = stalls[cows[x] - cows[0]];

			for (int y = 0; y < x; y++)
				if (stalls[cows[x] - cows[y + 1]] + dp[y] < dp[x])
					dp[x] = stalls[cows[x] - cows[y + 1]] + dp[y];
		}
		System.out.println(dp[dp.length - 1]);
	}
}