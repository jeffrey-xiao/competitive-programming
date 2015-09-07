package codebook.graph.network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class PushRelabel {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static Edge[] e;
	static int[] last;
	static int n, m, cnt;

	static int[] height;
	static int[] count;
	static int[] excess;
	static boolean[] active;
	static Queue<Integer> q = new ArrayDeque<Integer>();

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		m = readInt();

		last = new int[n];
		e = new Edge[2 * m];

		height = new int[n];
		count = new int[2 * n];
		excess = new int[n];
		active = new boolean[n];

		for (int i = 0; i < n; i++)
			last[i] = -1;

		for (int i = 0; i < m; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			addEdge(a, b, c, 0);
		}

		out.println(getFlow(0, n - 1));
		out.close();
	}

	static int getFlow (int s, int t) {
		count[0] = n - 1;
		count[n] = 1;

		height[s] = n;
		active[s] = active[t] = true;
		for (int i = last[s]; i != -1; i = e[i].next) {
			excess[s] += e[i].cap;
			push(i);
		}
		while (!q.isEmpty()) {
			int curr = q.poll();
			active[curr] = false;
			discharge(curr);
		}
		int totalFlow = 0;
		for (int i = last[s]; i != -1; i = e[i].next)
			totalFlow += e[i].flow;
		return totalFlow;
	}

	static void discharge (int v) {
		for (int i = last[v]; i != -1 && excess[v] > 0; i = e[i].next)
			push(i);
		if (excess[v] > 0) {
			if (count[height[v]] == 1)
				gap(height[v]);
			else
				relabel(v);
		}
	}

	static void relabel (int v) {
		count[height[v]]--;
		height[v] = 2 * n;
		for (int i = last[v]; i != -1; i = e[i].next)
			if (e[i].cap - e[i].flow > 0)
				height[v] = Math.min(height[v], height[e[i].dest] + 1);
		count[height[v]]++;
		add(v);
	}

	static void gap (int h) {
		for (int i = 0; i < n; i++) {
			if (height[i] < h)
				continue;
			count[height[i]]--;
			height[i] = Math.max(height[i], n + 1);
			count[height[i]]++;
			add(i);
		}
	}

	static void push (int i) {
		int flow = Math.min(excess[e[i].src], e[i].cap - e[i].flow);
		if (height[e[i].src] <= height[e[i].dest] || flow == 0)
			return;
		e[i].flow += flow;
		e[i ^ 1].flow -= flow;
		excess[e[i].src] -= flow;
		excess[e[i].dest] += flow;
		add(e[i].dest);
	}

	static void add (int i) {
		if (!active[i] && excess[i] > 0) {
			active[i] = true;
			q.offer(i);
		}
	}

	static void addEdge (int x, int y, int xy, int yx) {
		e[cnt] = new Edge(x, y, xy, 0, last[x]);
		last[x] = cnt++;
		e[cnt] = new Edge(y, x, yx, 0, last[y]);
		last[y] = cnt++;
	}

	static class Edge {
		int src, dest, cap, flow, next;

		Edge (int src, int dest, int cap, int flow, int next) {
			this.src = src;
			this.dest = dest;
			this.cap = cap;
			this.flow = flow;
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
