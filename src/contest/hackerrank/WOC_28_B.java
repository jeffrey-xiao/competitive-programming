package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_28_B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int Q = readInt();
		for (int i = 0; i < Q; i++)
			out.println(solve(readLong()));

		out.close();
	}

	static long solve (long n) {
		long ans = 0;
		for (int i = 0; i < 60; i++)
			if ((1L << i) < n && (n & 1L << i) == 0)
				ans += 1L << i;
		return ans;
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
