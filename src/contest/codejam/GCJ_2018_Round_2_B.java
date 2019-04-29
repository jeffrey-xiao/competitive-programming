package codejam;

import java.io.*;
import java.util.StringTokenizer;

public class GCJ_2018_Round_2_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int N;
  static int R, B;
  static int[][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    dp = new int[50][501][501];

    for (int t = 1; t <= T; t++) {
      R = readInt();
      B = readInt();

      out.printf("Case #%d: %d%n", t, solve(0, R, B) - 1);
    }

    out.close();
  }

  static int solve(int j, int r, int b) {
    if (dp[j][r][b] != 0) {
      return dp[j][r][b];
    }

    int currR = r;
    int currB = b;
    int ret = 0;
    int index = 1;

    for (int i = 0; currB >= i && currR >= j; i++) {
      currR -= j;
      currB -= i;
      ret = Math.max(ret, index + solve(j + 1, currR, currB));
      if (i == 0) {
        if (currR >= j + 1) {
          ret = Math.max(ret, index + solve(j + 1, currR, b));
        }
      }
      index++;
    }

    return dp[j][r][b] = ret;
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
