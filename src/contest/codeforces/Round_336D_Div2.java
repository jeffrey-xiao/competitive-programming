package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_336D_Div2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] a;
	static ArrayList<Integer> best = new ArrayList<Integer>();
	static boolean[][] v;
	static int[][] dp;
	static int n;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		a = new int[n];
		dp = new int[n][n];
		for (int i = 0; i < n; i++) {
			a[i] = readInt();
			for (int j = 0; j < n; j++)
				dp[i][j] = -1;
		}
		out.println(compute(0, n-1, 0));
		out.close();
	}
	
	static int compute (int i, int j, int flag) {
		if (i > j)
			return flag;
		if (dp[i][j] != -1)
			return dp[i][j];
		int res = 1 << 30;
		if (a[i] == a[j] && flag == 1)
			res = compute(i + 1, j - 1, 1);
		for (int k = i; k <= j; k++)
			if (a[i] == a[k])
				res = Math.min(res, compute(i + 1, k - 1, 1) + compute(k + 1, j, 0));
		return dp[i][j] = res;
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

