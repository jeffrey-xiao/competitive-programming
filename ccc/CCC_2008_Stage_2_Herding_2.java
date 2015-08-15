package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_2008_Stage_2_Herding_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int r;
	static int c;
	static int count = 1;
	static HashSet<Integer> nums = new HashSet<Integer>();

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		char[][] grid = new char[r][];
		for (int x = 0; x < r; x++) {
			grid[x] = next().toCharArray();
		}
		int[][] id = new int[r][c];
		for (int z = 0; z < 4; z++) {
			for (int x = 0; x < r; x++) {
				for (int y = 0; y < c; y++) {
					computeState(x, y, id, grid);
				}
			}
			for (int x = r - 1; x >= 0; x--) {
				for (int y = 0; y < c; y++) {
					computeState(x, y, id, grid);
				}
			}
			for (int x = 0; x < r; x++) {
				for (int y = c - 1; y >= 0; y--) {
					computeState(x, y, id, grid);
				}
			}
			for (int x = r - 1; x >= 0; x--) {
				for (int y = c - 1; y >= 0; y--) {
					computeState(x, y, id, grid);
				}
			}
		}

		for (int x = 0; x < r; x++)
			for (int y = 0; y < c; y++)
				nums.add(id[x][y]);
		System.out.println(nums.size());
	}

	private static void computeState (int x, int y, int[][] id, char[][] g) {
		int nx = x;
		int ny = y;
		switch (g[x][y]) {
			case 'S':
				nx++;
				break;
			case 'N':
				nx--;
				break;
			case 'W':
				ny--;
				break;
			case 'E':
				ny++;
				break;
		}
		if (id[x][y] == 0 && id[nx][ny] == 0) {
			id[x][y] = count;
			id[nx][ny] = count++;
		} else if (id[x][y] == 0) {
			id[x][y] = id[nx][ny];
		} else if (id[nx][ny] == 0)
			id[nx][ny] = id[x][y];
		else if (id[x][y] != 0 && id[nx][ny] != 0) {
			int min = Math.min(id[nx][ny], id[x][y]);
			id[nx][ny] = id[x][y] = min;
		}
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
