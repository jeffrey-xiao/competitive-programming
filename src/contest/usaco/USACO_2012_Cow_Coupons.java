package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2012_Cow_Coupons {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		long m = readLong();
		boolean[] bought = new boolean[n];
		PriorityQueue<Long> redemn = new PriorityQueue<Long>();
		PriorityQueue<Cow> C = new PriorityQueue<Cow>();
		PriorityQueue<Cow> P = new PriorityQueue<Cow>();
		long[] p = new long[n];
		long[] c = new long[n];
		for (int x = 0; x < n; x++) {
			p[x] = readLong();
			c[x] = readLong();
			C.offer(new Cow(c[x], x));
			P.offer(new Cow(p[x], x));
		}
		int x = 0;
		for (; x < n && m > 0; x++) {
			while (bought[C.peek().index])
				C.poll();
			while (bought[P.peek().index])
				P.poll();
			long nextValue = redemn.size() < k ? 0 : redemn.peek();
			if (nextValue + C.peek().value < P.peek().value) {
				long cost = nextValue + C.peek().value;
				if (m < cost)
					break;
				m -= cost;
				if (redemn.size() >= k)
					redemn.poll();
				redemn.offer(p[C.peek().index] - c[C.peek().index]);
				bought[C.peek().index] = true;
			} else {
				if (m < P.peek().value)
					break;
				m -= P.peek().value;
				bought[P.peek().index] = true;
			}
		}
		System.out.println(x);
	}

	static class Cow implements Comparable<Cow> {
		long value;
		int index;

		Cow (long value, int index) {
			this.value = value;
			this.index = index;
		}

		@Override
		public int compareTo (Cow o) {
			if (value == o.value)
				return 0;
			return value < o.value ? -1 : 1;
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
