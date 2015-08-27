package contest.codeforces;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Round_299A {

	static BufferedReader br;
	static PrintWriter pr;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		pr = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		// pr = new PrintWriter(new FileWriter("out.txt"));

		long a = readInt();
		long b = readInt();
		int n = readInt();
		for (int i = 0; i < n; i++) {
			int l = readInt();
			long t = readInt();
			long m = readInt();
			int lo = l - 1;
			int hi = 1000001;
			System.out.println(t + " " + t * m);
			while (lo <= hi) {
				int mid = lo + (hi - lo) / 2;
				long max = a + (mid - 2) * b;
				long largest = b * (mid - l - 1);

				long sum = (a + (l - 1) * b) * (mid - l) + largest * ((mid - l) / 2) + ((mid - l) % 2 == 1 ? largest / 2 : 0);
				// System.out.println((l) + " " + (mid-1) + " " + max + " " +
				// sum + " " + largest);
				if (max <= t && sum <= m * t) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			// System.out.println(hi);
			if (hi <= l)
				System.out.println(-1);
			else
				System.out.println(hi - 1);
		}

		pr.close();
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
