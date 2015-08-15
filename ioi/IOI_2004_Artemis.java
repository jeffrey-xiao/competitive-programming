package ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class IOI_2004_Artemis {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static HashMap<Integer, Integer> toX = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> toY = new HashMap<Integer, Integer>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		Point[] p = new Point[n];
		int countX = 1, countY = 1;
		int[] X = new int[n];
		int[] Y = new int[n];
		for (int x = 0; x < n; x++) {
			p[x] = new Point(readInt(), readInt(), x);
			X[x] = p[x].x;
			Y[x] = p[x].y;
		}
		Arrays.sort(X);
		Arrays.sort(Y);
		for (int x = 0; x < n; x++) {
			if (!toX.containsKey(X[x]))
				toX.put(X[x], countX++);
			if (!toY.containsKey(Y[x]))
				toY.put(Y[x], countY++);
		}
		int[][] sum = new int[countX][countY];
		for (int x = 0; x < n; x++)
			sum[toX.get(p[x].x)][toY.get(p[x].y)]++;
		for (int x = 1; x < countX; x++) {
			for (int y = 1; y < countY; y++) {
				sum[x][y] += sum[x][y - 1] + sum[x - 1][y] - sum[x - 1][y - 1];
				// System.out.print(sum[x][y] + " ");
			}
			// System.out.println();
		}
		int min = Integer.MAX_VALUE;
		Arrays.sort(p);
		int first = 0, second = 0;
		for (int x = 0; x < n; x++) {
			for (int y = x - 1; y >= 0; y--) {
				int x1 = toX.get(p[x].x);
				int y1 = toY.get(p[x].y);
				int x2 = toX.get(p[y].x) - 1;
				int y2 = toY.get(p[y].y) - 1;
				int s = sum[x1][y1] - sum[x1][y2] - sum[x2][y1] + sum[x2][y2];
				// System.out.println(s);
				if (s >= k && s < min) {
					min = s;
					first = p[x].id + 1;
					second = p[y].id + 1;
				}
			}
		}
		System.out.println(first + " " + second);

	}

	static class Point implements Comparable<Point> {
		int x, y, id;

		Point (int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}

		@Override
		public int compareTo (Point o) {
			if (x == o.x)
				return y - o.y;
			return x - o.x;
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
