package contest.ioi;

import java.util.*;
import java.io.*;

public class IOI_2005_Polish_Flag {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static long N, L, B, R;
	static long lWhite, lRed, bWhite, bRed, rRed, rWhite;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		L = readInt();
		B = readInt();
		R = readInt();

		HashSet<String> vis = new HashSet<String>();

		// doing white
		long lo = 1, hi = N;
		while (lo <= hi) {
			vis.add(toString(getRow(lo)));

			long innerLo = lo;
			long innerHi = hi;
			while (innerLo <= innerHi) {
				long mid = (innerLo + innerHi) >> 1;
				if (vis.contains(toString(getRow(mid))))
					innerLo = mid + 1;
				else
					innerHi = mid - 1;
			}

			long beg = lo; // first row of segment
			long end = innerHi; // last row of segment

			lo = innerHi + 1;

			innerLo = beg;
			innerHi = end;
			while (innerLo <= innerHi) {
				long l = innerLo;
				long r = innerHi;
				long[] firstSize = toSize(getRow(innerLo));

				while (l <= r) {
					long mid = (l + r) >> 1;
					long diff = mid - innerLo + 1;
					long[] currSize = toSize(getRow(mid));

					if (valid(currSize, firstSize, diff))
						l = mid + 1;
					else
						r = mid - 1;
				}
				// first row of segment with same size as l = innerLo
				// last row of segment with same size as l = r

				long[] lastSize = toSize(getRow(r));
				lWhite += (firstSize[0] + lastSize[0]) * (r - innerLo + 1) / 2;
				bWhite += (firstSize[1] + lastSize[1]) * (r - innerLo + 1) / 2;
				rWhite += (firstSize[2] + lastSize[2]) * (r - innerLo + 1) / 2;

				innerLo = r + 1;
			}
		}

		// doing red
		hi = 2 * N;
		while (lo <= hi) {
			vis.add(toString(getRow(lo)));

			long innerLo = lo;
			long innerHi = hi;
			while (innerLo <= innerHi) {
				long mid = (innerLo + innerHi) >> 1;
				if (vis.contains(toString(getRow(mid))))
					innerLo = mid + 1;
				else
					innerHi = mid - 1;
			}

			long beg = lo; // first row of segment
			long end = innerHi; // last row of segment

			lo = innerHi + 1;

			innerLo = beg;
			innerHi = end;
			while (innerLo <= innerHi) {
				long l = innerLo;
				long r = innerHi;

				long[] firstSize = toSize(getRow(innerLo));

				while (l <= r) {
					long mid = (l + r) >> 1;
					long diff = mid - innerLo + 1;
					long[] currSize = toSize(getRow(mid));

					if (valid(currSize, firstSize, diff))
						l = mid + 1;
					else
						r = mid - 1;
				}
				// first row of segment with same size as l = innerLo
				// last row of segment with same size as l = r

				long[] lastSize = toSize(getRow(r));
				lRed += (firstSize[0] + lastSize[0]) * (r - innerLo + 1) / 2;
				bRed += (firstSize[1] + lastSize[1]) * (r - innerLo + 1) / 2;
				rRed += (firstSize[2] + lastSize[2]) * (r - innerLo + 1) / 2;

				innerLo = r + 1;
			}
		}
		out.printf("%d %d %d %d %d %d\n", lWhite, lRed, bWhite, bRed, rWhite, rRed);
		out.close();
	}

	static boolean valid (long[] sz1, long[] sz2, long diff) {
		if (Math.abs(sz1[0] - sz2[0]) != 0 && diff != 1 && Math.abs(sz1[0] - sz2[0]) % (diff - 1) != 0)
			return false;
		if (Math.abs(sz1[1] - sz2[1]) != 0 && diff != 1 && Math.abs(sz1[1] - sz2[1]) % (diff - 1) != 0)
			return false;
		if (Math.abs(sz1[2] - sz2[2]) != 0 && diff != 1 && Math.abs(sz1[2] - sz2[2]) % (diff - 1) != 0)
			return false;
		return true;
	}

	static long getSize (long a, long b, long x, long y, long beg, long end) {
		long diff = Math.abs(b - a) + 1;
		return (a + b) * diff / 2 + (x - beg) * a + (end - y) * b;
	}

	static String toString (long[] row) {
		long[] sz = toSize(row);
		String ret = "";

		if (sz[0] > 0)
			ret += "L";
		if (sz[1] > 0)
			ret += "B";
		if (sz[2] > 0)
			ret += "R";

		return ret;
	}

	static long[] toSize (long[] row) {
		return new long[] {row[0], row[1] - row[0], row[2] - row[1]};
	}

	static long[] getRow (long row) {
		long[] ret = new long[3];

		// getting last L occurrence
		long lo, hi, mid;

		lo = 1;
		hi = 3 * N;

		while (lo <= hi) {
			mid = (lo + hi) >> 1;
			long distL = dist(L, 1, row, mid);
			long distB = dist(2 * N, B, row, mid);
			long distR = dist(R, 3 * N, row, mid);

			if (distL <= distB && distL <= distR)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		ret[0] = hi;

		// getting last B occurrence
		lo = hi + 1;
		hi = 3 * N;

		while (lo <= hi) {
			mid = (lo + hi) >> 1;
			long distL = dist(L, 1, row, mid);
			long distB = dist(2 * N, B, row, mid);
			long distR = dist(R, 3 * N, row, mid);

			if (distL <= distB && distL <= distR || (distB <= distR && distB <= distL))
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		ret[1] = hi;
		ret[2] = 3 * N;
		return ret;
	}

	static long dist (long r1, long c1, long r2, long c2) {
		return Math.abs(r1 - r2) + Math.abs(c1 - c2);
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
