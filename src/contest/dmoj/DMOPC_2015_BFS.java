package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_BFS {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, D, Q, L, T;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		D = readInt();
		Q = readInt();
		L = readInt();
		T = readInt();

		out.println(N * 5 + D * 10 + Q * 25 + L * 100 + T * 200);

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
