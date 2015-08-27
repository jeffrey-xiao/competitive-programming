package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class NEERC_2010_Ideal_Path {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		Queue<Integer> q = new LinkedList<Integer>();
		int[] dist = new int[n];
		for (int x = 0; x < n; x++)
			dist[x] = 1 << 30;
		dist[n - 1] = 0;
		q.offer(n - 1);
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (Edge e : adj.get(curr)) {
				if (dist[e.dest] <= dist[curr] + 1)
					continue;
				dist[e.dest] = dist[curr] + 1;
				q.offer(e.dest);
			}
		}

		PriorityQueue<State> qq = new PriorityQueue<State>();
		qq.offer(new State(0));
		State best = null;
		State curr = null;
		while (!qq.isEmpty()) {
			curr = qq.poll();
			boolean[] v = new boolean[n];
			int start = curr.curr;
			// System.out.println(start);
			ArrayList<State> nextStates = new ArrayList<State>();
			best = null;
			if (start == n - 1)
				break;
			for (Edge e : adj.get(start)) {
				if (dist[e.dest] >= dist[start])
					continue;
				curr.curr = e.dest;
				curr.add(e.color);

				int com = curr.compareTo(best);
				if (com < 0) {
					best = new State(curr);
					nextStates.clear();
					nextStates.add(new State(curr));
				} else if (com == 0)
					nextStates.add(new State(curr));
				curr.colors.remove(curr.colors.size() - 1);
			}
			for (State s : nextStates)
				qq.offer(s);
		}
		System.out.println(curr.colors.size());
		for (Integer i : curr.colors) {
			System.out.print(i + " ");
		}
	}

	static class State implements Comparable<State> {
		int curr;
		ArrayList<Integer> colors;

		State (int curr) {
			this.curr = curr;
			colors = new ArrayList<Integer>();
		}

		State (State s) {
			this.curr = s.curr;
			colors = (ArrayList<Integer>) s.colors.clone();
		}

		void add (int color) {
			colors.add(color);
		}

		@Override
		public int compareTo (State o) {
			if (o == null)
				return -1;
			if (colors.size() != o.colors.size())
				return colors.size() - o.colors.size();
			for (int x = 0; x < colors.size(); x++) {
				if (colors.get(x) < o.colors.get(x))
					return -1;
				else if (colors.get(x) > o.colors.get(x))
					return 1;
			}
			return 0;
		}
	}

	static class Edge {
		int dest, color;

		Edge (int dest_, int color_) {
			dest = dest_;
			color = color_;
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