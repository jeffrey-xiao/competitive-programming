package ioi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IOI_2004_Artemis_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		Point[] p = new Point[n];
		for (int x = 0; x < n; x++)
			p[x] = new Point(readInt(), readInt(), x + 1);
		Arrays.sort(p);
		int min = Integer.MAX_VALUE;
		int[] down = new int[n];
		int[] trees = new int[n];
		int first = 0, second = 0;
		main : for (int x = 0; x < n; x++) {
			down[x] = x;
			for (int y = 0; y < x; y++) {
				if (p[y].y > p[x].y) {
					down[x]--;
					down[y]++;
				}
			}
			trees[x] = down[x];
			int exit = 0; // Marks trees left of current tree
			for (int y = 0; y < x - k + 2; y++) {
				int sum = Math.abs(trees[x] + trees[y] - exit - down[y]) + 1;
				// System.out.println(sum);
				if (sum >= k && sum < min) {
					min = sum;
					first = p[x].id;
					second = p[y].id;
					if (sum == k) {
						break main;
					}
				}
				if (p[y].y < p[x].y)
					exit++;
			}
		}
		System.out.println(first + " " + second);
	}

	static class Point implements Comparable<Point> {
		int x, y, id;

		Point (int x, int y, int id) {
			this.x = x;
			this.y = y;
			this.id = id;
		}

		@Override
		public int compareTo (Point o) {
			if (x == o.x)
				return y - o.y;
			return x - o.x;
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
