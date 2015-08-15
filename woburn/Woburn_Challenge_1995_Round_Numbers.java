package woburn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Woburn_Challenge_1995_Round_Numbers {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		// first is digits, second is number of 0s
		long[][] dp = new long[33][33];
		for (int x = 0; x <= 32; x++) {
			for (int y = 0; y <= x; y++) {
				dp[x][y] += C(x, y);
			}
		}
		int sum = 0;
		int length = (int) (Math.log(n) / Math.log(2)) + 1;
		// System.out.println(length);
		for (int x = 1; x < length; x++) {
			for (int y = (x + 1) / 2; y < x; y++) {
				sum += dp[x - 1][y];
				// System.out.println(x + " " + y + " " + dp[x][y]);
			}
		}

		int totalOne = 0;
		int currZero = 0;
		for (int x = 0; x <= length; x++)
			if ((n & 1 << x) > 0)
				totalOne++;
		boolean first = true;
		// System.out.println("ADD HERE");
		if (totalOne <= (length) / 2) {
			sum++;
		}
		// System.out.println("CHOOSE " + C(3, 3));
		for (int x = length - 1; x >= 0; x--) {
			if ((n & 1 << x) > 0) {
				if (first) {
					first = false;
					totalOne--;
					continue;
				}
				int digits = x;
				for (int y = digits; y <= digits; y++) {
					for (int z = 0; z <= y; z++) {
						if (currZero + 1 + z >= (length + 1) / 2) {
							// System.out.println(digits + " " + y + " " + z +
							// " " + currZero + " " + dp[y][z]);
							sum += dp[y][z];
						}
					}
				}
				totalOne--;
			} else {
				currZero++;
			}
		}
		System.out.printf(
				"There are %d round numbers less than or equal to %d.\n", sum,
				n);
	}

	private static long C (int n, int k) {
		BigInteger result = new BigInteger("1");
		if (n - k < k)
			k = n - k;
		for (int x = n - k + 1; x <= n; x++) {
			result = result.multiply(new BigInteger(Integer.toString(x)));
		}
		for (int x = 2; x <= k; x++)
			result = result.divide(new BigInteger(Integer.toString(x)));
		return result.longValue();
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
