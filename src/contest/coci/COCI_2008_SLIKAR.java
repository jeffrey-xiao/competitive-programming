package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_SLIKAR {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int N, depth;
	static char[][] g;
	static char[][] ng;
	static int[][][] dp;

	static int[][] state = { {1, 1, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 4, 4, 4, 4, 4, 4}, {2, 2, 3, 3, 4, 4, 1, 1, 3, 3, 4, 4, 1, 1, 2, 2, 4, 4, 1, 1, 2, 2, 3, 3}, {3, 4, 2, 4, 2, 3, 4, 3, 4, 1, 1, 3, 2, 4, 1, 4, 2, 1, 2, 3, 1, 3, 1, 2}, {4, 3, 4, 2, 3, 2, 3, 4, 1, 4, 3, 1, 4, 2, 4, 1, 1, 2, 3, 2, 3, 1, 2, 1}};

	public static void main (String[] args) throws IOException {
		N = readInt();
		g = new char[N][N];
		ng = new char[N][N];
		for (int i = 0; i < N; i++)
			g[i] = next().toCharArray();
		System.out.println(solve(0, N - 1, 0, N - 1).type[2]);
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				System.out.print(ng[i][j]);
			System.out.println();
		}
	}

	// 1 = white, 2 = black, 3 = white/black
	private static State solve (int x1, int x2, int y1, int y2) {
		State s = new State();
		if (x1 == x2 && y1 == y2) {
			s.type[0] = g[x1][y1] == '0' ? 0 : 1;
			s.type[1] = g[x1][y1] == '1' ? 0 : 1;
			s.type[2] = 0;
			ng[x1][y1] = g[x1][y1];
			return s;
		}
		int mx = (x1 + x2) / 2;
		int my = (y1 + y2) / 2;
		State[][] ss = { {solve(x1, mx, y1, my), solve(x1, mx, my + 1, y2)}, {solve(mx + 1, x2, y1, my), solve(mx + 1, x2, my + 1, y2)}};

		s.type[0] = ss[0][0].type[0] + ss[1][0].type[0] + ss[0][1].type[0] + ss[1][1].type[0];
		s.type[1] = ss[0][0].type[1] + ss[1][0].type[1] + ss[0][1].type[1] + ss[1][1].type[1];

		int bestV = Integer.MAX_VALUE;
		int[] best = new int[4];
		// trying all permutations of placing the mixed piece
		for (int x = 0; x < 24; x++) {
			int val = ss[(state[0][x] - 1) >> 1][(state[0][x] - 1) & 1].type[0] + ss[(state[1][x] - 1) >> 1][(state[1][x] - 1) & 1].type[1] + ss[(state[2][x] - 1) >> 1][(state[2][x] - 1) & 1].type[2] + ss[(state[3][x] - 1) >> 1][(state[3][x] - 1) & 1].type[2];
			if (val < bestV) {
				bestV = val;
				best[0] = state[0][x] - 1;
				best[1] = state[1][x] - 1;
			}
		}
		// memoizing the best computed value for the mixed piece
		s.type[2] = bestV;
		int n = (x2 - x1 + 1) / 2;
		// System.out.println(best[0] + " " + best[1]);
		// placing the new characters into the array
		for (int r = 0; r < n; r++) {
			for (int c = 0; c < n; c++) {
				ng[x1 + (best[0] >> 1) * n + r][y1 + (best[0] & 1) * n + c] = '0';
				ng[x1 + (best[1] >> 1) * n + r][y1 + (best[1] & 1) * n + c] = '1';
			}
		}
		// System.out.println(x1 + " " + x2 + " " + y1 + " " + y2 + " " +
		// s.type[0] + " " + s.type[1] + " " + s.type[2]);
		return s;
	}

	static class State {
		int[] type = new int[3];
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
