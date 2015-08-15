package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2013_Wormholes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static int[] pair;
	static int[] next;
	static int n;
	static int count = 0;

	public static void main (String[] args) throws IOException {
		n = readInt();
		Point[] p = new Point[n];
		next = new int[n];
		pair = new int[n];
		for (int x = 0; x < n; x++) {
			p[x] = new Point(readInt(), readInt());
			next[x] = -1;
			pair[x] = -1;
		}
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (x == y)
					continue;
				if ((next[x] == -1 || p[next[x]].x > p[y].x)
						&& p[x].y == p[y].y && p[x].x < p[y].x) {
					// System.out.println("MATCHING " + x + " " + y);
					next[x] = y;
				}
			}
		}
		compute();
		System.out.println(count);
	}

	private static void compute () {
		int unpaired = 0;
		for (; unpaired < n; unpaired++) {
			if (pair[unpaired] == -1)
				break;
		}
		if (unpaired >= n) {
			if (checkStuck())
				count++;
		}
		for (int x = unpaired + 1; x < n; x++) {
			if (pair[x] == -1) {
				pair[x] = unpaired;
				pair[unpaired] = x;
				compute();
				pair[x] = pair[unpaired] = -1;
			}
		}
	}

	private static boolean checkStuck () {
		for (int x = 0; x < n; x++) {
			int curr = x;
			for (int y = 0; y < n && curr != -1; y++) {
				curr = pair[curr];
				curr = next[curr];
			}
			if (curr != -1)
				return true;
		}
		return false;
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
