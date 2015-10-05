package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class The_Torture_Chamber {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long start = readLong() - 1;
		if (start == 0)
			start++;
		long end = readLong();
		int end1 = (int) Math.sqrt(end);
		boolean[] sieve1 = new boolean[end1];
		LinkedList<Integer> ll = new LinkedList<Integer>();
		sieve1[0] = true;

		for (int x = 1; x < sieve1.length; x += 2)
			if (x + 1 % 2 == 0)
				sieve1[x] = true;
		for (int x = 1; x < sieve1.length; x++)
			if (sieve1[x] == false) {
				ll.add(x + 1);
				ps.println(x + 1);
				for (int y = (x + 1) * (x + 1) - 1; y >= 0 && y < sieve1.length; y += (x + 1))
					sieve1[y] = true;
			}
		HashSet<Long> sieve2 = new HashSet<Long>();

		for (int x : ll) {
			for (long y = x * x - 1; y < (end - start) + start; y += x) {
				if (y - start < 0)
					continue;
				sieve2.add(y - start);
			}
		}
		ps.println(end - start - sieve2.size());
		ps.close();
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