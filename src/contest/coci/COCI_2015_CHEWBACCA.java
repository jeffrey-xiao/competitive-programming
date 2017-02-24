package contest.coci;

import java.util.*;
import java.io.*;

public class COCI_2015_CHEWBACCA {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	@SuppressWarnings("unused")
	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		long n = readLong();
		int k = readInt();
		int q = readInt();
		for (int i = 0; i < q; i++) {
			long x = readLong();
			long y = readLong();
			if (k == 1)
				out.println(Math.abs(x - y));
			else {
				int cnt = 0;
				while (x != y) {
					if (x > y)
						x = (x - (2 - k)) / k;
					else
						y = (y - (2 - k)) / k;
					cnt++;
				}
				out.println(cnt);
			}
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