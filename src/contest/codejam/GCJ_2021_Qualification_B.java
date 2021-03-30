package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2021_Qualification_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static final int MAX_ANS = 100 * 1000;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();
    for (int t = 1; t <= T; t++) {
      int X = readInt();
      int Y = readInt();
      char[] mural = (" " + next()).toCharArray();
      int[][] dp = new int[mural.length][2];

      for (int i = 1; i < mural.length; i++) {
        if (mural[i] == 'C' || mural[i] == '?') {
          dp[i][0] = Math.min(dp[i - 1][0], dp[i - 1][1] + (i != 1 ? Y : 0));
        } else {
          dp[i][0] = MAX_ANS;
        }

        if (mural[i] == 'J' || mural[i] == '?') {
          dp[i][1] = Math.min(dp[i - 1][1], dp[i - 1][0] + (i != 1 ? X : 0));
        } else {
          dp[i][1] = MAX_ANS;
        }
      }

      out.printf("Case #%d: %d%n", t, Math.min(dp[mural.length - 1][0], dp[mural.length - 1][1]));
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
