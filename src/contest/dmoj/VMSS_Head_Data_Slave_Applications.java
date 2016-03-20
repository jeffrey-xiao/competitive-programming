package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_Head_Data_Slave_Applications {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = 1000000007;
	static int n;
	static int[] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		long[][] binomial = new long[501][501];
		for (int i = 0; i <= 500; i++)
			for (int j = 0; j <= i; j++)
				binomial[i][j] = (j == 0 || i <= 1) ? 1 : (binomial[i - 1][j - 1] + binomial[i - 1][j]) % MOD;

		n = readInt();
		a = new int[n];
		for (int i = 0; i < n; i++)
			a[i] = readInt();
		long totalWays = 1;
		int totalCnt = 0;
		for (int i = 0; i < n; i++) {
			totalWays = (totalWays * binomial[totalCnt + a[i] - 1][a[i] - 1]) % MOD;
			totalCnt += a[i];
		}

		out.println(totalWays % MOD);
		out.close();
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
