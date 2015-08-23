package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Woburn_Challenge_BitStrings {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int numOfBitStrings = readInt();
			int[][] dp = new int[readInt() + 1][readInt() + 1];// 0 is 0, 1 is 1
			int[][] bitstrings = new int[numOfBitStrings][2];
			for (int x = 0; x < numOfBitStrings; x++) {
				String s = readLine();
				for (int y = 0; y < s.length(); y++) {
					if (s.charAt(y) == '0')
						bitstrings[x][0]++;
					else
						bitstrings[x][1]++;
				}
			}
			for (int x = 1; x < dp.length; x++) {
				for (int y = 1; y < dp[0].length; y++) {
					for (int z = 0; z < bitstrings.length; z++) {
						if (bitstrings[z][0] <= x && bitstrings[z][1] <= y) {
							dp[x][y] = Math.max(dp[x][y], 1 + dp[x
									- bitstrings[z][0]][y - bitstrings[z][1]]);
						}
					}
				}
			}
			System.out.println(dp[dp.length - 1][dp[0].length - 1]);
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
