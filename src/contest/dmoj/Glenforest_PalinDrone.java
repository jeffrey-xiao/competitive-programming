package contest.dmoj;

import java.util.*;
import java.io.*;
import java.math.BigInteger; 

public class Glenforest_PalinDrone {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;
	
	static final long MOD = 1000000000;
	
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		BigInteger n = new BigInteger(readLine());
		if (n.mod(new BigInteger("2")).equals(new BigInteger("1"))) {
			long res = pow(10, n.add(new BigInteger("1")).divide(new BigInteger("2"))) + pow(10, n.subtract(new BigInteger("1")).divide(new BigInteger("2")));
			out.println((res - 1 - 1 + MOD)%MOD);
		} else {
			long res = 2*pow(10, n.add(new BigInteger("1")).divide(new BigInteger("2")));
			out.println((res-2 + MOD)%MOD);
		}
		out.close();
	}

	static long pow (long base, BigInteger pow) {
		if (pow.equals(new BigInteger("0")))
			return 1;
		if (pow.equals(new BigInteger("1")))
			return base;
		if (pow.mod(new BigInteger("2")).equals(new BigInteger("0")))
			return pow(base*base%MOD, pow.divide(new BigInteger("2")));
		return base*pow(base*base%MOD, pow.divide(new BigInteger("2")))%MOD;
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

