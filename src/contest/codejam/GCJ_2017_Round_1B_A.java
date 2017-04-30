package contest.codejam;

import java.util.*;
import java.io.*;

public class GCJ_2017_Round_1B_A {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, D, N;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			D = readInt();
			N = readInt();
			
			double maxTime = 0;
			for (int i = 0; i < N; i++) {
				int pos = readInt();
				int speed = readInt();
				maxTime = Math.max(1.0 * (D - pos) / speed, maxTime);
			}
			out.printf("Case #%d: %f\n", t, D / maxTime);
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

