package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2011_Rice_Hub {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		readInt();
		long b = readLong();
		long[] rice = new long[n + 1];
		long[] sum = new long[n + 1];
		for (int x = 1; x <= n; x++) {
			rice[x] = readInt();
			sum[x] = rice[x] + sum[x - 1];
		}
		int lo = 1;
		int hi = 1;
		int maxFields = 0;
		while (hi <= n) {
			int median = (hi + lo) / 2;
			int leftFields = median - lo + 1;
			int rightFields = hi - median;
			long rightCost = sum[hi] - sum[lo + leftFields - 1];
			long leftCost = sum[leftFields + lo - 1] - sum[lo - 1];
			// System.out.println(x + " " + leftFields + " " + rightFields + " "
			// + rightCost + " " + leftCost);

			long nextCost = leftFields * rice[median] - leftCost + rightCost
					- rightFields * rice[median];
			// System.out.println(nextCost);
			// System.out.println(leftFields*x - leftCost + " " +
			// (rightCost-rightFields*x));
			// System.out.println("RESULT: " + lo + " " + hi + " " + minCost);
			if (nextCost <= b) {
				maxFields = Math.max(maxFields, hi - lo + 1);
				hi++;
			} else {
				lo++;
				hi++;
			}
		}
		System.out.println(maxFields);
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
