package contest.codejam;

import java.util.*;
import java.io.*;

public class GCJ_2016_Qualification_A {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			N = readInt();
			if (N == 0) {
				out.printf("Case #%d: INSOMNIA\n", t);
				continue;
			}
			int seen = 0, i = 1;
			for (; seen != (1 << 10) - 1; i++) {
				int curr = N * i;
				while (curr != 0) {
					seen |= 1 << (curr % 10);
					curr /= 10;
				}
			}
			out.printf("Case #%d: %d\n", t, N * (i - 1));
		}
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
