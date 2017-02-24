package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_The_Elite_N {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		long N = readInt();
		long M = readInt();
		long A = readInt();
		long B = readInt();
		long S = readInt();
		
		long prev = 0;
		long totalTime = 0;
		
		for (int i = 0; i < M; i++) {
			int curr = readInt();
			totalTime += Math.min((curr - prev) * A, 2 * S + (curr - prev - 1) * B + A);
			prev = curr;
		}
		
		totalTime += Math.min((N - prev) * A, S + (N - prev) * B);
		
		out.println(totalTime);
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

