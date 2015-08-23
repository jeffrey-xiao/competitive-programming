package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge_1999_The_Wedding_Juicer_2 {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int[][] g;
	static boolean[][] v;

	static int r, c;
	static int min = 1 << 30;
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//pr = new PrintWriter(new FileWriter("out.txt"));

		int t = readInt();
		for (int qq = 0; qq < t; qq++) {
			r = readInt();
			c = readInt();
			g = new int[r+2][c+2];
			int max = 0;
			for (int i = 1; i <= r; i++) {
				for (int j = 1; j <= c; j++) {
					g[i][j] = readInt();
					max = Math.max(g[i][j], max);
				}
			}
			int[][] waterFull = new int[r+2][c+2];
			for (int i = 1; i <= r; i++)
				for (int j = 1; j <= c; j++)
					waterFull[i][j] = max;
			boolean isComplete = false;
			while (!isComplete) {
				isComplete = true;
				for (int i = 1; i <= r; i++)
					for (int j = 1; j <= c; j++) {
						int min = Math.max(g[i][j], Math.min(Math.min(waterFull[i-1][j], waterFull[i+1][j]), Math.min(waterFull[i][j-1], waterFull[i][j+1])));
						if (min < waterFull[i][j]) {
							waterFull[i][j] = min;
							isComplete = false;
						}
					}
			}
			int ans = 0;
			for (int i = 1; i <= r; i++)
				for (int j = 1; j <= c; j++)
					ans += waterFull[i][j] - g[i][j];
			System.out.println(ans);
		}

		pr.close();
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

