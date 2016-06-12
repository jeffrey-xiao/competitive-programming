package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Blackrock_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MOD = (int)(1e9 + 7);
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		
		long[] a = new long[N + 1];
		a[0] = 1;
		a[1] = readInt();
		for (int i = 1; i < N; i++) {
			int c = readInt();
			for (int j = i + 1; j > 0; j--) {
				a[j] = (a[j] + a[j - 1] * c % MOD) % MOD;
			}
		}

		long ans = 0;
		for (int i = 0; i <= N; i++) {
			ans = (ans + i * a[i]) % MOD;
		}
		
		out.println(ans);
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



