package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Breaking_The_Friend_Chain {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
	static ArrayList<ArrayList<Integer>> prev = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();

		for (int i = 0; i < n; i++)
			adj.add(new HashSet<Integer>());
		for (int i = 0; i < m; i++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			adj.get(x).add(y);
			adj.get(y).add(x);
		}
		int a = readInt() - 1;
		int b = readInt() - 1;
		boolean[] v = new boolean[n];
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(a);
		v[a] = true;
		while (!q.isEmpty()) {
			Integer curr = q.poll();
			for (int next : adj.get(curr)) {
				if (v[next])
					continue;
				v[next] = true;
				q.offer(next);
			}
		}
		out.println(v[b] ? 1 : 0);
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
