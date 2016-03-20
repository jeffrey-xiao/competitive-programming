package contest.dmoj;

import java.util.*;
import java.io.*;

public class VMSS_Diana_Socializes {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int[][] id;
	static int cnt;
	static char[][] grid;
	static int[] mover = {-1, 1, 0, 0};
	static int[] movec = {0, 0, -1, 1};

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		grid = new char[N][M];
		id = new int[N][M];

		for (int i = 0; i < N; i++)
			grid[i] = next().toCharArray();

		for (int i = 0; i < N; i++)
			for (int j = 0; j < M; j++)
				if (id[i][j] == 0 && grid[i][j] == '.') {
					cnt++;
					dfs(i, j);
				}
		int maxSize = 0;
		int ansR = -1;
		int ansC = -1;
		TreeSet<Integer> best = new TreeSet<Integer>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				TreeSet<Integer> curr = new TreeSet<Integer>();
				for (int k = 0; k < 4; k++) {
					int r = i + mover[k];
					int c = j + movec[k];
					if (r < 0 || r >= N || c < 0 || c >= M || grid[r][c] == '#')
						continue;
					if (id[r][c] != 0)
						curr.add(id[r][c]);
				}
				if (curr.size() > maxSize) {
					maxSize = curr.size();
					best = new TreeSet<Integer>(curr);
					ansR = i;
					ansC = j;
				}
			}
		}

		if (maxSize == 0) {
			out.println(-1);
		} else {
			out.println(maxSize);
			out.println(ansC + " " + ansR);
			for (int room : best)
				out.print(room + " ");
			out.println();

		}
		out.close();
	}

	static void dfs (int r, int c) {
		id[r][c] = cnt;
		for (int k = 0; k < 4; k++) {
			int nr = r + mover[k];
			int nc = c + movec[k];
			if (nr < 0 || nr >= N || nc < 0 || nc >= M || grid[nr][nc] == 'X' || id[nr][nc] != 0)
				continue;
			dfs(nr, nc);
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
