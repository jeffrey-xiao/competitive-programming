package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_STOL {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		int[][] sum = new int[r + 1][c + 1];
		boolean[][] poss = new boolean[r + 1][c + 1];
		for (int x = 0; x <= r; x++)
			poss[x][0] = true;
		for (int x = 0; x <= c; x++)
			poss[0][x] = true;
		for (int x = 1; x <= r; x++) {
			String s = " " + next();
			for (int y = 1; y <= c; y++) {
				sum[x][y] = sum[x][y - 1] + (s.charAt(y) == 'X' ? 1 : 0);
			}
		}
		int max = 0;
		for (int c1 = 1; c1 <= c; c1++) {
			for (int c2 = c1; c2 <= c; c2++) {
				int curr = 0;
				int currMax = 0;
				for (int x = 1; x <= r; x++) {
					int sumR = sum[x][c2] - sum[x][c1 - 1];
					if (sumR == 0)
						curr++;
					else {
						currMax = Math.max(currMax, curr);
						curr = 0;
					}
				}
				currMax = Math.max(currMax, curr);
				// System.out.println(c1 + " " + c2 + " " + currMax);
				if (currMax > 0 && currMax * 2 + (c2 - c1 + 1) * 2 > max)
					max = currMax * 2 + (c2 - c1 + 1) * 2;
			}
		}
		System.out.println(max - 1);
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
