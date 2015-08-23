package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_Super_Mario_Blues {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = readInt(); t > 0; t--) {
			String s = next();
			int level = (s.charAt(0) - 49) * 4 + (s.charAt(2) - 49) + 1;
			if (level <= 2)
				System.out.println(2 + 4 + (3 - level));
			else if (level <= 14)
				System.out.println((15 - level) + 4);
			else
				System.out.println(33 - level);
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
