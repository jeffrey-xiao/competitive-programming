package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class MinCostMaxFlowSuccessiveShortestPathPotential {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, src, sink;

	static Edge[] e;
	static int[] last;
	static int cnt = 0;

	static int[] phi;
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
		e = new Edge[m * 2];
		dist = new int[n];
		prev = new int[n];
		index = new int[n];
		phi = new int[n];

		for (int i = 0; i < n; i++)
			last[i] = -1;

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
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
		bellmanFord();
		reduceCost();
		while (dijkstra()) {
			for (int i = 0; i < n; i++)
				phi[i] = dist[i];
			reduceCost();
			int aug = 1 << 30;
			int curr = sink;
			while (prev[curr] != -1) {
				aug = Math.min(aug, e[index[curr]].flow);
				curr = prev[curr];
			}
			flow += aug;
			curr = sink;
			while (prev[curr] != -1) {
				e[index[curr]].flow -= aug;
				e[index[curr] ^ 1].flow += aug;
				cost += aug * e[index[curr]].origCost;
				curr = prev[curr];
			}
		}
		return cost;
	}

	static void reduceCost () {
		for (int i = 0; i < cnt; i += 2) {
			e[i].cost += phi[e[i].orig] - phi[e[i].dest];
			e[i ^ 1].cost = 0;
		}
	}

	static void bellmanFord () {
		for (int i = 0; i < n; i++)
			phi[i] = 1 << 25;
		phi[src] = 0;
		for (int j = 0; j < n - 1; j++)
			for (int i = 0; i < cnt; i++)
				if (e[i].flow > 0)
					phi[e[i].dest] = Math.min(phi[e[i].dest], phi[e[i].orig] + e[i].cost);

	}

	static boolean dijkstra () {
		for (int i = 0; i < n; i++) {
			dist[i] = 1 << 30;
			prev[i] = -1;
			index[i] = -1;
		}
		dist[src] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(src, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (int next = last[curr.index]; next != -1; next = e[next].last) {
				if (e[next].flow == 0 || dist[e[next].dest] <= dist[curr.index] + e[next].cost)
					continue;
				dist[e[next].dest] = dist[curr.index] + e[next].cost;
				prev[e[next].dest] = curr.index;
				index[e[next].dest] = next;
				pq.offer(new Vertex(e[next].dest, dist[e[next].dest]));
			}
		}
		return dist[sink] != 1 << 30;
	}

	static void addEdge (int x, int y, int costxy, int costyx, int flowxy, int flowyx) {
		e[cnt] = new Edge(x, y, costxy, flowxy, last[x]);
		last[x] = cnt++;
		e[cnt] = new Edge(y, x, costyx, flowyx, last[y]);
		last[y] = cnt++;
	}

	static class Vertex implements Comparable<Vertex> {
		int index, cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex v) {
			return cost - v.cost;
		}
	}

	static class Edge {
		int orig, dest, origCost, cost, flow, last;

		Edge (int orig, int dest, int cost, int flow, int last) {
			this.orig = orig;
			this.dest = dest;
			this.origCost = cost;
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
