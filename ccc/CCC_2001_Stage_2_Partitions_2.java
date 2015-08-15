package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CCC_2001_Stage_2_Partitions_2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;
	static int totalSum;
	static int a;

	public static void main (String[] args) throws IOException {
		main : for (int q = readInt(); q > 0; q--) {
			totalSum = 0;
			int k = readInt();
			a = readInt();
			if (a > 1000000000) {
				System.out.println("Too big");
				continue;
			}
			for (int x = 1; x <= k; x++) {
				ArrayList<Integer> nums = new ArrayList<Integer>();
				nums.add(x);
				if (compute(x, k - x, nums))
					continue main;
				// System.out.println(totalSum);
			}
			System.out.println("Too big");
		}
	}

	static boolean compute (int prev, int currSum, ArrayList<Integer> nums) {
		if (currSum == 0) {
			totalSum++;
			if (totalSum == a) {
				System.out.print("(" + nums.get(0));
				for (int x = 1; x < nums.size(); x++) {
					System.out.print("," + nums.get(x));
				}
				System.out.println(")");
				return true;
			}
			return false;
		}
		for (int x = 1; x <= Math.min(prev, currSum); x++) {
			nums.add(x);
			if (compute(x, currSum - x, nums))
				return true;
			nums.remove((Integer) x);
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
