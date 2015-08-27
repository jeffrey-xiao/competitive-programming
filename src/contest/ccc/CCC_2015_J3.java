package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2015_J3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main(String[] args) throws IOException {
		// abcdefgh ijklmn opqrstu vwxyz
		String vowel = "aeiou";
		char[] closest = { 'a', 'a', 'a', 'e', 'e', 'e', 'e', 'i', 'i', 'i',
				'i', 'i', 'o', 'o', 'o', 'o', 'o', 'o', 'u', 'u', 'u', 'u',
				'u', 'u', 'u', 'u' };
		char[] next = { 'b', 'c', 'd', 'f', 'f', 'g', 'h', 'j', 'j', 'k', 'l',
				'm', 'n', 'p', 'p', 'q', 'r', 's', 't', 'v', 'v', 'w', 'x',
				'y', 'z', 'z' };
		char[] in = readLine().toCharArray();
		String res = "";
		for (int x = 0; x < in.length; x++) {
			if (vowel.indexOf(in[x]) == -1)
				res += "" + in[x] + (char) (closest[in[x] - 'a'])
						+ (char) (next[in[x] - 'a']);
			else
				res += in[x];

		}
		System.out.println(res);
	}

	static String next() throws IOException {
		while (st == null || !st.hasMoreTokens())
			st = new StringTokenizer(br.readLine().trim());
		return st.nextToken();
	}

	static long readLong() throws IOException {
		return Long.parseLong(next());
	}

	static int readInt() throws IOException {
		return Integer.parseInt(next());
	}

	static double readDouble() throws IOException {
		return Double.parseDouble(next());
	}

	static char readCharacter() throws IOException {
		return next().charAt(0);
	}

	static String readLine() throws IOException {
		return br.readLine().trim();
	}
}
