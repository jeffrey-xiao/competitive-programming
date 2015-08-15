package coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_ZMIJA {

	static BufferedReader br;
	static PrintWriter ps;
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		// br = new BufferedReader(new InputStreamReader(System.in));
		ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
				System.out)));
		br = new BufferedReader(new FileReader("in.txt"));
		// ps = new PrintWriter("out.txt");

		int r = readInt();
		int c = readInt();
		char[][] g = new char[r][];
		for (int i = 0; i < r; i++) {
			g[i] = next().toCharArray();
		}
		int[] max = new int[r];
		int[] min = new int[r];
		for (int i = r - 1; i >= 0; i--) {
			max[i] = -1;
			min[i] = c;
			for (int j = 0; j < c; j++)
				if (g[i][j] == 'J') {
					max[i] = Math.max(max[i], j);
					min[i] = Math.min(min[i], j);
				}
		}
		int ans = 0;
		int pos = 0;
		int npos = 0;
		int start = 0;
		main : for (; start < r; start++) {
			for (int j = 0; j < c; j++)
				if (g[start][j] == 'J')
					break main;
		}
		for (int i = r - 1; i >= start; i--) {
			if ((r - 1 - i) % 2 == 0) {
				if (i == 0) {
					npos = max[i];
				} else {
					npos = Math.max(max[i], max[i - 1]);
				}
			} else {
				if (i == 0) {
					npos = min[i];
				} else {
					npos = Math.min(min[i], min[i - 1]);
				}
			}
			if (npos == -1 || npos == c)
				npos = pos;
			ans += Math.abs(pos - npos) + 1;
			pos = npos;
		}
		ps.println(ans - 1);
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