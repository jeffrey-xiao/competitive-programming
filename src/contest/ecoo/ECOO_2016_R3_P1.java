package contest.ecoo;

import java.util.*;
import java.io.*;

public class ECOO_2016_R3_P1 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int TEST_CASES = 3;


	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt")); // DATA11.txt
		// out = new PrintWriter(new FileWriter("out.txt"));

		for (int t = 1; t <= TEST_CASES; t++) {
			int N = readInt();
			double S = readInt();
			double ans = 0;
			double[] prob = new double[N + 1], newProb;
			prob[N] = 1;
			for (int k = 0; k < 2000; k++) {
				newProb = new double[N + 1];
				for (int i = 0; i <= N; i++) {
					double value = Math.pow(1 / S, i) * Math.pow(1 - 1 / S, 0) * 1;
					for (int j = 0; j <= i; j++) {
						newProb[j] += value * prob[i];
						value = value / (1 / S) * (1 - 1 / S) / (j + 1) * (i - j);
					}
				}
				for (int i = 0; i <= N; i++)
					prob[i] = newProb[i];
				ans += (k + 1) * prob[0];
				prob[0] = 0;
			}
			out.println((int)Math.ceil(ans));
			out.flush();
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

