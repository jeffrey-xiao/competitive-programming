package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class CCC_2015_Stage_2_Cars_On_Ice {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int r, c;
	static char[][] g;
	static int[][][] in;
	static String dir = "NESW";
	static boolean[][][] car;
	static int[] movex = {-1, 0, 1, 0};
	static int[] movey = {0, 1, 0, -1};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		r = readInt();
		c = readInt();
		g = new char[r][c];
		in = new int[r][c][4];
		car = new boolean[r][c][4];
		for (int i = 0; i < r; i++)
			g[i] = next().toCharArray();
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++) {
				for (int z = 0; z < 4; z++)
					if (0 <= i + movex[z] && i + movex[z] < r && 0 <= j + movey[z] && j + movey[z] < c) {
						in[i][j][z]++;
					}
				if (g[i][j] != '.') {
					int currDir = dir.indexOf(g[i][j]);
					for (int z = 0; z < 4; z++)
						if (z != currDir)
							in[i][j][z]++;
					car[i][j][currDir] = true;
				}
			}
		Queue<State> q = new ArrayDeque<State>();
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				for (int k = 0; k < 4; k++)
					if (in[i][j][k] == 0) {
						q.offer(new State(i, j, k));
					}
		while (!q.isEmpty()) {
			State curr = q.poll();
			if (car[curr.r][curr.c][curr.d]) {
				pr.printf("(%d,%d)\n", curr.r, curr.c);
				for (int z = 0; z < 4; z++)
					if (z != curr.d) {
						in[curr.r][curr.c][z]--;
						if (in[curr.r][curr.c][z] == 0)
							q.offer(new State(curr.r, curr.c, z));
					}
			}
			// depends on direction
			int nr = curr.r + movex[(curr.d + 2) % 4];
			int nc = curr.c + movey[(curr.d + 2) % 4];
			if (0 <= nr && nr < r && 0 <= nc && nc < c) {
				in[nr][nc][curr.d]--;
				if (in[nr][nc][curr.d] == 0)
					q.offer(new State(nr, nc, curr.d));
			}
		}
		pr.close();
	}

	static class State {
		int r, c, d;

		State (int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
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
