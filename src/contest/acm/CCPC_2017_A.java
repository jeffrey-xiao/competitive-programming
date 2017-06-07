package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCPC_2017_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static double[][] dp;
  static double[] p;
  static int N, M, total;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();

    total = N + M;

    p = new double[total - 1];
    dp = new double[N + 1][M + 1];
    for (int i = 0; i < total - 1; i++)
      p[i] = readDouble();

    for (int i = 0; i <= N; i++)
      for (int j = 0; j <= M; j++)
        dp[i][j] = -1;

    out.println(solve(N, M));
    out.close();
  }

  static double solve (int n, int m) {
    if (n == 0)
      return 0.0;
    if (m == 0)
      return 1.0;
    if (dp[n][m] != -1)
      return dp[n][m];
    int i = total - n - m;
    double a = solve(n, m - 1);
    double b = solve(n - 1, m);
    dp[n][m] = p[i] * a + (1 - p[i]) * b;
    return dp[n][m];
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
