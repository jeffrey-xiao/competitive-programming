package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class USACO_2013_Cow_Baseball {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] cows = new int[n];
		for (int x = 0; x < n; x++)
			cows[x] = readInt();
		Arrays.sort(cows);
		int total = 0;
		for (int x = 0; x < n - 2; x++) {
			for (int y = x + 1; y < n - 1; y++) {
				for (int z = y + 1; z < n; z++) {
					if (cows[y] - cows[x] <= cows[z] - cows[y] && (cows[z] - cows[y]) <= 2 * (cows[y] - cows[x])) {
						total++;
					}
				}
			}
		}
		System.out.println(total);

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
