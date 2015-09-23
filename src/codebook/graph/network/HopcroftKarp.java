package codebook.graph.network;

import java.util.*;
import java.io.*;

public class HopcroftKarp {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	static final int NULL = 0;
	
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int leftSize, rightSize, n;
	static int[] pair, dist;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		// number of vertices on the left side
		leftSize = readInt();
		// number of vertices on the right side
		rightSize = readInt();
		// number of edges
		n = readInt();
		for (int i = 0; i < leftSize + rightSize + 1; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < n; i++) {
			int left = readInt();
			int right = readInt();
			adj.get(left).add(leftSize + right);
		}
		out.println(getMaxMatching());
		out.close();
	}
	static int getMaxMatching () {
		pair = new int[leftSize + rightSize + 1];
		dist = new int[leftSize + rightSize + 1];
		int res = 0;
		while (bfs())
			for (int i = 1; i <= leftSize; i++)
				if (pair[i] == NULL)
					res += dfs(i) ? 1 : 0;
		return res;
	}
	static boolean bfs () {
		Queue<Integer> q = new ArrayDeque<Integer>();
		for (int i = 1; i <= leftSize; i++) {
			if (pair[i] == NULL) {
				dist[i] = 0;
				q.offer(i);
			} else {
				dist[i] = 1 << 30;
			}
		}
		dist[NULL] = 1 << 30;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			if (dist[curr] >= dist[NULL])
				continue;
			for (int next : adj.get(curr)) {
				if (dist[pair[next]] == 1 << 30) {
					dist[pair[next]] = dist[curr] + 1;
					q.offer(pair[next]);
				}
					
			}
		}
		return dist[NULL] != 1 << 30;
	}
	static boolean dfs (int i) {
		if (i == NULL)
			return true;
		for (int j : adj.get(i)) {
			if (dist[pair[j]] == dist[i] + 1)
				if (dfs(pair[j])) {
					pair[j] = i;
					pair[i] = j;
					return true;
				}
		}
		dist[i] = 1 << 30;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
