package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Bike_Racers {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[][] adj;
	static int[] prev;
	static boolean[] v;
	static long[] bx, by, rx, ry;
	static int n, m, k;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		k = readInt();

		adj = new int[n][m];
		rx = new long[n];
		ry = new long[n];
		bx = new long[m];
		by = new long[m];
		prev = new int[n];
		v = new boolean[n];
		for (int i = 0; i < n; i++) {
			rx[i] = readInt();
			ry[i] = readInt();
		}
		for (int i = 0; i < m; i++) {
			bx[i] = readInt();
			by[i] = readInt();
		}

		int lo = 0;
		int hi = 1 << 30;
		while (lo < hi) {
			int mid = (hi + lo - 1) / 2;
			if (isValid(mid)) {
				hi = mid;
			} else {
				lo = mid + 1;
			}
		}
		out.println(lo);
		out.close();
	}

	static boolean isValid (double time) {
		for (int i = 0; i < n; i++) {
			prev[i] = -1;
			for (int j = 0; j < m; j++) {
				adj[i][j] = 0;
				if ((rx[i] - bx[j]) * (rx[i] - bx[j]) + (ry[i] - by[j]) * (ry[i] - by[j]) <= time)
					adj[i][j] = 1;
			}
		}
		int count = 0;
		for (int i = 0; i < n; i++) {
			v = new boolean[m];
			count += hungary(i) ? 1 : 0;
		}
		return count >= k;
	}

	static boolean hungary (int i) {
		for (int j = 0; j < m; j++) {
			if (adj[i][j] == 1 && !v[j]) {
				v[j] = true;
				if (prev[j] == -1 || hungary(prev[j])) {
					prev[j] = i;
					return true;
				}
			}
		}
		return false;
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
