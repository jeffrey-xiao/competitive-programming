package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2015_YODA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		int n = readInt();
		int m = readInt();
		String resN = "";
		String resM = "";
		while (n != 0 || m != 0) {
			if (n % 10 == m % 10) {
				resN = n % 10 + resN;
				resM = n % 10 + resM;
			} else if (n % 10 > m % 10) {
				resN = n % 10 + resN;
			} else if (n % 10 < m % 10) {
				resM = m % 10 + resM;
			}
			n /= 10;
			m /= 10;
		}
		out.println((resN == "" ? "YODA" : Integer.parseInt(resN)) + "\n" + (resM == "" ? "YODA" : Integer.parseInt(resM)));
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

