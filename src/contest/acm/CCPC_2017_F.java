package contest.acm;

import java.util.*;
import java.io.*;

public class CCPC_2017_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	static final int GOOD = 0;
	static final int BAD = 1;
	static final int OPTION = 2;
	
	static int T, S;
	static int[] type;
	static int[][] next;
	static long[] dp;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 0; t < T; t++) {
			reset();
			S = readInt();
			
			for (int i = 0; i < S; i++) {
				int p = readInt() - 1;
				String token = next();
				
				if (isInteger(token)) {
					int a = Integer.parseInt(token) - 1;
					int b = readInt() - 1;
					int c = readInt() - 1;
					
					type[p] = OPTION;
					next[0][p] = a;
					next[1][p] = b;
					next[2][p] = c;
				} else {
					if (token.equals("favourably"))
						type[p] = GOOD;
					else if (token.equals("catastrophically"))
						type[p] = BAD;
				}
			}
			
			out.println(solve(0));
		}
		
		out.close();
	}
	
	static long solve (int curr) {
		if (type[curr] == GOOD) return 1;
		if (type[curr] == BAD) return 0;
		if (dp[curr] != -1) return dp[curr];
		return dp[curr] = solve(next[0][curr]) + solve(next[1][curr]) + solve(next[2][curr]);
	}
	
	static void reset () {
		dp = new long[401];
		next = new int[3][400];
		type = new int[400];
		
		Arrays.fill(dp, -1);
	}

	static boolean isInteger (String s) {
		if (s.isEmpty())
			return false;
		for (int i = 0; i < s.length(); i++)
			if (s.charAt(i) < '0' || s.charAt(i) > '9')
				return false;
		return true;
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

