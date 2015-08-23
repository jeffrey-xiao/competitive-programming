package contest.spoj;

import java.util.*;
import java.io.*;

public class Increasing_Subsequence {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int SIZE = 100010;
	static final int MOD = 5000000;
	static int[][] bit = new int[51][SIZE + 1];

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		int[] a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt() + 1;

		for (int j = 0; j < n; j++) {
			for (int i = k; i > 1; i--) {
				int res = query(a[j] - 1, i - 1);
				update(a[j], res, i);
			}
			update(a[j], 1, 1);
		}
		System.out.println(query(SIZE, k));
		pr.close();
	}

	static void update (int idx, int v, int i) {
		for (int x = idx; x <= SIZE; x += (x & -x)) {
			bit[i][x] = (bit[i][x] + v) % MOD;
		}
	}

	static int query (int idx, int i) {
		int sum = 0;
		for (int x = idx; x > 0; x -= (x & -x)) {
			sum = (sum + bit[i][x]) % MOD;
		}
		return sum;
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
