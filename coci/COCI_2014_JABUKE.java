package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_JABUKE {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("test.txt"));
		// ps = new PrintWriter("output.txt");

		int r = readInt();
		int c = readInt();

		int[][][] min = new int[r][c][2]; // row, column, left or right
		for (int i = 0; i < r; i++)
			for (int j = 0; j < c; j++)
				min[i][j][0] = min[i][j][1] = 1 << 30;

		for (int i = 0; i < r; i++) {
			char[] in = next().toCharArray();
			for (int j = 0; j < c; j++) {
				if (in[j] == 'x') {
					for (int k = 0; k < c; k++) {
						if (k == j)
							min[i][k][0] = min[i][k][1] = 0;
						else if (k < j)
							min[i][k][0] = min(min[i][k][0], sqr(k - j));
						else
							min[i][k][1] = min(min[i][k][1], sqr(k - j));
					}
				}
			}
		}
		int q = readInt();
		for (int k = 0; k < q; k++) {
			int x = readInt() - 1;
			int y = readInt() - 1;
			int minDist = 1 << 30;
			for (int i = 0; i < r; i++) {
				minDist = min(minDist,
						sqr(x - i) + min(min[i][y][0], min[i][y][1]));
				// ps.println(min[i][y][0] + " " + min[i][y][1]);
			}
			for (int i = 0; i < c; i++) {
				if (i == y)
					min[x][i][0] = min[x][i][1] = 0;
				else if (i < y)
					min[x][i][0] = min(min[x][i][0], sqr(y - i));
				else
					min[x][i][1] = min(min[x][i][1], sqr(y - i));
			}
			ps.println(minDist);
		}
		ps.close();
	}

	static int sqr (int x) {
		return x * x;
	}

	static int min (int x, int y) {
		return x < y ? x : y;
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