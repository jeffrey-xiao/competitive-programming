package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Epiccode_A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		long n = readInt();
		long p = readInt();
		long x = readInt();
		long max = Long.MIN_VALUE;
		long index = 0;
		for (int i = 0; i < n; i++) {
			long v = readLong();
			System.out.println(v * p);
			if (v * p > max) {
				max = v * p;
				index = i;
			}
			p -= x;
		}
		System.out.println(index + 1);

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
