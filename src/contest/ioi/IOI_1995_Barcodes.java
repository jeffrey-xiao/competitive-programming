package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_1995_Barcodes {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter pr = new PrintWriter(new OutputStreamWriter(System.out));
	static StringTokenizer st;
	static int[][][] dp = new int[34][34][34];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		int m = readInt();
		System.out.println(count(n, k, m));
		int num = readInt();
		for (int i = 0; i < num; i++) {
			String in = next();
			int[] cnt = new int[k];
			int index = 0;
			char currSymbol = '1';
			for (int j = 0; j < in.length(); j++) {
				if (in.charAt(j) == currSymbol)
					cnt[index]++;
				else {
					currSymbol = in.charAt(j);
					cnt[++index]++;
				}
			}
			index = 0;
			int sum = 0;
			for (int j = 0; j < k; j++) {
				if (j % 2 == 0)
					for (int l = cnt[j] - 1; l >= 1; l--)
						index += count(n - sum - l, k - j - 1, m);
				else
					for (int l = cnt[j] + 1; l <= m; l++)
						index += count(n - sum - l, k - j - 1, m);
				sum += cnt[j];
			}
			System.out.println(index);
		}
	}

	static int count (int n, int k, int m) {
		if (n == 0 && k == 0)
			return 1;
		if (n <= 0 || k <= 0)
			return 0;
		if (dp[n][k][m] != 0)
			return dp[n][k][m];
		int res = 0;
		for (int i = 1; i <= m; i++) {
			res += count(n - i, k - 1, m);
		}
		return dp[n][k][m] = res;
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
