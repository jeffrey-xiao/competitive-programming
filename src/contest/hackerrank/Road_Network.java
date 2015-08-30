package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Road_Network {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static ArrayList<ArrayList<Edge>> adj;
	static int[] dist;
	static int[][] cost;
	static int n, m;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		dist = new int[n];
		cost = new int[n][n];
		adj = new ArrayList<ArrayList<Edge>>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Edge>());
		for (int i = 0; i < m; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			int c = readInt();
			cost[a][b] = cost[b][a] = c;
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}

		long ans = 1;
		if (n <= 50) {
			for (int i = 0; i < n; i++) {
				for (int j = i+1; j < n; j++) {
					for (int k = 0; k < adj.size(); k++)
						for (Edge e : adj.get(k))
							cost[k][e.dest] = cost[e.dest][k] = e.cost;
					ans  = ans * getFlow(i, j) % 1000000007;
				}
			}
		} else {
			for (int i = 0; i < n; i++) {
				for (int j = i + 1; j < n; j++) {
					long src = 0;
					long sink = 0;
					for (Edge e : adj.get(i))
						src += e.cost;
					for (Edge e : adj.get(j))
						sink += e.cost;
					ans = ans * Math.min(src, sink) % 1000000007;
				}
			}
		}
		System.out.println(ans);
	}

	static long getFlow (int a, int b) {
		long res = 0;
		while (constructGraph(a, b)) {
			while (true) {
				int flow = getPath(a, b, 1 << 30);
				if (flow == 0)
					break;
				res += flow;
			}
		}
		return res;
	}
	static int getPath (int a, int b, int flow) {
		if (a == b)
			return flow;
		for (Edge e : adj.get(a)) {
			if (cost[a][e.dest] > 0 && dist[a] == dist[e.dest] - 1) {
				int ret = getPath(e.dest, b, Math.min(flow, cost[a][e.dest]));
				if (ret > 0) {
					cost[a][e.dest] -= ret;
					cost[e.dest][a] += ret;
					return ret;
				}
			}
		}
		return 0;
	}
	static boolean constructGraph (int a, int b) {
		for (int i = 0; i < n; i++)
			dist[i] = -1;
		dist[a] = 0;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(a);
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (Edge e : adj.get(curr)) {
				if (cost[curr][e.dest] > 0 && dist[e.dest] == -1) {
					dist[e.dest] = dist[curr] + 1;
					q.offer(e.dest);
				}
			}
		}
		return dist[b] != -1;
	}
	
	static class Edge{
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

