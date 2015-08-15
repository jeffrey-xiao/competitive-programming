package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Vincent_Massey_Senior_D_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static State[] states;
	static int[] dp;
	static int t;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		t = readInt();
		dp = new int[n + 2];
		states = new State[n + 2];
		for (int x = 1; x <= n; x++) {
			int a = readInt();
			int b = readInt();
			boolean side = readInt() == 0;
			states[x] = new State(a, b, side);
		}
		ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x <= n + 1; x++) {
			dp[x] = Integer.MIN_VALUE;
			prev.add(new ArrayList<Integer>());
		}
		states[0] = new State(0, 0, true);
		states[n + 1] = new State(Integer.MAX_VALUE, 0, true);
		Arrays.sort(states);
		boolean[] visited = new boolean[n + 2];
		Stack<Integer> moves = new Stack<Integer>();
		moves.push(0);
		while (!moves.empty()) {
			int curr = moves.pop();
			if (curr == n + 1 || visited[curr])
				continue;
			visited[curr] = true;
			if (states[curr + 1].side != states[curr].side || curr + 1 == n + 1) {
				prev.get(curr + 1).add(curr);
				moves.push(curr + 1);
			}
			for (int x = curr + 1; x <= n + 1; x++) {
				if (states[x].side == states[curr].side || x == n + 1) {
					prev.get(x).add(curr);
					moves.push(x);
					break;
				}
			}
		}
		dp[n + 1] = 0;
		for (int x = n + 1; x >= 0; x--) {
			dp[x] += states[x].cost;
			// System.out.println(dp[x]);
			for (Integer i : prev.get(x)) {
				// System.out.println("HERE " + i);
				if (states[i].side != states[x].side) {
					if (x == n + 1)
						dp[i] = Math.max(dp[x], dp[i]);
					else
						dp[i] = Math.max(dp[x] - t, dp[i]);
				} else
					dp[i] = Math.max(dp[x], dp[i]);
			}

		}
		System.out.println(dp[0]);
	}

	static class State implements Comparable<State> {
		int pos;
		int cost;
		boolean side;

		State (int pos, int cost, boolean side) {
			this.pos = pos;
			this.cost = cost;
			this.side = side;
		}

		@Override
		public int compareTo (State o) {
			return pos - o.pos;
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
