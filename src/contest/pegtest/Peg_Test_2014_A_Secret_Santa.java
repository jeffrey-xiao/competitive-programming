package contest.pegtest;

import java.util.*;
import java.io.*;

public class Peg_Test_2014_A_Secret_Santa {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static int[] ans;
	static boolean[] used;
	static ArrayList<ArrayList<Integer>> conn;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		conn = new ArrayList<ArrayList<Integer>>(N);

		used = new boolean[N];
		ans = new int[N];

		for (int i = 0; i < N; i++)
			conn.add(new ArrayList<Integer>());

		for (int i = 0; i < N; i++) {
			int sz = readInt();
			for (int j = 0; j < sz; j++)
				conn.get(i).add(readInt() - 1);
		}

		compute(0);

		for (int i = 0; i < N; i++)
			out.printf("%d ", ans[i]);
		out.println();
		out.close();
	}

	static boolean compute (int i) {
		if (i == N)
			return true;
		for (int j : conn.get(i)) {
			if (used[j])
				continue;
			used[j] = true;
			ans[i] = j + 1;
			if (compute(i + 1))
				return true;
			used[j] = false;
		}
		return false;
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
