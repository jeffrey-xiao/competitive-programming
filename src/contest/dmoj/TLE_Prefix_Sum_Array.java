package contest.dmoj;

import java.util.*;
import java.io.*;

public class TLE_Prefix_Sum_Array {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static long[] a;

	static int MOD = (int)(1e9 + 7);
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		a = new long[N + 1];
		
		for (int i = 1; i <= N; i++)
			a[i] = readInt();
		
		M = readInt();
		long[] res = new long[N + 1];
		long factor = -1;
		
		for (int i = 0; i < N; i++) {
			if (factor == -1) {
				factor = 1;
			} else {
				factor = (factor * (i + 1 + M - 2)) % MOD;
				factor = divMod(factor, (i + 1 + M - 2 - (M - 1)));
			}
			for (int j = 1 + i; j <= N; j++)
				res[j] = (res[j] + factor*a[j - i]) % MOD;
		}

		for (int i = 1; i <= N; i++)
			out.print(res[i] + " ");
		out.println();
		
		out.close();
	}

	// O(log P)
	static long divMod (long i, long j) {
		return i * pow(j, MOD - 2) % MOD;
	}	

	static long pow (long base, long pow) {
		if (pow == 0)
			return 1;
		if (pow == 1)
			return base;
		if (pow % 2 == 0)
			return pow(base * base % MOD, pow / 2);
		return base * pow(base * base % MOD, pow / 2) % MOD;
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

