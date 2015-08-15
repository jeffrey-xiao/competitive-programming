package usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2011_Escaping_The_Farm {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int max = 1;

	public static void main (String[] args) throws IOException {
		int numOfCows = readInt();
		int[] cows = new int[numOfCows];
		for (int x = 0; x < numOfCows; x++)
			cows[x] = readInt();
		getSolution(0, 0, 0, numOfCows, cows);// 0 index, 1 sum, 2 curr, 3 max

		System.out.println(max);
	}

	private static void getSolution (int i, int j, int k, int end, int[] cows) {
		if (k > max)
			max = k;
		if (i == end)
			return;
		if (isValid(cows[i], j)) {
			getSolution(i + 1, cows[i] + j, k + 1, end, cows);
			getSolution(i + 1, j, k, end, cows);
		} else
			getSolution(i + 1, j, k, end, cows);

	}

	private static boolean isValid (int i, int j) {
		while (i != 0 && j != 0) {
			if (i % 10 + j % 10 >= 10)
				return false;
			i /= 10;
			j /= 10;
		}
		return true;
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
/*
 * int[][] dp = new int[numOfCows][2];//0 is num, 1 is num of nums for(int x =
 * 0; x < numOfCows; x++){ dp[x][0] = readInt(); dp[x][1] = 1; int temp =
 * dp[x][0]; for(int y = 0; y < x; y++){ if(isValid(dp[y][0], temp)){
 * if(dp[y][1]+1 > dp[x][1]){ dp[x][1] = dp[y][1]+1; dp[x][0] = temp+dp[y][0]; }
 * }
 * 
 * }System.out.println(dp[x][0] + " " + dp[x][1]); } int max = 1; for(int x = 0;
 * x < numOfCows; x++) max = Math.max(dp[x][1],max); System.out.println(max);
 */

