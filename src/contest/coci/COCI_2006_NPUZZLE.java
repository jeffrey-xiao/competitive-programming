package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class COCI_2006_NPUZZLE {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static char[][] g;

	public static void main (String[] args) throws IOException {
		g = new char[4][];
		for (int x = 0; x < 4; x++)
			g[x] = next().toCharArray();
		System.out.println(scatter());
	}

	public static int scatter () {
		int count = 0;
		for (int x = 0; x < 4; x++)
			for (int y = 0; y < 4; y++) {
				int value = g[x][y] - 'A';
				if (g[x][y] == '.')
					continue;
				int gx = (value) / 4;
				int gy = (value) % 4;
				count += (Math.abs(x - gx) + Math.abs(y - gy));
			}
		return count;
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

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
