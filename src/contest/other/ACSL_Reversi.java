package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACSL_Reversi {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;// 2 is x 1 is O
	static byte[][] board;
	static byte[] movex = {-1, -1, -1, 0, 0, 1, 1, 1};
	static byte[] movey = {-1, 0, 1, -1, 1, -1, 0, 1};
	static int counter;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			counter = 0;
			board = new byte[][] { {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 2, 1, 0, 0, 0}, {0, 0, 0, 1, 2, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0, 0, 0}};
			String move = next();
			boolean isXMove = true;
			while (!move.equals("##")) {
				int x = 8 - (move.charAt(0) - 48);
				int y = move.charAt(1) - 65;
				for (int d = 0; d <= 7; d++) {
					makeMove(x + movex[d], y + movey[d], isXMove, d);
					board[x][y] = (byte) (isXMove ? 2 : 1);
				}

				isXMove = !isXMove;
				move = next();
			}
			System.out.println(counter);
		}
	}

	private static boolean makeMove (int i, int j, boolean isXMove, int move) {

		// byte other = (byte) (isXMove?1:2);
		byte same = (byte) (isXMove ? 2 : 1);
		if (i < 0 || j < 0 || i > 7 || j > 7)
			return false;
		if (board[i][j] == same)
			return true;
		if (board[i][j] == 0)
			return false;
		boolean valid = makeMove(i + movex[move], j + movey[move], isXMove, move);
		if (valid) {
			board[i][j] = same;
			counter++;
		}
		return valid;
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
