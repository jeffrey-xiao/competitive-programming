package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2007_Stage_2_Road_Construction {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
	static int count;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		for (int x = 0; x < n; x++)
			adjlist.add(new ArrayList<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adjlist.get(a).add(b);
			adjlist.get(b).add(a);
		}
		boolean[] visited = new boolean[n];
		int[] depthVisited = new int[n];
		dfs(0, visited, 0, depthVisited, -1);
		boolean flag = checkRoot(0, new boolean[n], -1);
		if (!flag) {
			count--;
		}
		System.out.println((count) / 2);
		// System.out.println(count);
	}

	private static boolean checkRoot (int i, boolean[] visited, int prev) {
		if (visited[i])
			return false;
		visited[i] = true;
		for (int x = 0; x < adjlist.get(i).size(); x++) {
			int next = adjlist.get(i).get(x);
			if (i != next) {
				boolean flag = checkRoot(adjlist.get(i).get(x), visited, i);
				if (i == 0 && x > 0 && flag)
					return true;
			}
		}
		if (i != 0)
			return true;
		return false;
	}

	private static int dfs (int curr, boolean[] visited, int depth,
			int[] depthVisited, int prev) {
		int min = Integer.MAX_VALUE;
		if (visited[curr])
			return depthVisited[curr];
		visited[curr] = true;
		depthVisited[curr] = depth;
		for (int x = 0; x < adjlist.get(curr).size(); x++) {
			int next = adjlist.get(curr).get(x);
			if (next != prev)
				min = Math.min(min,
						dfs(next, visited, depth + 1, depthVisited, curr));

		}
		if (min >= depth && adjlist.get(curr).size() > 0) {
			count++;
		}
		// System.out.println(curr + " " + min);
		depthVisited[curr] = min;
		return min;
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
