package contest.acm;

import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class Waterloo_Local_2016_C {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));


		String a = next();
		String b = next();
		if (b.length() >= 4)
			out.println("Your wish is granted!");
		else {
			BigInteger ba = new BigInteger(a);
			BigInteger bb = new BigInteger("2");
			bb = bb.pow(Integer.parseInt(b));
			if (ba.compareTo(bb) <= 0)
				out.println("Your wish is granted!");
			else
				out.println("You will become a flying monkey!");
		}
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
