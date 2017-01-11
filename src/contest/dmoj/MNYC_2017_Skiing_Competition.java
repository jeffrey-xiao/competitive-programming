package contest.dmoj;

import java.util.*;
import java.io.*;

public class MNYC_2017_Skiing_Competition {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final int SZ = 50;
	
	static int N, M, A, B, Q;
	static int[] dist, min;
	static Edge[] prev;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	static ArrayList<Path> bestPaths = new ArrayList<Path>();
	static TreeSet<Path> possPaths = new TreeSet<Path>();

	static TreeMap<Integer, Integer> paths = new TreeMap<Integer, Integer>();
	static HashSet<Edge> usedEdges = new HashSet<Edge>();
	static HashSet<Integer> usedVertices = new HashSet<Integer>();
	
	static boolean[] vis = new boolean[200];
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		A = readInt() - 1;
		B = readInt() - 1;
		Q = readInt();

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Edge>());
		dist = new int[N];
		prev = new Edge[N];
		min = new int[N];

		for (int i = 0; i < M; i++) {
			int u = readInt() - 1;
			int v = readInt() - 1;
			int c = readInt();
			adj.get(u).add(new Edge(u, v, c));
			adj.get(v).add(new Edge(v, u, c));
		}

		if (N <= 100) {
			vis[A] = true;
			dfs(A, 0, 1 << 30);	
			int[] cost = new int[SZ], min = new int[SZ];
			Arrays.fill(cost, -1);
			Arrays.fill(min, -1);
			int index = 0;

			for (Map.Entry<Integer, Integer> p : paths.entrySet()) {
				cost[index] = p.getKey();
				min[index] = p.getValue();
				index++;
			}

			for (int i = 0; i < Q; i++) {
				int val = readInt() - 1;
				if (cost[val] == -1)
					out.printf("-1\n");
				else
					out.printf("%d %d\n", cost[val], min[val]);
			}
			out.close();
			return;
		}
		
		Path nextPath = shortestPath(A, B);

		if (Q == 1) {
			if (nextPath == null)
				out.println(-1);
			else
				out.println(nextPath.cost + " " + nextPath.min);

			out.close();
			return;
		}
		
		bestPaths.add(nextPath);

		if (!paths.containsKey(nextPath.cost))
			paths.put(nextPath.cost, nextPath.min);
		paths.put(nextPath.cost, Math.min(nextPath.min, paths.get(nextPath.cost)));

		for (int i = 1; paths.size() < SZ; i++) {
			for (int j = 0; j < bestPaths.get(i - 1).nodes.size(); j++) {
				int spurNode = bestPaths.get(i - 1).nodes.get(j).src;
				ArrayList<Edge> rootPath = new ArrayList<Edge>(bestPaths.get(i - 1).nodes.subList(0, j));
				usedEdges.clear();
				usedVertices.clear();
				for (Path p : bestPaths) {
					if (p.nodes.size() < rootPath.size())
						continue;
					ArrayList<Edge> currPath = new ArrayList<Edge>(p.nodes.subList(0, j));
					if (currPath.equals(rootPath)) {
						usedEdges.add(p.nodes.get(j));
						usedEdges.add(p.nodes.get(j).negate());
					}
				}

				usedVertices.add(A);
				for (Edge node : rootPath) 
					usedVertices.add(node.sink);

				Path spurPath = shortestPath(spurNode, B);
				if (spurPath == null)
					continue;

				ArrayList<Edge> totalPath = rootPath;
				totalPath.addAll(spurPath.nodes);

				Path ret = new Path();
				ret.nodes = totalPath;
				ret.compute();

				possPaths.add(ret);
				if (possPaths.size() > SZ)
					possPaths.pollLast();
			}

			if (possPaths.isEmpty())
				break;

			nextPath = possPaths.pollFirst();
			bestPaths.add(nextPath);

			if (!paths.containsKey(nextPath.cost))
				paths.put(nextPath.cost, nextPath.min);
			paths.put(nextPath.cost, Math.min(nextPath.min, paths.get(nextPath.cost)));
		}
		
		int[] cost = new int[SZ], min = new int[SZ];
		Arrays.fill(cost, -1);
		Arrays.fill(min, -1);
		int index = 0;

		for (Map.Entry<Integer, Integer> p : paths.entrySet()) {
			cost[index] = p.getKey();
			min[index] = p.getValue();
			index++;
		}

		for (int i = 0; i < Q; i++) {
			int val = readInt() - 1;
			if (cost[val] == -1)
				out.printf("-1\n");
			else
				out.printf("%d %d\n", cost[val], min[val]);
		}
		out.close();
	}
	
	static void dfs (int u, int cost, int min) {
		if (u == B) {
			if (!paths.containsKey(cost))
				paths.put(cost, min);
			paths.put(cost, Math.min(min, paths.get(cost)));
			if (paths.size() > 10)
				paths.pollLastEntry();
			return;
		}
		for (Edge e : adj.get(u)) {
			if (vis[e.sink])
				continue;
			int nextCost = cost + e.cost;
			int nextMin = Math.min(min, e.cost);
			if (paths.size() == 10 && nextCost > paths.lastEntry().getKey())
				continue;
			vis[e.sink] = true;
			dfs(e.sink, nextCost, nextMin);
			vis[e.sink] = false;
		}
	}
	
	static Path shortestPath (int src, int sink) {
		Arrays.fill(dist, 1 << 30);
		Arrays.fill(prev, null);
		Arrays.fill(min, 1 << 30);
		dist[src] = 0;

		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(src, dist[src], min[src]));

		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			for (Edge next : adj.get(curr.index)) {
				if (usedEdges.contains(next))
					continue;
				if (usedVertices.contains(next.sink))
					continue;
				if (next.cost + curr.cost < dist[next.sink] ||
						(next.cost + curr.cost == dist[next.sink] && Math.min(curr.min, next.cost) < min[next.sink])) {
					int nextCost = next.cost + curr.cost;
					dist[next.sink] = nextCost;
					prev[next.sink] = next;
					min[next.sink] = Math.min(curr.min, next.cost);
					pq.offer(new Vertex(next.sink, dist[next.sink], min[next.sink]));
				}
			}
		}
		
		if (prev[sink] == null)
			return null;
		
		Path ret = new Path();
		ret.cost = dist[sink];
		ret.min = min[sink];
		int curr = sink;
		while (prev[curr] != null) {
			ret.nodes.add(prev[curr]);
			curr = prev[curr].src;
		}
		Collections.reverse(ret.nodes);
		return ret;
	}

	static class Vertex implements Comparable<Vertex> {
		int index, cost, min;
		Vertex (int index, int cost, int min) {
			this.index = index;
			this.cost = cost;
			this.min = min;
		}
		@Override
		public int compareTo (Vertex v) {
			if (cost == v.cost)
				return min - v.min;
			return cost - v.cost;
		}
	}

	static class Edge {
		int src, sink, cost;
		Edge (int src, int sink, int cost) {
			this.src = src;
			this.sink = sink;
			this.cost = cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Edge) {
				Edge e = (Edge)o;
				return (src == e.src && sink == e.sink && cost == e.cost);
			}
			return false;
		}

		@Override
		public int hashCode () {
			return 31 * src + sink;
		}
		
		public Edge negate () {
			return new Edge(sink, src, cost);
		}
	}

	static class Path implements Comparable<Path> {
		ArrayList<Edge> nodes = new ArrayList<Edge>();
		int cost, min;
		@Override
		public int compareTo (Path v) {
			if (cost == v.cost)
				return min - v.min;
			return cost - v.cost;
		}

		public void compute () {
			cost = 0;
			min = 1 << 30;
			for (Edge edge : nodes) {
				cost += edge.cost;
				min = Math.min(edge.cost, min);
			}
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

