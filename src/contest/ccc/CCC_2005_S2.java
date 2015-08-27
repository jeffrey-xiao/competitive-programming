package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_S2 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int c = readInt();
		int r = readInt();
		int x = 0;
		int y = 0;
		int nx = readInt();
		int ny = readInt();
		while (nx != 0 || ny != 0) {
			x = Math.min(c, x + nx);
			y = Math.min(r, y + ny);
			x = Math.max(x, 0);
			y = Math.max(y, 0);
			System.out.println(x + " " + y);
			nx = readInt();
			ny = readInt();
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
