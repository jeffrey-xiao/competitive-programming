package contest.ccc;

import java.util.*;
import java.io.*;

public class CCC_2016_J3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		out.println(getLongestPalindrome(readLine()));
		out.close();
	}

	static int getLongestPalindrome (String s) {
		int len = s.length() * 2 - 1;
		char[] text = new char[len];
		for (int i = 0; i < len; i++)
			text[i] = '#';
		for (int i = 0; i < len; i += 2)
			text[i] = s.charAt(i / 2);
		int[] max = new int[len];

		int c = 0;
		int r = 0;
		for (int i = 1; i < len; i++) {
			int j = (c - (i - c));

			max[i] = r > i ? Math.min(r - i, max[j]) : 0;
			while (i + 1 + max[i] < len && i - 1 - max[i] >= 0 && text[i + 1 + max[i]] == text[i - 1 - max[i]])
				max[i]++;

			if (i + max[i] > r) {
				r = i + max[i];
				c = i;
			}
		}
		int maxLength = 0;
		int index = 0;
		for (int i = 1; i < len - 1; i++) {
			if (max[i] > maxLength) {
				maxLength = max[i];
				index = i;
			}
		}
		return (index - maxLength) / 2 + maxLength + 1 - (index - maxLength + 1) / 2;
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

