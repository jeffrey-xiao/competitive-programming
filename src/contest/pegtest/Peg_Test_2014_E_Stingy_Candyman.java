package contest.pegtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Peg_Test_2014_E_Stingy_Candyman {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static long[] nums;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		nums = new long[n];
		for (int x = 0; x < n; x++)
			nums[x] = readLong();
		compute(0, n, new int[n], new HashSet<Long>());
	}

	static boolean compute (int i, int n, int[] b, HashSet<Long> prev) {
		if (i == n) {
			for (Integer x : b)
				System.out.println(x);
			// for(int x = 0; x < b.length; x++)
			// System.out.println(toTen(nums[x], b[x]));
			return true;
		}
		main : for (int x = 2; x <= 10; x++) {
			long next = toTen(nums[i], x);
			if (next == -1)
				continue;
			for (Long l : prev) {
				if (gcf(next, l) != 1)
					continue main;
			}
			b[i] = x;
			prev.add(next);
			if (compute(i + 1, n, Arrays.copyOf(b, b.length), prev))
				return true;
			prev.remove(next);
		}
		return false;
	}

	private static long gcf (long x, long y) {
		return y == 0 ? x : gcf(y, x % y);
	}

	private static long toTen (long x, int b) {
		long result = 0;
		long power = 1;
		while (x != 0) {
			long next = x % 10;
			if (next >= b)
				return -1;
			result += next * power;
			power *= b;
			x /= 10;
		}
		return result;
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
