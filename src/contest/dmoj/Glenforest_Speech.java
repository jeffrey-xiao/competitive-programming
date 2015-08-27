package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Glenforest_Speech {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int n = readInt();
		String[] s1 = new String[n];
		String[] s2 = new String[n];
		for (int i = 0; i < n; i++) {
			s1[i] = next();
			s2[i] = next();
		}
		String[] in = readLine().split(" ");
		for (int i = 0; i < in.length; i++) {
			for (int j = 0; j < n; j++) {
				if (i < in.length - 1) {
					if (in[i].equals(s2[j]) && i < in.length - 1)
						in[i] = s1[j];
				} else {
					if (in[i].substring(0, in[i].length() - 1).equals(s2[j]) && i == in.length - 1)
						in[i] = s1[j] + ".";
				}
			}
		}
		for (String s : in)
			System.out.print(s + " ");
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
