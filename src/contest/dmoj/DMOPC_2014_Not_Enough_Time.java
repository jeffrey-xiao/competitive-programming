package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class DMOPC_2014_Not_Enough_Time {

  private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  private static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int n = readInt();
    int t = readInt();
    int[][] p = new int[n][3];
    int[][] v = new int[n][3];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < 3; j++) {
        p[i][j] = readInt();
        v[i][j] = readInt();
      }
    }
    int[] dp = new int[t + 1];
    for (int i = 0; i <= t; i++)
      dp[i] = -1;
    dp[0] = 0;
    for (int i = 0; i < n; i++) {
      for (int j = t; j >= 0; j--) {
        for (int k = 0; k < 3; k++) {
          if (j + p[i][k] <= t && dp[j] >= 0) {
            dp[j + p[i][k]] = Math.max(dp[j + p[i][k]], dp[j] + v[i][k]);
          }
        }

      }
    }
    int max = 0;
    for (int i = 0; i <= t; i++)
      max = Math.max(max, dp[i]);
    System.out.println(max);
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