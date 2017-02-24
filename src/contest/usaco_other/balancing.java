package contest.usaco_other;

/*
ID: jeffrey40
LANG: JAVA
TASK: balancing
*/
import java.util.*;
import java.io.*;

public class balancing {
	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int SIZE = 1000000;

	static HashMap<Integer, ArrayList<Integer>> xCows = new HashMap<Integer, ArrayList<Integer>>();
	static int N;
	static int[] left = new int[SIZE + 1], right = new int[SIZE + 1];

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new FileReader("balancing.in"));
		out = new PrintWriter(new FileWriter("balancing.out"));
		// br = new BufferedReader(new InputStreamReader(System.in));
		// out = new PrintWriter(new OutputStreamWriter(System.out));

		N = readInt();

		for (int i = -1; i <= SIZE; i += 2)
			xCows.put(i, new ArrayList<Integer>());

		for (int i = 0; i < N; i++) {
			int x = readInt();
			int y = readInt();
			xCows.get(x).add(y);
			update(right, y, 1);
		}

		int ans = 1 << 30;

		for (int i = 0; i <= SIZE; i += 2) {
			for (int y : xCows.get(i - 1)) {
				update(right, y, -1);
				update(left, y, 1);
			}

			int lo = 0;
			int hi = (SIZE + 1) / 2;
			while (lo <= hi) {
				int mid = (lo + hi) >> 1;

				int ul = query(left, mid * 2);
				int ur = query(right, mid * 2);
				int bl = query(left, 1000000) - ul;
				int br = query(right, 1000000) - ur;

				int upperVal = Math.max(ul, ur);
				int lowerVal = Math.max(bl, br);

				if (upperVal < lowerVal) {
					lo = mid + 1;
				} else if (upperVal > lowerVal) {
					hi = mid - 1;
				} else {
					ans = Math.min(ans, Math.max(upperVal, lowerVal));
					break;
				}
			}

			int ul = query(left, lo * 2);
			int ur = query(right, lo * 2);
			int bl = query(left, SIZE) - ul;
			int br = query(right, SIZE) - ur;
			ans = Math.min(ans, Math.max(Math.max(ul, ur), Math.max(bl, br)));

			ul = query(left, (lo + 1) * 2);
			ur = query(right, (lo + 1) * 2);
			bl = query(left, SIZE) - ul;
			br = query(right, SIZE) - ur;
			ans = Math.min(ans, Math.max(Math.max(ul, ur), Math.max(bl, br)));
		}
		out.println(ans);
		out.close();
		System.exit(0);
	}

	static void update (int[] bit, int x, int val) {
		for (int i = x; i <= SIZE; i += (i & -i))
			bit[i] += val;
	}

	static int query (int[] bit, int x) {
		int sum = 0;
		for (int i = x; i > 0; i -= (i & -i))
			sum += bit[i];
		return sum;
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
