package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_5 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int w = readInt();
		int h = readInt();
		int n = readInt();
		int[][] grid = new int[h+1][w+1];
		for (int i = 1; i <= h; i++)
			for (int j = 1; j <= w; j++)
				grid[i][j] = readInt() + grid[i-1][j] + grid[i][j-1] - grid[i-1][j-1];
		int ans = 0;
		for (int i = 1; i <= h; i++) {
			int width = n / i;
			if (width == 0)
				break;
			for (int j = 1; j <= h-i+1; j++) {
				for (int k = 1; k <= w - width+1; k++) {
					ans = Math.max(ans, grid[j+i-1][k+width-1] - grid[j-1][k+width-1] - grid[j+i-1][k-1] + grid[j-1][k-1]);
				}
				ans = Math.max(ans, grid[j+i-1][Math.min(w, 1+width-1)] - grid[j-1][Math.min(w, 1+width-1)] - grid[j+i-1][1-1] + grid[j-1][1-1]);
			}
		}
		out.println(ans);
		out.close();
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

