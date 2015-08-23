package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2007_J2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		String n = "";
		while (!(n = next()).equals("TTYL")) {
			if (n.equals("CU"))
				System.out.println("see you");
			else if (n.equals(":-)"))
				System.out.println("I'm happy");
			else if (n.equals(":-("))
				System.out.println("I'm sad");
			else if (n.equals(";-)"))
				System.out.println("wink");
			else if (n.equals(":-P"))
				System.out.println("stick out my tongue");
			else if (n.equals("(~.~)"))
				System.out.println("sleepy");
			else if (n.equals("TA"))
				System.out.println("totally awesome");
			else if (n.equals("CCC"))
				System.out.println("Canadian Computing Competition");
			else if (n.equals("CUZ"))
				System.out.println("because");
			else if (n.equals("TY"))
				System.out.println("thank-you");
			else if (n.equals("YW"))
				System.out.println("you're welcome");
			else
				System.out.println(n);
		}
		System.out.println("talk to you later");
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
