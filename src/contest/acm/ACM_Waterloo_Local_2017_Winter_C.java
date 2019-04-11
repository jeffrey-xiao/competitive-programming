package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Winter_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N;
  static int[] duck;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    while (br.ready()) {
      N = readInt();
      duck = new int[N];
      dp = new int[N][N];

      for (int i = 0; i < N; i++) {
        Arrays.fill(dp[i], -1);
        duck[i] = readInt();
      }

      System.out.println(solve(0, N - 1));
    }

    out.close();
  }

  static int solve(int i, int j) {
    if (i >= j) {
      return 0;
    }
    if (dp[i][j] != -1) {
      return dp[i][j];
    }

    if (duck[i] == duck[j]) {
      return dp[i][j] = Math.max(1 + solve(i + 1, j - 1), Math.max(solve(i + 1, j), solve(i, j - 1)));
    } else {
      return dp[i][j] = Math.max(solve(i + 1, j), solve(i, j - 1));
    }
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
