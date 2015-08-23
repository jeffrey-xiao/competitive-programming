package contest.ccc;

import java.util.Scanner;

public class CCC_2003_S5_2 {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfCities = scan.nextInt();
		int numOfRoads = scan.nextInt();
		int numOfDest = scan.nextInt();
		int[][] costs = new int[numOfCities][];
		int[] cities = new int[numOfCities];// 0 is not visited, 1 is visited
		boolean[] visited = new boolean[numOfCities];
		visited[0] = true;
		cities[0] = 1000000000;
		for (int x = 0; x < costs.length; x++) {
			costs[x] = new int[costs.length - x - 1];
		}
		for (int x = 0; x < numOfRoads; x++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			int c = scan.nextInt();
			int i1 = Math.min(a, b);
			int i2 = Math.max(a, b);
			if (c > costs[i1][i2 - i1 - 1]) {
				costs[i1][i2 - i1 - 1] = c;
			}
		}

		int[] destinations = new int[numOfDest];
		for (int x = 0; x < destinations.length; x++)
			destinations[x] = scan.nextInt() - 1;
		// 0 is cost, 1 is city
		int curr = 0;
		int max = -1;
		int index = 0;
		while (max != 0) {
			max = 0;
			index = 0;
			for (int x = 0; x < costs.length; x++) {
				int i1 = Math.min(curr, x);
				int i2 = Math.max(curr, x);
				// System.out.println(costs[i1][i2-i1-1]);
				if (!visited[x]
						&& cities[x] < Math.min(costs[i1][i2 - i1 - 1],
								cities[curr]))
					cities[x] = Math.min(costs[i1][i2 - i1 - 1], cities[curr]);
				if (!visited[x] && cities[x] > max) {
					max = cities[x];
					index = x;
				}
			}
			visited[index] = true;
			curr = index;
		}
		int min = Integer.MAX_VALUE;
		for (int x : destinations)
			if (cities[x] < min)
				min = cities[x];
		System.out.println(min);
	}
}