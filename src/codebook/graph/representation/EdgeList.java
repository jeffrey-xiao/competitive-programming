/*
 * An edge list is a data structure that represents a graph. Good for sparse graphs.
 */

package codebook.graph.representation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class EdgeList {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Edge[] edges;
	static int n, m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		edges = new Edge[m];

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			edges[i] = new Edge(a, b, c);
		}
		for (int i = 0; i < m; i++)
			out.print(edges[i].orig + " IS CONNECTED TO: " + edges[i].dest);

		out.close();
	}

	static class Edge {
		int orig, dest, cost;

		Edge (int orig, int dest, int cost) {
			this.orig = orig;
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
