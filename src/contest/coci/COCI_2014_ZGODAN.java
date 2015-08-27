package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class COCI_2014_ZGODAN {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;
	static String s1 = "-1";
	static String s2 = "-1";
	static BigInteger diff = new BigInteger("-1");
	static BigInteger target;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		String in = next();
		target = new BigInteger(in);
		char[] n = in.toCharArray();
		compute(n, 0);
		// System.out.println(diff.toString());
		if (s2.equals("-1"))
			System.out.println(s1);
		else
			System.out.println(s1 + " " + s2);
	}

	private static void compute (char[] n, int i) {
		// System.out.println(new String(n) + " " + i);
		if (i == n.length)
			return;
		if (i == 0) {
			char org = n[i];
			for (int j = -1; j <= 1; j++) {
				char c = (char) (n[i] + j);
				if (c < '0' || c > '9')
					continue;
				n[i] = c;
				// System.out.println("START " + new String(n));
				compute(n, i + 1);
				n[i] = org;
				// System.out.println("END " + new String(n));
			}
		} else {
			if (n[i] % 2 != n[i - 1] % 2)
				compute(n, i + 1);
			else {
				// System.out.println(i + " " + (i+1) + " " + n[i] + " " +
				// n[i+1]);
				char[] org = new char[n.length];
				for (int j = 0; j < n.length; j++)
					org[j] = n[j];
				// System.out.println("orginal " + new String(org));
				char c = n[i];
				if (c - 1 >= '0') {
					n[i] = (char) (c - 1);
					for (int j = i + 1; j < n.length; j++) {
						if ((n[j - 1] - '0') % 2 == 0)
							n[j] = '9';
						else
							n[j] = '8';
					}
					replace(n);
					// System.out.println("new string " + new String(n));
				}
				if (c + 1 <= '9') {
					n[i] = (char) (c + 1);
					for (int j = i + 1; j < n.length; j++) {
						if ((n[j - 1] - '0') % 2 == 0)
							n[j] = '1';
						else
							n[j] = '0';
					}
					replace(n);
					// System.out.println("new string " + new String(n));
				}
				for (int j = 0; j < n.length; j++)
					n[j] = org[j];
			}
		}
	}

	static void replace (char[] n) {
		BigInteger next = new BigInteger(new String(n));
		BigInteger d = target.subtract(next).abs();
		int cmp = d.compareTo(diff);
		// System.out.println(diff.equals(new BigInteger("-1")));
		// System.out.println("ENTERED WITH " + new String(n));
		if (diff.equals(new BigInteger("-1"))) {
			// System.out.println("DIFFERENCE " + new String(n));
			diff = d;
			s1 = new String(n);
		} else if (cmp < 0) {
			diff = d;
			s1 = new String(n);
		} else if (cmp == 0) {
			s2 = new String(n);
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}