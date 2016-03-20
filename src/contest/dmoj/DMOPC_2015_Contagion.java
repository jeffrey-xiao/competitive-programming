package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Contagion {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, x;
	static Point[] p;
	static long[][] adj;
	static long[] dist;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();

		p = new Point[n];
		dist = new long[n];
		v = new boolean[n];
		adj = new long[n][n];

		for (int i = 0; i < n; i++) {
			p[i] = new Point(readInt(), readInt());
			dist[i] = Long.MAX_VALUE;
		}

		x = readInt() - 1;

		dist[x] = 0;

		for (int i = 0; i < n - 1; i++) {
			int minIndex = -1;
			for (int j = 0; j < n; j++)
				if (!v[j] && (minIndex == -1 || dist[j] < dist[minIndex]))
					minIndex = j;

			v[minIndex] = true;
			for (int j = 0; j < n; j++)
				dist[j] = Math.min(dist[j], dist[minIndex] + dist(j, minIndex));
		}

		Arrays.sort(dist);

		int q = readInt();
		for (int i = 0; i < q; i++) {
			long time = readLong();
			int lo = 0, hi = n - 1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				if (dist[mid] <= time) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			System.out.println(lo);
		}
		out.close();
	}

	static long dist (int i, int j) {
		return (p[i].x - p[j].x) * (p[i].x - p[j].x) + (p[i].y - p[j].y) * (p[i].y - p[j].y);
	}

	static class Point {
		long x, y;

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
