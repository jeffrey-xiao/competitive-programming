package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Trees {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[][] matrix;
	static boolean[] v;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		matrix = new int[n][n];
		v = new boolean[n];
		int count = 0;
		int com = 0;
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++) {
				matrix[x][y] = readInt();
				count += matrix[x][y];
			}
		for (int x = 0; x < n; x++)
			if (!v[x]) {
				dfs(x);
				com++;
			}
		System.out.println(count / 2 - (n - com));
	}

	private static void dfs (int x) {
		v[x] = true;
		for (int i = 0; i < n; i++)
			if (matrix[x][i] == 1 && !v[i])
				dfs(i);

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
