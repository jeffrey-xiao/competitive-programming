package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_2016_Diplomacy {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, sz;
	static Event[] e;
	static ArrayList<Integer> ts = new ArrayList<Integer>();

	static int[] tree, lazy;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		e = new Event[2 * N];

		for (int i = 0; i < N; i++) {
			int x1 = readInt();
			int x2 = readInt() + 1;
			int y1 = readInt();
			int y2 = readInt();
			e[i << 1] = new Event(x1, y1, y2, 1);
			e[i << 1 | 1] = new Event(x2, y1, y2, -1);
			ts.add(y1);
			ts.add(y2);
		}

		Collections.sort(ts);
		ts = unique(ts);

		for (int i = 0; i < 2 * N; i++) {
			e[i].y1 = getIndex(ts, e[i].y1);
			e[i].y2 = getIndex(ts, e[i].y2);
		}

		Arrays.sort(e);
		out.flush();
		sz = ts.size();
		tree = new int[4 * sz];
		lazy = new int[4 * sz];

		int ans = 0;
		for (int i = 0; i < 2 * N; i++) {
			update(1, 0, sz - 1, e[i].y1, e[i].y2, e[i].type);
			if (e[i].type == 1)
				ans = Math.max(ans, tree[1]);
		}

		out.println(ans);
		out.close();
	}

	static ArrayList<Integer> unique (ArrayList<Integer> a) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (a.isEmpty())
			return ret;
		ret.add(a.get(0));
		for (int i = 1; i < a.size(); i++)
			if (a.get(i) != a.get(i - 1))
				ret.add(a.get(i));
		return ret;
	}

	static int getIndex (ArrayList<Integer> a, int val) {
		int lo = 0;
		int hi = a.size() - 1;
		while (lo <= hi) {
			int mid = (lo + hi) >> 1;
			if (a.get(mid) < val)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return lo;
	}

	static void update (int n, int lo, int hi, int qlo, int qhi, int val) {
		if (lo == qlo && hi == qhi) {
			lazy[n] += val;
			tree[n] += val;
			return;
		}

		if (lazy[n] != 0) {
			lazy[n << 1] += lazy[n];
			lazy[n << 1 | 1] += lazy[n];
			tree[n << 1] += lazy[n];
			tree[n << 1 | 1] += lazy[n];
			lazy[n] = 0;
		}

		int mid = (lo + hi) >> 1;
		if (qhi <= mid)
			update(n << 1, lo, mid, qlo, qhi, val);
		else if (qlo > mid)
			update(n << 1 | 1, mid + 1, hi, qlo, qhi, val);
		else {
			update(n << 1, lo, mid, qlo, mid, val);
			update(n << 1 | 1, mid + 1, hi, mid + 1, qhi, val);
		}

		tree[n] = Math.max(tree[n << 1], tree[n << 1 | 1]);
	}

	static class Event implements Comparable<Event> {
		int x, y1, y2, type;

		Event (int x, int y1, int y2, int type) {
			this.x = x;
			this.y1 = y1;
			this.y2 = y2;
			this.type = type;
		}

		@Override
		public int compareTo (Event e) {
			if (x != e.x)
				return x - e.x;
			return type - e.type;
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
