package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_The_Lazy_Cow_Bronze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt() * 2 + 1;
		int[] line = new int[1000002];
		int maxX = 0;
		for (int x = 0; x < n; x++) {
			int a = readInt();
			int b = readInt() + 1;
			maxX = Math.max(maxX, b);
			line[b] += a;
		}
		int max = 0;
		k = Math.min(k, maxX);
		for (int x = 1; x < maxX + 1; x++) {

			line[x] += line[x - 1];
			if (x >= k) {
				max = Math.max(max, line[x] - line[x - k]);
				// System.out.println(line[x]-line[x-k]);
			}
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
