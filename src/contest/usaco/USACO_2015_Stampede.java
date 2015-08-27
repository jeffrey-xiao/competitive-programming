package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.SortedSet;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2015_Stampede {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		TreeSet<Integer> xV = new TreeSet<Integer>();
		ArrayList<Event> e = new ArrayList<Event>();
		for (int i = 0; i < n; i++) {
			int x = readInt();
			int y = readInt();
			int r = readInt();
			x *= -r;
			e.add(new Event(y, x - r, x));
			xV.add(x - r - 1);
			xV.add(x);
			xV.add(x - r + 1);
			xV.add(x - 1);
			xV.add(x);
			xV.add(x + 1);
		}
		Collections.sort(e);
		int res = 0;
		for (int x = 0; x < e.size(); x++) {
			Integer e1 = xV.lower(e.get(x).l);
			Integer e2 = xV.lower(e.get(x).r);
			// System.out.println(e1 + " " + e2 + " " + e.get(x).l + " " +
			// e.get(x).r);
			if (e1 == null || e2 == null)
				continue;
			if (!e1.equals(e2)) {
				res++;
				SortedSet ss = ((TreeSet) (xV.clone())).subSet(e1, false, e2, false);
				xV.removeAll(ss);
			}
		}
		System.out.println(res);
	}

	static class Event implements Comparable<Event> {
		int y, l, r;

		Event (int y, int l, int r) {
			this.y = y;
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo (Event o) {
			return y - o.y;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Event) {
				Event e = (Event) o;
				return y == e.y && l == e.l && r == e.r;
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
