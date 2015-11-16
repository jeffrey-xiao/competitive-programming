package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class USACO_2013_Vacation_Planning_Gold_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n = 0;
	static int[][] minDistanceTo = null;
	static int[][] minDistanceFrom = null;

	public static void main (String[] args) throws IOException {
		n = readInt();
		int m = readInt();
		int k = readInt();
		int q = readInt();
		minDistanceTo = new int[n][k];
		minDistanceFrom = new int[n][k];
		ArrayList<ArrayList<int[]>> straight = new ArrayList<ArrayList<int[]>>();
		ArrayList<ArrayList<int[]>> reverse = new ArrayList<ArrayList<int[]>>();
		for (int x = 0; x < n; x++) {
			straight.add(new ArrayList<int[]>());
			reverse.add(new ArrayList<int[]>());
		}
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			straight.get(a).add(new int[] {b, c});
			reverse.get(b).add(new int[] {a, c});
		}
		ArrayList<Integer> hubs = new ArrayList<Integer>();
		for (int x = 0; x < k; x++)
			hubs.add(readInt() - 1);
		for (int y = 0; y < k; y++) {
			shortest_path(hubs.get(y), reverse, y, true);
			shortest_path(hubs.get(y), straight, y, false);
		}
		int count = 0;
		long totalCost = 0;
		for (int x = 0; x < q; x++) {
			long min = Long.MAX_VALUE;
			int a = readInt() - 1;
			int b = readInt() - 1;
			for (int y = 0; y < k; y++) {
				int dist1 = minDistanceTo[a][y];
				int dist2 = minDistanceFrom[b][y];
				if ((a != hubs.get(y) && dist1 == 0) || (b != hubs.get(y) && dist2 == 0))
					continue;
				min = Math.min(min, dist1 + dist2);
			}
			if (min != Long.MAX_VALUE) {
				totalCost += min;
				count++;
			}
		}
		System.out.println(count);
		System.out.println(totalCost);
	}

	private static void shortest_path (int i, ArrayList<ArrayList<int[]>> adjlist, int index, boolean isStraight) {
		int[] min = new int[n];
		for (int x = 0; x < n; x++)
			if (x != i)
				min[x] = Integer.MAX_VALUE;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.add(new Vertex(i, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			min[curr.index] = curr.cost;

			if (isStraight)
				minDistanceTo[curr.index][index] = curr.cost;
			else
				minDistanceFrom[curr.index][index] = curr.cost;
			for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
				Vertex next = new Vertex(adjlist.get(curr.index).get(x)[0], adjlist.get(curr.index).get(x)[1] + curr.cost);
				if (min[next.index] > next.cost) {
					min[next.index] = next.cost;
					pq.add(next);
				}
			}
		}
	}

	static class Vertex implements Comparable<Vertex> {
		int index;
		int cost;

		Vertex (int index, int cost) {
			this.index = index;
			this.cost = cost;
		}

		@Override
		public int compareTo (Vertex o) {
			return cost - o.cost;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Vertex) {
				Vertex v = (Vertex) o;
				return index == v.index;
			}
			return false;
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
