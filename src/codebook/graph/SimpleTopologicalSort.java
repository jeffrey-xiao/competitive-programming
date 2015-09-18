package codebook.graph;

import java.util.*;
import java.io.*;

public class SimpleTopologicalSort {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static Queue<Integer> q = new ArrayDeque<Integer>(), order = new ArrayDeque<Integer>();
	static int[] cnt;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		cnt = new int[n];
		
		for (int i = 0; i < m; i++) {
			int a = readInt()-1;
			int b = readInt()-1;
			adj.get(a).add(b);
			cnt[b]++;
		}
		for (int i = 0; i < n; i++)
			if (cnt[i] == 0) {
				q.offer(i);
				order.offer(i);
			}
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int next : adj.get(curr)) {
				cnt[next]--;
				if (cnt[next] == 0) {
					q.offer(next);
					order.offer(next);
				}
			}
		}
		if (order.size() != n) {
			out.println("NOT A DIRECTED ACYCLIC GRAPH");
		} else {
			while (!order.isEmpty())
				out.println(order.poll() + 1);
		}
		
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

