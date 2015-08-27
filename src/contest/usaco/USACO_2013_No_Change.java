package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2013_No_Change {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int[] c = new int[n];
		int[] dp = new int[1 << n];
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (int x = 0; x < n; x++)
			c[x] = readInt();
		int sum = 0;
		for (int x = 0; x < m; x++) {
			int a = readInt();
			sum += a;
			ts.add(sum);
		}
		int res = -1;
		for (int x = 1; x < (1 << n); x++) {
			int best = 0;
			int left = 0;
			for (int y = 0; y < n; y++) {
				if ((x & (1 << y)) > 0) {
					x ^= (1 << y);
					Integer next = ts.floor(dp[x] + c[y]);
					// System.out.println(next + " " + Integer.toString(x, 2));
					if (next != null && next > best)
						best = next;
					x ^= (1 << y);
				} else {
					left += c[y];
				}
			}
			dp[x] = best;
			// System.out.println("HERE " + left + " " + best);
			if (best == sum)
				res = Math.max(res, left);
		}
		System.out.println(res);
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
