package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACM_Mount_Allison_2017_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int K, T, P, Q;
  static int[] X;
  static double[] prob;
  static double[][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    K = readInt();
    T = readInt();
    P = readInt();
    Q = readInt();

    X = new int[T];
    prob = new double[T];
    dp = new double[T + 1][T + 1];
    X[0] = readInt();

    for (int i = 1; i < T; i++)
      X[i] = (X[i - 1] * P) % Q;
    for (int i = 0; i < T; i++)
      X[i] %= 4;

    for (int i = 0; i < T; i++) {
      double a = readInt(), b = readInt(), c = readInt(), d = readInt();
      if (X[i] == 0)
        prob[i] = a / (a + b) * (c / (c + d) * a / (a + c) + d / (c + d) * a / (a + d));
      if (X[i] == 1)
        prob[i] = b / (a + b) * (c / (c + d) * b / (b + c) + d / (c + d) * b / (b + d));
      if (X[i] == 2)
        prob[i] = c / (c + d) * (a / (a + b) * c / (c + a) + b / (a + b) * c / (c + b));
      if (X[i] == 3)
        prob[i] = d / (c + d) * (a / (a + b) * d / (d + a) + b / (a + b) * d / (d + b));
    }

    // after b rounds, a won
    dp[0][0] = 1.0;
    for (int i = 0; i < T; i++) {
      for (int j = 0; j < T; j++) {
        dp[i + 1][j] += dp[i][j] * (1 - prob[i]);
        dp[i + 1][j + 1] += dp[i][j] * prob[i];
      }
    }

    double ans = 0;
    for (int i = K; i <= T; i++)
      ans += dp[T][i];

    out.println(ans);
    out.close();
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
