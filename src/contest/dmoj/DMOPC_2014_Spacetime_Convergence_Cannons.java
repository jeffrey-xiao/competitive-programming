package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.StringTokenizer;

public class DMOPC_2014_Spacetime_Convergence_Cannons {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static long[] bit = new long[200001];

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int X = readInt();
		ArrayList<Pair> sorted = new ArrayList<Pair>();
		ArrayList<Slope> search = new ArrayList<Slope>();
		for (int i = 0; i < n; i++) {
			int x = readInt();
			int y = readInt();
			sorted.add(new Pair(y, x, y, X - x, y, x));
			search.add(new Slope(y, X - x, y));
		}
		Collections.sort(search);
		HashMap<Slope, Integer> hm = new HashMap<Slope, Integer>();
		int cnt = 1;
		for (int i = 0; i < n; i++) {
			if (i == 0)
				hm.put(search.get(i), cnt++);
			else {
				Slope o1 = search.get(i - 1);
				Slope o2 = search.get(i);
				if (o2.dx * o1.dy - o1.dx * o2.dy == 0) {
					continue;
				}
				hm.put(search.get(i), cnt++);
			}
		}
		Collections.sort(sorted);
		long ans = 0;
		for (int i = 0; i < n; i++) {
			int idx = hm.get(sorted.get(i).s2);
			long res = i - query(idx - 1);
			ans += res * res;
			update(idx, 1);
		}
		pr.println(ans);
		pr.close();
	}

	static void update (int idx, int v) {
		for (int x = idx; x <= 200000; x += (x & -x))
			bit[x] += v;
	}

	static long query (int idx) {
		long sum = 0;
		for (int x = idx; x > 0; x -= (x & -x))
			sum += bit[x];
		return sum;
	}

	static class Pair implements Comparable<Pair> {
		Slope s1, s2;
		int x, y;

		Pair (int dy1, int dx1, int dy2, int dx2, int y, int x) {
			s1 = new Slope(dy1, dx1, y);
			s2 = new Slope(dy2, dx2, y);
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo (Pair o) {
			int cmp = -s1.compareTo(o.s1);
			return cmp;
		}
	}

	static class Slope implements Comparable<Slope> {
		int dy, dx, y;

		Slope (int dy, int dx, int y) {
			int gcf = gcf(dy, dx);
			this.dy = dy / gcf;
			this.dx = dx / gcf;
			this.y = y;
		}

		int gcf (int a, int b) {
			while (b != 0) {
				int temp = a % b;
				a = b;
				b = temp;
			}
			return a;
		}

		@Override
		public int compareTo (Slope o) {
			int cmp = (dy * o.dx - dx * o.dy);
			if (cmp == 0)
				return y - o.y;
			return cmp;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Slope) {
				Slope s = (Slope) o;
				return dy == s.dy && dx == s.dx;
			}
			return false;
		}

		@Override
		public int hashCode () {
			return new Integer(dy).hashCode() * 1001 + new Integer(dx).hashCode();
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