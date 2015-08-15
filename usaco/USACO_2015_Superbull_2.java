package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_Superbull_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] t = new int[n];
		for (int i = 0; i < n; i++)
			t[i] = readInt();
		long[] c = new long[n];
		boolean[] v = new boolean[n];
		c[0] = 0;
		long res = 0;
		for (int x = 0; x < n; x++) {
			int curr = -1;
			for (int i = 0; i < n; i++) {
				if (v[i])
					continue;
				if (curr == -1 || c[i] > c[curr]) {
					curr = i;
				}
			}
			v[curr] = true;
			res += c[curr];
			for (int i = 0; i < n; i++) {
				if (v[i])
					continue;
				c[i] = Math.max(c[i], (t[i] ^ t[curr]));
			}
		}
		System.out.println(res);
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
