package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2012_Flowerpot {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int d = readInt();
		Pot[] pots = new Pot[n];
		for (int x = 0; x < n; x++) {
			pots[x] = new Pot(readInt(), readInt(), x);
		}
		Arrays.sort(pots, new Comparator<Pot>() {

			@Override
			public int compare (Pot o1, Pot o2) {
				if (o1.x == o2.x)
					return o1.y - o2.y;
				return o1.x - o2.x;
			}

		});

		int lo = 0;
		int hi = 0;
		int minWidth = Integer.MAX_VALUE;
		TreeSet<Pot> p = new TreeSet<Pot>();
		p.add(pots[0]);
		while (true) {
			int min = p.first().y;
			int max = p.last().y;
			if (max - min >= d) {
				minWidth = Math.min(minWidth, pots[hi].x - pots[lo].x);
				p.remove(pots[lo++]);
			} else {
				if (hi == n - 1)
					break;
				p.add(pots[++hi]);
			}
		}
		System.out.println(minWidth == Integer.MAX_VALUE ? -1 : minWidth);
	}

	static class Pot implements Comparable<Pot> {
		int x, y, index;

		Pot (int x, int y, int i) {
			this.x = x;
			this.y = y;
			index = i;
		}

		@Override
		public int compareTo (Pot o) {
			if (y == o.y)
				return x - o.x;
			return y - o.y;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Pot) {
				Pot p = (Pot) o;
				return y == p.y;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}