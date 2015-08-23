package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class USACO_2014_Learning_By_Example {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int a = readInt();
		int b = readInt();
		ArrayList<Cow> c = new ArrayList<Cow>();
		for (int x = 0; x < n; x++) {
			c.add(new Cow(next().equals("S"), readInt()));
		}
		c.add(new Cow(false, 1 << 30));
		c.add(new Cow(false, -1 << 30));
		Collections.sort(c);
		for (int x = c.size() - 1; x >= 1; x--) {
			if (c.get(x).weight == c.get(x - 1).weight) {
				if (!c.get(x).spot)
					c.remove(x);
				else if (!c.get(x - 1).spot)
					c.remove(x - 1);
				else if (c.get(x).spot && c.get(x - 1).spot)
					c.remove(x);
			}
		}
		int count = 0;
		for (int x = 0; x < c.size() - 1; x++) {
			int start = c.get(x).weight;
			int end = c.get(x + 1).weight;
			int mid = (start + end) / 2;
			// System.out.println(start + " " + end + " " + mid);
			if (c.get(x).spot) {
				int is = Math.max(a, start + 1);
				int ie = Math.min(b, mid);
				// System.out.println(ie-is+1);
				if (ie >= is)
					count += ie - is + 1;
			}
			if (c.get(x + 1).spot) {
				int is = Math.max(a, mid + 1);
				int ie = Math.min(b, end);
				// System.out.println(ie-is+1);
				if (ie >= is)
					count += ie - is + 1;
			}
			if (a <= mid && mid <= b && start % 2 == end % 2
					&& c.get(x + 1).spot && !c.get(x).spot) {
				count++;
			}
		}
		System.out.println(count);
	}

	static class Cow implements Comparable<Cow> {
		boolean spot;
		int weight;

		Cow (boolean spot, int weight) {
			this.spot = spot;
			this.weight = weight;
		}

		@Override
		public int compareTo (Cow o) {
			return weight - o.weight;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Cow) {
				Cow c = (Cow) o;
				return weight == c.weight;
			}
			return false;
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
