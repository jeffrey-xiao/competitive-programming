package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2013_Dreaming {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int max = 0;
	static int end = 0;
	static boolean[] visited;
	static int[] prev;
	static int[] maxD;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		int l = readInt();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Edge>());
		visited = new boolean[n];
		prev = new int[n];
		for (int x = 0; x < m; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}
		ArrayList<Integer> ends = new ArrayList<Integer>();
		for (int x = 0; x < n; x++) {
			if (!visited[x]) {
				visited[x] = true;
				max = end = -1;
				getEnd(x);
				// System.out.println("END " + end);
				ends.add(end);
			}
		}
		// START
		ArrayList<Integer> diameters = new ArrayList<Integer>();
		ArrayList<Integer> starts = new ArrayList<Integer>();
		visited = new boolean[n];
		for (int x = 0; x < n; x++)
			prev[x] = -1;
		for (Integer i : ends) {
			visited[i] = true;
			max = end = -1;
			getPath(i);
			starts.add(end);
		}
		ArrayList<Integer> components = new ArrayList<Integer>();
		for (Integer curr : starts) {
			int min = Integer.MAX_VALUE;
			int i = curr;
			while (i != -1) {
				visited = new boolean[n];
				maxD = new int[n];
				visited[i] = true;
				int currMin = getMin(i);
				if (i == curr) {
					diameters.add(currMin);
				}
				min = Math.min(currMin, min);
				i = prev[i];
			}
			components.add(min);
		}
		Collections.sort(components, Collections.reverseOrder());
		Collections.sort(diameters, Collections.reverseOrder());
		// System.out.println(components.get(0) + components.get(1) + l + " " +
		// (components.get(1) + components.get(2) + 2*l));
		int max = diameters.get(0);
		if (components.size() >= 3)
			max = Math.max(components.get(0) + components.get(1) + l,
					components.get(1) + components.get(2) + 2 * l);
		else
			max = components.get(0) + components.get(1) + l;
		System.out.println(max);
	}

	private static int getMin (int i) {
		Stack<State> s = new Stack<State>();
		s.push(new State(i, 0));
		int maxDist = 0;
		while (!s.isEmpty()) {
			State curr = s.pop();
			// System.out.println(curr.index + " : " + curr.count);
			maxDist = Math.max(curr.count, maxDist);
			for (Edge e : adj.get(curr.index)) {
				if (!visited[e.dest]) {
					visited[e.dest] = true;
					s.push(new State(e.dest, curr.count + e.cost));
				}
			}
		}
		return maxDist;
	}

	private static void getPath (int i) {
		Stack<State> s = new Stack<State>();
		s.push(new State(i, 0));
		while (!s.isEmpty()) {
			State curr = s.pop();
			if (curr.count > max) {
				max = curr.count;
				end = curr.index;
			}
			for (Edge e : adj.get(curr.index)) {
				if (!visited[e.dest]) {
					visited[e.dest] = true;
					prev[e.dest] = curr.index;
					s.push(new State(e.dest, curr.count + 1));
				}
			}
		}
	}

	private static void getEnd (int i) {
		Stack<State> s = new Stack<State>();
		s.push(new State(i, 0));
		while (!s.isEmpty()) {
			State curr = s.pop();
			if (curr.count > max) {
				max = curr.count;
				end = curr.index;
			}
			for (Edge e : adj.get(curr.index)) {
				if (!visited[e.dest]) {
					visited[e.dest] = true;
					s.push(new State(e.dest, curr.count + 1));
				}
			}
		}
	}

	static class Edge {
		int dest;
		int cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
		}
	}

	static class State {
		int count;
		int index;

		State (int index, int count) {
			this.index = index;
			this.count = count;
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