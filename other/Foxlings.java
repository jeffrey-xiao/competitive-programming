package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Foxlings {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		ArrayList<ArrayList<Integer>> foxes = new ArrayList<ArrayList<Integer>>();
		int n = scan.nextInt();
		boolean[] visited = new boolean[n];
		for (int x = 0; x < n; x++)
			foxes.add(new ArrayList<Integer>());
		int m = scan.nextInt();
		for (int x = 0; x < m; x++) {
			int a = scan.nextInt() - 1;
			int b = scan.nextInt() - 1;
			foxes.get(Math.min(a, b)).add(Math.max(a, b));
		}
		int count = 0;
		Queue<Integer> moves = new LinkedList<Integer>();
		for (int x = 0; x < n; x++) {
			if (visited[x])
				continue;
			visited[x] = true;
			count++;
			int next = x;
			moves.add(next);
			while (!moves.isEmpty()) {
				next = moves.poll();
				for (int y = 0; y < foxes.get(next).size(); y++) {
					int curr = foxes.get(next).get(y);
					if (visited[curr])
						count--;
					visited[curr] = true;
					moves.add(curr);

				}
			}
		}
		System.out.println(count);
	}
}
