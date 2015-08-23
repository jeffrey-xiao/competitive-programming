package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_142A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static final int MAX = 1 << 22;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		int[][] min = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				min[i][j] = MAX;
		for (int i = 0; i < n; i++) {
			char[] in = next().toCharArray();
			int len = in.length;
			for (int j = 0; j < len; j++)
				if (in[j] == '1')
					min[i][j] = 0;
			for (int k = 0; k < 10; k++) {
				for (int j = 0; j < len; j++)
					min[i][j] = Math.min(min[i][(j - 1 + len) % len] + 1, min[i][j]);
				for (int j = len - 1; j >= 0; j--)
					min[i][j] = Math.min(min[i][(j + 1) % len] + 1, min[i][j]);
			}
		}
		int best = MAX;
		for (int j = 0; j < m; j++) {
			int curr = 0;
			for (int i = 0; i < n; i++) {
				curr += min[i][j];
			}
			best = Math.min(best, curr);
		}
		if (best >= MAX)
			System.out.println(-1);
		else
			System.out.println(best);
		pr.close();
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
