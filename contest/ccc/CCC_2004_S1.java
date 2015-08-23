package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2004_S1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int x = readInt(); x > 0; x--) {
			String q = next();
			String w = next();
			String e = next();
			if (q(q, e) || q(q, w) || q(w, e) || w(q, e) || w(q, w) || w(w, e))
				System.out.println("No");
			else
				System.out.println("Yes");
		}
	}

	static boolean q (String q, String w) {
		if (q.indexOf(w) == 0 || w.indexOf(q) == 0)
			return true;
		return false;
	}

	static boolean w (String q, String w) {
		if ((q.indexOf(w) == q.length() - w.length() && q.indexOf(w) >= 0)
				|| (w.indexOf(q) >= 0 && w.length() - q.length() == w
						.indexOf(q)))
			return true;
		return false;
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
