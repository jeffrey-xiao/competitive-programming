package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class MockCCC_2014_S4 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfCities = readInt();
		int numOfRoads = readInt();
		int[][] cities = new int[numOfCities][2];// 0 is cost and 1 is prev city
		for (int x = 0; x < cities.length; x++)
			cities[x][0] = 2000000;
		boolean visited[] = new boolean[numOfCities];
		ArrayList<ArrayList<int[]>> list = new ArrayList<ArrayList<int[]>>();
		for (int x = 0; x < numOfCities; x++) {
			list.add(new ArrayList<int[]>());
		}

		for (int x = 0; x < numOfRoads; x++) {
			int c = readInt() - 1;
			int d = readInt() - 1;
			list.get(c).add(new int[] {d, 1});
			list.get(d).add(new int[] {c, 1});
		}

		cities[0][0] = 0;
		int curr = 0;
		int min = 2000000;
		int index = 0;
		while (index != -1) {
			index = -1;
			visited[curr] = true;
			min = 2000000;
			for (int x = 0; x < list.get(curr).size(); x++) {

				int[] con = list.get(curr).get(x);
				if (visited[con[0]])
					continue;
				if (con[1] + cities[curr][0] < cities[con[0]][0]) {
					cities[con[0]][1] = curr;
					cities[con[0]][0] = con[1] + cities[curr][0];
				}

			}
			if (cities[cities.length - 1][0] < 2000000)
				break;
			for (int x = 0; x < numOfCities; x++) {
				if (!visited[x] && cities[x][0] < min) {
					min = cities[x][0];
					index = x;
				}
			}
			curr = index;
		}
		if (cities[cities.length - 1][0] == 2000000) {
			System.out.println("No");
			return;
		}
		int sum = cities[cities.length - 1][0];

		int counter = numOfCities - 1;
		while (counter != 0) {
			int prev = cities[counter][1];
			// System.out.println(counter + " " + prev);
			for (int x = 0; x < list.get(counter).size(); x++) {
				if (list.get(counter).get(x)[0] == prev) {
					list.get(counter).get(x)[1] = 2;
					break;
				}
			}
			for (int x = 0; x < list.get(prev).size(); x++) {
				if (list.get(prev).get(x)[0] == counter) {
					list.get(prev).get(x)[1] = 2;
					break;
				}
			}
			counter = cities[counter][1];
		}

		visited = new boolean[numOfCities];
		cities = new int[numOfCities][2];
		for (int x = 0; x < cities.length; x++)
			cities[x][0] = 2000000;
		cities[0][0] = 0;
		curr = 0;
		min = 2000000;
		index = 0;
		while (index != -1) {
			index = -1;
			visited[curr] = true;
			min = 2000000;
			for (int x = 0; x < list.get(curr).size(); x++) {

				int[] con = list.get(curr).get(x);
				// System.out.println("X:" +(curr+1) + " " + (con[0]+1) + " " +
				// con[1]);
				if (visited[con[0]])
					continue;

				if (con[1] + cities[curr][0] - 1 < cities[con[0]][0]) {
					cities[con[0]][1] = curr;
					cities[con[0]][0] = con[1] + cities[curr][0] - 1;
				}
			}
			for (int x = 0; x < numOfCities; x++) {
				// System.out.println(x + " " + cities[x][0]);
				if (!visited[x] && cities[x][0] < min) {
					// System.out.println("ASDASDASDSADA " + x);
					min = cities[x][0];
					index = x;
				}
			}
			curr = index;
			// System.out.println(curr);
		}
		// System.out.println(cities[numOfCities-1][0]);
		// System.out.println(sum);
		if (cities[numOfCities - 1][0] != sum)
			System.out.println("Yes");
		else
			System.out.println("No");
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
