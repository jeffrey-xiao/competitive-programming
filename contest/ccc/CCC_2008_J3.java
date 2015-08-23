package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2008_J3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String s = readLine();
		int r = 0;
		int c = 0;
		int total = 0;
		for (int x = 0; x < s.length(); x++) {
			char curr = s.charAt(x);
			int nr = 4;
			int nc = 0;
			if (curr == ' ') {
				nc = 2;
			} else if (curr == '-') {
				nc = 3;
			} else if (curr == '.') {
				nc = 4;
			} else {
				nr = (curr - 'A') / 6;
				nc = (curr - 'A') % 6;
			}
			total += Math.abs(r - nr) + Math.abs(c - nc);
			r = nr;
			c = nc;
		}
		System.out.println(total + Math.abs(r - 4) + Math.abs(c - 5));
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
