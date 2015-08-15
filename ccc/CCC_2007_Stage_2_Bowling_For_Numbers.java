package ccc;

import java.util.*;
import java.io.*;

public class CCC_2007_Stage_2_Bowling_For_Numbers {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int[][] dp;
	static int[] sum, pins;
	static int n, k, w;
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			dp = new int[501][10101];
			sum = new int[10101];
			pins = new int[10101];
			for (int i = 0; i <= 500; i++)
				for (int j = 0; j <= 10100; j++)
					dp[i][j] = -1;
			n = readInt();
			k = readInt();
			w = readInt();
			for (int i = 1; i <= n; i++)
				pins[i] = readInt();
			for (int i = 1; i <= n + w; i++) {
				sum[i] = pins[i] + sum[i-1];
			}
			System.out.println(compute(k, n+w));
		}
		
		pr.close();
	}

	private static int compute (int k, int x) {
		System.out.println(k + " " + x);
		if (k <= 0 || x < 0)
			return 0;
		if (dp[k][x] != -1)
			return dp[k][x];
		int usePos = Math.max(0, x - w);
		int useScore = sum[x] - sum[usePos];
		int res = Math.max(compute(k, x-1), compute(k-1, usePos) + useScore);
		if (k >= 2) {
			int sum = 0;
			for (int i = usePos; i > usePos - w + 1 && i > 0; i--) {
				sum += pins[i];
				res = Math.max(res, compute(k-2, i-1) + useScore + sum);
			}
		}
		System.out.println(res);
		return dp[k][x] = res;
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

