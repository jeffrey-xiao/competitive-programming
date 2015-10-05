package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

public class SccKosaraju {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static ArrayList<ArrayList<Integer>> rev = new ArrayList<ArrayList<Integer>>();
	static int[] id;
	static int cnt;
	static Stack<Integer> order = new Stack<Integer>();
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		id = new int[n];
		v = new boolean[n];
		for (int i = 0; i < n; i++) {
			adj.add(new ArrayList<Integer>());
			rev.add(new ArrayList<Integer>());
		}

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			rev.get(b).add(a);
		}
		for (int i = 0; i < n; i++)
			if (!v[i])
				dfs(i);
		v = new boolean[n];
		while (!order.isEmpty()) {
			int curr = order.pop();
			if (!v[curr]) {
				mark(curr);
				cnt++;
			}
		}
		for (int i = 0; i < n; i++)
			out.printf("vertex %d is in component %d\n", i + 1, id[i]);
		out.close();
	}

	static void dfs (int i) {
		v[i] = true;
		for (int j : adj.get(i))
			if (!v[j])
				dfs(j);
		order.push(i);
	}

	static void mark (int i) {
		v[i] = true;
		id[i] = cnt;
		for (int j : rev.get(i))
			if (!v[j])
				mark(j);
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
