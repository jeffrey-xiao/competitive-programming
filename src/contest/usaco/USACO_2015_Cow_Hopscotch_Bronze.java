package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Cow_Hopscotch_Bronze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		boolean[][] g = new boolean[r][c];
		for (int i = 0; i < r; i++) {
			char[] in = next().toCharArray();
			for (int j = 0; j < c; j++) {
				g[i][j] = in[j] == 'R';
			}
		}
		int[][] ways = new int[r][c];
		ways[0][0] = 1;
		for (int i = 0; i < r; i++) {
			for (int j = 0; j < c; j++) {
				if (i == 0 && j == 0)
					continue;
				for (int x = 0; x < i; x++)
					for (int y = 0; y < j; y++)
						ways[i][j] += (g[x][y] != g[i][j] ? ways[x][y] : 0);
			}
		}
		System.out.println(ways[r - 1][c - 1]);
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
