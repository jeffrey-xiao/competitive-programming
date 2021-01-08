package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_3_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      out.printf("Case #%d: ", t);
      solve();
    }

    out.close();
  }

  static void solve() throws IOException {
    String s1 = " " + next();
    String s2 = " " + next();
    int[][] dp = new int[s1.length()][s2.length()];
    Point[][] prev = new Point[s1.length()][s2.length()];
    for (int i = 0; i < s1.length(); i++) {
      dp[i][0] = i;
      prev[i][0] = new Point(i - 1, 0);
    }
    for (int j = 0; j < s2.length(); j++) {
      dp[0][j] = j;
      prev[0][j] = new Point(0, j - 1);
    }
    for (int i = 1; i < s1.length(); i++) {
      for (int j = 1; j < s2.length(); j++) {
        if (s1.charAt(i) == s2.charAt(j)) {
          dp[i][j] = dp[i - 1][j - 1];
          prev[i][j] = new Point(i - 1, j - 1);
        } else {
          dp[i][j] = dp[i - 1][j] + 1;
          prev[i][j] = new Point(i - 1, j);
          if (dp[i][j - 1] + 1 < dp[i][j]) {
            dp[i][j] = dp[i][j - 1] + 1;
            prev[i][j] = new Point(i, j - 1);
          }
          if (dp[i - 1][j - 1] + 1 < dp[i][j]) {
            dp[i][j] = dp[i - 1][j - 1] + 1;
            prev[i][j] = new Point(i - 1, j - 1);
          }
        }
      }
    }
    int r = s1.length() - 1;
    int c = s2.length() - 1;
    int dist = dp[r][c];
    int curr = dist;
    while (r >= 0 && c >= 0 && dp[r][c] * 2 > dist) {
      Point p = prev[r][c];
      r = p.r;
      c = p.c;
    }
    out.println(s1.substring(1, r + 1) + s2.substring(c + 1));
  }

  static class Point {
    int r, c;
    Point(int r, int c) {
      this.r = r;
      this.c = c;
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
