package codeforces;

import java.util.*;
import java.io.*;

public class Round_309A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int MOD = 1000000007;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));
		long[][] C = new long[1002][1002];
		for (int i = 1; i <= 1001; i++) {
			for (int j = 1; j <= 1001; j++) {
				if (i == j || j == 1)
					C[i][j] = 1;
				else
					C[i][j] = (C[i-1][j] + C[i-1][j-1])%MOD;
			}
		}
		
		int k = readInt();
		int[] cnt = new int[k];
		for (int i = 0; i < k; i++) {
			cnt[i] = readInt();
		}
		long[] ways = new long[k];
		ways[0] = 1;
		int balls = cnt[0];
		for (int i = 1; i < k; i++) {
			ways[i] = (ways[i-1] * C[balls+1 + cnt[i]-1 - 1 + 1][cnt[i]-1+1])%MOD;
			balls += cnt[i];
		}
		System.out.println(ways[k-1]);
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

