package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2014_STUDENTSKO {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		// br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int n = readInt();
		int k = readInt();
		int[] id = new int[n];
		Number[] sorted = new Number[n];
		for (int i = 0; i < n; i++) {
			sorted[i] = new Number(i, readInt());
		}
		Arrays.sort(sorted);
		for (int i = 0; i < n; i++) {
			id[sorted[i].i] = i / k + 1;
		}
		int ans = 0;
		int[] dp = new int[n + 1];
		for (int i = 1; i <= n; i++)
			dp[i] = 1 << 30;
		for (int i = 0; i < n; i++) {
			int lo = 0;
			int hi = n;
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				if (dp[mid] <= id[i])
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			dp[hi + 1] = Math.min(dp[hi + 1], id[i]);
			ans = Math.max(hi + 1, ans);
		}

		ps.println(n - ans);
		ps.close();
		Arrays.sort(sorted);
	}

	static class Number implements Comparable<Number> {
		int i, val;

		Number (int i, int val) {
			this.i = i;
			this.val = val;
		}

		@Override
		public int compareTo (Number n) {
			return val - n.val;
		}
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}