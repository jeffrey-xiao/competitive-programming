package contest.hackerrank;

import java.util.*;
import java.io.*;

public class WOC_28_A {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, C, M;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		C = readInt();
		M = readInt();

		for (int i = 0; i < N; i++) {
			if (readInt() > C * M) {
				out.println("No");
				out.close();
				return;
			}
		}
		out.println("Yes");
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
