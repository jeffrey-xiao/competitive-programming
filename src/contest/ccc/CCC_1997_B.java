package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class CCC_1997_B {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			if (isNasty(n))
				System.out.println(n + " is nasty");
			else
				System.out.println(n + " is not nasty");
		}
	}

	private static boolean isNasty (int n) {
		HashSet<Integer> nums = new HashSet<Integer>();
		for (int x = 1; x * x <= n; x++) {
			if (n % x == 0) {
				int size = nums.size();
				nums.add(x + n / x);
				nums.add(Math.abs(x - n / x));
				if (nums.size() != size + 2)
					return true;
			}
		}
		return false;
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
