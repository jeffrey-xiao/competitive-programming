package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2012_Stage_2_Editor_Distance {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static int[] movex = {0, 0, -1, 1};
	static int[] movey = {-1, 1, 0, 0};

	public static void main (String[] args) throws IOException {
		boolean[][] visited = new boolean[100001][81];
		int n = readInt();
		int[] rows = new int[n];
		for (int x = 0; x < n; x++)
			rows[x] = readInt();
		Point s = new Point(readInt() - 1, readInt() - 1, 0);
		Point e = new Point(readInt() - 1, readInt() - 1);
		Queue<Point> moves = new LinkedList<Point>();
		moves.offer(s);
		visited[s.x][s.y] = true;
		while (!moves.isEmpty()) {
			Point curr = moves.poll();
			// System.out.println(curr.x + " " + curr.y + " " + curr.count);
			if (curr.x == e.x && curr.y == e.y) {
				System.out.println(curr.count);
				return;
			}

			for (int z = 0; z < 4; z++) {
				int nx = curr.x + movex[z];
				int ny = curr.y + movey[z];
				if (nx < 0)
					nx = 0;
				else if (nx >= n)
					nx = n - 1;
				if (movex[z] != 0 && rows[nx] <= ny) {
					ny = rows[nx] - 1;
				}
				if (ny < 0) {
					nx = Math.max(0, nx - 1);
					ny = rows[nx] - 1;
				} else if (ny >= rows[nx] && nx < n - 1) {
					nx++;
					ny = 0;
				}
				ny = Math.min(rows[nx] - 1, ny);
				if (visited[nx][ny])
					continue;
				visited[nx][ny] = true;
				moves.offer(new Point(nx, ny, curr.count + 1));
			}
		}
	}

	static class Point {
		int x, y, count;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
		}

		Point (int x, int y, int count) {
			this.x = x;
			this.y = y;
			this.count = count;
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
