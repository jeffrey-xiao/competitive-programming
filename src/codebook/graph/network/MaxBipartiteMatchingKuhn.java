/*
 * Kuhn's algorithm finds the maximal matching of a bipartite graph.
 * 
 * Time complexity: O(V^3)
 */

package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class MaxBipartiteMatchingKuhn {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int leftSize, rightSize;
	static int edges;
	static boolean[][] adj;
	static boolean[] v;
	static int[] prev;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		leftSize = readInt();
		rightSize = readInt();
		edges = readInt();
		adj = new boolean[leftSize][rightSize];
		prev = new int[rightSize];

		for (int i = 0; i < rightSize; i++)
			prev[i] = -1;

		for (int i = 0; i < edges; i++)
			adj[readInt() - 1][readInt() - 1] = true;

		int ans = 0;
		for (int i = 0; i < leftSize; i++) {
			v = new boolean[rightSize];
			ans += match(i) ? 1 : 0;
		}
		out.println(ans);
		out.close();
	}

	private static boolean match (int i) {
		for (int j = 0; j < rightSize; j++) {
			if (adj[i][j] && !v[j]) {
				v[j] = true;
				if (prev[j] == -1 || match(prev[j])) {
					prev[j] = i;
					return true;
				}
			}
		}
		return false;
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
