package contest.hackercup;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FHC_2017_Round_1_Pie_Progress {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int N, M;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      N = readInt();
      M = readInt();

      long[] dp = new long[N + 1];
      Arrays.fill(dp, 1 << 30);
      dp[0] = 0;

      for (int i = 0; i < N; i++) {
        int[] cost = new int[M];
        for (int j = 0; j < M; j++)
          cost[j] = readInt();
        Arrays.sort(cost);
        for (int j = 1; j < M; j++)
          cost[j] += cost[j - 1];
        for (int j = N; j >= 1; j--)
          for (int k = 0; k < M; k++) // buying k + 1 pies
            if (j - (k + 1) >= i)
              dp[j] = Math.min(dp[j], dp[j - (k + 1)] + cost[k] + (k + 1) * (k + 1));
      }
      out.printf("Case #%d: %d\n", t, dp[N]);
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
