package contest.other;

import java.util.Scanner;

public class Shortest_Path {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		int[][] edges = new int[scan.nextInt()][3];
		// 0-> source, 1-> destination, 2-> distance
		for (int x = 0; x < edges.length; x++) {
			edges[x][0] = scan.nextInt();
			edges[x][1] = scan.nextInt();
			edges[x][2] = scan.nextInt();
		}
		int[][] vertices = new int[n][2];
		// 0 -> current value
		// 1-> if gone to already
		for (int x = 1; x < vertices.length; x++)
			vertices[x][0] = Integer.MAX_VALUE;
		while (vertices[n - 1][1] == 0) {
			/*
			 * for(int[] x: vertices) System.out.print(x[0] + " ");
			 * System.out.println();
			 */
			int smallestValue = Integer.MAX_VALUE;
			int smallestIndex = 0;
			for (int x = 0; x < vertices.length; x++) {
				if (vertices[x][1] == 0 && vertices[x][0] <= smallestValue) {
					smallestValue = vertices[x][0];
					smallestIndex = x;
				}
			}
			for (int x = 0; x < edges.length; x++) {
				if (edges[x][0] == smallestIndex + 1
						&& vertices[edges[x][1] - 1][1] == 0
						&& edges[x][2] + vertices[smallestIndex][0] < vertices[edges[x][1] - 1][0]) {
					vertices[edges[x][1] - 1][0] = edges[x][2]
							+ vertices[smallestIndex][0];
				}
			}
			vertices[smallestIndex][1] = -1;
		}
		System.out.println(vertices[n - 1][0]);

	}
}
