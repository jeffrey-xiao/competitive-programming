package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2012_Connect_The_Cows {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		Point[] p = new Point[12];
		for (int x = 1; x <= n; x++) {
			p[x] = new Point(readInt(), readInt());
		}
		p[0] = p[n + 1] = new Point(0, 0);
		System.out.println(permutate(1, p));
	}

	private static int permutate (int i, Point[] p) {
		if (i == n)
			return check(p);
		int ans = 0;
		for (int x = i; x < n + 1; x++) {
			exch(p, x, i);
			ans += permutate(i + 1, p);
			exch(p, x, i);
		}
		return ans;
	}

	private static int check (Point[] p) {
		for (int x = 0; x <= n; x++)
			if (direction(p[x], p[x + 1]) == -1)
				return 0;
		for (int x = 1; x <= n; x++)
			if (direction(p[x], p[x - 1]) == direction(p[x + 1], p[x]))
				return 0;
		return 1;
	}

	private static int direction (Point p1, Point p2) {
		if (p1.x != p2.x && p1.y != p2.y)
			return -1;
		if (p1.x == p2.x && p1.y > p2.y)
			return 0;
		if (p1.x == p2.x && p1.y < p2.y)
			return 1;
		if (p1.x < p2.x && p1.y == p2.y)
			return 2;
		if (p1.x > p2.x && p1.y == p2.y)
			return 3;
		return -1;
	}

	private static void exch (Point[] p, int x, int y) {
		Point temp = p[x];
		p[x] = p[y];
		p[y] = temp;
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
