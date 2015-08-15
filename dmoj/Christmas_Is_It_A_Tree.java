package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Christmas_Is_It_A_Tree {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[][] adj = new boolean[4][4];
	static boolean[] v = new boolean[4];

	public static void main (String[] args) throws IOException {
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++)
				adj[x][y] = readInt() == 1;
		boolean yes = dfs(0, -1);
		for (int x = 1; x < 4; x++)
			if (!v[x])
				yes = false;
		if (yes)
			System.out.println("Yes");
		else
			System.out.println("No");
	}

	private static boolean dfs (int i, int prev) {
		v[i] = true;
		for (int x = 0; x < 4; x++) {
			if (x != prev && adj[i][x]) {
				if (v[x])
					return false;
				if (!dfs(x, i))
					return false;
			}
		}
		return true;
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
