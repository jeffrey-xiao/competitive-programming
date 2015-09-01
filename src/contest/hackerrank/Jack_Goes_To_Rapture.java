package contest.hackerrank;

import java.util.*;
import java.io.*;

public class Jack_Goes_To_Rapture {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int e = readInt();
		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		int[] dist = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
			dist[i] = 1 << 30;
		}
		for (int i = 0; i < e; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		dist[0] = 1;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(0, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (Edge edge : adj.get(curr.index)) {
				if (dist[edge.dest] <= curr.cost + Math.max(0, edge.cost - curr.cost))
					continue;
				dist[edge.dest] = curr.cost + Math.max(0, edge.cost - curr.cost);
				pq.offer(new Vertex(edge.dest, dist[edge.dest]));
			}
		}
		out.println(dist[n-1] == 1 << 30 ? "NO PATH EXISTS" : dist[n-1]);
		out.close();
	}

	static class Edge {
		int dest, cost;
		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
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

