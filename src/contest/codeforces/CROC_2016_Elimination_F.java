package contest.codeforces;

import java.util.*;
import java.io.*;

public class CROC_2016_Elimination_F {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int MOD = (int) (1e9 + 7);

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int k = readInt();
		int q = readInt();

		int[] a = new int[n + q];
		long[] coeff = new long[1000001];
		long[] choose = new long[1000001]; // I choose k
		int[] divisorCnt = new int[1000001];

		ArrayList<ArrayList<Integer>> divisors = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < n + q; i++)
			a[i] = readInt();

		for (int i = 0; i <= 1000000; i++)
			divisors.add(new ArrayList<Integer>());

		for (int i = 1; i <= 1000000; i++) {
			coeff[i] += i;
			divisors.get(i).add(i);
			for (int j = 2 * i; j <= 1000000; j += i) {
				coeff[j] -= coeff[i];
				divisors.get(j).add(i);
			}
		}
		long prev = 1;
		for (int i = k; i <= 1000000; i++) {
			if (i != k) {
				prev = (prev * i) % MOD;
				prev = divMod(prev, i - k);
			}
			choose[i] = prev;
		}

		long res = 0;

		for (int i = 0; i < n + q; i++) {
			for (int d : divisors.get(a[i])) {
				divisorCnt[d]++;
				res = (res + coeff[d] * (choose[divisorCnt[d]] - choose[divisorCnt[d] - 1]) % MOD + MOD) % MOD;
			}
			if (i >= n)
				out.println(res);
		}
		out.close();
	}

	static int divMod (long i, long j) {
		return (int) (i * pow(j, MOD - 2) % MOD);
	}

	static long pow (long base, long pow) {
		if (pow == 0)
			return 1;
		if (pow == 1)
			return base;
		if (pow % 2 == 0)
			return pow(base * base % MOD, pow / 2);
		return base * pow(base * base % MOD, pow / 2) % MOD;
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
