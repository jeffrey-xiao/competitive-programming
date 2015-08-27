package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2010_Traffic_Congestion2 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int[] sum;
	static boolean[] visited;
	static int[] dp;
	static ArrayList<ArrayList<Integer>> adjlist;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		adjlist = new ArrayList<ArrayList<Integer>>();
		sum = new int[n];
		visited = new boolean[n];
		dp = new int[n];
		for (int x = 0; x < n; x++) {
			adjlist.add(new ArrayList<Integer>());
			sum[x] = readInt();
		}
		for (int x = 0; x < n - 1; x++) {
			int a = readInt();
			int b = readInt();
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		Stack<Integer> moves = new Stack<Integer>();
		Stack<int[]> bottom = new Stack<int[]>();
		moves.push(0);
		bottom.push(new int[] {0, -1});
		while (!moves.isEmpty()) {
			int curr = moves.pop();
			visited[curr] = true;
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				int next = adjlist.get(curr).get(x);
				if (visited[next])
					continue;
				moves.push(next);
				bottom.push(new int[] {next, curr});
			}
		}
		while (!bottom.isEmpty()) {

			int[] curr = bottom.pop();
			// System.out.println(curr[0]);
			int total = sum[curr[0]];
			dp[curr[0]] = 0;
			for (int x = 0; x < adjlist.get(curr[0]).size(); x++) {
				int next = adjlist.get(curr[0]).get(x);
				if (curr[1] == next)
					continue;
				total += sum[next];
				dp[curr[0]] = Math.max(dp[curr[0]], sum[next]);
			}
			sum[curr[0]] = total;

		}

		int min = Integer.MAX_VALUE;
		int index = 0;
		for (int x = 0; x < n; x++) {
			dp[x] = Math.max(dp[x], sum[0] - sum[x]);
			// System.out.println(sum[x] + " " + x);
			if (dp[x] < min) {
				min = dp[x];
				index = x;
			}

		}
		System.out.println(index);
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