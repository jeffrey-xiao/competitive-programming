package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UTS_Open_2014_Second_MST_E {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int VSIZE = 50500;
	static int[] id = new int[VSIZE];
	static int[] size = new int[VSIZE];
	static int n, m;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<Edge> e = new ArrayList<Edge>();
		for (int x = 0; x < m; x++) {
			e.add(new Edge(readInt(), readInt(), readInt()));
		}
		Collections.sort(e);
		int firstMin = 1 << 30;
		int secondMin = 1 << 30;
		for (Edge e1 : e) {
			init();
			int currCost = 0;
			merge(e1.a, e1.b);
			currCost += e1.c;
			for (Edge e2 : e)
				if (merge(e2.a, e2.b))
					currCost += e2.c;
			if (currCost < firstMin) {
				secondMin = firstMin;
				firstMin = currCost;
			} else if (currCost < secondMin && currCost != firstMin)
				secondMin = currCost;
		}
		if (secondMin == 1 << 30 || m < n - 1)
			ps.println(-1);
		else
			ps.println(secondMin);
		ps.close();
	}

	static int N, len, num;
	static int[] paths = new int[N];
	static int[] dp = new int[N];
	static boolean[] visited = new boolean[N];
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	static void dfs (int i) {
		visited[i] = true;
		if (paths[i] == 0)
			paths[i] = 1;
		for (int next : adj.get(i)) {
			if (visited[next])
				continue;
			dfs(next);
			int x = dp[next] + 1;
			if (x + dp[i] > len) {
				len = x + dp[i]; // update length
				num = paths[i] * paths[next]; // update number
			} else if (x + dp[i] == len) {
				num += paths[i] * paths[next]; // increment number
			}
			if (dp[i] < x) {
				dp[i] = x; // update dp
				paths[i] = paths[next]; // update paths
			} else if (dp[i] == x) {
				paths[i] += paths[next]; // increment paths
			}
		}
	}

	static class Edge implements Comparable<Edge> {
		int a, b, c;

		Edge (int a, int b, int c) {
			this.a = a;
			this.b = b;
			this.c = c;
		}

		@Override
		public int compareTo (Edge o) {
			return c - o.c;
		}
	}

	private static void init () {
		for (int x = 0; x < VSIZE; x++)
			id[x] = x;
	}

	private static int find (int x) {
		return (x == id[x]) ? x : (id[x] = find(id[x]));
	}

	private static boolean merge (int x, int y) {
		int rootx = find(x);
		int rooty = find(y);
		if (rootx == rooty)
			return false;
		if (size[rootx] > size[rooty]) {
			size[rootx] += size[rooty];
			id[rooty] = rootx;
		} else {
			size[rooty] += size[rootx];
			id[rootx] = rooty;
		}
		return true;
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
