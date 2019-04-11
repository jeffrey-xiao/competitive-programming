package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2015_J5 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int n, m; // n represents the number of pies, m represents the number of people
  static int[][][] dp = new int[251][251][251]; // memoization array: dp[i][j][k] where i is the number of pies left, j is the number of people left, and k is the previous pie given

  public static void main(String[] args) throws IOException {
    n = readInt();
    m = readInt();
    for (int i = 0; i <= 250; i++)
      for (int j = 0; j <= 250; j++)
        for (int k = 0; k <= 250; k++)
          dp[i][j][k] = -1;
    out.println(compute(n, m, 1));
    out.close();
  }

  private static int compute(int i, int j, int prev) {
    // if the previous value has been computed, we return the "cached" value
    if (dp[i][j][prev] != -1)
      return dp[i][j][prev];
    // if we are out of people to give pies to and we are out of pies to give, then it is one way we can allocate pies
    if (i == 0 && j == 0)
      return dp[i][j][prev] = 1;
    // if we are out of people to give pies, but we have more pies to give or
    //    we are out of pies to give, but we have more people to give to
    // then we return 0, because it is not a valid allocation
    if (i == 0 || j == 0)
      return dp[i][j][prev] = 0;
    // computing the value of dp[i][j][prev] using the recurrence
    dp[i][j][prev] = 0;
    for (int next = prev; next <= i; next++)
      dp[i][j][prev] += compute(i - next, j - 1, next);
    return dp[i][j][prev];
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
