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

public class FHC_2016_Qualification_Text_Editor {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, K;
  static int[][] dp;
  static int[] lcp;
  static String[] words;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("in.txt"));
    out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      N = readInt();
      K = readInt();

      dp = new int[N][K + 1];
      lcp = new int[N];
      words = new String[N];

      for (int i = 0; i < N; i++)
        words[i] = next();

      Arrays.sort(words);

      for (int i = 1; i < N; i++)
        while (lcp[i] < words[i - 1].length() && lcp[i] < words[i].length() && words[i].charAt(lcp[i]) == words[i - 1].charAt(lcp[i]))
          lcp[i]++;

      for (int i = 0; i < N; i++)
        for (int j = 0; j <= K; j++)
          dp[i][j] = 1 << 30;

      for (int k = 1; k <= K; k++) {
        for (int n = 0; n < N; n++) {
          if (k == 1) {
            dp[n][k] = words[n].length() + 1;
          } else {
            int curr = 1 << 30;
            for (int m = n + 1; m < N; m++) {
              curr = Math.min(curr, lcp[m]);
              dp[m][k] = Math.min(dp[m][k], dp[n][k - 1] + words[n].length() + words[m].length() - 2 * curr + 1);
            }
          }
        }
      }

      int ans = 1 << 30;
      for (int n = 0; n < N; n++)
        ans = Math.min(ans, dp[n][K] + words[n].length());

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
