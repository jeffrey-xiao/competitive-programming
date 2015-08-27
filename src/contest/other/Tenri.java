package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Tenri {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[][] dp = new int[1 << 11][16];
	static int[] boxes = new int[11];

	static int n, s;
	static ArrayList<HashSet<Pair>> comp = new ArrayList<HashSet<Pair>>();

	public static void main (String[] args) throws IOException {
		for (int y = 0; y < 1 << 11; y++)
			for (int z = 0; z < 16; z++)
				dp[y][z] = -1;
		n = readInt();
		s = readInt();
		for (int x = 0; x < n; x++)
			boxes[x] = readInt();
		for (int x = 0; x < 1 << n; x++) {
			comp.add(new HashSet<Pair>());
			generateSubsets(0, x, 0, comp.get(x));
		}
		System.out.println(compute((1 << n) - 1, s));
	}

	public static int compute (int box, int s) {
		// System.out.println(i + " " + box + " " + s);
		if (dp[box][s] != -1)
			return dp[box][s];
		// w = min(wi×wj , (m+s)^2) + s
		int res = 0;
		for (int x = 0; x < n; x++) {
			if ((box & (1 << x)) > 0)
				box ^= (1 << x);
			else
				continue;

			if (box == 0) {
				res = Math.max(res, (boxes[x] + s) * (boxes[x] + s) + s);
				break;
			}
			HashSet<Pair> subsets = comp.get(box);

			for (Pair p : subsets) {
				for (int a = 0; a <= s; a++) {
					for (int b = 0; b <= s - a; b++) {
						int c = s - a - b;
						// System.out.println(Integer.toString(currsets.get(y),2)+" "+Integer.toString(box
						// & (~currsets.get(y)),2));
						int next = compute(p.x, a) * compute(p.y, b);
						// System.out.println("Next is " + next);
						int curr = (boxes[x] + c) * (boxes[x] + c);
						res = Math.max(res, Math.min(next, curr) + c);
					}
				}
			}
			box ^= (1 << x);
		}
		dp[box][s] = res;
		return res;
	}

	private static int count (int i) {
		int c = 0;
		for (int x = 0; x < n; x++)
			if ((i & (1 << x)) > 0)
				c++;
		return c;
	}

	private static void generateSubsets (int s, int b, int res, HashSet<Pair> subsets) {
		if (s == n) {
			if (Integer.bitCount(res) % 2 == 1)
				subsets.add(new Pair(res, b & (~res)));
			return;
		}
		if ((b & (1 << s)) > 0)
			generateSubsets(s + 1, b, res | 1 << s, subsets);
		generateSubsets(s + 1, b, res, subsets);
	}

	static class Pair {
		Integer x, y;

		Pair (int x, int y) {
			if (x > y) {
				this.x = x;
				this.y = y;
			} else {
				this.x = y;
				this.y = x;
			}
		}

		@Override
		public int hashCode () {
			return x.hashCode() * 31 + y.hashCode();
		}

		@Override
		public boolean equals (Object o) {
			if (o instanceof Pair) {
				Pair p = (Pair) o;
				return x == p.x && y == p.y;
			}
			return false;
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
