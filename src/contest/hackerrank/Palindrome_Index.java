package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Palindrome_Index {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 1; qq <= t; qq++) {
			String a = next();
			if (a.equals(new StringBuilder(a).reverse().toString())) {
				out.println(-1);
				continue;
			}
			int res = -1;
			if (a.length() % 2 == 0) {
				if (res == -1)
					res = check(a.length() / 2, a.length() / 2, a, false);
				if (res == -1)
					res = check(a.length() / 2 - 1, a.length() / 2 - 1, a, true);
			} else {
				if (res == -1)
					res = check(a.length() / 2, a.length() / 2 + 1, a, false);
				if (res == -1)
					res = check(a.length() / 2 - 1, a.length() / 2, a, true);
			}
			out.println(res);
		}

		out.close();
	}

	static int check (int i, int j, String a, boolean leftShort) {
		boolean mismatch = false;
		int indexToRemove = -1;
		while (true) {
			while (i >= 0 && j < a.length() && a.charAt(i) == a.charAt(j)) {
				i--;
				j++;
			}
			if (i == -1 && j == a.length())
				return indexToRemove;
			else if (i == -1)
				return a.length() - 1;
			else if (j == a.length())
				return 0;

			if (mismatch)
				return -1;
			if (leftShort) {
				indexToRemove = j;
				j++;
			} else {
				indexToRemove = i;
				i--;
			}
			mismatch = true;
		}
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