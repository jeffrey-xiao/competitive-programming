package contest.dmoj;

import java.util.*;
import java.io.*;

public class CCO_Prep_Subsets {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = 1000000001;
	
	static int n;
	static ArrayList<Integer> row;
	static int gln3;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		row = new ArrayList<Integer>();
		gln3 = (int)(Math.log(n) / Math.log(3)) + 1;
		for (int i = 0; i < 1 << gln3; i++) {
			boolean valid = true;
			for (int j = 0; j < gln3-1; j++)
				if ((i & 1 << j) > 0 && (i & 1 << (j + 1)) > 0)
					valid = false;
			if (valid)
				row.add(i);
		}
		long ans = 1;
		for (int i = 1; i <= n; i++) {
			if (i % 2 == 0 || i % 3 == 0)
				continue;
			ans = (ans * solve(i)) % MOD;
		}
		out.println(ans);
		out.close();
	}
	static int solve (int c) {
		int ln2 = (int)(Math.log(n/c) / Math.log(2)) + 1;
		int[][] dp = new int[2][1 << gln3];
		for (int i = 0; i < ln2; i++) {
			dp[i%2] = new int[1 << gln3];
			for (int j : row) {
				long curr = c * 1 << i;
				boolean valid = true;
				for (int k = 0; k < gln3; k++) {
					if (curr > n && (j & 1 << k) > 0)
						valid = false;
					curr *= 3;
				}
				if (!valid)
					break;
				if (i == 0)
					dp[i][j] = 1;
				else
					for (int k : row)
						if ((k & j) == 0)
							dp[i%2][j] = mod(dp[i%2][j] + dp[(i-1)%2][k]);
				
			}
		}
		int ans = 0;
		for (int j : row)
			ans = mod(ans + dp[(ln2-1)%2][j]);
		return ans;
	}
	static int mod (int x) {
		if (x > MOD)
			x -= MOD;
		return x;
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

