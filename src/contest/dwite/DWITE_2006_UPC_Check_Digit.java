package contest.dwite;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DWITE_2006_UPC_Check_Digit {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			String s = next();
			int even = 0;
			int odd = 0;
			for (int x = 0; x < s.length(); x++) {
				if ((x + 1) % 2 == 0)
					even += (s.charAt(x) - '0');
				else
					odd += (s.charAt(x) - '0');
			}
			int add = (10 - (even + odd * 3) % 10);
			int newLastDigit = (s.charAt(11) - '0' + add) % 10;
			System.out.println(s.substring(0, 11) + newLastDigit);
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
