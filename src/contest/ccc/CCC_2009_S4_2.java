package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2009_S4_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfCities = readInt();
		int numOfRoutes = readInt();
		int[] cities = new int[numOfCities];
		ArrayList<ArrayList<short[]>> adjlist = new ArrayList<ArrayList<short[]>>();// 0
																					// is
																					// connected
																					// to,
																					// 1
																					// cost,
																					// 2
																					// danger
		for (int x = 0; x < numOfCities; x++) {
			adjlist.add(new ArrayList<short[]>());
			cities[x] = Integer.MAX_VALUE;
		}

		for (int x = 0; x < numOfRoutes; x++) {
			short a = (short) (readShort() - 1);
			short b = (short) (readShort() - 1);
			short c = readShort();
			adjlist.get(a).add(new short[] {b, c});
			adjlist.get(b).add(new short[] {a, c});
		}
		int shops = readInt();
		PriorityQueue<City> pq = new PriorityQueue<City>();
		for (int x = 0; x < shops; x++) {
			int a = readInt() - 1;
			int b = readInt();
			cities[a] = b;
			pq.offer(new City(a, b));
		}
		int destination = readInt() - 1;

		while (!pq.isEmpty()) {

			City curr = pq.poll();

			if (curr.index == destination)
				break;
			if (curr.value > cities[curr.index])
				continue;

			for (int x = 0; x < adjlist.get(curr.index).size(); x++) {
				short[] conn = adjlist.get(curr.index).get(x);
				if (conn[1] + curr.value < cities[conn[0]]) {
					// System.out.println(conn[1] + " " + curr.value + " " +
					// cities[conn[0]]);
					// System.out.println(curr.index + " " + conn[0]);
					// System.out.println("SIZE " + pq.size());
					// System.out.println(pq.peek() != null?pq.peek().index:"");
					// pq.remove(new City(conn[0], cities[conn[0]]));
					// System.out.println("SIZE " + pq.size());
					cities[conn[0]] = conn[1] + curr.value;
					pq.add(new City(conn[0], cities[conn[0]]));
				}
			}
		}
		System.out.println(cities[destination]);
	}

	static class City implements Comparable<City> {
		int index;
		int value;

		City (int index, int value) {
			this.index = index;
			this.value = value;
		}

		@Override
		public int compareTo (City o) {
			return value - o.value;
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof City) {
				City c = (City) o;
				return index == c.index;
			}
			return false;
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

	static short readShort () throws IOException {
		return Short.parseShort(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
