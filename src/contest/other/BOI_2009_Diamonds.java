package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

public class BOI_2009_Diamonds {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static PrintStream pr = new PrintStream(System.out);

	public static void main (String[] args) throws IOException {
		int L = readInt();
		int M = readInt();
		int N = readInt();
		int[][][] grid = new int[N + 1][M + 1][L + 1];
		for (int x = 1; x <= N; x++) {
			for (int y = 1; y <= M; y++) {
				for (int z = 1; z <= L; z++) {
					grid[x][y][z] = readInt() + grid[x - 1][y][z]
							+ grid[x][y - 1][z] + grid[x][y][z - 1]
							- grid[x - 1][y - 1][z] - grid[x - 1][y][z - 1]
							- grid[x][y - 1][z - 1] + grid[x - 1][y - 1][z - 1];
				}
			}
		}
		while (true) {
			String nextLine = br.readLine();
			if (nextLine == null)
				break;
			StringTokenizer next = new StringTokenizer(nextLine);
			int z1 = Integer.parseInt(next.nextToken());
			int y1 = Integer.parseInt(next.nextToken());
			int x1 = Integer.parseInt(next.nextToken());
			int z2 = Integer.parseInt(next.nextToken());
			int y2 = Integer.parseInt(next.nextToken());
			int x2 = Integer.parseInt(next.nextToken());
			// System.out.printf("%d %d %d %d %d %d\n",x1,y1,z1,x2,y2,z2);
			int sum = grid[x2][y2][z2] - grid[x1][y2][z2] - grid[x2][y1][z2]
					- grid[x2][y2][z1] + grid[x1][y1][z2] + grid[x2][y1][z1]
					+ grid[x1][y2][z1] - grid[x1][y1][z1];
			pr.print(sum + "\n");
		}
		// System.out.println(grid[N][M][L]);
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
