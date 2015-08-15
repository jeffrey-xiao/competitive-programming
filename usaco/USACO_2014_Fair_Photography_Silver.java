package usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class USACO_2014_Fair_Photography_Silver {

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
		for (int x = 1; x <= n; x++) {
			cows[x] = new Cow(readInt(), readCharacter() == 'W');
		}
		Arrays.sort(cows, 1, cows.length);
		TreeSet<Pair> even = new TreeSet<Pair>();
		TreeSet<Pair> odd = new TreeSet<Pair>();
		int prevOdd = Integer.MIN_VALUE;
		int prevEven = Integer.MIN_VALUE;
		for (int x = 1; x <= n; x++) {
			int dist = cows[x].dist;
			int i = cows[x].isW ? 1 : -1;
			sum[x] = sum[x - 1] + i;

			Pair next = null;
			if (sum[x] % 2 == 0) {
				next = even.floor(new Pair(0, sum[x]));
			} else {
				next = odd.floor(new Pair(0, sum[x]));
			}
			if (next != null) {
				// System.out.println(dist + " " + next.dist);
				largest = Math.max(dist - next.dist, largest);
			}

			if (sum[x - 1] % 2 == 0 && sum[x - 1] > prevEven) {
				prevEven = sum[x - 1];
				even.add(new Pair(dist, sum[x - 1]));
			} else if (sum[x - 1] % 2 == 1 && sum[x - 1] > prevOdd) {
				prevOdd = sum[x - 1];
				odd.add(new Pair(dist, sum[x - 1]));
			}
		}
		System.out.println(largest);
	}

	static class Pair implements Comparable<Pair> {
		int w;
		int dist;

		Pair (int dist, int w) {
			this.dist = dist;
			this.w = w;
		}

		@Override
		public int compareTo (Pair o) {
			return o.dist - dist;
		}

	}

	static class Cow implements Comparable<Cow> {
		boolean isW;
		int dist;

		Cow (int dist, boolean isW) {
			this.dist = dist;
			this.isW = isW;
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