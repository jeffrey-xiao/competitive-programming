package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Subway_Routes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int max;
	static int total;
	static ArrayList<ArrayList<Integer>> adj;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		adj = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int x = 0; x < n - 1; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		// FIRST DFS
		int end = 0;
		Queue<State> moves = new LinkedList<State>();
		v = new boolean[n];
		v[0] = true;
		moves.offer(new State(0, 0));
		while (!moves.isEmpty()) {
			State curr = moves.poll();
			if (curr.moves > max) {
				max = curr.moves;
				end = curr.dest;
			}
			for (Integer i : adj.get(curr.dest))
				if (!v[i]) {
					v[i] = true;
					moves.offer(new State(i, curr.moves + 1));
				}
		}
		moves.clear();
		v = new boolean[n];
		v[end] = true;
		ArrayList<Integer> start = new ArrayList<Integer>();
		start.add(end);
		moves.offer(new State(end, 0, start));
		max = 0;
		State best = null;
		// GETTING THE DIAMETER
		while (!moves.isEmpty()) {
			State curr = moves.poll();
			if (curr.moves > max) {
				max = curr.moves;
				best = new State(0, 0, curr.path);
			}
			for (Integer i : adj.get(curr.dest))
				if (!v[i]) {
					v[i] = true;
					curr.path.add(i);
					moves.offer(new State(i, curr.moves + 1, curr.path));
					curr.path.remove(i);
				}
		}
		// SETTING THE CENTERS AND SEPARATING INTO TWO SUBTREES IF THERE ARE TWO
		// CENTERS
		ArrayList<Integer> centers = new ArrayList<Integer>();
		double split = (best.path.size() - 1) / 2.0d;
		for (int x = (int)Math.floor(split); x <= (int)Math.ceil(split); x++)
			centers.add(best.path.get(x));
		for (int x = 0; x < centers.size() - 1; x++) {
			int a = centers.get(x);
			int b = centers.get(x + 1);
			// System.out.println(a + " " + b + "REMOVE ");
			int index1 = adj.get(a).indexOf(b);
			int index2 = adj.get(b).indexOf(a);
			adj.get(a).remove(index1);
			adj.get(b).remove(index2);
		}
		v = new boolean[n];
		max = max / 2;
		long sum = 1;
		ArrayList<Long> subtrees = new ArrayList<Long>();

		if (centers.size() == 1) {
			v[centers.get(0)] = true;
			subtrees.clear();
			for (Integer i : adj.get(centers.get(0))) {
				v[i] = true;
				long add = dfs(i, 1);
				subtrees.add(add);
			}
			long currSum = 0;
			for (int y = 0; y < subtrees.size(); y++) {
				for (int z = y + 1; z < subtrees.size(); z++) {
					currSum += (subtrees.get(y) * subtrees.get(z));
				}
			}
			sum = currSum;
		} else {
			for (int x = 0; x < centers.size(); x++) {
				v[centers.get(x)] = true;
				subtrees.clear();
				for (Integer i : adj.get(centers.get(x))) {
					v[i] = true;
					long add = dfs(i, 1);
					subtrees.add(add);
				}
				long currSum = 0;
				for (int y = 0; y < subtrees.size(); y++)
					currSum += subtrees.get(y);
				if (currSum != 0)
					sum *= currSum;
			}

		}
		System.out.println(sum);
	}

	private static long dfs (Integer i, int l) {
		long total = 0;
		for (Integer next : adj.get(i)) {
			if (!v[next]) {
				v[next] = true;
				long n = dfs(next, l + 1);
				total += n;
			}
		}
		if (l == max) {
			++total;
		}
		return total;
	}

	static class State {
		int moves;
		int dest;
		ArrayList<Integer> path;

		State (int dest, int moves) {
			this.dest = dest;
			this.moves = moves;
		}

		State (int dest, int moves, ArrayList<Integer> a) {
			this.dest = dest;
			this.moves = moves;
			path = new ArrayList<Integer>();
			for (Integer i : a)
				path.add(i);
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