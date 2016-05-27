package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Araxxi {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int[] last, dp, val, delay, next, max;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		val = new int[N];
		delay = new int[M];
		dp = new int[N];
		last = new int[M];
		next = new int[N];
		max = new int[N];
		
		Arrays.fill(last, -1);
		
		for (int i = 0; i < N; i++)
			val[i] = readInt() - 1;
		
		for (int i = 0; i < M; i++)
			delay[i] = readInt();
		
		for (int i = N - 1; i >= 0; i--) {
			next[i] = last[val[i]];
			last[val[i]] = i;
		}
		
		for (int i = N - 1; i >= 0; i--) {
			dp[i] = 1;
			if (next[i] != -1)
				dp[i] = Math.max(dp[i], 1 + dp[next[i]]);
			if (i + delay[val[i]] + 1 < N)
				dp[i] = Math.max(dp[i], 1 + max[i + delay[val[i]] + 1]);
			max[i] = dp[i];
			if (i != N - 1)
				max[i] = Math.max(max[i], max[i + 1]);
		}
		out.println(max[0]);
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

