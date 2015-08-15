package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Virtuoso {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static final int INF = 1 << 30;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		State[][][] dp = new State[n + 1][7][3];
		for (int x = 0; x <= n; x++) {
			for (int y = 0; y < 7; y++) {
				for (int i = 0; i <= 2; i++) {
					dp[x][y][i] = new State(INF, INF);
				}
			}
		}
		for (int x = 0; x < n; x++) {
			int slides = readInt();
			int[] s = new int[slides];
			for (int y = 0; y < slides; y++)
				s[y] = readInt() - 1;
			if (x == 0)
				for (int z = 0; z < slides; z++) {
					for (int i = 0; i <= 2; i++) {
						dp[x][s[z]][i].moves = dp[x][s[z]][i].turns = 0;
						// System.out.println(x + " " + s[z]);
					}
				}
			else {
				for (int y = 0; y < 7; y++) {
					for (int i = 0; i <= 2; i++) {
						for (int z = 0; z < slides; z++) {
							int nextMoves = Math.abs(s[z] - y)
									+ dp[x - 1][y][i].moves;
							int nextDir = 0;
							int nextTurns = dp[x - 1][y][i].turns;
							if (y < s[z])
								nextDir = 1;
							else if (y > s[z])
								nextDir = 2;

							if (nextDir != i && x != 1) {
								nextTurns++;
							}

							if (dp[x][s[z]][nextDir].moves > nextMoves
									|| (dp[x][s[z]][nextDir].moves == nextMoves && nextTurns < dp[x][s[z]][nextDir].turns)) {
								dp[x][s[z]][nextDir].moves = nextMoves;
								dp[x][s[z]][nextDir].turns = nextTurns;
								// System.out.println(x + " " + (s[z]+1) + " " +
								// (y+1) + " " + nextMoves + " " + nextDir + " "
								// + nextTurns);
							}
						}
					}
				}
			}
		}
		// 6 5 4 5 5 5 6 7 7 7
		//
		int minMoves = INF;
		int minTurns = INF;
		for (int y = 0; y < 7; y++) {
			for (int i = 0; i <= 2; i++) {
				if (dp[n - 1][y][i].moves < minMoves
						|| (dp[n - 1][y][i].turns < minTurns && dp[n - 1][y][i].moves == minMoves)) {
					minMoves = dp[n - 1][y][i].moves;
					minTurns = dp[n - 1][y][i].turns;

				}
			}
		}
		System.out.println(minMoves + " " + (minTurns));
	}

	static class State {
		int moves, turns;

		State (int moves, int turns) {
			this.moves = moves;
			this.turns = turns;
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
