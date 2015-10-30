package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Christmas_Mine_And_Tree {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, q, currChain, currPos = 1;
	static Node[] a;
	static Node[] tree1;
	static Node[] tree2;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int[] size, parent, depth;
	static int[] chainPos, chain, head, toIndex;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		a = new Node[n];

		toIndex = new int[n + 1];
		size = new int[n];
		parent = new int[n];
		depth = new int[n];
		chainPos = new int[n];
		chain = new int[n];
		head = new int[n];
		a = new Node[n + 1];
		tree1 = new Node[n * 4];
		tree2 = new Node[n * 4];

		for (int i = 0; i < n; i++) {
			head[i] = -1;
			adj.add(new ArrayList<Integer>());
		}
		for (int i = 0; i < n; i++)
			a[i] = readNode();
		for (int i = 0; i < n - 1; i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		dfs(0, -1, 0);
		hld(0, -1);

		build(1, 1, n);
		for (int i = 0; i < m; i++) {
			char command = readCharacter();
			if (command == 'U') {
				int u = readInt() - 1;
				a[u] = readNode();
				update(1, 1, n, chainPos[u]);
			} else {
				int u = readInt() - 1;
				int v = readInt() - 1;
				Node start = new Node(0, readDouble(), readDouble(), 1);
				int lca = lca(u, v);
				Node res = merge(solve(u, lca, true, false), solve(v, lca, false, true));
				res = merge(start, res);
				Point ans = rotate(res.dp, res.rotate);
				out.printf("%.6f %.6f\n", ans.x, ans.y);
			}
		}
		out.close();

	}

	static int lca (int i, int j) {
		while (chain[i] != chain[j]) {
			if (depth[head[chain[i]]] < depth[head[chain[j]]])
				j = parent[head[chain[j]]];
			else
				i = parent[head[chain[i]]];
		}
		if (depth[i] < depth[j])
			return i;
		return j;
	}

	static Node solve (int u, int v, boolean isRev, boolean isInclude) {
		Node res = new Node();
		while (chain[u] != chain[v]) {
			if (isRev)
				res = merge(res, query(1, 1, n, chainPos[head[chain[u]]], chainPos[u], isRev));
			else
				res = merge(query(1, 1, n, chainPos[head[chain[u]]], chainPos[u], isRev), res);
			u = parent[head[chain[u]]];
		}
		if (isInclude) {
			if (isRev)
				res = merge(res, query(1, 1, n, chainPos[v], chainPos[u], isRev));
			else
				res = merge(query(1, 1, n, chainPos[v], chainPos[u], isRev), res);
		} else {
			if (u == v)
				return res;
			if (isRev)
				res = merge(res, query(1, 1, n, chainPos[v] + 1, chainPos[u], isRev));
			else
				res = merge(query(1, 1, n, chainPos[v] + 1, chainPos[u], isRev), res);
		}
		return res;
	}

	static Node merge (Node n1, Node n2) {
		Node res = new Node();
		res.dp.x = n1.dp.x * n2.percent;
		res.dp.y = n1.dp.y * n2.percent;
		Point rotated = rotate(n2.dp, -n1.rotate);
		res.dp.x += rotated.x;
		res.dp.y += rotated.y;
		res.percent = n1.percent * n2.percent;
		res.rotate = n1.rotate + n2.rotate;
		return res;
	}

	static void build (int n, int l, int r) {
		if (l == r) {
			tree1[n] = a[toIndex[l]];
			tree2[n] = a[toIndex[l]];
			return;
		}
		int mid = (l + r) >> 1;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		tree1[n] = merge(tree1[n << 1], tree1[n << 1 | 1]);
		tree2[n] = merge(tree2[n << 1 | 1], tree2[n << 1]);
	}

	static Node query (int n, int l, int r, int ql, int qr, boolean rev) {
		if (l == ql && r == qr)
			return rev ? tree2[n] : tree1[n];
		int mid = (l + r) >> 1;
		if (qr <= mid)
			return query(n << 1, l, mid, ql, qr, rev);
		else if (ql > mid)
			return query(n << 1 | 1, mid + 1, r, ql, qr, rev);
		Node n1 = query(n << 1, l, mid, ql, mid, rev);
		Node n2 = query(n << 1 | 1, mid + 1, r, mid + 1, qr, rev);
		return rev ? merge(n2, n1) : merge(n1, n2);
	}

	static void update (int n, int l, int r, int x) {
		if (x == l && x == r) {
			tree1[n] = a[toIndex[l]];
			tree2[n] = a[toIndex[l]];
			return;
		}
		int mid = (l + r) >> 1;
		if (x <= mid)
			update(n << 1, l, mid, x);
		else
			update(n << 1 | 1, mid + 1, r, x);
		tree1[n] = merge(tree1[n << 1], tree1[n << 1 | 1]);
		tree2[n] = merge(tree2[n << 1 | 1], tree2[n << 1]);
	}

	static void hld (int i, int par) {
		if (head[currChain] == -1)
			head[currChain] = i;
		chain[i] = currChain;
		toIndex[currPos] = i;
		chainPos[i] = currPos++;

		int maxIndex = -1;
		for (int j : adj.get(i))
			if (j != par && (maxIndex == -1 || size[maxIndex] < size[j]))
				maxIndex = j;
		if (maxIndex != -1)
			hld(maxIndex, i);
		for (int j : adj.get(i))
			if (j != par && j != maxIndex) {
				currChain++;
				hld(j, i);
			}
	}

	static int dfs (int i, int par, int d) {
		parent[i] = par;
		depth[i] = d;
		size[i] = 1;
		for (int j : adj.get(i))
			if (j != par)
				size[i] += dfs(j, i, d + 1);
		return size[i];
	}

	static Point rotate (Point p, double angle) {
		double sin = Math.sin(angle / 180 * Math.PI);
		double cos = Math.cos(angle / 180 * Math.PI);
		double x = p.x * cos - p.y * sin;
		double y = p.x * sin + p.y * cos;
		return new Point(x, y);
	}

	static Node readNode () throws IOException {
		char c = readCharacter();
		if (c == 'R')
			return new Node(readInt(), 0, 0, 1);
		else if (c == 'T')
			return new Node(0, readInt(), readInt(), 1);
		else {
			double x = readInt();
			double y = readInt();
			double p = readInt() / 100.0d;
			x *= (1 - p);
			y *= (1 - p);
			return new Node(0, x, y, p);
		}
	}

	static class Point {
		double x, y;

		Point (double x, double y) {
			this.x = x;
			this.y = y;
		}
	}

	static class Node {
		double rotate;
		Point dp;
		double percent;

		Node () {
			this(0, 0, 0, 1);
		}

		Node (double rotate, double dx, double dy, double percent) {
			this.rotate = rotate;
			this.dp = new Point(dx, dy);
			this.percent = percent;
		}

		public String toString () {
			return String.format("(%f, %f) with %f and %f", dp.x, dp.y, rotate, percent);
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
