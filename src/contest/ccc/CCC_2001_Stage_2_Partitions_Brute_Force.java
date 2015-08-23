package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Partitions_Brute_Force {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int[] values;
	static int start;

	public static void main (String[] args) throws IOException {
		testcases : for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int index = readInt();
			start = n;
			// initial
			values = new int[n + 1];
			compute(n, 0, n);
			if (index > values[n]) {
				System.out.println("Too big");
				continue testcases;
			}
			values[n] = 1;
			String s = "";

			int next = 0;
			int sum = 0;
			main : while (index != 0 || sum == n) {
				int currSum = 0;

				for (int x = 1; x < n + 1; x++) {
					currSum += values[x];
					if (currSum >= index) {
						if (index == 1)
							index = 0;
						else
							index = index - (currSum - values[x]);
						next = x;
						sum += x;

						s += x + ",";
						if (sum == n)
							break main;
						// System.out.println(index);
						break;
					}
				}
				values = new int[n + 1];
				compute(index, 0, next);

			}
			while (n != sum) {
				s += "1,";
				sum++;
			}
			System.out.println("(" + s.substring(0, s.length() - 1) + ")");
		}

	}

	private static int compute (int n, int sum, int prev) {
		System.out.println(n);
		int total = 0;
		if (n == 0)
			return 1;
		if (prev < n) {
			if (n > start)
				return 0;
			values[n] = values[prev];
			return values[prev];
		}
		for (int x = Math.min(prev, n); x >= 1; x--) {
			if (values[n - x] != 0) {
				total += values[n - x];
			} else {
				total += compute(n - x, 0, x);
			}
		}

		values[n] = total;
		return total;
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
