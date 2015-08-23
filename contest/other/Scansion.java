package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Scansion {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static String[] s = {"0001", "0010", "0011", "0100", "0110", "1000",
			"1001"};
	static ArrayList<String> four = new ArrayList<String>(Arrays.asList(s));

	public static void main (String[] args) throws IOException {
		String s = " " + next();
		long[] dp = new long[s.length()];
		dp[2] = 1;
		dp[3] = 1;
		for (int x = 4; x < dp.length; x++) {
			long count = dp[x - 2] + dp[x - 3];
			if (x >= 4) {
				// System.out.println(four.contains(s.substring(x-3,x+1)));
				if (x == 4 && four.contains(s.substring(x - 3, x + 1)))
					count += 1;
				else if (x != 4 && four.contains(s.substring(x - 3, x + 1)))
					count += dp[x - 4];
			}
			dp[x] = count % 10007;
			// System.out.print(dp[x] + " ");
		}
		// System.out.println();
		System.out.println(dp[dp.length - 1]);
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
