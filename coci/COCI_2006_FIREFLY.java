package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_FIREFLY {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int[] diff = new int[m + 1];
		for (int x = 0; x < n; x++) {
			if (x % 2 == 0) {
				diff[0]++;
				diff[readInt()]--;
			} else {
				diff[m - readInt()]++;
				diff[m]--;

			}
		}
		int curr = 0;
		int min = Integer.MAX_VALUE;
		int count = 0;
		for (int x = 0; x < m; x++) {
			diff[x] = (curr += diff[x]);
			if (diff[x] < min) {
				min = diff[x];
				count = 1;
			} else if (diff[x] == min)
				count++;
		}
		System.out.println(min + " " + count);
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
