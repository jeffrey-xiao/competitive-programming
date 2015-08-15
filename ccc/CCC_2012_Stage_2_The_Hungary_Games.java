package ccc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class CCC_2012_Stage_2_The_Hungary_Games {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfNodes = scan.nextInt();
		boolean[] visited = new boolean[numOfNodes];
		int[][] nodes = new int[numOfNodes][2];
		ArrayList<ArrayList<int[]>> adjlist = new ArrayList<ArrayList<int[]>>();
		for (int x = 0; x < numOfNodes; x++) {
			nodes[x][0] = 2000000;
			nodes[x][1] = 2000000;
			adjlist.add(new ArrayList<int[]>());
		}
		nodes[0][0] = 0;
		nodes[0][1] = 0;
		int numOfEdges = scan.nextInt();
		for (int x = 0; x < numOfEdges; x++) {
			adjlist.get(scan.nextInt() - 1).add(
					new int[] {scan.nextInt() - 1, scan.nextInt()});
		}
		int curr = 0;
		int min = 2000000;
		int index = 0;
		while (index != -1) {
			visited[curr] = true;
			index = -1;
			min = 2000000;
			for (int x = 0; x < adjlist.get(curr).size(); x++) {
				int[] edge = adjlist.get(curr).get(x);
				if (visited[edge[0]])
					continue;
				int[] values = new int[] {nodes[edge[0]][1],
						nodes[edge[0]][0], edge[1] + nodes[curr][1],
						edge[1] + nodes[curr][0]};
				Arrays.sort(values);
				nodes[edge[0]][0] = values[0];
				// System.out.println(values[0] + " " + values[1] + " " +
				// values[2] + " " + values[3]);
				for (int y = 1; y < values.length; y++) {
					if (values[y] > nodes[edge[0]][0]) {
						nodes[edge[0]][1] = values[y];
						break;
					}
				}
				// System.out.println(nodes[edge[0]][0]+" "+nodes[edge[0]][1]);
			}
			for (int x = 0; x < nodes.length; x++) {
				if (!visited[x] && nodes[x][0] < min) {
					min = nodes[x][0];
					index = x;
				}
			}
			curr = index;
		}
		/*
		 * for(int[] x: nodes){ System.out.println(x[0] + " " + x[1]); }
		 */
		System.out.println(nodes[nodes.length - 1][1]);
	}

}
