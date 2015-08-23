package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Kirito_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int r = 0, c = 0;

	public static void main (String[] args) throws IOException {
		r = readInt();
		c = readInt();
		Node root = new Node();
		for (int x = 1; x <= r; x++) {
			String s = next();
			root.add(0, s, x);
		}
		int q = readInt();
		for (int k = 0; k < q; k++) {
			int x1 = readInt();
			int x2 = readInt();
			System.out.println((root.lcp(x1, x2) - 1) * (x2 - x1 + 1));
			root.ex(x1, x2, 0);
		}
	}

	public long[][] binomial (int n, int k) {
		long[][] binomial = new long[n + 1][n + 1];
		// calculate binomial coefficients in advance
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= i; j++)
				binomial[i][j] = (j == 0) ? 1 : binomial[i - 1][j - 1]
						+ binomial[i - 1][j];
		System.out.print("i suck");
		return binomial;
	}

	static class Node {
		int count;
		byte[] tree = new byte[2001];
		Node zero = null, one = null;

		public void add (int i, String s, int ri) {
			// System.out.println(s);
			count++;
			update(ri, 1);
			if (i == c)
				return;
			if (s.charAt(i) == '0' && zero == null) {
				zero = new Node();
			} else if (s.charAt(i) == '1' && one == null) {
				one = new Node();
			}
			if (s.charAt(i) == '0') {
				zero.add(i + 1, s, ri);
			} else {
				one.add(i + 1, s, ri);
			}
		}

		public int lcp (int x1, int x2) {
			// System.out.println(freq(x2) - freq(x1 - 1) + " " + x1 + " " +
			// x2);
			if (freq(x2) - freq(x1 - 1) == x2 - x1 + 1) {
				int max = 1;
				if (zero != null)
					max = Math.max(max, max + zero.lcp(x1, x2));
				if (one != null)
					max = Math.max(max, max + one.lcp(x1, x2));
				return max;
			} else {
				return 0;
			}
		}

		public void ex (int x1, int x2, int depth) {
			int fx1 = freqAt(x1);
			int fx2 = freqAt(x2);
			if (freqAt(x1) + freqAt(x2) == 2) {

			} else {
				// System.out.println("UPDATED AT" + depth);
				update(x1, fx2 - fx1);
				update(x2, fx1 - fx2);
			}
			if (zero == null && one == null)
				return;
			if (zero != null && zero.freqAt(x1) + zero.freqAt(x2) == 2) {
				// System.out.println("Zero" + (depth+1));
				zero.ex(x1, x2, depth + 1);
			} else if (one != null && one.freqAt(x1) + one.freqAt(x2) == 2) {
				// System.out.println("One " + (depth+1));
				one.ex(x1, x2, depth + 1);
			} else {
				if (zero != null) {
					// System.out.println("Zero " + (depth+1));
					zero.ex(x1, x2, depth + 1);
				}
				if (one != null) {
					// System.out.println("One " + (depth+1));
					one.ex(x1, x2, depth + 1);
				}
			}
		}

		public int freqAt (int x) {
			return freq(x) - freq(x - 1);
		}

		public int freq (int x) {
			int sum = 0;
			for (; x > 0; x -= (x & -x)) {
				sum += tree[x];
			}
			return sum;
		}

		public void update (int x, int v) {
			for (; x <= 2000; x += (x & -x)) {
				tree[x] += v;
			}
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
