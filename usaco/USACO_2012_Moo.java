package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Moo {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int curr = 3;
		int next = 4;
		while (curr < n) {
			curr = 2 * curr + next;
			next++;
		}
		compute(curr, next - 1, n);
		// System.out.println(curr);

	}

	private static void compute (int curr, int next, int n) {
		// System.out.println(curr + " " + next + " " + n);
		if (next == 0) {
			if (n == 1)
				System.out.println("m");
			else
				System.out.println("o");
			return;
		}
		int index = (curr - next) / 2;
		if (index < n && n <= index + next) {
			if (n - index == 1)
				System.out.println("m");
			else
				System.out.println("o");
			return;
		}
		if (n > index + next)
			n -= (index + next);
		compute(index, next - 1, n);

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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
