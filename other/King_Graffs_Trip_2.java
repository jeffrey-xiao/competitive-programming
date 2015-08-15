package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class King_Graffs_Trip_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static ArrayList<ArrayList<Edge>> adj = new ArrayList<ArrayList<Edge>>();
	static boolean[] temple = new boolean[10001];

	static int n, m, s, e;
	static long t;

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		s = readInt() - 1;
		e = readInt() - 1;
		t = readLong();
		for (int x = 0; x < n; x++)
			adj.add(new ArrayList<Edge>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			long c = readLong();
			adj.get(a).add(new Edge(b, c));
		}
		int nt = readInt();
		for (int x = 0; x < nt; x++)
			temple[readInt() - 1] = true;
		long lo = 0;
		long hi = Long.MAX_VALUE - 1;

		// binary search for the minimum maximum
		while (lo <= hi) {
			long mid = lo + (hi - lo) / 2;
			if (isPossible(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println(lo == Long.MAX_VALUE ? -1 : lo);
	}

	private static boolean isPossible (long tt) {
		long[] min = new long[n];
		long[] minTime = new long[n];
		for (int x = 0; x < n; x++) {
			min[x] = Long.MAX_VALUE;
			minTime[x] = Long.MAX_VALUE;
		}
		min[s] = 0;
		minTime[s] = 0;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
		pq.offer(new Vertex(s, 0, 0, 0));
		while (!pq.isEmpty()) {
			Vertex curr = pq.poll();
			if (curr.curr == e)
				return true;
			for (Edge next : adj.get(curr.curr)) {
				// computing the values for the next location
				long nextTime = curr.time + next.cost;
				long nextCurrTemple = 0;
				if (!temple[next.dest])
					nextCurrTemple = curr.currTemple + next.cost;
				long nextMaxTemple = Math.max(curr.maxTemple, curr.currTemple
						+ next.cost);
				if ((nextMaxTemple >= min[next.dest] && nextTime >= minTime[next.dest])
						|| nextTime > t || nextMaxTemple > tt)
					continue;
				// always memoize the minimum maximum distance without any
				// shrine as well as the time for that shrine
				// if the minimum maximum distance is bigger than the current
				// minimum, but has a time that is lower, it is possible for
				// that to be a solution
				if (nextMaxTemple < min[next.dest]) {
					min[next.dest] = nextMaxTemple;
					minTime[next.dest] = nextTime;
				}
				pq.offer(new Vertex(next.dest, nextTime, nextCurrTemple,
						nextMaxTemple));
			}
		}
		return false;
	}

	static class Vertex implements Comparable<Vertex> {
		int curr;
		Long time, currTemple, maxTemple;

		Vertex (int curr, long time, long currTemple, long maxTemple) {
			this.curr = curr;
			this.time = time;
			this.currTemple = currTemple;
			this.maxTemple = maxTemple;
		}

		@Override
		public int compareTo (Vertex o) {
			if (time == o.time)
				return maxTemple.compareTo(o.maxTemple);
			return time.compareTo(o.time);
		}
	}

	static class Edge {
		int dest;
		long cost;

		Edge (int dest, long cost) {
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
