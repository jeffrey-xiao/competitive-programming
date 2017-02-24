package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Reward_Reminiscence {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			int N = readInt();
			int B = readInt();
			int cnt = 0;

			for (int i = 0; i < N - 1; i++) {
				if (readInt() > B)
					cnt++;
			}

			out.printf("Bob wins $%.2f at IOI!\n", Math.sqrt(N - cnt) * 100);
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
