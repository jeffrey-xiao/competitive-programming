package ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class IOI_2011_Crocodile_Underground_City {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int k = readInt();
		ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < m; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		int[] min = new int[n];
		for (int x = 0; x < n; x++) {
			min[x] = 1000000000;
		}
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		for (int x = 0; x < k; x++) {
			int a = readInt();
			min[a] = 0;
			for (Edge e : adj.get(a)) {
				pq.offer(new Vertex(e.dest, e.cost));
			}
		}
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			ArrayList<Integer> a = new ArrayList<Integer>();
			for (Edge next : adj.get(curr.index)) {
				a.add(min[next.dest] + next.cost);
			}
			Collections.sort(a);
			if (a.size() >= 2 && min[curr.index] > a.get(1)) {
				min[curr.index] = a.get(1);
			} else {
				continue;
			}
			for (Edge next : adj.get(curr.index)) {
				pq.offer(new Vertex(next.dest, min[curr.index] + next.cost));
			}
		}
		System.out.println(min[0]);
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
