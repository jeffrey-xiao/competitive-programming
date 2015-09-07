package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FordFulkerson {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Edge[] e;
	static int[] last;
	static boolean[] v;
	static int n, m, cnt, src, sink;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		src = readInt();
		sink = readInt();

		last = new int[n];
		e = new Edge[2 * m];

		for (int i = 0; i < n; i++)
			last[i] = -1;

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			addEdge(a, b, c, 0);
		}

		out.println(getFlow());
		out.close();
	}

	static int getFlow () {
		int res = 0;
		int curr = 0;
		v = new boolean[n];
		while ((curr = dfs(src, 1 << 30)) > 0) {
			res += curr;
			v = new boolean[n];
		}
		return res;
	}

	static int dfs (int curr, int flow) {
		v[curr] = true;
		if (curr == sink - 1)
			return flow;
		for (int i = last[curr]; i != -1; i = e[i].next) {
			if (e[i].cost > 0 && !v[e[i].dest]) {
				int res = dfs(e[i].dest, Math.min(flow, e[i].cost));
				if (res > 0) {
					e[i].cost -= res;
					e[i ^ 1].cost += res;
					return res;
				}
			}
		}
		return 0;
	}

	static void addEdge (int x, int y, int xy, int yx) {
		e[cnt] = new Edge(y, xy, last[x]);
		last[x] = cnt++;
		e[cnt] = new Edge(x, yx, last[y]);
		last[y] = cnt++;
	}

	static class Edge {
		int dest, cost, next;

		Edge (int dest, int cost, int next) {
			this.dest = dest;
			this.cost = cost;
			this.next = next;
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
