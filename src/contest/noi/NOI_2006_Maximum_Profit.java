package contest.noi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class NOI_2006_Maximum_Profit {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int n;
	static int m;
	static int total = 0;

	static Edge[] e;
	static int[] last;
	static int[] dist;
	static int cnt = 0;

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		last = new int[n + m + 2];
		e = new Edge[1000000];
		for (int x = 0; x < n + m + 2; x++) {
			last[x] = -1;
		}
		for (int x = 0; x < n; x++)
			addEdge(x + m + 1, n + m + 1, readInt(), 0);
		int total = 0;
		for (int x = 1; x <= m; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			addEdge(0, x, c, 0);
			total += c;
			addEdge(x, m + a, 1 << 30, 0);
			addEdge(x, m + b, 1 << 30, 0);
		}
		System.out.println(total - getFlow());
	}

	static int getFlow () {
		int res = 0;
		int curr = 0;
		while (getPath())
			while ((curr = dfs(0, 1 << 30)) > 0)
				res += curr;
		return res;
	}

	static boolean getPath () {
		dist = new int[n + m + 2];
		for (int i = 0; i < n + m + 2; i++)
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
		return dist[n + m + 1] != -1;
	}

	static int dfs (int curr, int flow) {
		if (curr == n + m + 1)
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
