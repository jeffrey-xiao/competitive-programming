package codebook.graph.network;

import java.util.*;
import java.io.*;

public class MinCostMaxFlowSuccessiveShortestPath {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, src, sink;

	static Edge[] e;
	static int[] last;
	static int cnt = 0;

	static int[] prev, dist, index;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		src = readInt() - 1;
		sink = readInt() - 1;

		last = new int[n];
		e = new Edge[m*2];

		for (int i = 0; i < n; i++)
			last[i] = -1;
		
		for (int i = 0; i < m; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			int flow = readInt();
			int cost = readInt();
			addEdge(a, b, cost, -cost, flow, 0);
		}
		out.println(getMinCostMaxFlow());
		out.close();
	}
	@SuppressWarnings ("unused")
	static int getMinCostMaxFlow () {
		int flow = 0;
		int cost = 0;
		while (bellmanFord()) {
			int aug = 1 << 30;
			int curr = sink;
			while (prev[curr] != -1) {
				aug = Math.min(aug, e[index[curr]].flow);
				curr = prev[curr];
			}
			flow += aug;
			cost += aug * dist[sink];
			curr = sink;
			while (prev[curr] != -1) {
				e[index[curr]].flow -= aug;
				e[index[curr] ^ 1].flow += aug;
				curr = prev[curr];
			}
		}
		return cost;
	}
	static boolean bellmanFord () {
		prev = new int[n];
		dist = new int[n];
		index = new int[n];
		for (int i = 0; i < n; i++) {
			prev[i] = -1;
			dist[i] = 1 << 25;
		}
		dist[src] = 0;
		for (int j = 0; j < n-1; j++) {
			for (int i = 0; i < cnt; i++) {
				if (e[i].flow > 0 && dist[e[i].orig] + e[i].cost < dist[e[i].dest]) {
					dist[e[i].dest] = dist[e[i].orig] + e[i].cost;
					index[e[i].dest] = i;
					prev[e[i].dest] = e[i].orig;
				}
			}
		}
		return dist[sink] != 1 << 25;
	}
	static void addEdge (int x, int y, int costxy, int costyx, int flowxy, int flowyx) {
		e[cnt] = new Edge(x, y, costxy, flowxy, last[x]);
		last[x] = cnt++;
		e[cnt] = new Edge(y, x, costyx, flowyx, last[y]);
		last[y] = cnt++;
	}

	static class Edge {
		int orig, dest, cost, flow, last;
		Edge (int orig, int dest, int cost, int flow, int last) {
			this.orig = orig;
			this.dest = dest;
			this.cost = cost;
			this.flow = flow;
			this.last = last;
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

