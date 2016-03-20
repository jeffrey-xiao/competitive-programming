package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2009_PASIJANS {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static final long BASE = 137;
	static final long MOD = 1000000007;

	static int n;
	static int[] index;

	static int[][] a;
	static long[][] hash;
	static long[] pow = new long[1002];

	static int[] minIndex;
	static int[] minValue;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		minIndex = new int[4 * n];
		minValue = new int[4 * n];

		index = new int[n + 1];
		a = new int[n + 1][];
		hash = new long[n + 1][];

		pow[0] = 1;
		for (int i = 1; i <= 1001; i++)
			pow[i] = pow[i - 1] * BASE % MOD;

		int total = 0;
		for (int i = 1; i <= n; i++) {
			int sz = readInt();
			total += sz;

			a[i] = new int[sz + 2];
			hash[i] = new long[sz + 2];
			index[i] = 1;

			for (int j = 1; j <= sz; j++) {
				a[i][j] = readInt();
				hash[i][j] = (a[i][j] + hash[i][j - 1] * BASE) % MOD;
			}
			a[i][sz + 1] = 1 << 30;
		}

		build(1, 1, n);

		for (int i = 0; i < total; i++) {
			out.printf("%d ", minValue[1]);
			assert (minValue[1] == a[minIndex[1]][index[minIndex[1]]]);
			index[minIndex[1]]++;
			update(1, 1, n, minIndex[1]);
		}
		out.println();
		out.close();
	}

	static void build (int n, int l, int r) {
		if (l == r) {
			minIndex[n] = l;
			minValue[n] = a[l][index[l]];
			return;
		}
		int mid = (l + r) / 2;
		build(n << 1, l, mid);
		build(n << 1 | 1, mid + 1, r);
		pushUp(n);
	}

	static void update (int n, int l, int r, int x) {
		if (l == x && x == r) {
			minIndex[n] = l;
			minValue[n] = a[l][index[l]];
			return;
		}
		int mid = (l + r) / 2;
		if (x <= mid)
			update(n << 1, l, mid, x);
		else
			update(n << 1 | 1, mid + 1, r, x);
		pushUp(n);
	}

	static void pushUp (int n) {
		if (minValue[n << 1] < minValue[n << 1 | 1]) {
			minValue[n] = minValue[n << 1];
			minIndex[n] = minIndex[n << 1];
		} else if (minValue[n << 1] > minValue[n << 1 | 1]) {
			minValue[n] = minValue[n << 1 | 1];
			minIndex[n] = minIndex[n << 1 | 1];
		} else {
			int leftIndex = minIndex[n << 1];
			int rightIndex = minIndex[n << 1 | 1];
			int sz = Math.min(a[leftIndex].length - 1 - index[leftIndex], a[rightIndex].length - 1 - index[rightIndex]);
			int lo = 0;
			int hi = sz;
			while (lo <= hi) {
				int mid = (hi + lo) / 2;
				if (hash(leftIndex, index[leftIndex], index[leftIndex] + mid) == hash(rightIndex, index[rightIndex], index[rightIndex] + mid))
					lo = mid + 1;
				else
					hi = mid - 1;
			}
			if (lo > sz) {
				if (a[leftIndex].length - 1 - index[leftIndex] < a[rightIndex].length - 1 - index[rightIndex]) {
					minValue[n] = minValue[n << 1 | 1];
					minIndex[n] = minIndex[n << 1 | 1];
				} else {
					minValue[n] = minValue[n << 1];
					minIndex[n] = minIndex[n << 1];
				}
				return;
			}
			int cmp = a[leftIndex][index[leftIndex] + lo] - a[rightIndex][index[rightIndex] + lo];
			if (cmp == 0) {
				if (a[leftIndex].length - 1 - index[leftIndex] < a[rightIndex].length - 1 - index[rightIndex]) {
					minValue[n] = minValue[n << 1 | 1];
					minIndex[n] = minIndex[n << 1 | 1];
				} else {
					minValue[n] = minValue[n << 1];
					minIndex[n] = minIndex[n << 1];
				}
			} else if (cmp < 0) {
				minValue[n] = minValue[n << 1];
				minIndex[n] = minIndex[n << 1];
			} else {
				minValue[n] = minValue[n << 1 | 1];
				minIndex[n] = minIndex[n << 1 | 1];
			}
		}
	}

	static long hash (int i, int l, int r) {
		return ((hash[i][r] - hash[i][l - 1] * pow[r - l + 1]) % MOD + MOD) % MOD;
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
