package other;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class UTS_Open_P2_Generation {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps;
	static StringTokenizer st;

	static String alpha = "abcdefghijklmnopqrstuvwxyz";
	static HashSet<String> repeat = new HashSet<String>();

	public static void main (String[] args) throws IOException {
		ps = new PrintWriter(new File("UTS - P2 - " + args[3] + ".in"));
		int x = Integer.parseInt(args[0]);
		ps.println(x);
		int[] map1 = new int[x];
		int[] map2 = new int[x];
		boolean[] alreadyMapped = new boolean[x];
		for (int y = 0; y < x; y++) {
			char a = getChar(x);
			while (alreadyMapped[a - 'a'])
				a = getChar(x);
			alreadyMapped[a - 'a'] = true;
			map1[y] = a - 'a';
		}
		for (int y = 0; y < x; y++) {
			char a = getChar(x);
			while (a - 'a' == map1[y])
				a = getChar(x);
			map2[y] = a - 'a';
		}
		for (int y = 0; y < x; y++) {
			int ran = (int) (Math.random() * 2);
			if (ran == 0)
				ps.println((char) (map1[y] + 'a') + " "
						+ (char) (map2[y] + 'a'));
			else
				ps.println((char) (map2[y] + 'a') + " "
						+ (char) (map1[y] + 'a'));
		}
		int q = Integer.parseInt(args[1]);
		ps.println(q);
		int bound = Integer.parseInt(args[2]);
		for (int y = 0; y < q; y++) {
			int r = (int) (Math.random() * bound) + 1;
			String in = getString(x, r);
			String out = getString(x, r);
			while (repeat.contains(in + out)) {
				in = getString(x, r);
				out = getString(x, r);
			}
			int ran = (int) (Math.random() * 3);
			if (ran == 0) {
				out = "";
				for (int z = 0; z < in.length(); z++) {
					out += (char) (map1[in.charAt(z) - 'a'] + 'a');
				}
			}
			ps.println(in + " " + out);
		}
		ps.close();
	}

	private static String getString (int x, int l) {
		String s = "";
		for (int y = 0; y < l; y++)
			s += getChar(x);
		return s;
	}

	private static char getChar (int x) {
		return alpha.charAt((int) (Math.random() * x));
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
