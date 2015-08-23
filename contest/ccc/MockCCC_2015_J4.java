package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.StringTokenizer;

public class MockCCC_2015_J4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[][] g = new char[n][n];
		for (int x = 0; x < n; x++)
			g[x] = next().toCharArray();
		boolean first = true;
		boolean second = true;
		boolean third = true;
		boolean fourth = true;
		HashMap<Character, Character> o = new HashMap<Character, Character>();
		o.put('\\', '/');
		o.put('/', '\\');
		o.put(')', '(');
		o.put('(', ')');
		o.put('O', 'O');
		o.put('.', '.');
		for (int x = 0; x < n / 2; x++) {
			for (int y = 0; y < n; y++) {
				if (x != n - 1 - x
						&& !(g[x][y] == o.get(g[n - 1 - x][y])
								|| (g[x][y] == '(' && g[n - 1 - x][y] == '(') || (g[x][y] == ')' && g[n
								- 1 - x][y] == ')')))
					first = false;
				if (x == n - 1 - x && (g[x][y] == '\\' || g[x][y] == '/'))
					first = false;

				if (x != n - 1 - x && !(g[y][x] == o.get(g[y][n - 1 - x])))
					second = false;
				if (x == n - 1 - x
						&& (g[y][x] == '(' || g[y][x] == ')' || g[y][x] == '\\' || g[y][x] == '/'))
					second = false;
			}
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (x == y) {
					if (g[x][y] == '(' || g[x][y] == ')')
						third = false;
				} else {
					if (g[x][y] != g[y][x])
						third = false;
				}
				if (x == n - 1 - y) {
					if (g[x][y] == '(' || g[x][y] == ')')
						fourth = false;
				} else {
					if (g[x][n - 1 - y] != g[n - 1 - y][x])
						fourth = false;
				}
			}
		}
		int count = 0;
		if (first)
			count++;
		if (second)
			count++;
		if (third)
			count++;
		if (fourth)
			count++;
		System.out.println(count);
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
