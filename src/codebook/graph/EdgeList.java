/*
 * Implementation of an edge list for representing graphs. 
 * Can be extended to directed graphs by only adding one edge in addEdge ()
 * Notice that when using this implementation, the reverse edge of edges[i] is edges[i ^ 1]
 */

package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static Edge[] edges;
	static int[] last;
	static int cnt = 0;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		int n = readInt();
		int m = readInt();
		
		edges = new Edge[m*2];
		last = new int[n];
		
		for (int i = 0; i < n; i++)
			last[i] = -1;
			
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			addEdge(a, b, c, c);
		}
		for (int i = 0; i < n; i++) {
			out.print(i + " IS CONNECTED TO: ");
			for (int j = last[i]; j != -1; j = edges[j].lastEdge)
				out.print(edges[j].dest + " ");
			out.println();
		}
		
		out.close();
	}
	static void addEdge (int a, int b, int ab, int ba) {
		edges[cnt] = new Edge(b, ab, last[a]);
		last[a] = cnt++;
		edges[cnt] = new Edge(a, ba, last[b]);
		last[b] = cnt++;
	}
	static class Edge {
		int dest, cost, lastEdge;
		Edge (int dest, int cost, int lastEdge) {
			this.dest = dest;
			this.cost = cost;
			this.lastEdge = lastEdge;
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
