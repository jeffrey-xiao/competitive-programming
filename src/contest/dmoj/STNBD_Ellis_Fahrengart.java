package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class STNBD_Ellis_Fahrengart {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int SIZE = 100005;
	static int n, m;
	static int[] bit = new int[SIZE], a = new int[SIZE];
	static int[] prefix = new int[SIZE], suffix = new int[SIZE];
	static int size = 0;
	static long res = 0;

	public static void main (String[] args) throws IOException {

		n = readInt();
		TreeSet<Integer> ts = new TreeSet<Integer>();
		for (int i = 1; i <= n; i++) {
			a[i] = readInt();
			ts.add(a[i]);
		}
		HashMap<Integer, Integer> hs = new HashMap<Integer, Integer>();
		int c = 1;
		for (int i : ts)
			hs.put(i, c++);
		for (int i = 1; i <= n; i++)
			a[i] = hs.get(a[i]);
		int m = readInt();
		LinkedList<Query> q = new LinkedList<Query>();
		for (int i = 0; i < m; i++)
			q.add(new Query(readInt(), readInt(), i));
		long[] ans = new long[m];
		int sz = (int) Math.sqrt(n);
		for (int i = 0; i < (n - 1) / sz + 1; i++) {
			int start = i * sz + 1;
			int end = Math.min((i + 1) * sz, n);
			ArrayList<Query> curr = new ArrayList<Query>();
			for (Iterator<Query> it = q.iterator(); it.hasNext();) {
				Query x = it.next();
				if (start <= x.l && x.l <= end) {
					curr.add(x);
					it.remove();
				}
			}
			Collections.sort(curr, new Comparator<Query>() {
				@Override
				public int compare (Query o1, Query o2) {
					return o1.r - o2.r;
				}
			});
			init();
			res = 0;
			int l = start;
			int r = start - 1;
			for (Query x : curr) {
				while (r < x.r) {
					r++;
					res += size - query(a[r]);
					update(a[r], 1);
				}
				while (l < x.l) {
					res -= query(a[l] - 1);
					update(a[l], -1);
					l++;
				}
				while (l > x.l) {
					l--;
					res += query(a[l] - 1);
					update(a[l], 1);
				}
				ans[x.index] = res;

			}
		}

		for (int i = 0; i < m; i++)
			ps.println(ans[i]);
		ps.close();
	}

	static class Query implements Comparable<Query> {
		int l, r, index;

		Query (int l, int r, int index) {
			this.l = l;
			this.r = r;
			this.index = index;
		}

		@Override
		public int compareTo (Query o) {
			return l - o.l;
		}
	}

	static void init () {
		bit = new int[SIZE];
		size = 0;
	}

	static void update (int idx, int v) {
		for (int x = idx; x < SIZE; x += (x & -x))
			bit[x] += v;
		size += v;
	}

	static int query (int idx) {
		int sum = 0;
		for (int x = idx; x > 0; x -= (x & -x))
			sum += bit[x];
		return sum;
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
