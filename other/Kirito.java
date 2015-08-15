package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Kirito {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	// rows and columns
	static int[][] tree = new int[2001][5001];

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		for (int x = 1; x <= r; x++) {
			String in = next();
			for (int y = 1; y <= c; y++) {
				update(x, y, in.charAt(y - 1) - '0');
			}
		}
		int q = readInt();
		for (int k = 0; k < q; k++) {
			int x1 = readInt();
			int x2 = readInt();
			int y = 1;
			for (; y <= c; y++) {
				int sum = freq(x2, y) - freq(x1 - 1, y);
				if (sum != 0 && sum != x2 - x1 + 1) {
					break;
				}
			}
			ps.println((x2 - x1 + 1) * (y - 1));
			for (; y <= c; y++) {
				int s1 = freqAt(x1, y);
				int s2 = freqAt(x2, y);
				update(x1, y, s2 - s1);
				update(x2, y, s1 - s2);
			}
			// for(int x = 1; x <= r; x++){
			// for(int y1 = 1; y1 <= c; y1++)
			// System.out.print(freqAt(x, y1));
			// System.out.println();
			// }
			// System.out.println();
		}
		ps.close();
	}

	static void update (int x, int y, int v) {
		for (; x <= 2000; x += (x & -x)) {
			tree[x][y] += v;
		}
	}

	static int freq (int x, int y) {
		int sum = 0;
		for (; x > 0; x -= (x & -x)) {
			sum += tree[x][y];
		}
		return sum;
	}

	static int freqAt (int x, int y) {
		return freq(x, y) - freq(x - 1, y);
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
