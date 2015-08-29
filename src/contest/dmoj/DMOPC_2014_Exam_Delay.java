package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class DMOPC_2014_Exam_Delay {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;
	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();

	public static void main (String[] args) throws IOException {
		int v = readInt();
		int e = readInt();
		for (int x = 0; x < v; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < e; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int d = readInt();
			int s = readInt();
			adj.get(a).add(new Edge(b, d, s));
			adj.get(b).add(new Edge(a, d, s));
		}
		State[] s = new State[v];
		for (int x = 0; x < v; x++) {
			s[x] = new State(Integer.MAX_VALUE, Integer.MAX_VALUE, -1, null);
		}
		PriorityQueue<State> pq = new PriorityQueue<>();
		pq.offer(new State(0, 0, 0, -1, null));
		s[0] = new State(0, 0, -1, null);
		while (!pq.isEmpty()) {
			State curr = pq.poll();
			for (Edge next : adj.get(curr.index)) {
				double nextTime = curr.time + next.dist / (double) next.speed;
				int nextInter = curr.inter + 1;
				if (s[next.dest].time < nextTime || (s[next.dest].time == nextTime && s[next.dest].inter <= nextInter))
					continue;
				s[next.dest] = new State(nextTime, nextInter, curr.index, next);
				pq.offer(new State(next.dest, nextTime, nextInter, curr.index, next));
			}
		}
		double delay = 0;
		int c = v - 1;
		int count = 0;
		while (c != 0) {
			delay += s[c].prevE.dist / ((s[c].prevE.speed) * 0.75);
			count++;
			c = s[c].prev;
			// System.out.println(c);
		}
		System.out.println(count);
		System.out.println((int) (Math.round(60 * (delay - s[v - 1].time))));
	}

	static class Edge implements Comparable<Edge> {
		int dest, dist, speed;

		Edge (int dest, int dist, int speed) {
			this.dest = dest;
			this.dist = dist;
			this.speed = speed;
		}

		@Override
		public int compareTo (Edge o) {
			Double d1 = dist / (double) speed;
			Double d2 = o.dist / (double) o.speed;
			return d1.compareTo(d2);
		}
	}

	static class State implements Comparable<State> {
		int index, inter, prev;
		double time;
		Edge prevE;

		State (int index, double time, int inter, int prev, Edge prevE) {
			this.index = index;
			this.time = time;
			this.inter = inter;
			this.prev = prev;
			this.prevE = prevE;
		}

		State (double time, int inter, int prev, Edge prevE) {
			this.time = time;
			this.inter = inter;
			this.prev = prev;
			this.prevE = prevE;
		}

		@Override
		public int compareTo (State o) {
			return new Double(time).compareTo(o.time);
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
