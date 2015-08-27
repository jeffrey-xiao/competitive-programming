package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class CCC_2005_S3 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int[][] a = new int[1024][1024];
		int[][] b = new int[1024][1024];
		int[][] result = new int[1024][1024];
		int bx = 1;
		int by = 1;
		int ax = 0;
		int ay = 0;
		b[0][0] = 1;
		int n = readInt();
		for (int z = 0; z < n; z++) {
			ax = readInt();
			ay = readInt();
			for (int x = 0; x < ax; x++)
				for (int y = 0; y < ay; y++)
					a[x][y] = readInt();

			for (int x = 0; x < ax * bx; x++)
				for (int y = 0; y < ay * by; y++)
					result[x][y] = a[x / bx][y / by] * b[x % bx][y % by];
			bx *= ax;
			by *= ay;
			for (int x = 0; x < bx; x++) {
				for (int y = 0; y < by; y++) {
					b[x][y] = result[x][y];
					// System.out.print(b[x][y] + " ");
				}
				// System.out.println();
			}
			// System.out.println("NEXT");
		}
		int maxe = Integer.MIN_VALUE;
		int mine = Integer.MAX_VALUE;
		int maxr = Integer.MIN_VALUE;
		int minr = Integer.MAX_VALUE;
		int maxc = Integer.MIN_VALUE;
		int minc = Integer.MAX_VALUE;
		for (int x = 0; x < bx; x++) {
			int sum = 0;
			for (int y = 0; y < by; y++) {
				sum += b[x][y];
				maxe = Math.max(maxe, b[x][y]);
				mine = Math.min(mine, b[x][y]);
			}
			maxr = Math.max(maxr, sum);
			minr = Math.min(minr, sum);
		}
		for (int y = 0; y < by; y++) {
			int sum = 0;
			for (int x = 0; x < bx; x++) {
				sum += b[x][y];
			}
			maxc = Math.max(maxc, sum);
			minc = Math.min(minc, sum);
		}
		System.out.printf("%d\n%d\n%d\n%d\n%d\n%d", maxe, mine, maxr, minr, maxc, minc);
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
