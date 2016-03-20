package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_All_Your_Base {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int E, N;
	static int[] A;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		E = readInt();
		N = readInt();

		A = new int[N];

		for (int i = 0; i < N; i++)
			A[i] = readInt();

		for (int i = N - 1; i > 0; i--) {
			A[i - 1] = convert(A[i - 1], A[i]);
		}

		out.println(convert(E, A[0]));
		out.close();
	}

	static int convert (int a, int b) {
		int base = 1;
		int res = 0;
		while (a != 0) {
			res += base * (a % 10);
			a /= 10;
			base *= b;
		}
		return res;
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
