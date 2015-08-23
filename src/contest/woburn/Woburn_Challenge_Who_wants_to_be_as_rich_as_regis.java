package contest.woburn;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Woburn_Challenge_Who_wants_to_be_as_rich_as_regis {
	static Scanner scan = new Scanner(System.in);

	public static void main (String[] args) {
		int n = scan.nextInt();
		while (n != -1) {
			char[][][] grid = new char[n][n][];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					grid[x][y] = scan.next().toCharArray();
				}
			}
			int counter = 0;
			boolean[][][] visited = new boolean[n][n][n];
			int startx = scan.nextInt() - 1;
			int starty = scan.nextInt() - 1;
			Queue<int[]> moves = new LinkedList<int[]>();// z,x,y
			moves.add(new int[] {0, startx, starty});
			while (!moves.isEmpty()) {
				int[] c = moves.poll();
				if (c[0] < 0 || c[1] < 0 || c[2] < 0 || c[0] >= n || c[1] >= n
						|| c[2] >= n || visited[c[0]][c[1]][c[2]]
						|| grid[c[0]][c[1]][c[2]] == '1')
					continue;
				visited[c[0]][c[1]][c[2]] = true;
				counter++;
				moves.add(new int[] {c[0] + 1, c[1], c[2]});
				moves.add(new int[] {c[0] - 1, c[1], c[2]});
				moves.add(new int[] {c[0], c[1] + 1, c[2]});
				moves.add(new int[] {c[0], c[1] - 1, c[2]});
				moves.add(new int[] {c[0], c[1], c[2] + 1});
				moves.add(new int[] {c[0], c[1], c[2] - 1});
			}
			System.out.println(counter);
			n = scan.nextInt();
		}
	}
}
