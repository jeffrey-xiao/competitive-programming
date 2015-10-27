package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2005_Mountains {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int sz;
	static ArrayList<Query> q;
	static TreeSet<Integer> ts;
	static HashMap<Integer, Integer> toIndex, toVal;
	static int[] lazyVal;
	static boolean[] lazy;
	static int[] sum;
	static int[] max;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = new ArrayList<Query>();
		ts = new TreeSet<Integer>();
		toIndex = new HashMap<Integer, Integer>();
		toVal = new HashMap<Integer, Integer>();
		
		char cmd;
		while ((cmd = readCharacter()) != 'E') {
			if (cmd == 'I') {
				int a = readInt();
				int b = readInt();
				ts.add(a-1);
				ts.add(a);
				ts.add(b);
				q.add(new Query(a, b, readInt()));
			} else
				q.add(new Query(readInt()));
		}
		ts.add(0);

		int cnt = 1;
		for (Integer i : ts) {
			toVal.put(cnt, i);
			toIndex.put(i, cnt++);
		}
		sz = toIndex.size();
		lazyVal = new int[sz*4];
		lazy = new boolean[sz*4];
		sum = new int[sz*4];
		max = new int[sz*4];

		for (Query query : q) {
			if (query.b != -1) {
				update(1, 1, sz, toIndex.get(query.a), toIndex.get(query.b), query.c);
			} else {
				int i = search(1, 1, sz, query.a);
				int j = i-1;
				int ival = query(1, 1, sz, 1, i);
				int jval = query(1, 1, sz, 1, j);
				if (ival <= query.a && toVal.get(i+1) == null) {
					out.println(n);
					continue;
				}
				int change = (ival - jval) / (toVal.get(i) - toVal.get(j));
				int lo = toVal.get(j);
				int hi = toVal.get(i);
				while (lo <= hi) {
					int mid = (hi + lo) / 2;
					if (jval + (mid - toVal.get(j))*change <= query.a)
						lo = mid + 1;
					else
						hi = mid - 1;
				}
				out.println(hi);
			}
		}
		out.close();
	}
	static int query (int n, int l, int r, int ql, int qr) {
		if (l == ql && r == qr)
			return sum[n];
		int mid = (l + r) >> 1;
		if (lazy[n]) {
			sum[n << 1] = (toVal.get(mid) - toVal.get(l-1)) * lazyVal[n];
			sum[n << 1 | 1] = (toVal.get(r) - toVal.get(mid)) * lazyVal[n];
			max[n << 1] = Math.max(0, sum[n << 1]);
			max[n << 1 | 1] = Math.max(0, sum[n << 1 | 1]);
			lazy[n << 1] = true;
			lazy[n << 1 | 1] = true;
			lazyVal[n << 1] = lazyVal[n << 1 | 1] = lazyVal[n];
			lazy[n] = false;
		}
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr);
		else if (ql > mid)
			return query(n << 1 | 1, mid+1, r, ql, qr);
		return query(n << 1, l, mid, ql, mid) + query(n << 1 | 1, mid+1, r, mid+1, qr);
	}
	
	static int search (int n, int l, int r, int h) {
		if (l == r)
			return l;
		int mid = (l + r) >> 1;
		if (lazy[n]) {
			sum[n << 1] = (toVal.get(mid) - toVal.get(l-1)) * lazyVal[n];
			sum[n << 1 | 1] = (toVal.get(r) - toVal.get(mid)) * lazyVal[n];
			max[n << 1] = Math.max(0, sum[n << 1]);
			max[n << 1 | 1] = Math.max(0, sum[n << 1 | 1]);
			lazy[n << 1] = true;
			lazy[n << 1 | 1] = true;
			lazyVal[n << 1] = lazyVal[n << 1 | 1] = lazyVal[n];
			lazy[n] = false;
		}
		if (max[n << 1] > h)
			return search(n << 1, l, mid, h);
		else
			return search(n << 1 | 1, mid+1, r, h - sum[n << 1]);
	}
	
	static void update (int n, int l, int r, int ql, int qr, int val) {
		if (ql == l && qr == r) {
			sum[n] = (toVal.get(qr) - toVal.get(ql-1)) * val;
			max[n] = sum[n];
			lazyVal[n] = val;
			lazy[n] = true;
			return;
		}
		int mid = (l + r) >> 1;
			if (lazy[n]) {
				sum[n << 1] = (toVal.get(mid) - toVal.get(l-1)) * lazyVal[n];
				sum[n << 1 | 1] = (toVal.get(r) - toVal.get(mid)) * lazyVal[n];
				max[n << 1] = Math.max(0, sum[n << 1]);
				max[n << 1 | 1] = Math.max(0, sum[n << 1 | 1]);
				lazy[n << 1] = true;
				lazy[n << 1 | 1] = true;
				lazyVal[n << 1] = lazyVal[n << 1 | 1] = lazyVal[n];
				lazy[n] = false;
			}
		if (qr <= mid)
			update(n << 1, l, mid, ql, qr, val);
		else if (ql > mid)
			update(n << 1 | 1, mid+1, r, ql, qr, val);
		else {	
			update(n << 1, l, mid, ql, mid, val);
			update(n << 1 | 1, mid+1, r, mid+1, qr, val);
		}
		sum[n] = sum[n << 1] + sum[n << 1 | 1];
		max[n] = Math.max(max[n << 1], sum[n << 1] + max[n << 1 | 1]);
	}
	
	static class Query {
		int a, b, c;
		Query (int a) {
			this(a, -1, -1);
		}
		Query (int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
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
