package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class USACO_2014_Cross_Country_Skiing {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		int[][] grid = new int[r][c];
		long[][] dist = new long[r][c];
		ArrayList<int[]> waypoints = new ArrayList<int[]>();
		boolean[][] visited = new boolean[r][c];
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				grid[x][y] = readInt();
				dist[x][y] = Integer.MAX_VALUE;
			}
		}
		int startx = 0;
		int starty = 0;
		for (int x = 0; x < r; x++) {
			for (int y = 0; y < c; y++) {
				int i = readInt();
				if (i == 1)
					waypoints.add(new int[] {x, y});
			}
		}
		startx = waypoints.get(0)[0];
		starty = waypoints.get(0)[1];
		// System.out.println(startx + " " + starty);
		Queue<int[]> moves = new LinkedList<int[]>();
		moves.offer(new int[] {startx, starty, 0});
		while (!moves.isEmpty()) {
			int[] curr = moves.poll();
			// System.out.println(curr[0] + " " + curr[1] + " " + curr[2]);
			if (curr[2] >= dist[curr[0]][curr[1]])
				continue;
			dist[curr[0]][curr[1]] = curr[2];
			if (!visited[curr[0]][curr[1]]) {
				visited[curr[0]][curr[1]] = true;
				if (curr[0] + 1 < r)
					moves.add(new int[] {curr[0] + 1, curr[1], Math.abs(grid[curr[0] + 1][curr[1]] - grid[curr[0]][curr[1]])});
				if (curr[0] - 1 >= 0)
					moves.add(new int[] {curr[0] - 1, curr[1], Math.abs(grid[curr[0] - 1][curr[1]] - grid[curr[0]][curr[1]])});
				if (curr[1] + 1 < c)
					moves.add(new int[] {curr[0], curr[1] + 1, Math.abs(grid[curr[0]][curr[1] + 1] - grid[curr[0]][curr[1]])});
				if (curr[1] - 1 >= 0)
					moves.add(new int[] {curr[0], curr[1] - 1, Math.abs(grid[curr[0]][curr[1] - 1] - grid[curr[0]][curr[1]])});
			}
		}
		/*
		 * for(int x = 0; x < r; x++){ for(int y = 0; y < c; y++){
		 * System.out.print(dist[x][y] + " "); } System.out.println(); }
		 */

		long max = Integer.MIN_VALUE;
		for (int[] i : waypoints) {
			max = Math.max(max, dist[i[0]][i[1]]);
		}
		System.out.println(max);
	}

	static String next () throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong () throws IOException {
		return Long.parseLong(next());
	}

	static int readInt () throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble () throws IOException {
		return Double.parseDouble(next());
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
