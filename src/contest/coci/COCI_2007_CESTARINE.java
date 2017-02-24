package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2007_CESTARINE {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] entrance, exit;
	static long[] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		entrance = new int[N + 1];
		exit = new int[N + 1];
		dp = new long[N + 1];

		for (int i = 1; i <= N; i++) {
			entrance[i] = readInt();
			exit[i] = readInt();
		}

		Arrays.sort(entrance, 1, N + 1);
		Arrays.sort(exit, 1, N + 1);

		Arrays.fill(dp, 1l << 60);
		dp[0] = 0;

		for (int i = 1; i <= N; i++) {
			for (int k = 1; k <= Math.min(3, i); k++) {
				dp[i] = Math.min(dp[i], dp[i - k] + compute(i, k));
			}
		}

		out.println(dp[N]);
		out.close();
	}

	static long compute (int i, int k) {
		int[] a = new int[k];
		int[] b = new int[k];

		for (int j = i - k + 1; j <= i; j++) {
			a[j - (i - k + 1)] = entrance[j];
			b[j - (i - k + 1)] = exit[j];
		}

		return minPermute(a, b, 0);
	}

	static long minPermute (int[] a, int[] b, int i) {
		if (i == a.length) {
			long ret = 0;
			for (int j = 0; j < a.length; j++) {
				if (a[j] == b[j])
					return 1l << 60;
				ret += Math.abs(a[j] - b[j]);
			}
			return ret;
		}
		long ret = 1l << 60;
		for (int j = i; j < a.length; j++) {
			swap(a, i, j);
			ret = Math.min(ret, minPermute(a, b, i + 1));
			swap(a, i, j);
		}
		return ret;
	}

	static void swap (int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
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
