package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Crab_Graphs {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Edge[] e;
	static int[] last;
	static int cnt;
	static int[] dist;
	static int n, f, m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		for (int t = readInt(); t > 0; t--) {
			n = readInt();
			f = readInt();
			m = readInt();
			last = new int[n * 2 + 2];
			e = new Edge[n * 4 + m * 4];
			cnt = 0;
			for (int i = 0; i < n * 2 + 2; i++)
				last[i] = -1;
			for (int i = 0; i < m; i++) {
				int a = readInt();
				int b = readInt();
				addEdge(a * 2 - 1, b * 2, 1 << 30, 0);
				addEdge(b * 2 - 1, a * 2, 1 << 30, 0);
			}
			for (int i = 1; i <= n; i++) {
				addEdge(0, i * 2 - 1, f, 0);
				addEdge(i * 2, 2 * n + 1, 1, 0);
			}
			//			for (int i = 0; i < 2*n+2; i++) {
			//				out.println(i + " IS CONNECTED TO ");
			//				for (int j = last[i]; j != -1; j = e[j].next) {
			//					out.println("\t " +e[j].dest + " WITH " + e[j].cost);
			//				}
			//			}
			out.println(getFlow());
		}

		out.close();
	}

	static int getFlow () {
		int res = 0;
		while (getPath()) {
			int curr = 0;
			while ((curr = dfs(0, 1 << 30)) > 0)
				res += curr;
		}
		return res;
	}

	static int dfs (int curr, int flow) {
		if (curr == 2 * n + 1)
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

	static boolean getPath () {
		dist = new int[n * 2 + 2];
		for (int i = 0; i < n * 2 + 2; i++)
			dist[i] = -1;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		dist[0] = 0;
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int i = last[curr]; i != -1; i = e[i].next) {
				if (e[i].cost > 0 && dist[e[i].dest] == -1) {
					dist[e[i].dest] = dist[curr] + 1;
					q.offer(e[i].dest);
				}
			}
		}
		return dist[n * 2 + 1] != -1;
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
