package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Gel_Bananas {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long A, B, N;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		A = readLong();
		B = readLong();
		N = readLong();

		out.println((N - 1) / lcm(A, B) + 1);
		out.close();
	}

	static long lcm (long a, long b) {
		return a * b / gcf(a, b);
	}

	static long gcf (long a, long b) {
		return b == 0 ? a : gcf(b, a % b);
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
