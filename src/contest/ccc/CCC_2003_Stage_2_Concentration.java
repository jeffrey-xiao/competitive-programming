package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2003_Stage_2_Concentration {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			int n = readInt();
			int h = readInt();
			int w = readInt();
			int min = Integer.MAX_VALUE;
			for (int x = 1; x <= n; x++) {
				if (n % x == 0)
					min = Math.min(min, (x * h + (w * n / x)) * 2);
			}
			for (int x = 1; x <= n; x++) {
				if (x * w % h == 0) {
					int factor = x * w / h;
					for (int y = 1; y * x <= n; y++) {
						if ((n - x * y) % factor == 0) {
							int repeat = (n - x * y) / factor;
							min = Math.min(min, 2 * (x + repeat) * w + 2 * y * h);
						}
					}
				}
			}
			System.out.println(min);
		}
	}

	static int lcm (int a, int b) {
		return a * b / (gcf(a, b));
	}

	static int gcf (int a, int b) {
		return b == 0 ? a : gcf(b, b % a);
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
