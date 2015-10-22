package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class Subway_Routes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
	static int finalMax = -1;
	static int count = 0;
	static int n;

	public static void main (String[] args) throws IOException {
		n = readInt();
		for (int x = 0; x < n; x++)
			adjlist.add(new ArrayList<Integer>());
		for (int x = 0; x < n - 1; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		ArrayList<Integer> far = null;
		Stack<int[]> moves = new Stack<int[]>();
		boolean[] visited = new boolean[n];
		int max = -1;
		moves.add(new int[] {0, 0});
		while (!moves.isEmpty()) {
			int[] curr = moves.pop();
			int node = curr[0];
			int length = curr[1];
			visited[node] = true;
			if (length > max) {
				max = length;
				far = new ArrayList<Integer>();
				far.add(node);
			} else if (length == max)
				far.add(node);

			for (int x = 0; x < adjlist.get(node).size(); x++) {
				int next = adjlist.get(node).get(x);
				if (!visited[next]) {
					moves.add(new int[] {next, length + 1});
				}
			}
		}
		finalMax = max;
		visited = new boolean[n];
		// dfs(far.get(0), Arrays.copyOf(visited, visited.length));

		for (int x = 0; x < far.size(); x++) {
			visited[far.get(x)] = true;
			dfs(far.get(x), Arrays.copyOf(visited, visited.length));
			// visited[far.get(x)] = false;
		}
		System.out.println(count);
	}

	private static void dfs (int start, boolean[] visited) {
		Stack<int[]> moves = new Stack<int[]>();

		moves.add(new int[] {start, 0});
		while (!moves.isEmpty()) {
			int[] curr = moves.pop();
			int node = curr[0];
			int length = curr[1];
			visited[node] = true;
			if (length > finalMax) {
				finalMax = length;
				count = 1;
			} else if (length == finalMax)
				count++;

			for (int x = 0; x < adjlist.get(node).size(); x++) {
				int next = adjlist.get(node).get(x);
				if (!visited[next]) {
					moves.add(new int[] {next, length + 1});
				}
			}
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