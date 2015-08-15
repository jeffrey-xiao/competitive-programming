package noi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class NOI_2012_Random_Number_Generator {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long m = readLong();
		long a = readLong();
		long c = readLong();
		long x = readLong();
		long n = readLong();
		long g = readLong();
		long an = pow(a, n, m);
		if (a == 1) {
			System.out.println((c * n + x) % m % g);
			return;
		}
		long ans = ((an * (x % m)) % m + ((c % m) * (1 - an) / (1 - a)) % m)
				% m;
		System.out.println(ans % g);
	}

	static long pow (long a, long b, long m) {
		if (b == 0)
			return 1;
		if (b == 1)
			return a;
		if (b % 2 == 0)
			return pow((a * a) % m, b / 2, m) % m;
		return a * (pow((a * a) % m, b / 2, m)) % m;
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
