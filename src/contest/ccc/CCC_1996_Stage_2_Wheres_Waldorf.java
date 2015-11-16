package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CCC_1996_Stage_2_Wheres_Waldorf {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int r;
	static int c;
	static boolean a;
	static boolean b;
	static boolean d;
	static boolean e;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			r = readInt();
			c = readInt();
			char[][] grid = new char[r][c];
			for (int x = 0; x < r; x++) {
				grid[x] = next().toLowerCase().toCharArray();
			}
			int n = readInt();

			for (int x1 = 0; x1 < n; x1++) {
				String s = next().toLowerCase();
				TreeSet<Point> p = new TreeSet<Point>();
				main : for (int x = 0; x < r; x++) {
					for (int y = 0; y < c; y++) {
						if (find(x, y, grid, s)) {
							p.add(new Point(x + 1, y + 1));
							break main;
						} else if (find(x, y, grid, new StringBuilder(s).reverse().toString())) {
							if (b)
								p.add(new Point(x + 1, y + s.length()));
							else if (d)
								p.add(new Point(x + s.length(), y + 1));
							else if (a)
								p.add(new Point(x + s.length(), y + s.length()));
							else
								p.add(new Point(x + s.length(), y - s.length() + 2));
							break main;
						}
					}
				}
				if (p.size() > 0)
					System.out.println(p.first());
			}
			System.out.println();
		}
	}

	static class Point implements Comparable<Point> {
		int x;
		int y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo (Point o) {
			if (this.x == o.x)
				return this.y - o.y;
			return this.x - o.x;
		}

		@Override
		public String toString () {
			return (x) + " " + (y);
		}
	}

	private static boolean find (int x, int y, char[][] grid, String s) {
		a = true;
		b = true;
		d = true;
		e = true;
		for (int z = 0; z < s.length(); z++) {
			if (x + z >= r || y + z >= c || grid[x + z][y + z] != s.charAt(z))
				a = false;
			if (y + z >= c || grid[x][y + z] != s.charAt(z))
				b = false;
			if (x + z >= r || grid[x + z][y] != s.charAt(z))
				d = false;
			if (x + z >= r || y - z < 0 || grid[x + z][y - z] != s.charAt(z))
				e = false;

		}
		return a || b || d || e;
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
