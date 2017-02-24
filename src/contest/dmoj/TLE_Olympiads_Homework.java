package contest.dmoj;

import java.util.*;
import java.io.*;

public class TLE_Olympiads_Homework {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long N;
	static long MOD = (long)(1e9) + 13;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readLong();

		if (N <= 3)
			out.println(1);
		else {
			long ans = 0;
			if (N <= 3)
				ans = 1;
			else {
				ans = pow(2, N - 2);
				if (N % 8 == 2 || N % 8 == 6) {
				} else if (N % 8 == 1 || N % 8 == 7) {
					ans = (ans + pow(2, (N - 1) / 2 - 1) + MOD) % MOD;
				} else if (N % 8 == 3 || N % 8 == 5) {
					ans = (ans - pow(2, (N - 1) / 2 - 1) + MOD) % MOD;
				} else if (N % 8 == 0) {
					ans = (ans + pow(2, N / 2 - 1) + MOD) % MOD;
				} else if (N % 8 == 4) {
					ans = (ans - pow(2, N / 2 - 1) + MOD) % MOD;
				}
			}
			out.println(ans);
		}
		out.close();
	}

	static long pow (long a, long b) {
		if (b == 0)
			return 1;
		if (b == 1)
			return a;
		if (b % 2 == 0)
			return pow(a * a % MOD, b / 2);
		return a * pow(a * a % MOD, b / 2) % MOD;
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