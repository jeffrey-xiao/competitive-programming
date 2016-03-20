package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_Restoring_Reputation {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int D = readInt();
		int I = readInt();
		int R = readInt();
		char[] s1 = (" " + next()).toCharArray();
		char[] s2 = (" " + next()).toCharArray();
		int[][] dp = new int[s1.length][s2.length];
		for (int i = 0; i < s1.length; i++) {
			for (int j = 0; j < s2.length; j++) {
				if (i == 0)
					dp[i][j] = j * I;
				else if (j == 0)
					dp[i][j] = i * D;
				else if (s1[i] == s2[j])
					dp[i][j] = dp[i - 1][j - 1];
				else
					dp[i][j] = Math.min(Math.min(dp[i - 1][j] + D, dp[i][j - 1] + I), dp[i - 1][j - 1] + Math.min(R, D + I));
			}
		}
		out.println(dp[s1.length - 1][s2.length - 1]);
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
