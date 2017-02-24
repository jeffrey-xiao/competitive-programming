package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: crosswords
 */
import java.util.*;
import java.io.*;

public class crosswords {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("crosswords.in"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("crosswords.out")));

		int n = readInt();
		int m = readInt();
		char[][] grid = new char[n][];
		for (int x = 0; x < n; x++)
			grid[x] = next().toCharArray();
		int count = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (grid[x][y] == '#')
					continue;
				if ((y - 1 < 0 || grid[x][y - 1] == '#') && y + 2 < m && grid[x][y + 1] != '#' && grid[x][y + 2] != '#') {
					grid[x][y] = '!';
					count++;
				} else if ((x - 1 < 0 || grid[x - 1][y] == '#') && x + 2 < n && grid[x + 1][y] != '#' && grid[x + 2][y] != '#') {
					grid[x][y] = '!';
					count++;
				}
			}
		}
		pr.println(count);
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < m; y++) {
				if (grid[x][y] == '!')
					pr.println(x + 1 + " " + (y + 1));
			}
		}
		pr.close();
		System.exit(0);
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
