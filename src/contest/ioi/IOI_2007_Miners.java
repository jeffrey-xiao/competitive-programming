package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2007_Miners {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		char[] s = readLine().toCharArray();
		int[][][][][] dp = new int[2][4][4][4][4];
		for (int x = 0; x < 2; x++)
			for (int y = 0; y < 4; y++)
				for (int z = 0; z < 4; z++)
					for (int i = 0; i < 4; i++)
						for (int j = 0; j < 4; j++)
							dp[x][y][z][i][j] = -1;
		dp[0][0][0][0][0] = 0;
		for (int x = 0; x < n; x++) {
			for (int y = 0; y < 4; y++)
				for (int z = 0; z < 4; z++)
					for (int i = 0; i < 4; i++)
						for (int j = 0; j < 4; j++) {
							if (dp[x % 2][y][z][i][j] != -1) {
								int f = 1;
								if (s[x] == 'F')
									f = 2;
								else if (s[x] == 'B')
									f = 3;
								int next = getValue(f, y, z);
								dp[(x + 1) % 2][z][f][i][j] = Math.max(
										dp[x % 2][y][z][i][j] + next,
										dp[(x + 1) % 2][z][f][i][j]);
								next = getValue(f, i, j);
								dp[(x + 1) % 2][y][z][j][f] = Math.max(
										dp[x % 2][y][z][i][j] + next,
										dp[(x + 1) % 2][y][z][j][f]);
							}
						}
		}
		int max = 0;
		for (int y = 0; y < 4; y++)
			for (int z = 0; z < 4; z++)
				for (int i = 0; i < 4; i++)
					for (int j = 0; j < 4; j++)
						max = Math.max(max, dp[n % 2][y][z][i][j]);
		System.out.println(max);
	}

	private static int getValue (int x, int y, int z) {
		int sol = 0;
		if (x == 1 || y == 1 || z == 1)
			sol++;
		if (x == 2 || y == 2 || z == 2)
			sol++;
		if (x == 3 || y == 3 || z == 3)
			sol++;
		return sol;
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
