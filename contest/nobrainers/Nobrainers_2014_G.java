package contest.nobrainers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Nobrainers_2014_G {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] nums = new int[n + 1];
		int sum = 0;
		for (int x = 1; x <= n; x++) {
			nums[x] = readInt();
			sum += nums[x];
		}
		boolean[] dp = new boolean[sum / 2 + 1];
		dp[0] = true;
		int max = 0;
		for (int y = 1; y < nums.length; y++) {
			for (int x = dp.length - 1; x >= 1; x--) {
				if (x - nums[y] >= 0 && dp[x - nums[y]]) {
					dp[x] = true;
					max = Math.max(x, max);
				}
			}

		}
		System.out.println((sum - max) - max);
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
