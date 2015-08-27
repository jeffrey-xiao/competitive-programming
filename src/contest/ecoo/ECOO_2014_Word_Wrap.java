package contest.ecoo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ECOO_2014_Word_Wrap {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		for (int t = 0; t < 5; t++) {
			int l = readInt();
			String s = readLine();
			char[][] c = new char[s.length() / l][l];
			int counter = 0;
			for (int y = 0; y < l; y++) {
				for (int x = 0; x < s.length() / l; x++) {
					c[x][y] = s.charAt(counter);
					counter++;
				}
			}
			String decode = "";
			for (int x = 0; x < c.length; x++) {
				boolean firstSpace = true;
				for (int y = 0; y < l; y++) {
					if (firstSpace && c[x][y] == ' ') {
					} else {
						firstSpace = false;
						decode += c[x][y];
					}
				}
				decode += ' ';
			}
			System.out.println("\n" + decode);
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
		return br.readLine();
	}
}
