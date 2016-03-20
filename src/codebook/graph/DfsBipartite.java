/*
 * An application of depth-first search (DFS) to determine if a given graph is bipartite
 *
 * Time complexity: O(V + E)
 */

package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class DfsBipartite {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	static int[] color;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());

		color = new int[n];

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
		}
		for (int i = 0; i < n; i++)
			if (color[i] == 0)
				dfs(i, 1);
		out.println("The graph is bipartite!");
		out.close();
	}

	static void dfs (int i, int c) {
		color[i] = c;
		for (int j : adj.get(i)) {
			if (color[j] == 0)
				dfs(j, -c);
			else if (color[j] == c) {
				out.println("The graph is not bipartite!");
				out.close();
				System.exit(0);
			}
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
