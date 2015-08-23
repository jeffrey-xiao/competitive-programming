package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2008_CUSKIJA {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		ArrayList<Integer> zero = new ArrayList<Integer>();
		ArrayList<Integer> one = new ArrayList<Integer>();
		ArrayList<Integer> two = new ArrayList<Integer>();
		int[] count = new int[3];
		for (int i = 0; i < n; i++) {
			int a = readInt();
			count[a % 3]++;
			if (a % 3 == 0)
				zero.add(a);
			else if (a % 3 == 1)
				one.add(a);
			else
				two.add(a);
		}
		if (count[0] >= 2 + count[1] + count[2]
				|| (count[1] >= 1 && count[2] >= 1 && count[0] == 0)) {
			System.out.println("impossible");
		} else {
			if (count[0] == 0) {
				if (count[1] == 0) {
					for (Integer i : two)
						System.out.print(i + " ");
				} else if (count[2] == 0) {
					for (Integer i : one)
						System.out.print(i + " ");
				}
			} else {
				one.add(zero.get(0));
				zero.remove(0);
				one.addAll(two);
				int i = 0;
				int j = 0;
				boolean prev = false, next = false, isZero = true;
				;
				while (i < zero.size() || j < one.size()) {
					// System.out.println(i + " " + j + " " + zero.size() + " "
					// + one.size() + " " + prev + " " + next);
					if (isZero) {
						next = j < one.size() && one.get(j) % 3 == 0;
						if (!prev && !next && i < zero.size())
							System.out.print(zero.get(i++) + " ");
						isZero = !isZero;
					} else {
						if (j < one.size()) {
							prev = one.get(j) % 3 == 0;
							System.out.print(one.get(j++) + " ");
						}
						isZero = !isZero;
					}
				}
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
