package contest.ccc;

//use prim's algorithm

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CCC_2003_S5 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) {
		int numOfCities = nextInt();
		int numOfEdges = nextInt();
		int citiesThatSell = nextInt();
		int[][] edges = new int[numOfEdges][3];
		// 0 and 1 are places, 2 is value
		int[] cities = new int[numOfCities];
		ArrayList<ArrayList<int[]>> adjlist = new ArrayList<ArrayList<int[]>>();
		for (int x = 0; x < numOfCities; x++) {
			adjlist.add(new ArrayList<int[]>());
			cities[x] = 50000000;
		}
		for (int x = 0; x < edges.length; x++) {
			int a = nextInt() - 1;
			int b = nextInt() - 1;
			int c = nextInt();
			adjlist.get(a).add(new int[] {b, c});
			adjlist.get(b).add(new int[] {a, c});
		}

		boolean[] visited = new boolean[numOfCities];
		int[] destinations = new int[citiesThatSell];
		/*
		 * for(int x = 1; x < cities.length; x++) cities[x][0] = -1;
		 */
		for (citiesThatSell -= 1; citiesThatSell >= 0; citiesThatSell--)
			destinations[citiesThatSell] = nextInt() - 1;
		cities[0] = 0;
		visited[0] = true;

		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(numOfCities,
				new Comparator<int[]>() {
					@Override
					public int compare (int[] arg0, int[] arg1) {
						if (arg0[1] > arg1[1])
							return -1;
						else if (arg0[1] < arg1[1])
							return 1;
						else
							return 0;
					}
				});
		// edges, 0 is dest, 2 is cost
		for (int x = 0; x < adjlist.get(0).size(); x++) {
			int[] next = adjlist.get(0).get(x);
			pq.add(next);
		}
		while (!pq.isEmpty()) {
			int[] curr = pq.poll();
			int currDest = curr[0];
			if (visited[currDest])
				continue;
			// System.out.println(cities[currDest]+" "+(curr[1]));
			cities[currDest] = Math.min(cities[currDest], curr[1]);
			visited[currDest] = true;
			for (int x = 0; x < adjlist.get(currDest).size(); x++) {
				int[] next = adjlist.get(currDest).get(x);
				if (!visited[next[0]]) {

					pq.add(next);
				}
			}
		}

		int max = 2000000;
		for (int x = 0; x < destinations.length; x++)
			if (cities[destinations[x]] < max)
				max = cities[destinations[x]];
		System.out.println(max);

	}

	static String next () {
		while (st == null || !st.hasMoreTokens()) {
			try {
				st = new StringTokenizer(br.readLine().trim());
			} catch (IOException e) {
			}
		}
		return st.nextToken();
	}

	static int nextInt () {
		return Integer.parseInt(next());
	}

	static String nextLine () {
		String s = "";
		try {
			s = br.readLine().trim();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return s;
	}
}
