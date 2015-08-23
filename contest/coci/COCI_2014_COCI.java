package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_COCI {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;
	static int[][] g = new int[652][652];

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int n = readInt();

		int[] a = new int[n];
		int[] b = new int[n];
		int[][] p = new int[652][652];
		for (int i = 0; i < n; i++) {
			a[i] = readInt() + 1;
			b[i] = readInt() + 1;
			g[a[i]][b[i]]++;
			p[a[i]][b[i]]++;
		}
		for (int i = 1; i <= 651; i++) {
			for (int j = 1; j <= 651; j++) {
				g[i][j] += g[i - 1][j] + g[i][j - 1] - g[i - 1][j - 1];
			}
		}
		for (int i = 0; i < n; i++) {
			ps.print(query(651, 651, a[i] + 1, b[i] + 1) + 1 + " ");
			int sum = n - query(a[i] - 1, b[i] - 1, 1, 1);
			if (a[i] == 651)
				sum -= p[1][b[i]];
			if (b[i] == 651)
				sum -= p[a[i]][1];
			ps.println(sum);
		}
		ps.close();
	}

	static int query (int x2, int y2, int x1, int y1) {
		x1--;
		y1--;
		return g[x2][y2] - g[x1][y2] - g[x2][y1] + g[x1][y1];
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