package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2013_ORGANIZATOR {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int MAX_SIZE = 2000000;

	static int N;
	static int[] occ = new int[MAX_SIZE + 1];

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		for (int i = 0; i < N; i++)
			occ[readInt()]++;

		long ans = 0;
		for (int i = 1; i <= MAX_SIZE; i++) {
			long curr = 0;
			for (int j = 1; i * j <= MAX_SIZE; j++)
				curr += occ[j * i];
			if (curr > 1)
				ans = Math.max(ans, curr * i);
		}

		out.println(ans);
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
