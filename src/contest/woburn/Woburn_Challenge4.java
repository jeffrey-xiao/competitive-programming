package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge4 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static Bomb[] b;
	static TreeMap<Integer, Bomb> tb = new TreeMap<Integer, Bomb>();
	static TreeMap<Integer, Integer> tp = new TreeMap<Integer, Integer>();
	static Person[] civilian;
	static int[] cnt;

	// seg tree
	static int[] minReach, maxReach;
	static int[] left, right;

	public static void main (String[] args) throws Exception {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		tb = new TreeMap<Integer, Bomb>();
		civilian = new Person[M];

		minReach = new int[4*N];
		maxReach = new int[4*N];
		left = new int[4*N];
		right = new int[4*N];

		for (int i = 0; i < N; i++) {
			int pos = readInt();
			int l = readInt();
			int r = readInt();
//			int pos = (int)(Math.random() * 100);
//			int l = (int)(Math.random() * 100);
//			int r = (int)(Math.random() * 100);
//			System.out.println(pos + " " + l + " " + r);
			if (!tb.containsKey(pos))
				tb.put(pos, new Bomb(l, r));
			tb.get(pos).l = Math.max(l, tb.get(pos).l);
			tb.get(pos).r = Math.max(r, tb.get(pos).r);
			tb.get(pos).cnt++;
		}

		N = tb.size();
		b = new Bomb[N + 1];
		int cnt = 1;
		
		for (Map.Entry<Integer, Bomb> entry : tb.entrySet()) {
			b[cnt] = new Bomb(entry.getKey(), entry.getValue().l, entry.getValue().r, cnt);
			b[cnt].cnt = entry.getValue().cnt;
			cnt++;
		}
		for (int i = 1; i <= N; i++) 
			tb.get(b[i].pos).index = i;

		build(minReach, 1, 1, N);
		build(maxReach, 1, 1, N);
		build(left, 1, 1, N);
		build(right, 1, 1, N);

		for (int i = 1; i <= N; i++) {
			update(left, 1, 1, N, i, b[i].pos - b[i].l);
			update(right, 1, 1, N, i, -(b[i].pos + b[i].r));
			
			update(minReach, 1, 1, N, i, i);
			Map.Entry<Integer, Bomb> lowInterval = tb.ceilingEntry(b[i].pos - b[i].l);
			update(minReach, 1, 1, N, i, query(minReach, 1, 1, N, lowInterval.getValue().index, i));
		}

		for (int i = N; i >= 1; i--) {
			update(maxReach, 1, 1, N, i, -i);
			Map.Entry<Integer, Bomb> highInterval = tb.floorEntry(b[i].pos + b[i].r);
			update(maxReach, 1, 1, N, i, query(maxReach, 1, 1, N, i, highInterval.getValue().index));
		}

		

		for (int i = 0; i < M; i++)
			civilian[i] = new Person(readInt(), i);
		
		int[] ans = new int[M + 1];
		for (int i = 1; i <= N; i++) {
			int ll = query(minReach, 1, 1, N, i, i);
			int rr = -query(maxReach, 1, 1, N, i, i);

			int leftReach = query(left, 1, 1, N, ll, rr);
			int rightReach = -query(right, 1, 1, N, ll, rr);
			boolean finished = false;
			boolean[] exploded = new boolean[N + 1];
			exploded[i] = true;
			int l = b[i].pos - b[i].l;
			int r = b[i].pos + b[i].r;
			int loBomb = i;
			int hiBomb = i;
			while (!finished) {
				finished = true;
				for (int j = 1; j <= N; j++) {
					if (!exploded[j] && l <= b[j].pos && b[j].pos <= r) {
						exploded[j] = true;
						l = Math.min(l, b[j].pos - b[j].l);
						r = Math.max(r, b[j].pos + b[j].r);
						loBomb = Math.min(loBomb, j);
						hiBomb = Math.max(hiBomb, j);
						finished = false;
					}
				}
			}
			System.out.println(i + " " + loBomb + " " + hiBomb + " " + ll + " " + rr);
			if (loBomb != ll || hiBomb != rr)
				throw new Exception();
			for (int j = 0; j < M; j++) {
				if (l <= civilian[j].pos && civilian[j].pos <= r)
					ans[j] += b[i].cnt;
			}
		}
		for (int i = 0; i < M; i++) {
			out.println(ans[i]);
		}
		
		out.close();
	}

	static class Person implements Comparable<Person> {
		int pos, index;
		Person (int pos, int index) {
			this.pos = pos;
			this.index = index;
		}
		@Override
		public int compareTo (Person o) {
			return pos - o.pos;
		}
	}

	static class Bomb{
		int pos, l, r, index, cnt;
		Bomb (int l, int r) {
			this.l = l;
			this.r = r;
		}
		Bomb (int pos, int l, int r, int index) {
			this.pos = pos;
			this.l = l;
			this.r = r;
			this.index = index;
		}
	}

	static void build (int[] tree, int n, int l, int r) {
		tree[n] = 1 << 30;
		if (l == r)
			return;
		int mid = (l + r) >> 1;
		build(tree, n << 1, l, mid);
		build(tree, n << 1 | 1, mid + 1, r);
	}

	static void update (int[] tree, int n, int l, int r, int x, int val) {
		if (l == x && x == r) {
			tree[n] = Math.min(tree[n], val);
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(tree, n << 1, l, mid, x, val);
		else
			update(tree, n << 1 | 1, mid + 1, r, x, val);
		tree[n] = Math.min(tree[n << 1], tree[n << 1 | 1]);
	}

	static int query (int[] tree, int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr) {
			return tree[n];
		}
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(tree, n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(tree, n << 1 | 1, mid+1, r, ql, qr);
		else
			return Math.min(query(tree, n << 1, l, mid, ql, mid), query(tree, n << 1 | 1, mid+1, r, mid+1, qr));
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

