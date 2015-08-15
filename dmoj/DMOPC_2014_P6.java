package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_P6 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int t = readInt();
		Problem[] p = new Problem[n];
		long[][] dp = new long[2][t + 1];
		for (int x = 0; x < 2; x++)
			for (int y = 0; y <= t; y++)
				dp[x][y] = -1;
		dp[0][0] = 0;
		for (int x = 0; x < n; x++)
			p[x] = new Problem(readInt(), readInt(), readInt(), readInt(),
					readInt(), readInt());
		for (int x = 0; x < n; x++) {
			// for(int y = t; y >= 0; y--){
			// dp[(x+1)%2][y] = -1;
			// }
			for (int y = t; y >= 0; y--) {
				if (dp[x % 2][y] >= 0) {
					for (int z = 0; z < 3; z++) {
						int nextT = y + (int) p[x].p[z][0];
						long nextP = dp[x % 2][y] + p[x].p[z][1];
						if (nextT <= t) {
							dp[(x + 1) % 2][nextT] = Math.max(
									dp[(x + 1) % 2][nextT], nextP);
						}
					}
				}
				dp[x % 2][y] = Math.max(x - 1 < 0 ? 0 : dp[(x - 1) % 2][y],
						y - 1 < 0 ? 0 : dp[x % 2][y - 1]);
			}

		}
		long max = 0;
		for (int y = 0; y <= t; y++) {
			max = Math.max(max, dp[(n) % 2][y]);
		}
		System.out.println(max);
	}

	static class Problem {
		long[][] p = new long[3][2];

		Problem (long pp, long vp, long pa, long va, long pg, long vg) {
			p[0][0] = pp;
			p[0][1] = vp;
			p[1][0] = pa;
			p[1][1] = va;
			p[2][0] = pg;
			p[2][1] = vg;
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
