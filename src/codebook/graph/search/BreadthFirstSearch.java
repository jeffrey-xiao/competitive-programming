package codebook.graph.search;

import java.util.*;
import java.io.*;

public class BreadthFirstSearch {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, orig, dest;
	static ArrayList<ArrayList<Integer>> adj;
	static Queue<Integer> q;
	static int[] dist;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		orig = readInt() - 1;
		dest = readInt() - 1;

		adj = new ArrayList<ArrayList<Integer>>();

		dist = new int[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			dist[i] = 1 << 30;
		}
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		q = new ArrayDeque<Integer>();
		dist[orig] = 0;
		q.offer(orig);
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (Integer next : adj.get(curr)) {
				if (dist[next] > dist[curr] + 1) {
					dist[next] = dist[curr] + 1;
					q.offer(next);
				}
			}
		}
		out.println(dist[dest]);
		out.close();
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

