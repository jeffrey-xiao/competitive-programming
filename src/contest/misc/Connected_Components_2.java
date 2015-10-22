package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Connected_Components_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static TreeSet<Integer> ts = new TreeSet<Integer>();
	static boolean[][] matrix;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		matrix = new boolean[n][n];
		for (int x = 0; x < n; x++)
			for (int y = 0; y < n; y++)
				matrix[x][y] = readInt() == 1;
		boolean[] v = new boolean[n];
		for (int x = 0; x < n; x++) {
			if (!v[x]) {
				v[x] = true;
				ts.clear();
				bfs(x, v);
				for (Integer i : ts)
					System.out.print(i + 1 + " ");
				System.out.println();
			}
		}
	}

	private static void bfs (int y, boolean[] v) {
		ts.add(y);
		for (int x = 0; x < n; x++)
			if (matrix[y][x] && !v[x]) {
				v[x] = true;
				bfs(x, v);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
