package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Valentines_P2 {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    int k = readInt();
    Cage[] c = new Cage[n + 1];
    for (int x = 1; x <= n; x++)
      c[x] = new Cage(readInt(), readInt());
    int[][] dp = new int[n + 1][k + 1];
    for (int x = 0; x <= n; x++) {
      for (int y = 1; y <= k; y++) {
        dp[x][y] = -1;
      }
    }
    for (int x = 1; x <= n; x++) {
      for (int y = k; y >= 0; y--) {
        dp[x][y] = Math.max(dp[x][y], dp[x - 1][y]);
        if (y - c[x].t >= 0 && dp[x - 1][y - c[x].t] != -1) {
          dp[x][y] = Math.max(dp[x][y], dp[x - 1][y - c[x].t] + c[x].p);
        }
      }
    }
    int max = 0;
    for (int y = 0; y <= k; y++) {
      max = Math.max(max, dp[n][y]);
    }
    System.out.println(max);
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

  static class Cage {
    int p, t;

    Cage(int p, int t) {
      this.p = p;
      this.t = t;
    }
  }
}
