package codebook.graph.network;

import java.util.*;
import java.io.*;

public class MinCutStoerWagner {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n;
	static int[][] adj;
	static int[] weight;
	static boolean[] used;
	static boolean[] inContraction;

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		adj = new int[n][n];
		weight = new int[n];
		used = new boolean[n];
		inContraction = new boolean[n];

		for (int i = 0; i < n; i++)
			for (int j = 0; j < n; j++)
				adj[i][j] = readInt();

		out.println(getMinCut());
		out.close();
	}

	static int getMinCut () {
		int minCut = 1 << 30;
		for (int v = n - 1; v >= 0; v--) {
			// initializing the weights of each node -- a weight of a node is the sum of the connects to the growing subgraph
			// the initial subgraph will contain node 0
			for (int i = 1; i < n; i++) {
				weight[i] = adj[0][i];
				used[i] = inContraction[i];
			}
			int prev = 0, curr = 0;
			for (int sz = 1; sz <= v; sz++) {
				prev = curr;
				curr = -1;
				// finding the node that is most tightly connected to the current subgraph
				for (int i = 1; i < n; i++)
					if (!used[i] && (curr == -1 || (weight[i] > weight[curr])))
						curr = i;
				// if we haven't added every vertex to the subgraph, then we just update the weights
				// else we contract the last two added vertexes and update the minimum cut
				if (sz != v) {
					for (int i = 0; i < n; i++)
						weight[i] += adj[curr][i];
					used[curr] = true;
				} else {
					for (int i = 0; i < n; i++)
						adj[prev][i] = adj[i][prev] += adj[i][curr];
					inContraction[curr] = true;
					minCut = Math.min(minCut, weight[curr]);
				}
			}
		}
		return minCut;
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
