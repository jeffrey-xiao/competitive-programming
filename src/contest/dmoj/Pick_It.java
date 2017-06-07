package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pick_It {

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer st;

  private static int[][] dp = new int[201][201];
  private static int[] a;

  public static void main (String[] args) throws IOException {

    int n = 0;
    while ((n = readInt()) != 0) {
      a = new int[n];
      for (int i = 0; i < n; i++) {
        a[i] = readInt();
      }
      for (int i = 0; i <= 200; i++)
        for (int j = 0; j <= 200; j++)
          dp[i][j] = -1;
      System.out.println(solve(0, n - 1));
    }
  }

  private static int solve (int l, int r) {
    if (l == r)
      return 0;
    if (dp[l][r] != -1)
      return dp[l][r];
    int res = 0;
    for (int m = l + 1; m < r; m++) {
      res = Math.max(res, solve(l, m) + solve(m, r) + a[l] + a[m] + a[r]);
    }
    return dp[l][r] = res;
  }

  private static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  private static int readInt () throws IOException {
    return Integer.parseInt(next());
  }
}