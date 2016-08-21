package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Foxic_Expressions {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N, M;
	static String S; 
	static String E;
	static boolean[][] dp;
	
	static String fox = "FOXn";
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 0; t < T; t++) {
			N = readInt();
			S = " " + next();
			M = readInt();
			E = next();
			
			boolean valid = false;
			for (int i = 0; i <= M; i++) {
				// removing
				if (i < M)
					valid |= isValid(S, E.substring(0, i) + E.substring(i + 1, E.length()));
				for (int j = 0; j < 4; j++) {
					// adding
					valid |= isValid(S, E.substring(0, i) + fox.charAt(j) + E.substring(i, E.length()));
					// modifying
					if (i < M)
						valid |= isValid(S, E.substring(0, i) + fox.charAt(j) + E.substring(i + 1, E.length()));
				} 
			}
			valid |= isValid(S, E);
			out.println(valid ? "Yes" : "No");
		}
		out.close();
	}

	static boolean isValid (String s1, String s2) {
		if (!validExpression(s2))
			return false;
		String[] curr = ("n" + s2).split("n");
		int M = curr.length - 1;
		dp = new boolean[M + 1][N + 1];
		dp[0][0] = true;
		for (int i = 1; i <= M; i++) {
			for (int j = 1; j <= N; j++) {
				if (!dp[i - 1][j - 1])
					continue;
				for (int k = j - 1; k <= N; k++) {
					boolean valid = false;
					String currStr = S.substring(j, k + 1);
					
					valid |= checkEqual(currStr, curr[i], i == M && s2.charAt(s2.length() - 1) != 'n');
					
					dp[i][k] |= valid;
				}
			}
		}
		return dp[M][N];
	}
	
	static boolean validExpression (String s) {
		if (s.length() == 0)
			return false;
		for (int i = 1; i < s.length(); i++)
			if (s.charAt(i) == 'n' && s.charAt(i - 1) == 'n')
				return false;
		return !(s.charAt(0) == 'n');
	}
	
	static boolean checkEqual (String s1, String s2, boolean exact) {
		if (exact)
			return s1.equals(s2);
		if (s1.length() == s2.length() - 1)
			return s1.equals(s2.substring(0, s2.length() - 1));
		if (s1.length() < s2.length() || s2.length() == 0)
			return false;
		for (int i = 0; i < s2.length(); i++)
			if (s1.charAt(i) != s2.charAt(i))
				return false;
		for (int i = s2.length(); i < s1.length(); i++)
			if (s1.charAt(i) != s2.charAt(s2.length() - 1))
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

