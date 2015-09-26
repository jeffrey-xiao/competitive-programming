package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Cross_The_River {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static State[] tree;

	static int N, H, DH, DW;
	static ArrayList<LinkedList<State>> buckets;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		H = readInt();
		DH = readInt();
		DW = readInt();

		Rock[] r = new Rock[N];
		TreeSet<Integer> ts = new TreeSet<Integer>();
		HashMap<Integer, Integer> id = new HashMap<Integer, Integer>();
		for (int i = 0; i < N; i++) {
			int y = readInt();
			int x = readInt();
			int z = readInt();
			ts.add(x);
			r[i] = new Rock(x, y, z);
		}
		int cnt = 1;
		for (int i : ts)
			id.put(i, cnt++);
		tree = new State[cnt * 4];
		buckets = new ArrayList<LinkedList<State>>();
		for (int i = 0; i < cnt; i++) {
			buckets.add(new LinkedList<State>());
			buckets.get(i).add(new State(H, 0, i));
		}

		build(1, 1, cnt - 1);

		Arrays.sort(r);
		LinkedList<Update> list = new LinkedList<Update>();
		for (int i = 0; i < N; i++) {
			//			out.println(r[i].x + " " + r[i].y + " " + r[i].points);
			Integer lo = ts.ceiling(r[i].x - DW);
			Integer hi = ts.floor(r[i].x + DW);
			if (lo == null || hi == null || (lo > hi)) {
				add(id.get(r[i].x), new State(r[i].y, -1 << 27, id.get(r[i].x)));
				continue;
			} else {
				lo = id.get(lo);
				hi = id.get(hi);
				//				out.println("SEARCHING " + lo + " TO " + hi);
				State max = query(1, 1, cnt - 1, lo, hi);
				while (max.y - r[i].y > DH) {
					remove(max.index);
					update(1, max.index, 1, cnt - 1);
					max = query(1, 1, cnt - 1, lo, hi);
				}
				//				out.println(max.points + r[i].points + " AT " + id.get(r[i].x));
				int nextPoints = max.points == -1 << 27 ? max.points : (max.points + r[i].points);
				list.add(new Update(id.get(r[i].x), new State(r[i].y, nextPoints, id.get(r[i].x))));
				if (i == N - 1 || (r[i].y != r[i + 1].y)) {
					//					out.println("UPDATING");
					while (!list.isEmpty()) {
						Update u = list.pollFirst();
						add(u.x, u.s);
						update(1, u.x, 1, cnt - 1);
					}
				}
			}
		}
		State ans = query(1, 1, cnt - 1, 1, cnt - 1);
		while (ans.y - 0 > DH) {
			remove(ans.index);
			update(1, ans.index, 1, cnt - 1);
			ans = query(1, 1, cnt - 1, 1, cnt - 1);
		}
		out.println(ans.points);
		out.close();
	}

	static class Update {
		int x;
		State s;

		Update (int x, State s) {
			this.x = x;
			this.s = s;
		}
	}

	private static void update (int n, int x, int lo, int hi) {
		if (x == lo && x == hi) {
			if (buckets.get(x).size() == 0)
				buckets.get(x).add(new State(-1 << 27, -1 << 27, lo));
			tree[n] = buckets.get(x).getFirst();
			return;
		}
		int mid = (lo + hi) / 2;
		if (x <= mid)
			update(n << 1, x, lo, mid);
		else
			update(n << 1 | 1, x, mid + 1, hi);
		if (tree[n << 1].points > tree[n << 1 | 1].points)
			tree[n] = tree[n << 1];
		else
			tree[n] = tree[n << 1 | 1];
	}

	private static void remove (int x) {
		buckets.get(x).removeFirst();
	}

	private static State query (int n, int lo, int hi, int qlo, int qhi) {
		if (lo == qlo && hi == qhi) {
			return tree[n];
		}
		int mid = (lo + hi) / 2;
		if (qhi <= mid)
			return query(n << 1, lo, mid, qlo, qhi);
		else if (qlo > mid)
			return query(n << 1 | 1, mid + 1, hi, qlo, qhi);
		else {
			State s1 = query(n << 1, lo, mid, qlo, mid);
			State s2 = query(n << 1 | 1, mid + 1, hi, mid + 1, qhi);
			if (s1.points > s2.points)
				return s1;
			return s2;
		}
	}

	private static void add (int x, State s) {
		while (buckets.get(x).size() > 0 && buckets.get(x).getLast().points <= s.points)
			buckets.get(x).removeLast();
		buckets.get(x).add(s);
	}

	private static void build (int n, int l, int r) {
		if (l == r) {
			tree[n] = new State(H, 0, l);
			return;
		}
		int mid = (l + r) / 2;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		tree[n] = new State(H, 0, l);
	}

	static class State {
		int y, points, index;

		State (int y, int points, int index) {
			this.y = y;
			this.points = points;
			this.index = index;
		}
	}

	static class Rock implements Comparable<Rock> {
		int x, y, points;

		Rock (int x, int y, int points) {
			this.x = x;
			this.y = y;
			this.points = points;
		}

		@Override
		public int compareTo (Rock o) {
			return o.y - y;
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
