package codebook.datastructures;

import java.util.*;
import java.io.*;

public class SparseTable {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, q, ln;
	static int[][] m;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = readInt();
		ln = 1 + (int) (Math.ceil(Math.log(n) / Math.log(2)));

		m = new int[n][ln];

		for (int i = 0; i < n; i++)
			m[i][0] = readInt();
		for (int i = 1; i < ln; i++)
			for (int j = 0; j + (1 << i) - 1 < n; j++) {
				m[j][i] = Math.min(m[j][i - 1], m[j + (1 << (i - 1))][i - 1]);
			}
		for (int i = 0; i < q; i++) {
			int a = readInt() - 1;
			int b = readInt() - 1;
			int sz = (int) (Math.log(b - a + 1) / Math.log(2));
			out.println(Math.min(m[a][sz], m[b - (1 << sz) + 1][sz]));
		}

		out.close();
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
