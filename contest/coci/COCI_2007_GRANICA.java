package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class COCI_2007_GRANICA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int[] nums = new int[n];
		for (int x = 0; x < n; x++)
			nums[x] = readInt();
		Arrays.sort(nums);
		int diff = nums[1] - nums[0];
		for (int x = 1; x * x <= diff; x++) {
			if (diff % x == 0) {

				int other = diff / x;
				int remainder1 = nums[0] % x;
				int remainder2 = nums[1] % other;
				boolean isValid1 = true;
				if (other == x || x == 1)
					isValid1 = false;
				boolean isValid2 = true;
				for (int y = 2; y < n && (isValid1 || isValid2); y++) {
					if (nums[y] % x != remainder1)
						isValid1 = false;
					if (nums[y] % other != remainder2)
						isValid2 = false;
				}
				if (isValid1)
					System.out.print(x + " ");
				if (isValid2)
					System.out.print(other + " ");
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
