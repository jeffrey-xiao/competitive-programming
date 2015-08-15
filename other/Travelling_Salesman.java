package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// BFS
public class Travelling_Salesman {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		int k = readInt();
		Queue<State> q = new LinkedList<State>();
		boolean[] v = new boolean[n];
		for (int x = 0; x < k; x++) {
			int a = readInt() - 1;
			v[a] = true;
			q.offer(new State(a, 0));
		}
		int max = 0;
		while (!q.isEmpty()) {
			State curr = q.poll();
			max = Math.max(max, curr.moves);
			for (Integer next : adj.get(curr.index)) {
				if (v[next])
					continue;
				v[next] = true;
				q.offer(new State(next, curr.moves + 1));
			}
		}
		System.out.println(max);
	}

	static class State {
		int moves, index;

		State (int index, int moves) {
			this.index = index;
			this.moves = moves;
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
