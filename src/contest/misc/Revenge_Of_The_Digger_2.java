package contest.misc;

import java.util.*;
import java.io.*;

public class Revenge_Of_The_Digger_2 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M, src, sink, cnt;
	static Edge[] e;
	static int[] dist, last;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		src = 0;

		e = new Edge[(N + M) * 2];
		dist = new int[2 * N];
		last = new int[2 * N];

		Arrays.fill(last, -1);

		for (int i = 0; i < N; i++) {
			int flow = readInt();
			addEdge(in(i), out(i), flow, 0);
			if (flow == 0)
				sink = in(i);
		}

		for (int i = 0; i < M; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			addEdge(out(a), in(b), 1 << 30, 0);
		}

		out.println(getFlow());
		out.close();
	}

	static int in (int x) {
		return 2 * x;
	}

	static int out (int x) {
		return 2 * x + 1;
	}

	static int getFlow () {
		int res = 0;
		int curr = 0;
		while (getPath())
			while ((curr = dfs(src, 1 << 30)) > 0)
				res += curr;
		return res;
	}

	static boolean getPath () {
		dist = new int[2 * N];
		for (int i = 0; i < 2 * N; i++)
			dist[i] = -1;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(src);
		dist[src] = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int i = last[curr]; i != -1; i = e[i].next) {
				if (e[i].cost > 0 && dist[e[i].dest] == -1) {
					dist[e[i].dest] = dist[curr] + 1;
					q.offer(e[i].dest);
				}
			}
		}

		return dist[sink] != -1;
	}

	static int dfs (int curr, int flow) {
		if (curr == sink)
			return flow;
		for (int i = last[curr]; i != -1; i = e[i].next) {
			if (e[i].cost > 0 && dist[e[i].dest] == dist[curr] + 1) {
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
