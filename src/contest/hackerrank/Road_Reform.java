package contest.hackerrank;

import java.io.*;
import java.util.*;

public class Road_Reform {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int n, m;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		for (int i = 0; i <= n; i++)
			adj.add(new ArrayList<Edge>());
		for (int i = 0; i < m; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		long[] dist1 = getPath(1);
		long[] dist2 = getPath(n);
		Arrays.sort(dist2, 1, n+1);
		long[] sum = new long[n+1];
		for (int i = 1; i <= n; i++) {
			sum[i] = dist2[i] + sum[i-1];
		}
		TreeSet<Vertex> ts = new TreeSet<Vertex>();
		for (int i = 1; i <= n; i++)
			ts.add(new Vertex(i, dist2[i]));
		long shortestDist = dist1[n];
		long ans = 0;
		for (int i = 1; i <= n; i++) {
			long target = shortestDist - dist1[i];
			Vertex res = ts.lower(new Vertex(0, target));
			if (res == null)
				continue;
			//out.println(target + " " + res.index + " " + ((target-1) * res.index - sum[res.index]) + " " + sum[res.index]);
			ans += (target-1) * res.index - sum[res.index];
		}
		out.println(ans);
		out.close();
	}
	static long[] getPath (int start) {
		long[] dist = new long[n+1];
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(start, 0));
		for (int i = 0; i <= n; i++)
			dist[i] = 1 << 30;
		dist[0] = dist[start] = 0;
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (Edge next : adj.get(curr.index)) {
				if (dist[next.dest] <= dist[curr.index] + next.cost)
					continue;
				dist[next.dest] = dist[curr.index] + next.cost;
				pq.offer(new Vertex(next.dest, dist[next.dest]));
			}
		}
		return dist;
	}
	static class Edge {
		int dest;
        long cost;
		Edge (int dest, long cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}
	static class Vertex implements Comparable<Vertex> {
		int index;
        Long cost;
		Vertex (int index, long cost) {
			this.index = index;
			this.cost = cost;
		}
		public int compareTo (Vertex n) {
            int cmp = cost.compareTo(n.cost);
			if (cmp == 0)
				return index - n.index;
			return cmp;
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