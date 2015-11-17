package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2013_Snowman {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int m, n, s;
	static Integer[] sz;
	static char[][] g;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		m = readInt();
		n = readInt();
		s = readInt();
		sz = new Integer[s];
		for (int i = 0; i < s; i++)
			sz[i] = readInt();
		g = new char[m][n];
		for (int i = 0; i < m; i++)
			g[i] = next().toCharArray();
		out.println(solve(0) ? "yes" : "no");
		out.close();
	}

	static boolean solve (int k) {
		if (k == s)
			return true;
		boolean canAdd = false;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (i + sz[k] <= m) {
					canAdd = true;
					for (int l = 0; l < sz[k]; l++)
						canAdd &= g[i + l][j] == '0';
					if (canAdd) {
						for (int l = 0; l < sz[k]; l++)
							g[i + l][j] = '1';
						if (solve(k + 1))
							return true;
						for (int l = 0; l < sz[k]; l++)
							g[i + l][j] = '0';
					}
				}
				if (j + sz[k] <= n) {
					canAdd = true;
					for (int l = 0; l < sz[k]; l++)
						canAdd &= g[i][j + l] == '0';
					if (canAdd) {
						for (int l = 0; l < sz[k]; l++)
							g[i][j + l] = '1';
						if (solve(k + 1))
							return true;
						for (int l = 0; l < sz[k]; l++)
							g[i][j + l] = '0';
					}
				}
			}
		}
		return false;
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
