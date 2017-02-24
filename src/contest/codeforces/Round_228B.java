package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_228B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, cnt;
	static boolean[][] adj;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		cnt = bitCount(N);
		adj = new boolean[3 * cnt + 2][3 * cnt + 2];
		out.println(3 * cnt + 2);
		// layer
		for (int i = cnt - 1; i >= 0; i--) {
			int index = (cnt - 1 - i) * 3 + 2;
			if (i == cnt - 1) {
				//				adj[index][1] = adj[1][index] = true;
				adj[index + 2][1] = adj[1][index + 2] = true;
			} else {
				adj[index][index - 3] = adj[index - 3][index] = true;
				adj[index + 1][index - 3] = adj[index - 3][index + 1] = true;
				adj[index][index - 2] = adj[index - 2][index] = true;
				adj[index + 1][index - 2] = adj[index - 2][index + 1] = true;
				adj[index + 2][index - 1] = adj[index - 1][index + 2] = true;
				if ((N & 1 << (i + 1)) > 0) {
					adj[index][index - 1] = adj[index - 1][index] = true;
					adj[index + 1][index - 1] = adj[index - 1][index + 1] = true;
				}
			}
		}

		adj[0][(cnt - 1) * 3 + 2] = adj[(cnt - 1) * 3 + 2][0] = true;
		adj[0][(cnt - 1) * 3 + 3] = adj[(cnt - 1) * 3 + 3][0] = true;
		if ((N & 1) == 1)
			adj[0][(cnt - 1) * 3 + 4] = adj[(cnt - 1) * 3 + 4][0] = true;

		for (int i = 0; i < 3 * cnt + 2; i++) {
			for (int j = 0; j < 3 * cnt + 2; j++) {
				out.print(adj[i][j] ? 'Y' : 'N');
			}
			out.println();
		}
		//		out.println(check());
		out.close();
	}

	static int check () {
		int n = 3 * cnt + 2;
		int[] dp = new int[n];
		dp[1] = 1;
		for (int i = 1; i < n; i++)
			for (int j = 0; j < i; j++)
				if (adj[i][j])
					dp[i] += dp[j];
		int ret = 0;
		for (int j = 1; j < n; j++)
			if (adj[0][j])
				ret += dp[j];
		return ret;
	}

	static int bitCount (int n) {
		int ret = 0;
		while (n > 0) {
			n >>= 1;
			ret++;
		}
		return ret;
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
