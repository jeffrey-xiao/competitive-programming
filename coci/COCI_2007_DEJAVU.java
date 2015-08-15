package coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2007_DEJAVU {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		long[] x = new long[100001];
		long[] y = new long[100001];
		int[] px = new int[n];
		int[] py = new int[n];
		for (int z = 0; z < n; z++) {
			x[(px[z] = readInt())]++;
			y[(py[z] = readInt())]++;
		}
		long sum = 0;
		for (int z = 0; z < n; z++)
			sum += (x[px[z]] - 1) * (y[py[z]] - 1);
		System.out.println(sum);
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
