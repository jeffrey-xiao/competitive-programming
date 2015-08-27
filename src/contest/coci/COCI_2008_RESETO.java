package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2008_RESETO {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		int k = readInt();
		boolean[] primes = new boolean[n];
		primes[0] = true;
		int count = 0;
		for (int x = 1; x < primes.length; x++)
			if (primes[x] == false) {
				count++;
				for (int y = (x + 1) * (x + 1) - 1; y < primes.length; y += (x + 1)) {
					if (!primes[y])
						count++;
					if (count == k) {
						System.out.println(y + 1);
						return;
					}
					primes[y] = true;
				}

				if (count == k) {
					System.out.println(x + 1);
					return;
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
