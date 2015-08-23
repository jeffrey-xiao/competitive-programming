package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_MUZICARI {

	static BufferedReader br = new BufferedReader(new InputStreamReader(
			System.in));
	static PrintWriter ps = new PrintWriter(new BufferedWriter(
			new OutputStreamWriter(System.out)));
	static StringTokenizer st;

	public static void main (String[] args) throws IOException {
		int t = readInt();
		int n = readInt();
		int[] m = new int[n + 1];
		int[] time = new int[n + 1];
		for (int i = 1; i <= n; i++) {
			m[i] = readInt();
			time[i] = -1;
		}

		boolean[][] dp = new boolean[n + 1][t + 1];
		int[][] index = new int[n + 1][t + 1];
		for (int i = 0; i <= n; i++)
			for (int j = 0; j <= t; j++)
				index[i][j] = -1;
		dp[0][0] = true;
		for (int i = 1; i <= n; i++) {
			for (int j = 0; j <= t; j++) {
				dp[i][j] = dp[i - 1][j];
				index[i][j] = index[i - 1][j];
				if (j - m[i] >= 0 && dp[i - 1][j - m[i]] && !dp[i][j]) {
					dp[i][j] = true;
					index[i][j] = i;
				}
				// System.out.print((dp[i][j] ? 1 : 0) + " ");
			}
			// System.out.println();
		}
		int curr = 0;
		for (int i = t; i > 0; i--) {
			if (dp[n][i]) {
				curr = i;
				break;
			}
		}
		while (curr != 0) {
			// System.out.println(curr + " " + index[n][curr]);
			time[index[n][curr]] = curr - m[index[n][curr]];
			curr -= m[index[n][curr]];
		}
		curr = 0;
		for (int i = 1; i <= n; i++) {
			// System.out.print(time[i] + " ");
			if (time[i] == -1) {
				time[i] = curr;
				curr += m[i];
			}
			System.out.print(time[i] + " ");
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

	static char readCharacter () throws IOException {
		return next().charAt(0);
	}

	static String readLine () throws IOException {
		return br.readLine().trim();
	}
}
