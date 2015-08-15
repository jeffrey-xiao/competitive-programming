package ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class CCC_2013_Stage_2_All_Your_Bases_Are_Belong_To_Palindrome2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	/*
	 * 9 27 124 199 249 499 999
	 */
	public static void main (String[] args) throws IOException {
		long input = readLong();
		for (long x = 2; x < input; x++) {
			long y = input;
			ArrayList<Long> value = new ArrayList<Long>();
			long curr = (long) Math.pow(x,
					(long) (Math.log(input) / Math.log(x)));
			// System.out.println("BLAH" + curr);
			if (x * x > input) {
				if (input / curr == (input % curr) / (curr / x))
					System.out.println(x);
			} else {
				while (curr != 0) {

					value.add(y / curr);
					y %= curr;
					curr /= x;
				}
				ArrayList<Long> copy = new ArrayList<Long>(value);
				/*
				 * System.out.println(x); for(long z: copy) System.out.print(z +
				 * " "); System.out.println();
				 */
				Collections.reverse(value);
				if (value.equals(copy))
					System.out.println(x);
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