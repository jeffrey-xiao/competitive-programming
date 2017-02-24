package contest.misc;

import java.util.*;
import java.io.*;

public class The_Codefather {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;
	static long[] val;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		val = new long[N];

		long sum = 0;
		for (int i = 0; i < N; i++) {
			val[i] = readInt();
			if (i > 0)
				sum += val[i];
		}

		if (sum > (val[0] - 1) * (N - 1)) {
			out.println("impossible");
			out.close();
			return;
		}

		int lo = 0;
		int hi = 1000000;
		while (lo <= hi) {
			long[] curr = Arrays.copyOf(val, N);
			Arrays.sort(curr, 1, N);
			int mid = (lo + hi) >> 1;

			boolean valid = true;
			int index = 1;

			for (int i = N - 1; i > 0; i--) {
				if (curr[i] < curr[0])
					continue;
				int j = index;
				do {
					if (curr[i] < curr[0])
						break;
					if (j == 0) {
						j = (j + 1) % N;
						continue;
					}

					long transfer = mid;
					transfer = Math.min(transfer, curr[i] - curr[0] + 1);
					transfer = Math.min(transfer, curr[0] - 1 - curr[j]);
					if (transfer > 0) {
						curr[i] -= transfer;
						curr[j] += transfer;
					}

					j = (j + 1) % N;
				} while (j != index);

				index = (j - 1 + N) % N;

				if (curr[i] >= curr[0]) {
					valid = false;
					break;
				}
			}

			if (valid)
				hi = mid - 1;
			else
				lo = mid + 1;
		}
		out.println(lo);
		out.close();
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
