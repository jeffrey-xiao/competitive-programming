package coci;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class COCI_2014_KAMP {

	static BufferedReader br;
	static OutputStream ps;
	static StringTokenizer st;

	static final int SIZE = 500001;

	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int n, k;
	static long total = 0;
	static boolean[] inTree = new boolean[SIZE];
	static long[] max1 = new long[SIZE];
	static long[] max2 = new long[SIZE];
	static long[] maxi = new long[SIZE];
	static long[] maxChain = new long[SIZE];
	static long[] min = new long[SIZE];

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new BufferedOutputStream(System.out);
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();
		int k = readInt();

		for (int i = 0; i < SIZE; i++) {
			adj.add(new ArrayList<Edge>());
			max1[i] = max2[i] = Integer.MIN_VALUE;
			min[i] = -1;
		}
		for (int i = 0; i < n - 1; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		for (int i = 0; i < k; i++)
			inTree[readInt() - 1] = true;
		for (int i = 0; i < n; i++) {
			if (inTree[i]) {
				buildTree(i, -1);
				break;
			}
		}
		for (int i = 0; i < n; i++) {
			if (inTree[i]) {
				dfs1(i, -1);
				dfs2(i, -1, 0);
				break;
			}
		}
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < n; i++) {
			if (inTree[i]) {
				q.offer(i);
				min[i] = 0;
			}
		}
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (Edge next : adj.get(curr)) {
				if (min[next.dest] != -1)
					continue;
				min[next.dest] = min[curr] + next.cost;
				maxChain[next.dest] = maxChain[curr];
				q.offer(next.dest);
			}
		}
		StringBuilder out = new StringBuilder("");
		for (int i = 0; i < n; i++) {
			// System.out.println(inTree[i] + " " + maxChain[i]);
			out.append(total * 2 - maxChain[i] + min[i] + "\n");
		}
		ps.write(out.toString().getBytes());
		ps.close();
	}

	static void dfs2 (int i, int prev, long prevV) {
		maxChain[i] = Math.max(prevV, max1[i]);
		// System.out.println(i + " " + max1[i] + " " + max2[i] + " " +
		// maxChain[i] + " " + prevV + " " + maxi[i]);
		for (Edge next : adj.get(i)) {
			if (next.dest == prev || !inTree[next.dest])
				continue;
			if (maxi[i] == next.dest)
				dfs2(next.dest, i, Math.max(prevV, max2[i]) + next.cost);
			else
				dfs2(next.dest, i, Math.max(prevV, max1[i]) + next.cost);
		}
	}

	static void dfs1 (int i, int prev) {
		boolean hasNext = false;
		for (Edge next : adj.get(i)) {
			if (!inTree[next.dest] || next.dest == prev)
				continue;
			hasNext = true;
			dfs1(next.dest, i);
			adjust(max1[next.dest] + next.cost, i, next.dest);
		}
		if (!hasNext)
			max1[i] = 0;
	}

	static void adjust (long v, int i, int j) {
		if (v >= max1[i]) {
			max2[i] = max1[i];
			max1[i] = v;
			maxi[i] = j;
		} else if (v > max2[i]) {
			max2[i] = v;
		}
	}

	static boolean buildTree (int i, int prev) {
		boolean has = false;
		for (Edge next : adj.get(i)) {
			if (next.dest == prev)
				continue;
			if (buildTree(next.dest, i)) {
				has = true;
				total += next.cost;
			}

		}
		return inTree[i] |= has;
	}

	static class Edge {
		int cost, dest;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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