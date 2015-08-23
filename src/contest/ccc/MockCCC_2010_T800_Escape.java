package contest.ccc;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class MockCCC_2010_T800_Escape {
	public static void main (String[] args) {
		Scanner scan = new Scanner(System.in);
		int r = scan.nextInt();
		int c = scan.nextInt();
		int k = scan.nextInt();
		scan.nextLine();
		char[][] grid = new char[r][];
		boolean[][] visited = new boolean[r][c];
		int r1 = 0;
		int c1 = 0;
		for (int x = 0; x < grid.length; x++) {
			String s = scan.nextLine();
			grid[x] = s.toCharArray();
			if (s.indexOf('T') != -1) {
				r1 = x;
				c1 = s.indexOf('T');
			}
		}
		// 0 is r, 1 is c, 2 is moves, 3 is k
		Queue<int[]> moves = new LinkedList<int[]>();
		moves.add(new int[] {r1, c1, 0, k});
		// System.out.println(grid.length + " " + grid[0].length);
		while (!moves.isEmpty()) {
			int[] curr = moves.poll();

			if (curr[0] < 0 || curr[1] < 0 || curr[0] >= r || curr[1] >= c
					|| grid[curr[0]][curr[1]] == '#'
					|| visited[curr[0]][curr[1]])
				continue;
			if (curr[3] < 0) {
				visited[curr[0]][curr[1]] = false;
				continue;
			}
			// System.out.printf("%d %d %d %d\n", curr[0], curr[1], curr[2],
			// curr[3]);
			visited[curr[0]][curr[1]] = true;
			if (grid[curr[0]][curr[1]] == 'E') {
				System.out.println(curr[2]);
				scan.close();
				return;
			}
			if (grid[curr[0]][curr[1]] == 'R') {
				moves.add(new int[] {curr[0] - 1, curr[1], curr[2] + 1, k - 1});
				moves.add(new int[] {curr[0] + 1, curr[1], curr[2] + 1, k - 1});
				moves.add(new int[] {curr[0], curr[1] - 1, curr[2] + 1, k - 1});
				moves.add(new int[] {curr[0], curr[1] + 1, curr[2] + 1, k - 1});
			} else {
				moves.add(new int[] {curr[0] - 1, curr[1], curr[2] + 1,
						curr[3] - 1});
				moves.add(new int[] {curr[0] + 1, curr[1], curr[2] + 1,
						curr[3] - 1});
				moves.add(new int[] {curr[0], curr[1] - 1, curr[2] + 1,
						curr[3] - 1});
				moves.add(new int[] {curr[0], curr[1] + 1, curr[2] + 1,
						curr[3] - 1});
			}
		}
		System.out.println("T-800 Terminated.");
		scan.close();
	}
}
