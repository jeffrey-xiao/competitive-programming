package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2014_Maximum_Product {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int N = readInt();
		long ans = 1;
		long max = -1 << 30;

		boolean hasOne = false;
		boolean hasZero = false;
		for (int i = 0; i < N; i++) {
			int val = readInt();
			if (val != 0)
				ans *= val;

			if (val == 1)
				hasOne = true;

			if (val == 0)
				hasZero = true;

			if (val < 0)
				max = Math.max(max, val);
		}

		if (ans < 0)
			ans /= max;

		if (ans == 1 && !hasOne) {
			if (hasZero)
				out.println(0);
			else
				out.println(max);
		} else {
			out.println(ans);
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
