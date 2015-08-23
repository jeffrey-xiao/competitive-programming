package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Fibonacci {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static final long MOD = 1000000007;

	public static void main (String[] args) throws IOException {
		BigInteger l = new BigInteger(next());
		l = l.subtract(new BigInteger("1"));
		Matrix[] m = new Matrix[65];
		m[0] = new Matrix(1, 1, 1, 0);
		for (int x = 0; x < 64; x++) {

			m[x + 1] = multiply(m[x], m[x]);
		}
		Matrix result = null;
		for (int x = 0; x < 64; x++) {
			if (!l.and(new BigInteger(new Long(1L << x).toString())).toString()
					.equals("0")) {
				if (result == null)
					result = m[x];
				else
					result = multiply(result, m[x]);
			}
		}
		System.out.println(result.a);
	}

	static Matrix multiply (Matrix m1, Matrix m2) {
		long a = m1.a;
		long b = m1.b;
		long c = m1.c;
		long d = m1.d;
		long e = m2.a;
		long f = m2.b;
		long g = m2.c;
		long h = m2.d;
		long a1 = (a * e) % MOD + (b * g) % MOD;
		long b1 = (a * f) % MOD + (b * h) % MOD;
		long c1 = (c * e) % MOD + (d * g) % MOD;
		long d1 = (c * f) % MOD + (d * h) % MOD;
		Matrix result = new Matrix(a1 % MOD, b1 % MOD, c1 % MOD, d1 % MOD);
		return result;
	}

	static class Matrix {
		long a, b, c, d;

		Matrix (long a, long b, long c, long d) {
			this.a = a;
			this.b = b;
			this.c = c;
			this.d = d;
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
