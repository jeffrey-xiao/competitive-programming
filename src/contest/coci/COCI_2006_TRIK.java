package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2006_TRIK {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		char[] c = next().toCharArray();
		boolean[] move = {true, false, false};
		for (int i = 0; i < c.length; i++) {
			if (c[i] == 'A')
				swap(move, 0, 1);
			else if (c[i] == 'B')
				swap(move, 1, 2);
			else
				swap(move, 0, 2);
		}
		if (move[0])
			System.out.println(1);
		else if (move[1])
			System.out.println(2);
		else
			System.out.println(3);
	}

	static void swap (boolean[] b, int i, int j) {
		boolean temp = b[i];
		b[i] = b[j];
		b[j] = temp;
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
