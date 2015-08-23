package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class USACO_2014_Fair_Photography_Bronze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] sum = new int[n + 1];
		Cow[] cows = new Cow[n + 1];
		int largest = 0;
		HashMap<Integer, Integer> low = new HashMap<Integer, Integer>();
		for (int x = 1; x <= n; x++) {
			cows[x] = new Cow(readInt(), readCharacter() == 'G');
		}
		Arrays.sort(cows, 1, cows.length);
		for (int x = 1; x <= n;) {
			int sizeOfOne = 1;
			while (x + sizeOfOne <= n && cows[x].isG == cows[x + sizeOfOne].isG)
				sizeOfOne++;
			largest = Math.max(largest, cows[x + sizeOfOne - 1].dist
					- cows[x].dist);
			x += sizeOfOne;
		}
		for (int x = 1; x <= n; x++) {
			int dist = cows[x].dist;
			int i = cows[x].isG ? 1 : -1;
			sum[x] = sum[x - 1] + i;
			Integer best = low.get(sum[x]);
			if (best != null)
				largest = Math.max(largest, dist - best);
			if (!low.containsKey(sum[x - 1]))
				low.put(sum[x - 1], dist);
		}
		System.out.println(largest);
	}

	static class Cow implements Comparable<Cow> {
		boolean isG;
		int dist;

		Cow (int dist, boolean isG) {
			this.dist = dist;
			this.isG = isG;
		}

		@Override
		public int compareTo (Cow o) {
			return dist - o.dist;
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}