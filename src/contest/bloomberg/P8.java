package contest.bloomberg;

import java.util.*;
import java.io.*;

public class P8 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int A, B, K, G;
	static int[] path;
	static int[] pathCost;
	static ArrayList<ArrayList<State>> adj = new ArrayList<ArrayList<State>>();
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));
		
		N = readInt();
		M = readInt();
		
		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<State>());
		
		A = readInt() - 1;
		B = readInt() - 1;
		K = readInt();
		G = readInt();
		
		path = new int[G];
		pathCost = new int[G];
		
		for (int i = 0; i < G; i++) 
			path[i] = readInt() - 1;
		
		
		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new State(b, c));
			adj.get(b).add(new State(a, c));
		}
		
		for (int i = 1; i < G; i++) {
			for (int j = 0; j < N; j++)
				for (State e : adj.get(j)) {
					if (j == path[i - 1] && e.dest == path[i]) {
						pathCost[i] = pathCost[i - 1] + e.cost;
					}
				}
		}
		
		PriorityQueue<State> pq = new PriorityQueue<State>();
		int[] cost = new int[N];
		Arrays.fill(cost, 1 << 30);
		cost[A] = 0;
		pq.offer(new State(A, 0));
		
		while (!pq.isEmpty()) {
			State curr = pq.poll();
			for (State e : adj.get(curr.dest)) {
				int endA = -1;
				int endB = -1;
				int f = 0;
				for (int i = 1; i < G; i++)
					if (pathCost[i - 1] <= curr.cost + K && curr.cost + K < pathCost[i]) {
						endA = path[i - 1];
						endB = path[i];
						f = pathCost[i] - curr.cost - K;
					}
				
				int finalCost = curr.cost + e.cost;
				if (e.dest == endA && curr.dest == endB || e.dest == endB && curr.dest == endA) {
					finalCost = finalCost + f;
				}

				if (cost[e.dest] <= finalCost)
						continue;
				cost[e.dest] = finalCost;
				pq.offer(new State(e.dest, cost[e.dest]));
			}
		}
		out.println(cost[B]);
		out.close();
	}

	static class State implements Comparable<State> {
		int cost, dest;
		State (int dest, int cost) {
			this.cost = cost;
			this.dest = dest;
		}
		@Override
		public int compareTo (State o) {
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

