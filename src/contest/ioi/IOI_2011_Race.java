package contest.ioi;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class IOI_2011_Race {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, k;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static int[] dist, cnt;
	static int[] distTo;
	static boolean[] exclude;
	static int[] minCnt;
	static ArrayList<Node> inTree = new ArrayList<Node>();

	public static void main (String[] args) throws IOException {
		//br = new BufferedReader(new InputStreamReader(System.in));
		//out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		k = readInt();

		minCnt = new int[k + 1];
		for (int i = 0; i <= k; i++)
			minCnt[i] = 1 << 30;
		dist = new int[n];
		cnt = new int[n];
		distTo = new int[n];
		exclude = new boolean[n];

		for (int i = 0; i < n; i++)
			adj.add(new ArrayList<Edge>());

		for (int i = 0; i < n - 1; i++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();

			adj.get(a).add(new Edge(b, c));
			adj.get(b).add(new Edge(a, c));
		}

		Queue<Integer> q = new ArrayDeque<Integer>();
		q.offer(0);
		int min = 1 << 30;
		while (!q.isEmpty()) {
			int c = q.poll();
			int centroid = getCentroid(c, -1, getSize(c, -1));
			getDist(centroid);
			Queue<Integer> addToHm = new ArrayDeque<Integer>();
			int prev = -2;
			for (Node curr : inTree) {
				if (curr.branch != prev) {
					prev = curr.branch;
					while (!addToHm.isEmpty()) {
						int add = addToHm.poll();
						if (dist[add] <= k)
							minCnt[dist[add]] = Math.min(minCnt[dist[add]], cnt[add]);
					}
				}
				if (k - dist[curr.curr] >= 0)
					min = Math.min(min, cnt[curr.curr] + minCnt[k - dist[curr.curr]]);
				addToHm.offer(curr.curr);
			}
			for (Node curr : inTree)
				if (dist[curr.curr] <= k)
					minCnt[dist[curr.curr]] = 1 << 30;
			inTree.clear();
			exclude[centroid] = true;
			for (Edge next : adj.get(centroid))
				if (!exclude[next.dest])
					q.offer(next.dest);
		}
		out.println(min == 1 << 30 ? -1 : min);
		out.close();
	}

	static int getSize (int curr, int par) {
		int sz = 1;
		for (Edge next : adj.get(curr))
			if (next.dest != par && !exclude[next.dest])
				sz += getSize(next.dest, curr);
		return sz;
	}

	static int getCentroid (int curr, int par, int size) {
		int n = size;
		int sz = 1;
		boolean valid = true;
		for (Edge next : adj.get(curr)) {
			if (next.dest == par || exclude[next.dest])
				continue;
			int ret = getCentroid(next.dest, curr, size);
			if (ret >= 0)
				return ret;
			valid &= -ret <= n / 2;
			sz += -ret;
		}
		valid &= n - sz <= n / 2;
		return valid ? curr : -sz;
	}

	static void getDist (int curr) {
		Stack<State> s = new Stack<State>();
		s.push(new State(curr, -1, 0, 0, -1));
		while (!s.isEmpty()) {
			State u = s.pop();
			inTree.add(new Node(u.curr, u.branch));
			dist[u.curr] = u.distTo;
			cnt[u.curr] = u.cntTo;
			for (Edge v : adj.get(u.curr))
				if (v.dest != u.par && !exclude[v.dest])
					s.push(new State(v.dest, u.curr, u.distTo + v.cost, u.cntTo + 1, u.curr == curr ? v.dest : u.branch));
		}
	}

	static class Node {
		int curr, branch;

		Node (int curr, int branch) {
			this.curr = curr;
			this.branch = branch;
		}
	}

	static class State {
		int curr, par, distTo, cntTo, branch;

		State (int curr, int distTo) {
			this(curr, 0, distTo, 0, 0);
		}

		State (int curr, int par, int distTo) {
			this(curr, par, distTo, 0, 0);
		}

		State (int curr, int par, int distTo, int cntTo, int branch) {
			this.curr = curr;
			this.par = par;
			this.distTo = distTo;
			this.cntTo = cntTo;
			this.branch = branch;
		}
	}

	static class Edge {
		int dest, cost;

		Edge (int dest, int cost) {
			this.dest = dest;
			this.cost = cost;
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
