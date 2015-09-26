package codebook.graph;

import java.util.*;
import java.io.*;

public class DfsEulerian {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int n, m;
	static int[] used;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
		used = new int[n];
		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Edge>());
		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(new Edge(b, adj.get(b).size()));
			adj.get(b).add(new Edge(a, adj.get(a).size() - 1));
		}
		printEulerianPath();
		out.close();
	}

	static void printEulerianPath () {
		if (!isEulerianPath()) {
			out.println("No Eulerian Path Exists");
			return;
		}
		Stack<Integer> order = new Stack<Integer>();
		int curr = 0;
		for (int i = 0; i < n; i++)
			if ((adj.get(i).size() & 1) > 0)
				curr = i;
		while (true) {
			if (adj.get(curr).size() - used[curr] == 0) {
				out.print(curr + 1 + " ");
				if (order.size() == 0)
					break;
				curr = order.pop();
			} else {
				order.push(curr);
				for (int i = 0; i < adj.get(curr).size(); i++) {
					if (!adj.get(curr).get(i).used) {
						int dest = adj.get(curr).get(i).dest;
						int index = adj.get(curr).get(i).index;
						adj.get(curr).get(i).used = true;
						adj.get(dest).get(index).used = true;
						used[curr]++;
						used[dest]++;
						curr = dest;
						break;
					}
				}
			}
		}
		out.println();
	}

	static boolean isEulerianPath () {
		return getEuler() != -1;
	}

	static boolean isEulerianCycle () {
		return getEuler() == 0;
	}

	static int getEuler () {
		// assuming that all vertices are connected
		int odd = 0;
		for (int i = 0; i < n; i++)
			if ((adj.get(i).size() & 1) > 0)
				odd++;
		if (odd > 2)
			return -1;
		return odd == 0 ? 0 : 1;
	}

	static class Edge {
		int dest, index;
		boolean used;

		Edge (int dest, int index) {
			this.dest = dest;
			this.index = index;
			this.used = false;
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
