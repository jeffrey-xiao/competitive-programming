package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_NERED {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int[][] g = new int[n][n];
		for (int i = 0; i < m; i++)
			g[readInt() - 1][readInt() - 1]++;
		int min = Integer.MAX_VALUE;
		for (int i = 1; i <= m; i++) {
			if (m % i != 0)
				continue;

			for (int x1 = 0; x1 < n - i + 1; x1++) {
				for (int y1 = 0; y1 < n - m / i + 1; y1++) {
					int res = 0;
					for (int x2 = x1; x2 < x1 + i; x2++) {
						for (int y2 = y1; y2 < y1 + m / i; y2++) {
							if (g[x2][y2] == 0)
								res++;
						}
					}
					min = Math.min(min, res);
					// System.out.println("NEW");
				}
			}

		}
		System.out.println(min);
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
