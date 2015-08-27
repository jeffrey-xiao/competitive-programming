package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class ACSL_2009_Task_4_Rank {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
	static Set<Integer> cycle = new HashSet<Integer>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		boolean visited[] = new boolean[n];
		for (int x = 0; x < n; x++)
			adjlist.add(new ArrayList<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			int d = readInt();
			if (c > d)
				adjlist.get(a).add(b);
			else
				adjlist.get(b).add(a);
		}
		for (int x = 0; x < n; x++) {
			dfs(visited, x, x, 0);
		}
		System.out.println(cycle.size());
	}

	private static boolean dfs (boolean[] visited, int x, int start, int depth) {

		if (depth != 0 && x == start)
			return true;
		if (visited[x])
			return false;
		visited[x] = true;
		for (int y = 0; y < adjlist.get(x).size(); y++) {
			if (dfs(Arrays.copyOf(visited, visited.length), adjlist.get(x).get(y), start, depth + 1)) {
				cycle.add(x);
				return true;
			}
		}
		return false;
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
