package contest.hackercup;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FHC_2016_Round_2_Carnival_Coins {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    for (int t = 1; t <= T; t++) {
      int n = readInt();
      int k = readInt();
      double p = readDouble();

      double[][] dp = new double[n + 1][n + 1];
      double[] best = new double[n + 1];

      dp[0][0] = 1.0;
      for (int i = 1; i <= n; i++) {
        for (int j = 0; j <= i; j++) {
          if (j - 1 >= 0)
            dp[i][j] += dp[i - 1][j - 1] * p;
          dp[i][j] += dp[i - 1][j] * (1 - p);
        }
      }

      for (int i = k; i <= n; i++) {
        double curr = 0;
        for (int j = k; j <= i; j++)
          curr += dp[i][j];
        best[i] = curr;
        for (int j = 1; j <= i - 1; j++)
          best[i] = Math.max(best[i], best[j] + best[i - j]);
      }

      out.printf("Case #%d: %.15f\n", t, best[n]);
    }

    out.close();
  }

  static double choose(int n, int k) {
    double res = 1d;
    for (int i = k + 1; i <= n; i++)
      res *= i;
    for (int i = 1; i <= n - k; i++)
      res /= i;
    return res;
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
