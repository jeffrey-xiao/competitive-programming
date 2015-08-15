package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class COCI_2014_CESTA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		char[] in = next().toCharArray();
		Character[] sorted = new Character[in.length];
		for (int i = 0; i < in.length; i++)
			sorted[i] = in[i];
		Arrays.sort(sorted, new Comparator<Character>() {
			public int compare (Character c1, Character c2) {
				return c2 - c1;
			}
		});
		boolean hasZero = false;
		int sum = 0;
		for (int i = 0; i < sorted.length; i++) {
			sum += sorted[i] - '0';
			hasZero |= sorted[i] == '0';
		}
		if (hasZero && sum % 3 == 0)
			for (int i = 0; i < sorted.length; i++)
				ps.print(sorted[i]);
		else
			ps.println(-1);
		ps.close();
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