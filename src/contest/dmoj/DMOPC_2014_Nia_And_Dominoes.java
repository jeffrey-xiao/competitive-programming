package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DMOPC_2014_Nia_And_Dominoes {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int a = readInt();
		int b = readInt();
		int n = readInt();
		int m = readInt();
		int[] c = new int[3];
		c[0] = 1;
		for (int i = 1; i <= m; i++) {
			c[i % 3] = 0;
			if (i - 2 >= 0)
				c[i % 3] = (c[i % 3] + (c[(i - 2) % 3] * b) % n) % n;
			c[i % 3] = (c[i % 3] + (c[(i - 1) % 3] * a) % n) % n;
		}
		System.out.println(c[m % 3]);
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
