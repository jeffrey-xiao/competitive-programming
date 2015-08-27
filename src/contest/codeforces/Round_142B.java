package contest.codeforces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Round_142B {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static ArrayList<TreeSet<Interval>> p = new ArrayList<TreeSet<Interval>>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		n = readInt();
		m = readInt();
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Edge>());
			p.add(new TreeSet<Interval>());
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		for (int i = 0; i < n; i++) {
			int len = readInt();
			int start = -2;
			int last = -2;
			for (int j = 0; j < len; j++) {
				int in = readInt();
				if (start == -2) {
					start = in;
					last = in;
				} else if (last == in - 1) {
					last = in;
				} else {
					p.get(i).add(new Interval(start, last));
					// System.out.println(start + " " + last);
					start = last = in;
				}
			}
			if (len > 0) {
				p.get(i).add(new Interval(start, last));
				// System.out.println(start + " " + last);
			}
		}
		int[] min = new int[n];
		for (int i = 0; i < n; i++)
			min[i] = 1 << 30;
		min[0] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(0, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			Interval i = p.get(curr.index).floor(new Interval(curr.cost, 0));
			if (i != null)
				curr.cost = Math.max(curr.cost, i.r + 1);
			for (Edge next : adj.get(curr.index)) {
				int nextTime = curr.cost + next.cost;
				if (min[next.dest] <= nextTime)
					continue;
				min[next.dest] = nextTime;
				pq.offer(new Vertex(next.dest, nextTime));
			}
		}
		System.out.println(min[n - 1] == 1 << 30 ? -1 : min[n - 1]);
		pr.close();
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

	static class Interval implements Comparable<Interval> {
		int l, r;

		Interval (int l, int r) {
			this.l = l;
			this.r = r;
		}

		@Override
		public int compareTo (Interval o) {
			return l - o.l;
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
