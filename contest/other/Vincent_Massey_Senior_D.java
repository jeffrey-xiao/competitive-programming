package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Vincent_Massey_Senior_D {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static State[] states;
	static int[] dp;
	static int t;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		t = readInt();
		dp = new int[n + 1];
		states = new State[n + 1];
		for (int x = 1; x <= n; x++) {
			int a = readInt();
			int b = readInt();
			boolean side = readInt() == 0;
			states[x] = new State(a, b, side);
		}
		for (int x = 0; x <= n; x++)
			dp[x] = Integer.MIN_VALUE;
		states[0] = new State(0, 0, true);
		Arrays.sort(states);
		System.out.println(compute(0, n));
	}

	private static int compute (int i, int n) {
		if (dp[i] != Integer.MIN_VALUE)
			return dp[i];
		int sum = states[i].cost;
		if (i == n)
			return sum;
		int next = Integer.MIN_VALUE;
		if (states[i + 1].side != states[i].side) {
			next = Math.max(next, compute(i + 1, n) - t);
		}
		for (int x = i + 1; x <= n; x++) {
			if (states[x].side == states[i].side) {
				next = Math.max(next, compute(x, n));
				break;
			}
		}
		dp[i] = sum + next;
		return sum + next;
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
