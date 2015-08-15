package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Esdeath {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static State[] nodes;
	static boolean[] v;
	static boolean[] tv;
	static int count, n, k;
	static int[] index;

	public static void main (String[] args) throws IOException {
		n = readInt();
		k = readInt();
		nodes = new State[n];
		index = new int[n];
		for (int x = 0; x < n; x++) {
			adj.add(new ArrayList<Integer>());
			nodes[x] = new State(x, 0);
		}
		for (int x = 0; x < n - 1; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		getDepth(0, 1);
		Arrays.sort(nodes);
		for (int x = 0; x < n; x++) {
			// System.out.println(nodes[x].id + " " +nodes[x].depth + " " +
			// nodes[x].parent);
			index[nodes[x].id] = x;
		}
		int lo = 0, hi = 2500;

		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPossible(mid) <= k) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println(lo);
		// System.out.println(isPossible(3));
	}

	private static int isPossible (int r) {
		tv = new boolean[n];
		// System.out.println("RADIUS " + r);
		int count = 0;
		for (int x = 0; x < n; x++) {
			if (!tv[nodes[x].id]) {
				v = new boolean[n];
				// System.out.println("START " + (nodes[x].id+1));
				int i = findCenter(nodes[x].id, r);
				// System.out.println("end: " + (i+1));
				markNodes(i, r);
				count++;
			}
		}
		return count;
	}

	private static void markNodes (int i, int r) {
		if (r == -1)
			return;
		v[i] = true;
		tv[i] = true;
		for (Integer next : adj.get(i))
			if (!v[next])
				markNodes(next, r - 1);
	}

	private static int findCenter (int x, int r) {
		// System.out.println("HERE " + (x+1) + " " +
		// (nodes[index[x]].parent+1));
		if (r == 0)
			return x;
		return findCenter(nodes[index[x]].parent, r - 1);
	}

	private static void getDepth (int i, int d) {
		nodes[i].depth = d++;
		for (Integer next : adj.get(i))
			if (nodes[next].depth == 0) {
				getDepth(next, d);
				nodes[next].parent = i;
			}
	}

	static class State implements Comparable<State> {
		int id, depth, parent;

		State (int id, int depth) {
			this.id = id;
			this.depth = depth;
		}

		@Override
		public int compareTo (State o) {
			return o.depth - depth;
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
