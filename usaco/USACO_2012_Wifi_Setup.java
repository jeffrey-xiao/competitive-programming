package usaco;

import java.util.Arrays;
import java.util.Scanner;

public class USACO_2012_Wifi_Setup {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		// cost is a+b*r
		int a = scan.nextInt();
		int b = scan.nextInt();
		int[] cows = new int[n + 1];
		double[] dp = new double[n + 1];
		for (int x = 1; x < n + 1; x++) {
			cows[x] = scan.nextInt();
			dp[x] = 5000005;
		}
		Arrays.sort(cows);

		for (int x = 1; x < n + 1; x++) {
			for (int y = 1; y <= x; y++) {
				double r = (cows[x] - cows[y]) / 2.0;
				dp[x] = Math.min(dp[x], dp[y - 1] + a + b * r);
			}
		}
		System.out.println(dp[n]);
	}
}
