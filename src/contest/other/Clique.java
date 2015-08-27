package contest.other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Clique {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int m = readInt();
		ArrayList<HashSet<Integer>> adj = new ArrayList<HashSet<Integer>>();
		for (int x = 0; x < n; x++)
			adj.add(new HashSet<Integer>());
		for (int x = 0; x < m; x++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			adj.get(a).add(b);
			adj.get(b).add(a);
		}
		int sizeA = n / 2;
		int sizeB = n - sizeA;
		boolean[] dp = new boolean[1 << sizeA];
		boolean[] dp2 = new boolean[1 << sizeB];
		int max = 0;
		dp[0] = true;
		dp2[0] = true;
		for (int x = 0; x < 1 << sizeA; x++) {
			if (dp[x]) {
				HashSet<Integer> set = new HashSet<Integer>();
				for (int y = 0; y < sizeA; y++)
					if ((x & 1 << y) > 0)
						set.add(y);
				main : for (int y = 0; y < sizeA; y++)
					if ((x & 1 << y) == 0) {
						for (Integer i : set)
							if (!adj.get(y).contains(i))
								continue main;
						dp[x | (1 << y)] = true;
					}
				max = Math.max(max, Integer.bitCount(x));
			}
		}
		for (int x = 0; x < 1 << sizeB; x++) {
			if (dp2[x]) {
				HashSet<Integer> set = new HashSet<Integer>();
				HashSet<Integer> otherSet = new HashSet<Integer>();
				int[] count = new int[sizeA];
				for (int y = 0; y < sizeB; y++)
					if ((x & 1 << y) > 0) {
						set.add(y + sizeA);
						for (Integer i : adj.get(y + sizeA)) {
							if (i < sizeA)
								count[i]++;
						}
					}
				int size = Integer.bitCount(x);
				for (int y = 0; y < sizeA; y++)
					if (count[y] == size)
						otherSet.add(y);
				// System.out.println("HERE " + Integer.toBinaryString(x));
				// System.out.println("BITSET " + otherSet);
				int check = toBinary(otherSet);
				if (dp[check]) {
					max = Math.max(max, Integer.bitCount(check) + Integer.bitCount(x));
					if (Integer.bitCount(check) + Integer.bitCount(x) == 14)
						System.out.println(Integer.toBinaryString(check) + " " + Integer.toBinaryString(x));
				}
				main : for (int y = 0; y < sizeB; y++)
					if ((x & 1 << y) == 0) {
						for (Integer i : set)
							if (!adj.get(y + sizeA).contains(i))
								continue main;
						dp2[x | (1 << y)] = true;
					}
				max = Math.max(max, Integer.bitCount(x));
			}
		}
		System.out.println(max);
	}

	private static int toBinary (HashSet<Integer> otherSet) {
		int ret = 0;
		for (Integer i : otherSet)
			ret = ret | 1 << i;
		return ret;
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
