package contest.other;

import java.util.ArrayList;
import java.util.Scanner;

public class Cable_TV {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int numOfVertices = scan.nextInt();
		boolean[] visited = new boolean[numOfVertices];// if visited already
		int[][] vertices = new int[numOfVertices][2];// 0 is cost, 1 is danger
		visited[0] = true;
		for (int x = 1; x < vertices.length; x++) {
			vertices[x][0] = Integer.MAX_VALUE;
			vertices[x][1] = Integer.MAX_VALUE;
		}
		ArrayList<ArrayList<int[]>> adjList = new ArrayList<ArrayList<int[]>>();// 0
																				// is
																				// connected
																				// to,
																				// 1
																				// cost,
																				// 2
																				// danger
		for (int x = 0; x < numOfVertices; x++) {
			adjList.add(new ArrayList<int[]>());
		}
		int numOfEdges = scan.nextInt();
		for (int x = 0; x < numOfEdges; x++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			int c = scan.nextInt();
			int d = scan.nextInt();
			adjList.get(a).add(new int[] {b, c, d});
			adjList.get(b).add(new int[] {a, c, d});
		}
		int curr = 0;

		int index = -1;
		while (index != 0) {
			int minCost = Integer.MAX_VALUE;
			int minDanger = Integer.MAX_VALUE;
			index = 0;
			for (int x = 0; x < adjList.get(curr).size(); x++) {
				int[] connection = adjList.get(curr).get(x);
				if (!visited[connection[0]]) {
					if (connection[2] < vertices[connection[0]][1]) {
						vertices[connection[0]][1] = connection[2];
						vertices[connection[0]][0] = connection[1];
					} else if (connection[2] == vertices[connection[0]][1] && connection[1] < vertices[connection[0]][0]) {
						vertices[connection[0]][0] = connection[1];
					}
					// getting next min connected
				}
				for (int y = 0; y < vertices.length; y++) {
					if (!visited[y] && (vertices[y][1] < minDanger || (vertices[y][1] == minDanger && vertices[y][0] < minCost))) {
						index = y;
						minDanger = vertices[y][1];
						minCost = vertices[y][0];
					}
				}
			}
			curr = index;
			visited[curr] = true;
		}
		int cost = 0;
		int danger = 0;
		for (int[] x : vertices) {
			cost += x[0];
			danger += x[1];
		}
		System.out.println(danger + " " + cost);
	}
}
