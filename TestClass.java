import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

class TestClass {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintStream ps = new PrintStream(System.out);
	static int[] movex = new int[] {-1, 1, -2, 2, -2, 2, -1, 1};
	static int[] movey = new int[] {-2, -2, -1, -1, 1, 1, 2, 2};
	static int[][] board;

	public static void main (String[] args) throws IOException {
		
		
		String a = "GACTG";
		String b = "ATGGC";
		int[][] dp = new int[a.length()+1][b.length()+1];
		for (int i = 1; i <= a.length(); i++) {
			for (int j = 1; j <= b.length(); j++) {
				if (a.charAt(i-1) == b.charAt(i-1)) {
					dp[i][j] = dp[i-1][j-1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
				}
			}
		}
		
		/*
		// START
		int a = readInt();
		int b = readInt();
		// END
		int c = readInt();
		int d = readInt();
		board = new int[8][8];
		for (int x = 0; x < 8; x++)
			for (int y = 0; y < 8; y++)
				board[x][y] = Integer.MAX_VALUE;
		compute(a, b, 0);
		for (int x = 0; x < 8; x++) {
			for (int y = 0; y < 8; y++)
				System.out.printf("%3d ", board[x][y]);
			System.out.println();
		}
		System.out.println(board[c][d]);
		*/
	}

	private static void compute (int x, int y, int i) {
		board[x][y] = i;
		for (int z = 0; z < 8; z++) {
			int nx = x + movex[z];
			int ny = y + movey[z];
			if (nx < 0 || ny < 0 || nx >= 8 || ny >= 8
					|| board[nx][ny] <= i + 1)
				continue;
			board[nx][ny] = i + 1;
			compute(nx, ny, i + 1);
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
