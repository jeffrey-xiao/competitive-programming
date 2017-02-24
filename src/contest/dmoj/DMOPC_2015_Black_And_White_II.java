package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Black_And_White_II {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static ArrayList<Edge> e;
	static int[] last, dist;
	static int N, M, cnt, src, sink;
	static char[][] g;
	static boolean[] v;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		M = readInt();
		N = readInt();

		src = 0;
		sink = N * M * 2 + 1;
		g = new char[N][M];
		last = new int[N * M * 2 + 2];
		e = new ArrayList<Edge>();

		Arrays.fill(last, -1);

		for (int i = 0; i < N; i++)
			g[i] = readLine().toCharArray();

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				addEdge(getOut(i, j), getIn((i + 1) % N, j), 1 << 30, 0);
				addEdge(getOut(i, j), getIn((i + 2) % N, j), 1 << 30, 0);
				if (j > 0)
					addEdge(getOut(i, j), getIn((i + 1) % N, j - 1), 1 << 30, 0);
				if (j < M - 1)
					addEdge(getOut(i, j), getIn((i + 1) % N, j + 1), 1 << 30, 0);
				if (g[i][j] == '.')
					addEdge(getIn(i, j), getOut(i, j), 1, 0);
			}
		}

		for (int i = 0; i < N; i++) {
			addEdge(src, getIn(i, 0), 1 << 30, 0);
			addEdge(getOut(i, M - 1), sink, 1 << 30, 0);
		}

		out.println(getFlow());

		out.close();
	}

	static int getIn (int r, int c) {
		return (r * M + c) * 2 + 1;
	}

	static int getOut (int r, int c) {
		return (r * M + c) * 2 + 2;
	}

	static int getFlow () {
		int res = 0;
		int curr = 0;
		v = new boolean[N * M * 2 + 2];
		while ((curr = dfs(src, 1 << 30)) > 0) {
			res += curr;
			v = new boolean[N * M * 2 + 2];
		}
		return res;
	}

	static int dfs (int curr, int flow) {
		v[curr] = true;
		if (curr == sink)
			return flow;
		for (int i = last[curr]; i != -1; i = e.get(i).next) {
			if (e.get(i).cost > 0 && !v[e.get(i).dest]) {
				int res = dfs(e.get(i).dest, Math.min(flow, e.get(i).cost));
				if (res > 0) {
					e.get(i).cost -= res;
					e.get(i ^ 1).cost += res;
					return res;
				}
			}
		}
		return 0;
	}

	static void addEdge (int x, int y, int xy, int yx) {
		boolean valid = true;
		for (int i = last[x]; i != -1; i = e.get(i).next) {
			if (e.get(i).dest == y) {
				e.get(i).cost += xy;
				e.get(i ^ 1).cost += yx;
				valid = false;
			}
		}
		if (valid) {
			e.add(new Edge(y, xy, last[x]));
			last[x] = cnt++;
			e.add(new Edge(x, yx, last[y]));
			last[y] = cnt++;
		}
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