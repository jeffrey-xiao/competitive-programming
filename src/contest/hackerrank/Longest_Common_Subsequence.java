package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Longest_Common_Subsequence {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		int[][] dp = new int[n + 1][m + 1];
		int[] a = new int[n + 1];
		int[] b = new int[m + 1];
		for (int i = 1; i <= n; i++)
			a[i] = readInt();
		for (int i = 1; i <= m; i++)
			b[i] = readInt();
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (a[i] == b[j])
					dp[i][j] = dp[i - 1][j - 1] + 1;
				else
					dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
			}
		}
		int i = n;
		int j = m;
		String s = "";
		while (i != 0 || j != 0) {
			if (i > 0 && j > 0 && dp[i][j] == dp[i - 1][j - 1] + 1 && a[i] == b[j]) {
				s = a[i] + " " + s;
				i--;
				j--;
			} else if (i > 0 && dp[i][j] == dp[i - 1][j])
				i--;
			else
				j--;
		}
		System.out.println(s);

		pr.close();
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
