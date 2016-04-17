package contest.codejam;

import java.util.*;
import java.io.*;

public class Round_1A_B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T;
	
	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		//out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		
		for (int t = 1; t <= T; t++) {
			int N = readInt();
			int[] val = new int[2501];
			for (int i = 0; i < 2 * N - 1; i++)
				for (int j = 0; j < N; j++)
					val[readInt()]++;
			out.printf("Case #%d: ", t);
			for (int i = 1; i <= 2500; i++)
				if (val[i] > 0 && val[i] % 2 == 1)
					out.printf("%d ", i);
			out.println();
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

