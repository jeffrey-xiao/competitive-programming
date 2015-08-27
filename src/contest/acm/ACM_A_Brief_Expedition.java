package contest.acm;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ACM_A_Brief_Expedition {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		for (int t = scan.nextInt(); t > 0; t--) {
			int r = scan.nextInt();
			int c = scan.nextInt();
			char[][] grid = new char[r][];
			int cx = 0;
			int cy = 0;
			for (int x = 0; x < grid.length; x++) {
				String s = scan.next();
				grid[x] = s.toCharArray();
				if (s.indexOf('C') != -1) {
					cx = x;
					cy = s.indexOf('C');
				}
			}
			boolean[][] visited = new boolean[r][c];
			int total = 0;
			int max = 0;
			Queue<int[]> moves = new LinkedList<int[]>(); // 0 x, 1 y, 2 moves
			moves.offer(new int[] {cx, cy, 0});
			while (!moves.isEmpty()) {

				int[] i = moves.poll();

				if (i[0] < 0 || i[1] < 0 || i[0] >= r || i[1] >= c || visited[i[0]][i[1]] || grid[i[0]][i[1]] == '#')
					continue;
				visited[i[0]][i[1]] = true;
				if (grid[i[0]][i[1]] == 'S') {
					total += i[2] * 2 + 60;
					if (i[2] > max)
						max = i[2];
				}
				moves.add(new int[] {i[0] + 1, i[1], i[2] + 1});
				moves.add(new int[] {i[0] - 1, i[1], i[2] + 1});
				moves.add(new int[] {i[0], i[1] + 1, i[2] + 1});
				moves.add(new int[] {i[0], i[1] - 1, i[2] + 1});
			}
			System.out.println(total - max);
		}
	}
}
