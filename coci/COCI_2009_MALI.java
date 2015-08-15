package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_MALI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] A = new int[101];
		int[] B = new int[101];
		int[] TA = new int[101];
		int[] TB = new int[101];
		for (int x = 0; x < n; x++) {
			A[readInt()]++;
			B[readInt()]++;

			for (int y = 1; y <= 100; y++) {
				TA[y] = A[y];
				TB[y] = B[y];
			}

			int max = Integer.MIN_VALUE;
			int ia = 1;
			int ib = 100;
			while (true) {
				while (ia <= 100 && TA[ia] == 0)
					ia++;
				while (ib >= 1 && TB[ib] == 0)
					ib--;
				if (ia == 101 || ib == 0)
					break;
				max = Math.max(max, ia + ib);
				if (TA[ia] >= TB[ib]) {
					TA[ia] -= TB[ib];
					TB[ib] = 0;
				} else {
					TB[ib] -= TA[ia];
					TA[ia] = 0;
				}
			}
			System.out.println(max);
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
