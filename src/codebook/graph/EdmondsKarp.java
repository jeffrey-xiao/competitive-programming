package codebook.graph;

import java.util.*;
import java.io.*;

public class EdmondsKarp {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Edge[] e;
	static int[] last;
	static int n, m, cnt;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();
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
		while ((curr = bfs()) > 0)
			res += curr;
		return res;
	}

	static int bfs () {
		boolean[] v = new boolean[n];
		int[] prev = new int[n];
		int[] index = new int[n];
		v[0] = true;
		prev[0] = -1;
		index[0] = -1;
		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int i = last[curr]; i != -1; i = e[i].next) {
				if (v[e[i].dest] || e[i].cost <= 0)
					continue;
				v[e[i].dest] = true;
				prev[e[i].dest] = curr;
				index[e[i].dest] = i;
				q.offer(e[i].dest);
			}
		}
		if (!v[n - 1])
			return 0;
		int currNode = n - 1;
		int flow = 1 << 30;
		while (prev[currNode] != -1) {
			flow = Math.min(flow, e[index[currNode]].cost);
			currNode = prev[currNode];
		}
		currNode = n - 1;
		while (prev[currNode] != -1) {
			e[index[currNode]].cost -= flow;
			e[index[currNode] ^ 1].cost += flow;
			currNode = prev[currNode];
		}
		return flow;
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
