package contest.codejam;

import java.util.*;
import java.io.*;

public class Round_2_B {

	static BufferedReader br;
	static PrintWriter out;
	static StringTokenizer st;

	static int T, N, K;

	static double[] prob;
	static double ans;

	public static void main (String[] args) throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		out = new PrintWriter(new OutputStreamWriter(System.out));
		br = new BufferedReader(new FileReader("in.txt"));
		out = new PrintWriter(new FileWriter("out.txt"));

		T = readInt();

		for (int t = 1; t <= T; t++) {
			N = readInt();
			K = readInt();

			ans = 0.0;
			prob = new double[N];

			for (int i = 0; i < N; i++)
				prob[i] = readDouble();

			Arrays.sort(prob);

			ArrayList<Double> left = new ArrayList<Double>();
			ArrayList<Double> right = new ArrayList<Double>();

			for (int i = N - K; i < N; i++)
				right.add(prob[i]);

			ans = Math.max(ans, getProb(left, right));

			for (int i = 0; i < K; i++) {
				left.add(prob[i]);
				right.remove(0);

				ans = Math.max(ans, getProb(left, right));
			}
			out.printf("Case #%d: %f\n", t, ans);
		}

		out.close();
	}

	static double getProb (ArrayList<Double> left, ArrayList<Double> right) {
		ArrayList<Double> p = new ArrayList<Double>();
		p.addAll(left);
		p.addAll(right);

		assert p.size() == K;

		double[][] dp = new double[p.size() + 1][p.size() + 1];
		dp[0][0] = 1;

		for (int i = 0; i < p.size(); i++) {
			for (int j = 0; j < p.size(); j++) {
				dp[i + 1][j] += dp[i][j] * (1 - p.get(i));
				dp[i + 1][j + 1] += dp[i][j] * (p.get(i));
			}
		}
		return dp[p.size()][p.size() / 2];
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
