package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class VMSS_4_Nations_1_Secret {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		String c = next();
		int max = 0;
		String s = "";
		for (int i = 0; i < n; i++) {
			int l = i, r = i;
			while (l >= 0 && r < n && c.charAt(l) == c.charAt(r)) {
				l--;
				r++;
			}
			l++;
			r--;
			if (r - l + 1 > max) {
				max = r - l + 1;
				s = c.substring(l, r + 1);
			}
		}
		for (int i = 0; i < n - 1; i++) {
			int l = i, r = i + 1;
			while (l >= 0 && r < n && c.charAt(l) == c.charAt(r)) {
				l--;
				r++;
			}
			l++;
			r--;
			if (r - l + 1 > max) {
				max = r - l + 1;
				s = c.substring(l, r + 1);
			}
		}
		System.out.println(s);
		System.out.println(max);
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
