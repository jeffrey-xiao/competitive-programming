package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class COCI_2009_GENIJALAC {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	static int[] A;
	static boolean[] v;
	static int[] cycle;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int a = readInt();
		int b = readInt();
		int c = readInt();
		int d = readInt();
		A = new int[n];
		v = new boolean[n];
		cycle = new int[n];
		for (int i = 0; i < n; i++)
			A[readInt() - 1] = i;
		for (int i = 0; i < n; i++) {
			if (!v[i])
				findCycle(i);
		}
		int lcm = 1;
		for (int i = 0 + c; i < n - d; i++) {
			lcm = lcm(cycle[i], lcm);
		}
		// System.out.println(lcm);
		System.out.println((b + lcm - 1) / (lcm) - (a + lcm - 2) / lcm);
	}

	private static int lcm (int a, int b) {
		return a * b / gcf(a, b);
	}

	private static int gcf (int a, int b) {
		return b == 0 ? a : gcf(b, a % b);
	}

	private static void findCycle (int i) {
		ArrayList<Integer> visited = new ArrayList<Integer>();
		v[i] = true;
		visited.add(i);
		int count = 1;
		while (!v[A[i]]) {
			i = A[i];
			v[i] = true;
			visited.add(i);
			count++;
		}
		for (Integer j : visited)
			cycle[j] = count;
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
