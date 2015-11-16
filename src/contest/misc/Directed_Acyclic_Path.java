package contest.misc;

import java.util.ArrayList;
import java.util.Scanner;

public class Directed_Acyclic_Path {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int edges = scan.nextInt();
		ArrayList<ArrayList<Integer>> adjlist = new ArrayList<ArrayList<Integer>>();
		for (int x = 0; x < edges; x++)
			adjlist.add(new ArrayList<Integer>());
		// 0-> source, 1-> destination, 2-> distance

		for (int x = 0; x < edges; x++) {
			for (int y = 0; y < edges; y++) {
				int input = scan.nextInt();
				if (input != 0)
					adjlist.get(x).add(y);
			}
		}
		boolean[] vertex = new boolean[edges];
		vertex[0] = true;
		System.out.println(isDAG(0, vertex, adjlist) ? "YES" : "NO");

	}

	public static boolean isDAG (int curr, boolean[] vertex, ArrayList<ArrayList<Integer>> edges) {
		for (Integer i : edges.get(curr)) {
			if (vertex[i])
				return false;
			vertex[i] = true;
			return isDAG(i, vertex, edges);
		}
		return true;
	}
}
