package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2007_JEDNAKOST_2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static String a;
  static int[][] dp;
  static int n, target;

  public static void main(String[] args) throws IOException {
    String[] input = next().split("=");
    a = input[0];
    target = Integer.parseInt(input[1]);
    n = a.length();
    dp = new int[n + 1][target + 1];
    for (int i = 0; i < n; i++)
      for (int j = 0; j <= target; j++)
        dp[i][j] = -1;
    print(0, target);
  }

  private static void print(int i, int t) {
    if (i == n) {
      System.out.println("=" + target);
      return;
    }
    if (i != 0)
      System.out.print("+");
    int next = 0;
    for (int j = i; j < n; j++) {
      System.out.print(a.charAt(j));
      next = next * 10 + a.charAt(j) - '0';
      if (solve(i, t) == 1 + solve(j + 1, t - next)) {
        print(j + 1, t - next);
        return;
      }
    }
  }

  private static int solve(int i, int t) {
    if (i == n)
      return dp[i][t] = (t == 0 ? 0 : 1 << 30);
    if (dp[i][t] != -1)
      return dp[i][t];
    int res = 1 << 30;
    int next = 0;
    for (int j = i; j < n; j++) {
      next = next * 10 + a.charAt(j) - '0';
      if (next > t)
        break;
      res = Math.min(res, solve(j + 1, t - next) + 1);
    }
    return dp[i][t] = res;
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
