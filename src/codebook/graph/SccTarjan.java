package codebook.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.*;

public class SccTarjan {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, m, cnt, idCnt;
	static int[] disc, lo, id;
	static boolean[] inStack;
	static Stack<Integer> s = new Stack<Integer>();
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		disc = new int[n];
		lo = new int[n];
		id = new int[n];
		inStack = new boolean[n];
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Integer>());
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
		}
		for (int i = 0; i < n; i++)
			if (disc[i] == 0)
				dfs(i);
		for (int i = 0; i < n; i++)
			out.printf("Vertex %d is in component %d\n", i + 1, id[i]);
		out.close();
	}

	static void dfs (int i) {
		disc[i] = lo[i] = ++cnt;
		inStack[i] = true;
		s.push(i);
		for (int j : adj.get(i)) {
			if (disc[j] == 0) {
				dfs(j);
				lo[i] = Math.min(lo[i], lo[j]);
			} else if (inStack[j]) {
				lo[i] = Math.min(lo[i], disc[j]);
			}
		}
		if (disc[i] == lo[i]) {
			while (s.peek() != i) {
				inStack[s.peek()] = false;
				id[s.pop()] = idCnt;
			}
			inStack[s.peek()] = false;
			id[s.pop()] = idCnt++;
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
