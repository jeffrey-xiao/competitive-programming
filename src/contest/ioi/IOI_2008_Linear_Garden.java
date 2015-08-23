package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2008_Linear_Garden {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[][][] dp;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		long k = readLong();
		char[] s = next().toCharArray();
		dp = new int[n + 1][5][5];
		int curr = 0, min = 0, max = 0;
		int sum = 0;
		for (int x = 0; x < n; x++) {
			System.out.println(x);
			if (s[x] == 'P') {
				sum += compute(n - 1 - x, min, Math.max(max, curr + 1),
						curr + 1);
				curr--;
				min = Math.min(min, curr);
			} else {
				curr++;
				max = Math.max(max, curr);
			}
		}
		System.out.println((sum + 1) % k);
	}

	static int compute (int left, int lo, int hi, int curr) {
		if (hi - lo > 2 || Math.abs(curr) > 2)
			return 0;
		if (left == 0) {
			dp[left][lo + 2][hi + 2] = 1;
			return 1;
		}
		if (dp[left][lo + 2][hi + 2] > 0)
			return dp[left][lo + 2][hi + 2];
		int next = 0;

		next += compute(left - 1, lo, Math.max(hi, curr + 1), curr + 1);
		next += compute(left - 1, Math.min(lo, curr - 1), hi, curr - 1);

		dp[left][lo + 2][hi + 2] = next;
		return next;
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
