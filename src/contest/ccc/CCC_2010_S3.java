package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CCC_2010_S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static final int LEN = 1000000;
	static int[] h;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		h = new int[n];
		for (int x = 0; x < n; x++)
			h[x] = readInt();
		Arrays.sort(h);
		// for(Integer i : h)
		// System.out.println(i);
		int k = readInt();
		int lo = 0;
		int hi = LEN;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (poss(mid) > k) {
				lo = mid + 1;
			} else
				hi = mid - 1;
		}
		// System.out.println("HERE " + poss(500));
		System.out.println(lo);
	}

	private static int poss (int mid) {
		int best = Integer.MAX_VALUE;
		int size = h.length;
		int d = mid * 2;
		int x = 0;

		while (x < size && h[x] <= d + h[0]) {
			int curr = 1;
			int y = x + 1;
			if (y == size)
				return 1;
			int end = h[x + 1];
			curr++;
			// System.out.println("START " + y);
			while (y < size) {
				if (h[y] > end + d) {
					// System.out.println("END UPDATED " + h[y]);
					end = h[y];
					curr++;
				}
				y++;
			}
			best = Math.min(best, curr);
			x++;
		}
		return best;
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
