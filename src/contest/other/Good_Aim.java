package contest.other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

public class Good_Aim {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	static int n, q;
	static long X, Y;
	static Team[] sorted;
	static Team[] org;

	public static void main (String[] args) throws IOException {
		FasterScanner fs = new FasterScanner();
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		n = fs.nextInt();
		q = fs.nextInt();
		X = fs.nextInt();
		Y = fs.nextInt();
		sorted = new Team[n];
		org = new Team[n];
		for (int i = 0; i < n; i++) {
			int x = fs.nextInt();
			int y = fs.nextInt();
			int sz = fs.nextInt();
			sorted[i] = new Team(x, y, sz);
			org[i] = new Team(x, y, sz);
		}
		// shuffle(sorted);
		Arrays.sort(sorted);
		for (int i = 1; i < n; i++)
			sorted[i].sz += sorted[i - 1].sz;
		for (int i = 0; i < q; i++) {
			int id = fs.nextInt() - 1;
			if (org[id].compareTo(new Team(X, Y, 0)) < 0) {
				int lo = bs(org[id], false) - 1;
				long x = org[id].x;
				long y = org[id].y;
				org[id].x = X;
				org[id].y = Y;
				int hi = bs(org[id], true);
				org[id].x = x;
				org[id].y = y;
				pr.println(sorted[hi].sz - (lo == -1 ? 0 : sorted[lo].sz));
			} else {
				int hi = bs(org[id], true);
				long x = org[id].x;
				long y = org[id].y;
				org[id].x = X;
				org[id].y = Y;
				int lo = bs(org[id], false) - 1;
				org[id].x = x;
				org[id].y = y;
				// System.out.println(hi + " " + lo);
				pr.println(sorted[hi].sz - (lo == -1 ? 0 : sorted[lo].sz));
			}

		}
		pr.close();
	}

	static void shuffle (Team[] s) {
		for (int i = 0; i < n - 1; i++) {
			int j = (int) (Math.random() * (n - i - 1) + (i + 1));
			Team temp = s[j];
			s[j] = s[i];
			s[i] = temp;
		}
	}

	static int bs (Team t, boolean lower) {
		int lo = 0;
		int hi = n - 1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			// System.out.println(mid + " " + t.compareTo(sorted[mid]));
			int cmp = t.compareTo(sorted[mid]);
			if (cmp > 0)
				lo = mid + 1;
			else if (cmp < 0)
				hi = mid - 1;
			else
				return mid;
		}
		return lower ? hi : lo;
	}

	static class Team implements Comparable<Team> {
		long dx, dy;
		long x, y;
		long sz;

		Team (long dx, long dy, int sz) {
			this.dx = dx - X;
			this.dy = dy - Y;
			this.x = dx;
			this.y = dy;
			if (this.dx < 0) {
				this.dx = -this.dx;
				this.dy = -this.dy;
			}
			this.sz = sz;
		}

		@Override
		public int compareTo (Team o) {
			long cmp = dy * o.dx - o.dy * dx;
			if (cmp == 0) {
				int cmpy = new Long(y).compareTo(new Long(o.y));
				if (cmpy == 0) {
					int cmpx = new Long(x).compareTo(new Long(o.x));
					return cmpx;
				}
				return cmpy;
			}
			return cmp < 0 ? -1 : 1;
		}
	}

	static class FasterScanner {
		private InputStream mIs;
		private byte[] buf = new byte[1024];
		private int curChar;
		private int numChars;

		public FasterScanner () {
			this(System.in);
		}

		public FasterScanner (InputStream is) {
			mIs = is;
		}

		public int read () {
			if (numChars == -1)
				throw new InputMismatchException();
			if (curChar >= numChars) {
				curChar = 0;
				try {
					numChars = mIs.read(buf);
				} catch (IOException e) {
					throw new InputMismatchException();
				}
				if (numChars <= 0)
					return -1;
			}
			return buf[curChar++];
		}

		public String nextLine () {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isEndOfLine(c));
			return res.toString();
		}

		public String nextString () {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			StringBuilder res = new StringBuilder();
			do {
				res.appendCodePoint(c);
				c = read();
			} while (!isSpaceChar(c));
			return res.toString();
		}

		public long nextLong () {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			long res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public char nextChar () {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			return (char) c;
		}

		public int nextInt () {
			int c = read();
			while (isSpaceChar(c))
				c = read();
			int sgn = 1;
			if (c == '-') {
				sgn = -1;
				c = read();
			}
			int res = 0;
			do {
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				res *= 10;
				res += c - '0';
				c = read();
			} while (!isSpaceChar(c));
			return res * sgn;
		}

		public boolean isSpaceChar (int c) {
			return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}

		public boolean isEndOfLine (int c) {
			return c == '\n' || c == '\r' || c == -1;
		}

	}
}
