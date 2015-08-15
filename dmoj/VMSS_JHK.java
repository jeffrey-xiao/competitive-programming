package dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_JHK {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int INF = 1 << 30;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		boolean[] primes = new boolean[7001];
		int[] dp = new int[7001];

		for (int x = 0; x < 7001; x++)
			dp[x] = INF;

		primes[1] = true;
		dp[0] = 0;

		for (int x = 1; x <= 7000; x++)
			if (primes[x] == false) {
				for (int y = x * x; y <= 7000; y += x)
					primes[y] = true;
			}

		for (int x = 1; x <= 7000; x++)
			if (!primes[x])
				for (int y = 0; y <= 7000; y++)
					if (dp[y] != INF && y + x <= 7000)
						dp[y + x] = Math.min(dp[y + x], dp[y] + 1);

		int total = 0;
		for (int x = 1; x <= n; x++)
			if (dp[x] == k)
				total++;

		System.out.println(total);
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
