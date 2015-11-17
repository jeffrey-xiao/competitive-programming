package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class USACO_2012_Islands {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[][] islands = new int[n][2];
		boolean[] gone = new boolean[n];
		for (int x = 0; x < n; x++) {
			islands[x][0] = readInt();
			islands[x][1] = x;
		}
		Arrays.sort(islands, new Comparator<int[]>() {
			@Override
			public int compare (int[] arg0, int[] arg1) {
				return arg0[0] - arg1[0];
			}
		});
		int numOfIslands = 1;
		int max = 0;
		for (int x = 0; x < n; x++) {
			int index = islands[x][1];
			gone[index] = true;

			boolean right = (index < n - 1 && !gone[index + 1]);
			boolean left = (index > 0 && !gone[index - 1]);
			if (right && left)
				numOfIslands++;
			else if (!right && !left)
				numOfIslands--;
			if (x < n - 1 && islands[x + 1][0] == islands[x][0])
				continue;
			max = Math.max(max, numOfIslands);
		}
		System.out.println(max);
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
