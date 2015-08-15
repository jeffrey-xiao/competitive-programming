package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2000_Post_Office {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int numOfVillages = readInt();
		int numOfPostOffices = readInt();
		int[] villages = new int[numOfVillages + 1];
		int[][] dp = new int[numOfPostOffices + 1][numOfVillages + 1];

		for (int x = 1; x <= numOfVillages; x++) {
			villages[x] = readInt();
		}
		int[][] cost = new int[numOfVillages + 1][numOfVillages + 1];
		for (int x = 1; x <= numOfVillages; x++) {
			for (int y = 1, z = y + x; z <= numOfVillages; z++, y++) {
				cost[y][z] = villages[z] - villages[y]
						+ Math.min(cost[y + 1][z], cost[y][z - 1]);
			}

		}
		/*
		 * for(int[] x: cost){ for(int y:x) System.out.print(y + " ");
		 * System.out.println(); }
		 */
		dp[0][0] = 10000;
		for (int x = 0; x <= numOfPostOffices; x++) {

			for (int y = 1; y <= numOfVillages; y++) {
				if (x == 0)
					dp[x][y] = 10000;
				else {
					int min = dp[x - 1][y - 1];
					for (int z = y - 2; z >= 0; z--) {
						if (min == 0)
							min = dp[x][z] + Math.min(cost[z][y], cost[z][y]);
						else
							min = Math
									.min(min,
											dp[x][z]
													+ Math.min(cost[z][y],
															cost[z][y]));
					}

					dp[x][y] = min;
				}
			}
		}
		for (int[] x : dp) {
			for (int y : x)
				System.out.print(y + " ");
			System.out.println();
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

	public IOI_2000_Post_Office () {
	}
}
