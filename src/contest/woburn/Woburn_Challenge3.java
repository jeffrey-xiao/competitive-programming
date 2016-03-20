package contest.woburn;

import java.util.*;
import java.io.*;

public class Woburn_Challenge3 {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N, M;
	static int[][] a;
	static int[][] up;
	static int[][] left;
	static int[][] best;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		N = readInt();
		M = readInt();

		a = new int[N + 1][M + 1];
		up = new int[N + 1][M + 1];
		left = new int[N + 1][M + 1];
		best = new int[N + 1][M + 1];

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++) {
				a[i][j] = readInt();
				up[i][j] = a[i][j] + up[i - 1][j];
				left[i][j] = a[i][j] + left[i][j - 1];
			}
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= M; j++) {
				best[i][j] = up[i][j] + left[i][j] - a[i][j];
				best[i][j] = Math.max(best[i][j], Math.max(best[i - 1][j], best[i][j - 1]));
			}
		}
		int ans = 0;
		// side by side
		for (int i = 1; i <= N; i++) {
			int currBestLast = 0;
			int currBestLeft = 0;
			for (int j = 1; j <= M; j++) {
				int currSum = up[i][j];
				if (currBestLast + currBestLeft < best[N][j - 1]) {
					currBestLast = best[N][j - 1];
					currBestLeft = 0;
				}
				ans = Math.max(ans, currSum + currBestLast + currBestLeft);
				currBestLeft += a[i][j];
			}
		}
		//up and down
		for (int j = 1; j <= M; j++) {
			int currBestLast = 0;
			int currBestUp = 0;
			for (int i = 1; i <= N; i++) {
				int currSum = left[i][j];
				if (currBestLast + currBestUp < best[i - 1][M]) {
					currBestLast = best[i - 1][M];
					currBestUp = 0;
				}
				ans = Math.max(ans, currSum + currBestLast + currBestUp);
				currBestUp += a[i][j];
			}
		}

		for (int i = 1; i <= N; i++)
			for (int j = 1; j <= M; j++) {
				ans = Math.max(ans, up[i][j] + left[i][j] - a[i][j] + best[i - 1][j - 1]);
			}
		out.println(ans);
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
