package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

public class IOI_1998_Camelot {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int[][][][] min = new int[8][8][8][8];
	static int[] movex = {1, 2, 2, 1, -1, -2, -2, -1};
	static int[] movey = {-2, -1, 1, 2, 2, 1, -1, -2};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));
		precompute();
		String in = next();
		ArrayList<Point> ks = new ArrayList<Point>();
		int kx = 0, ky = 0;
		for (int i = 0; i < in.length(); i += 2) {
			if (i == 0) {
				kx = in.charAt(i) - 'A';
				ky = in.charAt(i + 1) - '1';
			} else {
				ks.add(new Point(in.charAt(i) - 'A', in.charAt(i + 1) - '1'));
			}
		}
		int[][] mk = new int[8][8];
		int[][] global = new int[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mk[i][j] = 1 << 30;
				for (Point p : ks)
					global[i][j] += min[p.x][p.y][i][j];
			}
		}
		Queue<Point> q = new ArrayDeque<Point>();
		mk[kx][ky] = 0;
		q.offer(new Point(kx, ky));
		while (!q.isEmpty()) {
			Point curr = q.poll();
			for (int mx = -1; mx <= 1; mx++) {
				for (int my = -1; my <= 1; my++) {
					if (mx == 0 && my == 0)
						continue;
					int nx = curr.x + mx;
					int ny = curr.y + my;
					if (0 <= nx && nx < 8 && 0 <= ny && ny < 8 && mk[curr.x][curr.y] + 1 < mk[nx][ny]) {
						mk[nx][ny] = mk[curr.x][curr.y] + 1;
						q.offer(new Point(nx, ny));
					}
				}
			}
		}
		int minV = 1 << 30;
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				// no combine
				int sum = mk[i][j] + global[i][j];
				minV = Math.min(minV, sum);
				q = new ArrayDeque<Point>();
				for (int k = 0; k < ks.size(); k++) {
					for (int l = 0; l < 8; l++) {
						for (int m = 0; m < 8; m++) {
							sum = mk[i][j] + min[ks.get(k).x][ks.get(k).y][i][j] + min[i][j][l][m] + global[l][m] - min[ks.get(k).x][ks.get(k).y][l][m];
							minV = Math.min(sum, minV);
						}
					}
				}
			}
		}
		System.out.println(minV);
		pr.close();
	}

	static void precompute () {
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++)
				for (int k = 0; k < 8; k++)
					for (int l = 0; l < 8; l++)
						min[i][j][k][l] = 1 << 30;
		for (int i = 0; i < 8; i++)
			for (int j = 0; j < 8; j++) {
				Queue<Point> q = new ArrayDeque<Point>();
				min[i][j][i][j] = 0;
				q.offer(new Point(i, j));
				while (!q.isEmpty()) {
					Point curr = q.poll();
					for (int z = 0; z < 8; z++) {
						int nx = curr.x + movex[z];
						int ny = curr.y + movey[z];
						if (0 <= nx && nx < 8 && 0 <= ny && ny < 8 && min[i][j][curr.x][curr.y] + 1 < min[i][j][nx][ny]) {
							min[i][j][nx][ny] = min[i][j][curr.x][curr.y] + 1;
							q.offer(new Point(nx, ny));
						}
					}
				}
			}
	}

	static class Point {
		int x, y;

		Point (int x, int y) {
			this.x = x;
			this.y = y;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
