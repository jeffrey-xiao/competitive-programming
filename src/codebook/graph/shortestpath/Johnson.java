/*
 * Johnson's algorithm solves the all-pairs shortest path (APSP) problems. It works with negative edge weights by using the Bellman-Ford algorithm to reweight the vertices.
 * After reweighting the edges, the algorithm runs Djikstra's algorithm on every vertex.
 *
 * Time complexity: O(V^2 log V + EV)
 */

package codebook.graph.shortestpath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Johnson {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, q;
	static ArrayList<ArrayList<Edge>> adj;
	static int[] h;
	static int[][] dist;
	static PriorityQueue<Vertex> pq;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		q = readInt();

		adj = new ArrayList<ArrayList<Edge>>();
		h = new int[n + 1];
		dist = new int[n + 1][n + 1];
		
		for (int i = 0; i <= n; i++) {
			adj.add(new ArrayList<Edge>());
			h[i] = 1 << 29;
		}

		for (int i = 1; i <= n; i++)
			adj.get(0).add(new Edge(i, 0));

		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
		}

		h[0] = 0;
		
		for (int i = 0; i < n; i++)
			for (int j = 0; j <= n; j++)
				for (Edge e : adj.get(j))
					if (h[e.dest] > e.cost + h[j])
						h[e.dest] = e.cost + h[j];

		for (int i = 0; i <= n; i++)
			for (Edge e : adj.get(i))
				if (h[e.dest] > e.cost + h[i]) {
					out.println("MIN COST CYCLE DETECTED");
					out.close();
					return;
				}
		
		for (int i = 1; i <= n; i++)
			dist[i] = getPath(i);
		
		for (int i = 0; i < q; i++) {
			int a = readInt();
			int b = readInt();
			out.println(dist[a][b] - h[a] + h[b]);
		}
		
		out.close();
	}

	static int[] getPath (int src) {
		int[] dist = new int[n + 1];
		
		for (int i = 0; i <= n; i++)
			dist[i] = 1 << 29;
		dist[src] = 0;
		
		pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(src, 0));
		
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			
			for (Edge next : adj.get(curr.index)) {
				if (dist[next.dest] <= curr.cost + next.cost + h[curr.index] - h[next.dest])
					continue;
				
				dist[next.dest] = curr.cost + next.cost + h[curr.index] - h[next.dest];
				pq.offer(new Vertex(next.dest, dist[next.dest]));
			}
		}
		
		return dist;
	}

	static class Vertex implements Comparable<Vertex> {
		int index, cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex o) {
			return cost - o.cost;
		}
	}

	static class Edge {
		int dest, cost;

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
