package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_KRATKI {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	static int[][] p;
	static boolean[][] poss;
	static int m;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();
		int k = readInt();
		if (n == k) {
			for (int i = 1; i <= n; i++)
				ps.print(i + " ");
			ps.println();
		} else if (k < (int) (Math.sqrt(n))) {
			ps.println(-1);
		} else {
			for (int i = 1; i <= (n + k - 1) / k; i++) {
				int start = n - i * k + 1;
				for (int j = Math.max(1, start); j < start + k; j++)
					ps.print(j + " ");
			}
			ps.println();
		}

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