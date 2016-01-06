/*
 * The Bron-Kerbosch algorithm is an algorithm for finding maximal cliques in an undirected graph using recursive backtracking.
 * A clique is defined to be a subset of a graph such that every pair of vertices in the subset are connected by an edge.
 * 
 * Time complexity: O(3^(n/3))
 */

package codebook.graph;

import java.util.*;
import java.io.*;

public class BronKerbosch {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static boolean[][] adj;
	static int[] w;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		
		adj = new boolean[n][n];
		
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			adj[a][b] = adj[b][a] = true;
		}
		
		w = new int[n];
		for (int i = 0; i < n; i++)
			w[i] = readInt();

		out.printf("Unweighted maximal clique: %d\n", solveUnweighted(n));
		out.printf("Weighted maximal clique: %d\n", solveWeighted(n));
		out.close();
	}

	static int solveWeighted (int nodes) {
		return solveWeighted (nodes, 0, (1 << nodes) - 1, 0);
	}
	
	static int solveWeighted (int nodes, int curr, int pool, int excl) {
		if (pool == 0 && excl == 0) {
			int cnt = 0;
			for (int i = 0; i < nodes; i++)
				if ((curr & 1 << i) > 0)
					cnt += w[i];
			return cnt;
		}
		int res = 0;
		int j = 0;
		for (int i = 0; i < nodes; i++)
			if ((pool & 1 << i) > 0 || (excl & 1 << i) > 0)
				j = i;
		
		for (int i = 0; i < nodes; i++) {
			if ((pool & 1 << i) == 0 || adj[i][j])
				continue;
			int ncurr = curr, npool = 0, nexcl = 0;
			ncurr |= 1 << i;
			
			for (int k = 0; k < nodes; k++) {
				if (adj[i][k]) {
					npool |= pool & 1 << k;
					nexcl |= excl & 1 << k;
				}
			}
			res = Math.max(res, solveWeighted(nodes, ncurr, npool, nexcl));
			
			pool &= ~(1 << i);
			excl |= 1 >> i;
		}
		return res;
	}
	
	static int solveUnweighted (int nodes) {
		return solveUnweighted (nodes, 0, (1 << nodes) - 1, 0);
	}
	
	static int solveUnweighted (int nodes, int curr, int pool, int excl) {
		if (pool == 0 && excl == 0) {
			int cnt = 0;
			for (int i = 0; i < nodes; i++)
				if ((curr & 1 << i) > 0)
					cnt++;
			return cnt;
		}
		int res = 0;
		int j = 0;
		for (int i = 0; i < nodes; i++)
			if ((pool & 1 << i) > 0 || (excl & 1 << i) > 0)
				j = i;
		
		for (int i = 0; i < nodes; i++) {
			if ((pool & 1 << i) == 0 || adj[i][j])
				continue;
			int ncurr = curr, npool = 0, nexcl = 0;
			ncurr |= 1 << i;
			
			for (int k = 0; k < nodes; k++) {
				if (adj[i][k]) {
					npool |= pool & 1 << k;
					nexcl |= excl & 1 << k;
				}
			}
			res = Math.max(res, solveUnweighted(nodes, ncurr, npool, nexcl));
			
			pool &= ~(1 << i);
			excl |= 1 >> i;
		}
		return res;
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

