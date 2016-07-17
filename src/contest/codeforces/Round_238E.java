package contest.codeforces;

import java.util.*;
import java.io.*;

public class Round_238E {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static ArrayList<ArrayList<Integer>> adj;
	static TreeSet<Long> used;
	static boolean[] vis;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		if ((M & 1) == 1) {
			out.println("No solution");
			out.close();
		}
		
		adj = new ArrayList<ArrayList<Integer>>();
		used = new TreeSet<Long>();
		vis = new boolean[N];

		for (int i = 0; i < N; i++)
			adj.add(new ArrayList<Integer>());

		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}

		dfs(0);
		out.close();
	}

	static int dfs (int u) {
		vis[u] = true;
		Stack<Integer> leftover = new Stack<Integer>();
		ArrayList<Integer> neighbours = new ArrayList<Integer>();
		for (int v : adj.get(u)) {
			if (used.contains(toIndex(u, v)))
				continue;
			used.add(toIndex(u, v));
			neighbours.add(v);
			
		}
		
		for (int v : neighbours) {
			int next = -1;
			if (!vis[v])
				next = dfs(v);
			
			if (next == -1)
				leftover.push(v);
			else
				out.printf("%d %d %d\n", u + 1, v + 1, next + 1);
		}

		while (leftover.size() >= 2) {
			int a = leftover.pop();
			int b = leftover.pop();
			out.printf("%d %d %d\n", a + 1, u + 1, b + 1);
		}
		return leftover.size() == 0 ? -1 : leftover.pop();
	}

	static long toIndex (int u, int v) {
		long a = Math.min(u, v);
		long b = Math.max(u, v);
		return a * N + b;
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

