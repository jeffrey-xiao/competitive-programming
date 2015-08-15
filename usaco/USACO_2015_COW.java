package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2015_COW {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[] s = (" " + readLine()).toCharArray();
		long[] dp1 = new long[n + 1];
		long[] dp2 = new long[n + 1];
		long[] dp3 = new long[n + 1];
		for (int i = 1; i <= n; i++) {
			dp1[i] = dp1[i - 1];
			dp2[i] = dp2[i - 1];
			dp3[i] = dp3[i - 1];
			if (s[i] == 'C')
				dp1[i]++;
			else if (s[i] == 'O')
				dp2[i] += dp1[i - 1];
			else
				dp3[i] += dp2[i - 1];
		}
		System.out.println(dp3[n]);
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
