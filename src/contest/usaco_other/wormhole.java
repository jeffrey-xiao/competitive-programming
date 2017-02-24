package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: wormhole
 */
import java.util.*;
import java.io.*;

public class wormhole {
	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;
	static int[] pair;
	static int[] next;
	static int n;
	static int count = 0;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("wormhole.in"));
		pr = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));
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
				if ((next[x] == -1 || p[next[x]].x > p[y].x) && p[x].y == p[y].y && p[x].x < p[y].x) {
					next[x] = y;
				}
			}
		}
		compute();
		pr.println(count);
		pr.close();
		System.exit(0);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
