package contest.dmoj;

import java.util.*;
import java.io.*;

public class DMOPC_2015_Counting_Money {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();

		for (int i = 1; i <= N; i++) {
			char op = readCharacter();
			String val = next();

			if (val.equals("0")) {
				out.println(0);
			} else {
				if (op == 'A') {
					out.println(toBaseTen(val));
				} else {
					out.println(toBaseNegTwo(val));
				}
			}
		}

		out.close();
	}

	static String toBaseNegTwo (String str) {
		long val = Long.parseLong(str);

		StringBuilder res = new StringBuilder("");
		while (val != 0) {
			long remainder = val % -2;
			val /= -2;

			if (remainder < 0) {
				remainder += 2;
				val += 1;
			}

			res.append("" + remainder);
		}
		return res.reverse().toString();
	}

	static int toBaseTen (String val) {
		int pow = 1;
		int res = 0;
		for (int i = val.length() - 1; i >= 0; i--) {
			res += pow * (val.charAt(i) - '0');
			pow *= -2;
		}
		return res;
	}

	static int pow (int b, int p) {
		if (p == 0)
			return 1;
		if (p == 1)
			return b;
		return p % 2 == 0 ? pow(b * b, p / 2) : b * pow(b * b, p / 2);
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
