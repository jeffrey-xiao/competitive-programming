package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2003_J1 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int t = readInt();
		int s = readInt();
		int h = readInt();
		for (int x = 0; x < t; x++)
			System.out.println("*" + space(s, ' ') + "*" + space(s, ' ') + "*");
		System.out.println(space(2 * s + 3, '*'));
		for (int x = 0; x < h; x++)
			System.out.println(space(s + 1, ' ') + "*");
	}

	private static String space (int s, char c) {
		String st = "";
		for (int x = 0; x < s; x++)
			st += c;
		return st;
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
