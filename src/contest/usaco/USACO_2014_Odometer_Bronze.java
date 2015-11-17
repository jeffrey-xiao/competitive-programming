package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2014_Odometer_Bronze {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		long n = readLong();
		long l = readLong();
		String s;
		int count = 0;
		for (int y = 0; y < 10; y++) {
			s = "";
			for (int x = 0; x < Long.toString(l).length(); x++) {
				s += "" + y;
				if (x > 1)
					for (int z = 0; z < 10; z++) {
						if (z != y)
							for (int i = 0; i < s.length(); i++) {
								if (i == 0 && z == 0)
									continue;
								StringBuilder newS = new StringBuilder(s).replace(i, i + 1, "" + z);
								if (newS.charAt(0) == '0')
									continue;
								long num = Long.parseLong(newS.toString());
								if (num <= l && num >= n) {
									count++;
								}

							}
					}
			}
		}
		System.out.println(count);
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
