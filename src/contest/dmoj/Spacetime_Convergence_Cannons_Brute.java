package contest.dmoj;

import java.util.*;
import java.io.*;

public class Spacetime_Convergence_Cannons_Brute {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int[] x, y;
	static long ans = 0;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int X = readInt();
		x = new int[n];
		y = new int[n];
		for (int i = 0; i < n; i++) {
			x[i] = readInt();
			y[i] = readInt();
		}
		boolean same_x = true;
		boolean same_y = true;
		for (int i = 1; i < n; i++) {
			if (x[i] != x[i - 1])
				same_x = false;
			if (y[i] != y[i - 1])
				same_y = false;
		}
		if (same_y) {
			System.out.println(0);
			return;
		}
		if (same_x) {
			System.out.println((n - 1) * (n) * (2 * n - 1) / 6);
			return;
		}
		for (int i = 0; i < n; i++) {
			int cnt = 0;
			for (int j = 0; j < n; j++) {
				if (i == j)
					continue;
				if (ccw(0, 0, x[i], y[i], x[j], y[j]) >= 0 && ccw(X, 0, x[i], y[i], x[j], y[j]) <= 0) {
					// System.out.println(i + " " + j);
					cnt++;
				}
			}
			ans += cnt * cnt;
		}
		System.out.println(ans);
		pr.close();
	}

	static int ccw (int x1, int y1, int x2, int y2, int x3, int y3) {
		return (x2 - x1) * (y3 - y1) - (y2 - y1) * (x3 - x1);
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
