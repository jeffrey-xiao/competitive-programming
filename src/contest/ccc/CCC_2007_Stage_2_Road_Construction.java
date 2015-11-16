package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Stack;
import java.util.StringTokenizer;

public class CCC_2007_Stage_2_Road_Construction {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	static Stack<Integer> s = new Stack<Integer>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static HashMap<Integer, Integer> nodeToCom = new HashMap<Integer, Integer>();
	static ArrayList<HashSet<Integer>> tree = new ArrayList<HashSet<Integer>>();
	static int leaves;
	static int count;
	static boolean[] visited;
	static int[] parent;
	static int[] d;
	static int[] low;
	static int numOfComponents;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		visited = new boolean[n];
		parent = new int[n];
		d = new int[n];
		low = new int[n];
		for (int x = 0; x < n; x++) {
			adj.add(new ArrayList<Integer>());
			parent[x] = -1;
		}
		int m = readInt();
		int a = 0;
		int b = 0;
		for (int x = 0; x < m; x++) {
			a = readInt() - 1;
			b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		for (int x = 0; x < n; x++) {
			if (!visited[x])
				dfs(x);
		}
		for (int x = 0; x < n; x++) {
			if (nodeToCom.get(x) == null) {
				nodeToCom.put(x, numOfComponents);
				numOfComponents++;
			}
		}
		for (int x = 0; x < numOfComponents; x++)
			tree.add(new HashSet<Integer>());
		for (int x = 0; x < n; x++)
			for (int y = 0; y < adj.get(x).size(); y++) {
				a = nodeToCom.get(x);
				b = nodeToCom.get(adj.get(x).get(y));

				if (a != b) {
					tree.get(a).add(b);
					tree.get(b).add(a);
				}
			}
		for (HashSet<Integer> h : tree) {
			if (h.size() == 1)
				leaves++;
		}
		System.out.println((leaves + 1) / 2);
	}

	static class Edge {
		int source;
		int dest;

		Edge (int source, int dest) {
			this.source = source;
			this.dest = dest;
		}
	}

	private static void dfs (int x) {
		visited[x] = true;
		low[x] = d[x] = ++count;
		for (int y = 0; y < adj.get(x).size(); y++) {
			int next = adj.get(x).get(y);
			if (!visited[next]) {
				s.push(x);
				parent[next] = x;
				dfs(next);
				if (low[next] >= low[x])
					addComponent(x);
				low[x] = Math.min(low[next], low[x]);
			} else if (parent[x] != next && d[next] < d[x]) {
				s.push(x);
				low[x] = Math.min(d[next], low[x]);
			}
		}
	}

	private static void addComponent (Integer x) {
		if (s.isEmpty())
			return;
		Integer e = s.pop();
		if (nodeToCom.containsKey(e))
			return;
		nodeToCom.put(e, numOfComponents);
		while (!s.isEmpty() && (e != x)) {
			e = s.pop();
			nodeToCom.put(e, numOfComponents);
		}
		numOfComponents++;
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
