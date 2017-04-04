package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_Mount_Allison_2017_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int[][] grid, minDist;
	static int N, M;
	static int[] mover = {0, 0, -1, 1};
	static int[] movec = {-1, 1, 0, 0};
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();
		
		grid = new int[N][M];
		minDist = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(minDist[i], 1 << 30);
			for (int j = 0; j < M; j++) {
				grid[i][j] = readInt();
			}
		}

		for (int i = 0; i < N; i++)
			computeShortestPath(i);

		int ans = 1 << 30;
		for (int i = 0; i < N; i++)
			ans = Math.min(ans, minDist[i][M - 1]);
		out.println(ans);
		out.close();
	}

	static void computeShortestPath (int i) {
		Queue<State> q = new ArrayDeque<State>();
		minDist[i][0] = grid[i][0];
		q.offer(new State(i, 0, minDist[i][0]));
		
		while (!q.isEmpty()) {
			State curr = q.poll();
			for (int k = 0; k < 4; k++) {
				int nr = curr.r + mover[k];
				int nc = curr.c + movec[k];
				if (nr < 0 || nc < 0 || nr >= N || nc >= M)
					continue;
				if (minDist[nr][nc] <= Math.max(curr.cost, grid[nr][nc]))
					continue;
				minDist[nr][nc] = Math.max(curr.cost, grid[nr][nc]);
				q.offer(new State(nr, nc, minDist[nr][nc]));
			}
		}
	}
	
	static class State implements Comparable<State> {
		int r, c, cost;
		State (int r, int c, int cost) {
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		
		@Override
		public int compareTo (State s) {
			return cost - s.cost;
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

