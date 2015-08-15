package ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2005_Mean_Sequence {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int prev = readInt();
		int min = Integer.MAX_VALUE;
		int prevLowerBound = 0;
		int prevUpperBound = 0;
		int lowerBound = 0;
		int upperBound = 0;
		for (int x = 0; x < n - 1; x++) {
			int curr = readInt();
			if (x == 0) {
				prevLowerBound = 2 * prev - curr;
				prevUpperBound = prev;
				min = Math.min(prevUpperBound - (prevLowerBound) + 1, min);
				// System.out.println(prevLowerBound + " " + prevUpperBound);
			}
			lowerBound = Math.max(prev, 2 * prev - prevUpperBound);
			upperBound = Math.min(curr, 2 * prev - prevLowerBound);
			// System.out.println(lowerBound + " " + upperBound + " " + curr);
			min = Math.min(upperBound - lowerBound + 1, min);
			prev = curr;
			prevLowerBound = lowerBound;
			prevUpperBound = upperBound;
		}
		System.out.println(min < 0 ? 0 : min);
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
