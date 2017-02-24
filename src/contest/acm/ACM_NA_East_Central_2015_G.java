package contest.acm;

import java.util.*;
import java.io.*;

public class ACM_NA_East_Central_2015_G {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int N = 3;
	static int M, K;
	static boolean[][] taken;
	static long[][] dp;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		//br = new BufferedReader(new FileReader("in.txt"));
		//out = new PrintWriter(new FileWriter("out.txt"));

		M = readInt();
		K = readInt();

		taken = new boolean[M][N];
		dp = new long[M][8];

		for (int i = 0; i < K; i++) {
			int a = (int)readDouble();
			int b = (int)readDouble();
			taken[a][b] = true;
		}

		for (int i = 0; i < M; i++) {
			if (i == 0) {
				for (int j = 0; j < 8; j++) {
					int[] p = getPoss(j);
					boolean valid = true;
					for (int k = 0; k < N;) {
						if (p[k] == 0)
							k++;
						else if (p[k] == 1 && (k == N - 1 || p[k + 1] == 0)) {
							valid = false;
							break;
						} else {
							k += 2;
						}
					}
					if (!checkValid(p, i) || !valid)
						continue;

					dp[i][j] = 1;
				}
			} else {
				for (int j = 0; j < 8; j++) {
					int[] curr = getPoss(j);
					if (!checkValid(curr, i))
						continue;

					for (int k = 0; k < 8; k++) {
						int[] prev = getPoss(k);
						if (!checkValid(prev, i - 1))
							continue;

						long[] currdp = new long[4];
						currdp[0] = 1;
						for (int l = 0; l < 3; l++) {
							if (curr[l] == 0 || taken[i][l]) {
								currdp[l + 1] = currdp[l];
							} else {
								if (prev[l] == 0)
									currdp[l + 1] += currdp[l];
								if (l > 0 && curr[l - 1] == 1 && !taken[i][l - 1])
									currdp[l + 1] += currdp[l - 1];
							}
						}

						dp[i][j] += currdp[3] * dp[i - 1][k];
					}
				}
			}
		}

		long ans = 0;
		for (int i = 0; i < 8; i++) {
			ans += dp[M - 1][i];
		}

		out.println(ans);
		out.close();
	}

	static boolean checkValid (int[] p, int r) {
		for (int i = 0; i < N; i++) {
			if (p[i] == 1 && taken[r][i])
				return false;
			else if (taken[r][i])
				p[i] = 1;
		}
		return true;
	}

	static int[] getPoss (int i) {
		int[] a = new int[3];
		a[0] = i % 2;
		i /= 2;
		a[1] = i % 2;
		i /= 2;
		a[2] = i % 2;
		i /= 2;

		return a;
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
