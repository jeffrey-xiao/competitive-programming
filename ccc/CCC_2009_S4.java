package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2009_S4 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfCities = readInt();
		int numOfRoutes = readInt();
		int[] cities = new int[numOfCities];
		boolean[] visited = new boolean[numOfCities];
		ArrayList<ArrayList<int[]>> adjlist = new ArrayList<ArrayList<int[]>>();// 0
																				// is
																				// connected
																				// to,
																				// 1
																				// cost,
																				// 2
																				// danger
		for (int x = 0; x < numOfCities; x++) {
			adjlist.add(new ArrayList<int[]>());
			cities[x] = 10000000;
		}

		for (int x = 0; x < numOfRoutes; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int c = readInt();
			if (a < numOfCities && b < numOfCities) {
				adjlist.get(a).add(new int[] {b, c});
				adjlist.get(b).add(new int[] {a, c});
			}
		}
		int shops = readInt();
		int min = 10000000;
		int index = 0;
		for (int x = 0; x < shops; x++) {
			int a = readInt() - 1;
			int b = readInt();
			cities[a] = b;
			if (b < min) {
				min = b;
				index = a;
			}
		}
		int destination = readInt() - 1;
		while (index != -1 && index != destination) {
			// System.out.println("INDEX: " + index);
			visited[index] = true;
			for (int x = 0; x < adjlist.get(index).size(); x++) {
				int[] conn = adjlist.get(index).get(x);
				if (!visited[conn[0]]) {

					if (conn[1] + cities[index] < cities[conn[0]]) {
						// System.out.println("MODIFY " + conn[0] + " " +
						// conn[1] + " " + cities[index]);
						cities[conn[0]] = conn[1] + cities[index];
						// System.out.println("AFTER MODIFY " +
						// cities[conn[0]]);
					}
				}
			}
			index = -1;
			min = 10000000;
			for (int x = 0; x < numOfCities; x++) {
				if (!visited[x] && cities[x] < min) {
					min = cities[x];
					index = x;
				}
			}
		}
		System.out.println(cities[destination]);
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
