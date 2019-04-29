package contest.codejam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class GCJ_2017_Round_1C_C {

  static final double EPS = 1e-15;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int T, N, K;
  static double U;
  static double[] prob;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      K = readInt();
      U = readDouble();

      prob = new double[N];
      for (int i = 0; i < N; i++)
        prob[i] = readDouble();

      Arrays.sort(prob);

      double ans = 0;

      for (int i = 0; i < N; i++) {
        // increment from [i, N - 1]
        double lo = 0;
        double hi = 1;
        while (hi - lo >= EPS) {
          double mid = (lo + hi) / 2.0;
          double total = getUsed(i, mid);
          if (total > U)
            hi = mid;
          else
            lo = mid;
        }

        double[] updatedProb = new double[N];
        for (int j = 0; j < N; j++) {
          if (prob[j] < lo && j >= i)
            updatedProb[j] = lo;
          else
            updatedProb[j] = prob[j];
        }

        double total = getUsed(i, lo);
        if (i > 0)
          updatedProb[i - 1] = Math.min(1, prob[i - 1] + U - total);

        ans = Math.max(ans, compute(updatedProb));
      }

      out.printf("Case #%d: %.20f%n", t, ans);
    }

    out.close();
  }

  static double getUsed(int i, double mid) {
    double total = 0;
    for (int j = i; j < N; j++)
      if (prob[j] < mid)
        total += mid - prob[j];
    return total;
  }

  static double compute(double[] prob) {
    double[] dp = new double[N + 1];
    dp[0] = 1.0;
    for (int i = 0; i < N; i++)
      for (int j = N - 1; j >= 0; j--) {
        dp[j + 1] += dp[j] * prob[i];
        dp[j] *= (1 - prob[i]);
      }
    double ret = 0;
    for (int i = K; i <= N; i++)
      ret += dp[i];
    return ret;
  }

  static String next() throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong() throws IOException {
    return Long.parseLong(next());
  }

  static int readInt() throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble() throws IOException {
    return Double.parseDouble(next());
  }

  static char readCharacter() throws IOException {
    return next().charAt(0);
  }

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
