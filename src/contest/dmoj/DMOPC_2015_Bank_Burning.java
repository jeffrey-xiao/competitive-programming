package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Bank_Burning {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int M, N;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		long total = 0;

		M = readInt();
		N = readInt();

		int[] val = new int[N];

		total += M;
		for (int i = 0; i < N; i++)
			total += val[i] = readInt();

		Arrays.sort(val);

		int left = N + 1;

		for (int i = N - 1; i >= 0; i--) {
			if (total * (left - 1) > (total - val[i]) * left) {
				left--;
				total -= val[i];
			}
		}
		out.println(N + 1 - left);
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
