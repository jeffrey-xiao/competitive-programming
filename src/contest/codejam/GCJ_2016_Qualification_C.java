package contest.codejam;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class GCJ_2016_Qualification_C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		// br = new BufferedReader(new FileReader("in.txt"));
		// out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();
		ArrayList<Integer> primes = getPrimesEratosthenes(65536 * 10);
		for (int t = 1; t <= T; t++) {
			int N = readInt();
			int J = readInt();
			out.printf("Case #%d:\n", t);
			int cnt = 0;
			for (long i = (1l << (N - 1)) + 1; cnt < J; i += 2) {
				BigInteger num = new BigInteger(Long.toString(i, 2));
				boolean valid = true;
				long[] div = new long[11];
				main : for (int j = 2; j <= 10; j++) {
					BigInteger curr = toBase(num, j);
					for (int prime : primes)
						if (curr.mod(BigInteger.valueOf(prime)).equals(BigInteger.valueOf(0)) && !curr.equals(BigInteger.valueOf(prime))) {
							div[j] = prime;
							continue main;
						}
					valid = false;
					break;
				}
				if (valid) {
					cnt++;
					out.printf("%d ", num);
					for (int j = 2; j <= 10; j++)
						out.printf("%d ", div[j]);
					out.println();
				}
			}
			out.close();
		}
	}

	static ArrayList<Integer> getPrimesEratosthenes (int n) {
		boolean[] prime = new boolean[n + 1];
		ArrayList<Integer> ret = new ArrayList<Integer>();

		Arrays.fill(prime, true);

		for (int i = 2; i * i <= n; i++)
			if (prime[i])
				for (int j = i * i; j <= n; j += i)
					prime[j] = false;

		for (int i = 2; i <= n; i++)
			if (prime[i])
				ret.add(i);

		return ret;
	}

	static BigInteger toBase (BigInteger n, long base) {
		BigInteger res = BigInteger.valueOf(0);
		BigInteger b = BigInteger.valueOf(1);
		while (n.compareTo(BigInteger.valueOf(0)) > 0) {
			res = res.add(n.mod(BigInteger.valueOf(10)).multiply(b));
			b = b.multiply(BigInteger.valueOf(base));
			n = n.divide(BigInteger.valueOf(10));
		}
		return res;
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
