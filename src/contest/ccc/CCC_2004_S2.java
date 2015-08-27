package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2004_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		int[] score = new int[n];
		int[] highestRank = new int[n];
		int[] currRank = new int[n];
		boolean[] isMax = new boolean[n];
		int greatest = Integer.MIN_VALUE;
		for (; k > 0; k--) {
			for (int x = 0; x < n; x++) {
				score[x] += readInt();
				currRank[x] = 0;
				if (k == 1 && score[x] > greatest) {
					greatest = score[x];
				}
			}
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < n; y++) {
					if (x != y) {
						if (score[x] > score[y])
							currRank[y]++;
					}
				}
			}
			for (int x = 0; x < n; x++) {
				highestRank[x] = Math.max(highestRank[x], currRank[x]);
				if (k == 1) {
					if (greatest == score[x])
						isMax[x] = true;
				}
			}
		}
		for (int x = 0; x < n; x++)
			if (isMax[x])
				System.out.printf("Yodeller %d is the TopYodeller: score %d, worst rank %d\n", x + 1, score[x], highestRank[x] + 1);
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
