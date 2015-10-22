package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class The_Cake_Is_A_Dessert {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int r = readInt();
		int c = readInt();
		int k = readInt();
		long[][] diff = new long[r + 2][c + 2];
		long[][] sum = new long[r + 2][c + 2];
		for (int x = 0; x < k; x++) {
			int x1 = readInt();
			int y1 = readInt();
			int x2 = readInt() + 1;
			int y2 = readInt() + 1;
			diff[x1][y1]++;
			diff[x1][y2]--;
			diff[x2][y1]--;
			diff[x2][y2]++;
		}
		for (int x = 1; x <= r; x++) {
			for (int y = 1; y <= c; y++) {
				diff[x][y] += diff[x - 1][y] + diff[x][y - 1] - diff[x - 1][y - 1];
				sum[x][y] = diff[x][y] + sum[x - 1][y] + sum[x][y - 1] - sum[x - 1][y - 1];
				System.out.print(diff[x][y] + " ");
			}
			System.out.println();
		}
		int q = readInt();
		for (int x = 0; x < q; x++) {
			int x1 = readInt() - 1;
			int y1 = readInt() - 1;
			int x2 = readInt();
			int y2 = readInt();
			long s = sum[x2][y2] + sum[x1][y1] - sum[x2][y1] - sum[x1][y2];
			System.out.println(s);
		}
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
