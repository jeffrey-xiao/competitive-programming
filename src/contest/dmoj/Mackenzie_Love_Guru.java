package contest.dmoj;

import java.util.*;
import java.io.*;

public class Mackenzie_Love_Guru {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		String a = readLine().toLowerCase();
		String b = readLine().toLowerCase();
		
		
		int compatA = 0;
		int compatB = 0;
		
		for (int i = 0; i < a.length(); i++) {
			compatA = (compatA + modpow(a.charAt(i) - 'a' + 1, i + 1, 10)) % 10;
		}
		
		for (int i = 0; i < b.length(); i++) {
			compatB = (compatB + modpow(b.charAt(i) - 'a' + 1, i + 1, 10)) % 10;
		}
		
		if (compatA == 0)
			compatA = 10;
		if (compatB == 0)
			compatB = 10;
		
		out.println(compatA + compatB);
		out.close();
	}

	static int modpow (int base, int pow, int mod) {
		if (pow == 0)
			return 1;
		if (pow == 1)
			return base;
		if (pow % 2 == 0)
			return modpow(base * base % mod, pow / 2, mod) % mod;
		return base * modpow(base * base % mod, pow / 2, mod) % mod;
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

