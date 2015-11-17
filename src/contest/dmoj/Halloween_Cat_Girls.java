package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Halloween_Cat_Girls {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		long w = readLong();
		long[] cute = new long[n + 1];
		long[] width = new long[n + 1];
		long[] max = new long[n + 1];
		int i = 1;
		for (int j = 0; j < n; j++) {
			char ch = readCharacter();
			if (ch == 'A') {
				width[i] = readLong() + width[i - 1];
				cute[i] = readLong() + cute[i - 1];
				int lo = 0, hi = i;
				while (lo <= hi) {
					int mid = lo + (hi - lo) / 2;
					if (width[i] - width[mid] <= w) {
						hi = mid - 1;
					} else {
						lo = mid + 1;
					}
				}
				max[i] = Math.max(max[i - 1], cute[i] - cute[lo]);
				ps.println(max[i]);
				i++;
			} else {
				i--;
			}
		}
		ps.close();
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
