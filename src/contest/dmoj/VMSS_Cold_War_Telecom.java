package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class VMSS_Cold_War_Telecom {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static int[] low, disc;
	static boolean[] v;
	static TreeSet<Integer> cutVertices;
	static int count = 0;
	static int n, m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		v = new boolean[n];
		disc = new int[n];
		low = new int[n];
		cutVertices = new TreeSet<Integer>();
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		for (int i = 0; i < n; i++)
			if (!v[i])
				dfs(i, -1);

		out.println(cutVertices.size());
		for (Integer u : cutVertices)
			out.printf("%d\n", u);
		out.close();
	}

	static void dfs (int i, int prev) {
		disc[i] = low[i] = count++;
		v[i] = true;
		int children = 0;
		for (Integer j : adj.get(i)) {
			if (!v[j]) {
				children++;
				dfs(j, i);
				low[i] = Math.min(low[i], low[j]);
				if ((disc[i] == 0 && children > 1) || (disc[i] > 0 && low[j] >= disc[i])) {
					cutVertices.add(i + 1);
				}
			} else if (j != prev && disc[j] < low[i]) {
				low[i] = disc[j];
			}
		}
	}

	static class Edge {
		int a, b;

		Edge (int a, int b) {
			this.a = a;
			this.b = b;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
