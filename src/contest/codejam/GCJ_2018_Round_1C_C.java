package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1C_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N;
  static long[] weights, dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      weights = new long[N];
      dp = new long[N + 1];
      Arrays.fill(dp, Long.MAX_VALUE);
      dp[0] = 0;

      int ans = 0;
      for (int i = 0; i < N; i++) {
        weights[i] = readInt();
        for (int j = ans; j >= 0; j--) {
          if (dp[j] <= weights[i] * 6) {
            dp[j + 1] = Math.min(dp[j + 1], dp[j] + weights[i]);
            ans = Math.max(ans, j + 1);
          }
        }
      }

      out.printf("Case #%d: %d%n", t, ans);
    }

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
