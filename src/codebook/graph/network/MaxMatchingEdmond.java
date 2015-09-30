package codebook.graph.network;

import java.util.*;
import java.io.*;

public class MaxMatchingEdmond {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static boolean[] mark, used;
	static int[] match, par, id;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		
		for (int j = 0; j < n; j++) {
			int a = readInt()-1;
			int b = readInt()-1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		out.println(getMaxMatching());
		out.close();
	}

	static int getMaxMatching () {
		match = new int[n];
		par = new int[n];
		id = new int[n];
		used = new boolean[n];
		
		Arrays.fill(match, -1);
		
		for (int i = 0; i < n; i++) {
			if (match[i] == -1) {
				int v = getAugmentingPath(i);
				while (v != -1) {
					int pv = par[v];
					int ppv = match[pv];
					match[v] = pv;
					match[pv] = v;
					v = ppv;
				}
			}
		}
		int res = 0;
		for (int i = 0; i < n; i++)
			if (match[i] != -1)
				res++;
		return res/2;
	}
	static int getAugmentingPath (int src) {
		Arrays.fill(par, -1);
		used = new boolean[n];
		for (int i = 0; i < n; i++)
			id[i] = i;
		used[src] = true;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(src);
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int next : adj.get(curr)) {
				if (id[curr] == id[next] || match[curr] == next)
					continue;
				if (next == src || (match[next] != -1 && par[match[next]] != -1)) {
					int newBase = lca(curr, next);
					boolean[] blossom = new boolean[n];
					markPath(blossom, curr, newBase, next);
					markPath(blossom, next, newBase, curr);
					// changing the base of the blossom because the edges of the blossom are modified
					for (int i = 0; i < n; i++) {
						if (blossom[id[i]]) {
							id[i] = newBase;
							if (!used[i]) {
								used[i] = true;
								q.offer(i);
							}
						}
					}
				} else if (par[next] == -1) {
					par[next] = curr;
					if (match[next] == -1)
						return next;
					next = match[next];
					used[next] = true;
					q.offer(next);
				}
			}
		}
		return -1;
	}	
	static void markPath (boolean[] blossom, int i, int b, int j) {
		for (; id[i] != b; i = par[match[i]]) {
			blossom[id[i]] = blossom[id[match[i]]] = true;
			par[i] = j;
			j = match[i];
		}
	}
	static int lca (int i, int j) {
		boolean[] v = new boolean[n];
		while (true) {
			i = id[i];
			used[i] = true;
			if (match[i] == -1)
				break;
			i = par[match[i]];
		}
		while (true) {
			j = id[j];
			if (v[j])
				return j;
			j = par[match[j]];
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

