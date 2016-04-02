package contest.dmoj;

import java.util.*;
import java.io.*;

public class MEC_P3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		
		int N = readInt();
		int T = readInt();
		
		int[] dp = new int[T + 1];
		for (int i = 1; i <= T; i++) {
			dp[i] = - 1 << 30;
		}
		
		for (int i = 0; i < N; i++) {
			
			int levels = readInt();
			int time = 0;
			int exp = 0;
			int[] times = new int[levels];
			int[] exps = new int[levels];
			for (int j = 0; j < levels; j++) {
				time += readInt();
				exp += readInt();
				times[j] = time;
				exps[j] = exp;
			}
			
			for (int k = T; k >= 0; k--) {
				for (int j = 0; j < levels; j++) {
					if (k - times[j] >= 0 && dp[k - times[j]] != -1 << 30 && dp[k] < dp[k - times[j]] + exps[j]) {
						dp[k] = dp[k - times[j]] + exps[j];
					}
				}
			}
		}
		
		int max = 0;
		for (int i = 0; i <= T; i++) {
			max = Math.max(max, dp[i]);
		}
		out.println(max);
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

