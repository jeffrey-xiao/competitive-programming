package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Cutting_Edge {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long N, M, K;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		K = readInt() + 1;

		
		if (K >= M - 1 + N - 1)
			out.println(-1);
		else {
			long ans = 0;
			long a = K / 2;
			long b = K - a;
			
			ans += compute(a) + compute(b);
			out.println(ans);
		}
		
		out.close();
	}
	
	static long compute (long n) {
		long min = Math.min(N, M);
		if (n < min)
			return (n + 1) * (n + 2) / 2 - 1;
		else
			return (min) * (min + 1) / 2 - 1 + (n - min + 1) * min; 
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

