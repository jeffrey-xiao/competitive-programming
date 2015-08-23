package contest.dmoj;

import java.util.*;
import java.io.*;

public class Touching_Segment {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static final int SIZE = 100005 * 2;
	static Node[] seg;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		int tt = readInt();
		for (int qq = 1; qq <= tt; qq++) {
			int n = readInt();
			Seg[] s = new Seg[n];
			seg = new Node[SIZE * 3];
			TreeSet<Integer> ts = new TreeSet<Integer>();
			for (int i = 0; i < n; i++) {
				s[i] = new Seg(readInt(), readInt());
				ts.add(s[i].a);
				ts.add(s[i].b);
			}
			// sorted list of end points (non compressed)
			HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
			int cnt = 1;
			for (int i : ts)
				hm.put(i, cnt++);
			build(1, ts.size(), 1);
			for (int i = 0; i < n; i++) {
				s[i].a = hm.get(s[i].a);
				s[i].b = hm.get(s[i].b);
				update(s[i].a, s[i].b, 1, 1);
			}
			Arrays.sort(s);
			PriorityQueue<Seg> pq = new PriorityQueue<Seg>(new Comparator<Seg>() {
				@Override
				public int compare (Seg o1, Seg o2) {
					return o1.b - o2.b;
				}
			});
			int j = 0;
			int ans = 0;
			for (int i = 1; i <= ts.size(); i++) {
				while (j < n && s[j].a <= i) {
					update(s[j].a, s[j].b, 1, -1);
					// System.out.println(i + " ADDED " + s[j].a + " " +
					// s[j].b);
					pq.offer(s[j]);
					j++;
				}
				while (!pq.isEmpty() && pq.peek().b < i)
					pq.poll();
				ans = Math.max(ans, pq.size() + query(i + 1, ts.size(), 1));
				// System.out.println(pq.size() + " " + query(i+1, ts.size(), 1)
				// + " " + i);
			}
			pr.printf("Case %d: %d\n", qq, ans);
		}
		pr.close();
	}

	static int bsearch (int i, ArrayList<Integer> a) {
		int lo = 0;
		int hi = a.size() - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (a.get(mid) > i) {
				hi = mid - 1;
			} else if (a.get(mid) < i) {
				lo = mid + 1;
			} else {
				return mid + 1;
			}
		}
		return -1;
	}

	static class Seg implements Comparable<Seg> {
		int a, b;

		Seg (int a, int b) {
			this.a = a;
			this.b = b;
		}

		@Override
		public int compareTo (Seg s) {
			return a - s.a;
		}
	}

	static void update (int l, int r, int n, int v) {
		if (seg[n].l == l && seg[n].r == r) {
			seg[n].max += v;
			seg[n].p += v;
			return;
		}
		if (seg[n].p != 0) {
			seg[2 * n].max += seg[n].p;
			seg[2 * n + 1].max += seg[n].p;
			seg[2 * n].p += seg[n].p;
			seg[2 * n + 1].p += seg[n].p;
			seg[n].p = 0;
		}
		int mid = (seg[n].l + seg[n].r) / 2;
		if (r <= mid)
			update(l, r, 2 * n, v);
		else if (l > mid)
			update(l, r, 2 * n + 1, v);
		else {
			update(l, mid, 2 * n, v);
			update(mid + 1, r, 2 * n + 1, v);
		}
		seg[n].max = Math.max(seg[2 * n].max, seg[2 * n + 1].max);
	}

	static int query (int l, int r, int n) {
		if (l > r)
			return 0;
		if (seg[n].l == l && seg[n].r == r) {
			return seg[n].max;
		}
		if (seg[n].p != 0) {
			seg[2 * n].max += seg[n].p;
			seg[2 * n + 1].max += seg[n].p;
			seg[2 * n].p += seg[n].p;
			seg[2 * n + 1].p += seg[n].p;
			seg[n].p = 0;
		}
		int mid = (seg[n].l + seg[n].r) / 2;
		if (r <= mid)
			return query(l, r, 2 * n);
		else if (l > mid)
			return query(l, r, 2 * n + 1);
		return Math.max(query(l, mid, 2 * n), query(mid + 1, r, 2 * n + 1));
	}

	static void build (int l, int r, int n) {
		seg[n] = new Node(l, r);
		if (l == r) {
			seg[n].max = 0;
			return;
		}
		int mid = (r + l) / 2;
		build(l, mid, 2 * n);
		build(mid + 1, r, 2 * n + 1);
	}

	static class Node {
		int l, r, max;
		int p;

		Node (int l, int r) {
			this.l = l;
			this.r = r;
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
