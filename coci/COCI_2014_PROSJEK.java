package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_PROSJEK {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();
		int k = readInt();
		int[] a = new int[n + 1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt();
		double lo = 0, hi = 1 << 30;
		double[] prefix = new double[n + 1];
		while (hi - lo > 0.000000001) {

			double mid = lo + (hi - lo) / 2;
			// System.out.println(mid);
			boolean valid = false;
			for (int i = 1; i <= n; i++)
				prefix[i] = a[i] + prefix[i - 1] - mid;
			double min = 0;
			for (int i = k; i <= n; i++) {
				if (prefix[i] >= min)
					valid = true;
				// System.out.println(prefix[i] + " " + min);
				min = Math.min(min, prefix[i - k + 1]);
			}
			if (valid)
				lo = mid;
			else
				hi = mid;
		}
		ps.println(lo);
		ps.close();
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