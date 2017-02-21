package contest.hackercup;

import java.util.*;
import java.io.*;

public class FHC_2017_Qualification_Fighting_The_Zombie {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, H, S;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			H = readInt();
			S = readInt();
			double ans = 0;
			for (int i = 0; i < S; i++) {
				String[] in = next().split("[d]");
				int offset = 0;
				if (in[1].indexOf('-') != -1) {
					String[] res = in[1].split("-");
					in[1] = res[0];
					offset = -Integer.parseInt(res[1]);
				} else if (in[1].indexOf('+') != -1) {
					String[] res = in[1].split("\\+");
					in[1] = res[0];
					offset = Integer.parseInt(res[1]);
				}
				ans = Math.max(ans, compute(Integer.parseInt(in[0]), Integer.parseInt(in[1]), offset));
			}
			out.printf("Case #%d: %.10f\n", t, ans);
		}

		out.close();
	}

	static double compute (int rolls, int damage, int offset) {
		if (H - offset <= 0)
			return 1.0;
		if (H - offset > rolls * damage)
			return 0.0;
		double[][] dp = new double[2][H - offset + 1];
		dp[0][0] = 1.0;
		for (int i = 1; i <= rolls; i++) {
			Arrays.fill(dp[i % 2], 0);
			for (int j = 0; j <= H - offset; j++) {
				for (int k = 1; k <= damage; k++) {
					dp[i % 2][Math.min(H - offset, j + k)] += dp[(i - 1) % 2][j] * 1.0 / damage;
				}
			}
		}
		return dp[rolls % 2][H - offset];
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
