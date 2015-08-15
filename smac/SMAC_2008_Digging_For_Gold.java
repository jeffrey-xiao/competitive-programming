package smac;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class SMAC_2008_Digging_For_Gold {
	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[][][] grid = new char[n][n][];
		for (int x = 0; x < n; x++) {
			readLine();
			for (int y = 0; y < n; y++) {

				grid[x][y] = next().toCharArray();
			}

		}
		int counter = 0;
		Queue<int[]> moves = new LinkedList<int[]>();// z,x,y
		boolean[][][] visited = new boolean[n][n][n];
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < n; y++) {
				if (!visited[0][x][y]) {
					moves.offer(new int[] {0, x, y});
				}
				while (!moves.isEmpty()) {

					int[] c = moves.poll();
					if (c[0] < 0 || c[1] < 0 || c[2] < 0 || c[0] >= n
							|| c[1] >= n || c[2] >= n
							|| visited[c[0]][c[1]][c[2]]
							|| grid[c[0]][c[1]][c[2]] == 'X')
						continue;
					visited[c[0]][c[1]][c[2]] = true;
					if (grid[c[0]][c[1]][c[2]] == '*')
						counter++;
					moves.add(new int[] {c[0] + 1, c[1], c[2]});
					moves.add(new int[] {c[0], c[1] + 1, c[2]});
					moves.add(new int[] {c[0], c[1] - 1, c[2]});
					moves.add(new int[] {c[0], c[1], c[2] + 1});
					moves.add(new int[] {c[0], c[1], c[2] - 1});
				}
			}
		}
		System.out.println(counter);
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
