package contest.ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_2013_Robots {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int n, m, t;
	static ArrayList<Integer> weak, small;
	static LinkedList<Toy> toys = new LinkedList<Toy>();

	public static void main (String[] args) throws IOException {
		n = readInt();
		m = readInt();
		t = readInt();
		weak = new ArrayList<Integer>();
		small = new ArrayList<Integer>();

		for (int i = 0; i < n; i++)
			weak.add(readInt());
		for (int i = 0; i < m; i++)
			small.add(readInt());
		Collections.sort(weak);
		Collections.sort(small);
		for (int i = 0; i < t; i++)
			toys.offer(new Toy(readInt(), readInt()));
		Collections.sort(toys, new Comparator<Toy>() {
			@Override
			public int compare (Toy o1, Toy o2) {
				if (o1.weight == o2.weight)
					return o1.size - o2.size;
				return o1.weight - o2.weight;
			}
		});
		int lo = 0;
		int hi = 500000;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (isPossible(mid)) {
				hi = mid - 1;
			} else {
				lo = mid + 1;
			}
		}
		System.out.println(lo == 501 ? -1 : lo);
	}

	private static boolean isPossible (int t) {
		Queue<Toy> curr = new LinkedList<Toy>(toys);
		// weak calculation
		PriorityQueue<Toy> pq = new PriorityQueue<Toy>();
		for (int i = 0; i < n; i++) {
			int size = curr.size();
			for (int j = 0; j < size; j++) {
				if (weak.get(i) > curr.peek().weight) {
					pq.offer(curr.poll());
				} else {
					break;
				}
			}
			size = Math.min(t, pq.size());
			for (int k = 0; k < size; k++) {
				pq.poll();
			}
		}
		int size = curr.size();
		for (int i = 0; i < size; i++)
			pq.offer(curr.poll());
		// strong calculation
		for (int i = m - 1; i >= 0; i--) {
			size = Math.min(t, pq.size());
			for (int j = 0; j < size; j++)
				if (pq.poll().size >= small.get(i))
					return false;
		}
		return pq.size() == 0;
	}

	static class Toy implements Comparable<Toy> {
		int weight, size;

		Toy (int weight, int size) {
			this.weight = weight;
			this.size = size;
		}

		@Override
		public int compareTo (Toy o) {
			return size == o.size ? o.weight - weight : o.size - size;
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
