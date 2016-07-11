package contest.misc;

import java.util.*;
import java.io.*;

public class TooSpookyForMe {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static HashMap<Integer, Integer> toX = new HashMap<Integer, Integer>();
	static HashMap<Integer, Integer> toIndex = new HashMap<Integer, Integer>();

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int l = readInt();
		int s = readInt();
		PriorityQueue<Event> moves = new PriorityQueue<Event>();
		int count = 0;
		TreeSet<Integer> m = new TreeSet<Integer>();
		for (int x = 0; x < n; x++) {
			int a = readInt();
			int b = readInt();
			int c = readInt();
			m.add(a);
			m.add(b + 1);
			moves.offer(new Event(a, c));
			moves.offer(new Event(b + 1, -c));
		}
		m.add(0);
		moves.offer(new Event(0, 0));
		for (Integer i : m) {
			if (!toX.containsKey(i)) {
				toX.put(count, i);
				toIndex.put(i, count++);
			}
		}
		int[] diff = new int[m.size()];
		int curr = 0;
		while (!moves.isEmpty()) {
			Event next = moves.poll();
			curr += next.cost;
			while (!moves.isEmpty() && moves.peek().x == next.x) {
				next = moves.poll();
				curr += next.cost;
			}
			diff[toIndex.get(next.x)] = curr;
		}
		int c = 0;
		for (int x = 0; x < m.size() - 1; x++)
			if (diff[x] >= s) {
				c += (toX.get(x + 1) - toX.get(x));
			}
		System.out.println(l - c);
	}

	static class Event implements Comparable<Event> {
		int x;
		int cost;

		Event (int x, int cost) {
			this.x = x;
			this.cost = cost;
		}

		@Override
		public int compareTo (Event o) {
			return x - o.x;
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
