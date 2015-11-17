package contest.pegtest;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Peg_Test_2014_Assembly_Elves {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static boolean[] used = new boolean[101];

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int total = 0;
		used[1] = true;
		while (!used[n]) {
			int max = -1 << 30;
			int bitCount = -1 << 30;
			for (int x = 1; x <= 100; x++) {
				for (int y = 1; y <= 100; y++) {
					int next = x + y;
					if (!used[x] || !used[y] || next > n)
						continue;

					int nextCount = count(next & n);
					if ((nextCount > bitCount && !used[next])) {
						max = next;
						bitCount = nextCount;
					}
				}
			}
			System.out.println(max);
			used[max] = true;
		}
		for (int x = 1; x <= 100; x++)
			if (used[x])
				total++;
		System.out.println(total);
		for (int x = 1; x <= 100; x++)
			if (used[x])
				System.out.print(x + " ");
	}

	private static int count (int next) {
		int res = 0;
		while (next != 0) {
			int bit = Integer.highestOneBit(next);
			res++;
			next &= ~bit;
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
