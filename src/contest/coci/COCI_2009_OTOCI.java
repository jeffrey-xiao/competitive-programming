package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2009_OTOCI {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[] val;
	// DSU
	static int[] id, size;
	// HLD
	static int[] parent, chainIndex, head, chain, subsize, depth;

	static Queue<Integer> unusedChains = new ArrayDeque<Integer>();
	static Queue<Integer> unusedIndex = new ArrayDeque<Integer>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int chainNo = -1;
	static int index = 1;
	static int sn;
	static int top;
	static int n;
	static int[][] seg;
	static int[] chainSize;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		sn = (int) (Math.sqrt(n));
		id = new int[n + 1];
		size = new int[n + 1];
		val = new int[n + 1];
		seg = new int[n][1];
		parent = new int[n + 1];
		chainSize = new int[n + 1];
		chainIndex = new int[n + 1];
		head = new int[n + 1];
		chain = new int[n + 1];
		subsize = new int[n + 1];
		depth = new int[n + 1];
		adj.add(new ArrayList<Integer>());
		chainSize[0] = 1;
		for (int i = 1; i <= n; i++) {
			val[i] = readInt();
			chainSize[i] = 1;
			adj.add(new ArrayList<Integer>());
			id[i] = i;
			size[i] = 1;
			head[i] = -1;
			chainNo++;
			dfs(i, 0, -1);
			index = 1;
			hld(i, -1, true);
		}
		int q = readInt();
		for (int i = 0; i < q; i++) {
			String command = next();
			int a = readInt();
			int b = readInt();
			if (command.equals("excursion")) {
				if (find(a) != find(b))
					out.println("impossible");
				else {
					int max = Math.max(getLen(a), getLen(b));
					if (max > sn) {
						// remaking HLD
						dfs(top, 0, -1);
						getUnused(top, -1, true);
						chainNo = unusedChains.poll();
						index = 1;
						hld(top, -1, true);
					}
					out.println(getSum(a, b));
				}
			} else if (command.equals("bridge")) {
				if (find(a) == find(b))
					out.println("no");
				else {
					out.println("yes");
					int prevA = find(a);
					merge(a, b);
					adj.get(a).add(b);
					adj.get(b).add(a);
					if (find(a) == prevA) {
						dfs(b, depth[a] + 1, a);
						getUnused(b, a, true);
						chainNo = unusedChains.poll();
						index = 1;
						hld(b, a, true);
					} else {
						dfs(a, depth[b] + 1, b);
						getUnused(a, b, true);
						chainNo = unusedChains.poll();
						index = 1;
						hld(a, b, true);
					}
				}
			} else if (command.equals("penguins")) {
				val[a] = b;
				update(seg[chain[a]], 1, 1, chainSize[chain[a]], chainIndex[a], b);
			}
		}

		out.close();
	}

	static int getSum (int i, int j) {
		int res = 0;
		while (chain[i] != chain[j]) {
			if (depth[head[chain[i]]] < depth[head[chain[j]]]) {
				assert (chainIndex[head[chain[j]]] <= chainIndex[j]);
				res += query(seg[chain[j]], 1, 1, chainSize[chain[j]], chainIndex[head[chain[j]]], chainIndex[j]);
				j = parent[head[chain[j]]];
			} else {
				assert (chainIndex[head[chain[i]]] <= chainIndex[i]);
				res += query(seg[chain[i]], 1, 1, chainSize[chain[i]], chainIndex[head[chain[i]]], chainIndex[i]);
				i = parent[head[chain[i]]];
			}
		}
		if (depth[i] < depth[j])
			return res + query(seg[chain[i]], 1, 1, chainSize[chain[i]], chainIndex[i], chainIndex[j]);
		return res + query(seg[chain[j]], 1, 1, chainSize[chain[j]], chainIndex[j], chainIndex[i]);
	}

	static int getLen (int a) {
		int cnt = 0;
		while (parent[head[chain[a]]] != -1) {
			a = parent[head[chain[a]]];
			cnt++;
		}
		top = head[chain[a]];
		return cnt;
	}

	static void getUnused (int i, int prev, boolean newChain) {
		if (newChain) {
			unusedChains.offer(chain[i]);
			seg[chain[i]] = new int[0];
		}
		for (int j : adj.get(i))
			if (j != prev)
				getUnused(j, i, chain[i] != chain[j]);
	}

	static void hld (int i, int prev, boolean newChain) {
		if (newChain)
			head[chainNo] = i;
		chainIndex[i] = index++;
		chain[i] = chainNo;
		int maxIndex = -1;
		for (int j : adj.get(i))
			if (j != prev && (maxIndex == -1 || subsize[maxIndex] < subsize[j]))
				maxIndex = j;
		boolean isEnd = true;
		if (maxIndex != -1) {
			hld(maxIndex, i, false);
			isEnd = false;
		}
		for (int j : adj.get(i))
			if (j != prev && j != maxIndex) {
				isEnd = false;
				chainNo = unusedChains.poll();
				index = 1;
				hld(j, i, true);
			}
		if (isEnd) {
			seg[chain[i]] = new int[chainIndex[i] * 4];
			chainSize[chain[i]] = chainIndex[i];
		}
		update(seg[chain[i]], 1, 1, chainSize[chain[i]], chainIndex[i], val[i]);
	}

	static void dfs (int i, int d, int prev) {
		parent[i] = prev;
		subsize[i] = 1;
		depth[i] = d;
		for (int j : adj.get(i))
			if (j != prev)
				dfs(j, d + 1, i);
	}

	static void update (int[] tree, int n, int lo, int hi, int i, int val) {
		if (lo == i && i == hi) {
			tree[n] = val;
			return;
		}
		int mid = (lo + hi) >> 1;
		if (i <= mid)
			update(tree, n << 1, lo, mid, i, val);
		else
			update(tree, n << 1 | 1, mid + 1, hi, i, val);
		tree[n] = tree[n << 1] + tree[n << 1 | 1];
	}

	static int query (int[] tree, int n, int lo, int hi, int qlo, int qhi) {
		if (lo == qlo && hi == qhi)
			return tree[n];
		int mid = (lo + hi) >> 1;
		if (qhi <= mid)
			return query(tree, n << 1, lo, mid, qlo, qhi);
		else if (qlo > mid)
			return query(tree, n << 1 | 1, mid + 1, hi, qlo, qhi);
		return query(tree, n << 1, lo, mid, qlo, mid) + query(tree, n << 1 | 1, mid + 1, hi, mid + 1, qhi);
	}

	static int find (int i) {
		return i == id[i] ? i : (id[i] = find(id[i]));
	}

	static void merge (int i, int j) {
		int rx = find(i);
		int ry = find(j);
		if (size[rx] > size[ry]) {
			size[rx] += size[ry];
			id[ry] = rx;
		} else {
			size[ry] += size[rx];
			id[rx] = ry;
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
