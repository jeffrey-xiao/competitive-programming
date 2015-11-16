package contest.ccc;

import java.util.Scanner;

public class CCC_2000_Stage_2_Packet_Routing {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		int[][] edges = new int[scan.nextInt()][3];
		int numOfTest = scan.nextInt();
		// 0-> source, 1-> destination, 2-> distance
		for (int x = 0; x < edges.length; x++) {
			edges[x][0] = scan.nextInt() - 1;
			edges[x][1] = scan.nextInt() - 1;
			edges[x][2] = scan.nextInt();
		}
		for (; numOfTest > 0; numOfTest--) {
			int[][] vertices = new int[n][2];
			// 0 -> current value
			// 1-> if gone to already
			for (int x = 0; x < vertices.length; x++)
				vertices[x][0] = Integer.MAX_VALUE;
			int start = scan.nextInt() - 1;
			int target = scan.nextInt() - 1;
			vertices[start][0] = 0;
			while (vertices[target][1] == 0) {
				int smallestValue = Integer.MAX_VALUE;
				int smallestIndex = 0;
				for (int x = 0; x < vertices.length; x++) {
					if (vertices[x][1] == 0 && vertices[x][0] <= smallestValue) {
						smallestValue = vertices[x][0];
						smallestIndex = x;
					}
				}
				for (int x = 0; x < edges.length; x++) {
					if (edges[x][0] == smallestIndex && vertices[edges[x][1]][1] == 0 && edges[x][2] + vertices[smallestIndex][0] < vertices[edges[x][1]][0]) {
						vertices[edges[x][1]][0] = edges[x][2] + vertices[smallestIndex][0];
					} else if (edges[x][1] == smallestIndex && vertices[edges[x][0]][1] == 0 && edges[x][2] + vertices[smallestIndex][0] < vertices[edges[x][0]][0]) {
						vertices[edges[x][0]][0] = edges[x][2] + vertices[smallestIndex][0];
					}
				}
				vertices[smallestIndex][1] = -1;
			}
			System.out.println(vertices[target][0]);
		}
	}
}
