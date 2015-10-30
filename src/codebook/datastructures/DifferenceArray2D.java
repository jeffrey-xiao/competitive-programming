/*
 * A 2D implementation of the difference array
 */

package codebook.datastructures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class DifferenceArray2D {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int n, q;
	static int[][] a;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		n = readInt();
		q = readInt();

		a = new int[n + 2][n + 2];
		for (int i = 0; i < q; i++) {
			int x1 = readInt();
			int y1 = readInt();
			int x2 = readInt() + 1;
			int y2 = readInt() + 1;
			int d = readInt();
			a[x1][y1] += d;
			a[x1][y2] -= d;
			a[x2][y1] -= d;
			a[x2][y2] += d;
		}
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				a[i][j] += a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1];
		for (int i = 1; i <= n; i++, out.println())
			for (int j = 1; j <= n; j++)
				out.print(a[i][j] + " ");
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
