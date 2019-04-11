package contest.usaco_other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: marathon_silver
 */
import java.util.StringTokenizer;

public class marathon_silver {
  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("marathon.in"));
    // br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new FileWriter("marathon.out")));

    int n = readInt();
    int k = readInt();
    Point[] p = new Point[n];
    for (int x = 0; x < n; x++) {
      p[x] = new Point(readInt(), readInt());
    }
    // dp represents the minimum distance traveled GOING to that checkpoint
    // while skipping x number of checkpoints
    int[][] dp = new int[n][k + 1];
    for (int x = 1; x < n; x++)
      for (int y = 0; y <= k; y++)
        dp[x][y] = Integer.MAX_VALUE;

    for (int x = 1; x < n; x++) {
      for (int y = 0; y <= k; y++) {
        if (y >= x - 1) {
          dp[x][y] = dist(p[x], p[0]);

        } else {
          for (int z = x - y; z <= x; z++) {
            dp[x][y] = Math.min(dp[x][y], dp[z - 1][y - (x - z)] + dist(p[z - 1], p[x]));
          }
        }
      }
    }
    pr.println(dp[n - 1][k]);
    pr.close();
    System.exit(0);
  }

  private static int dist(Point p1, Point p2) {
    return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }

  static class Point {
    int x, y;

    Point(int x, int y) {
      this.x = x;
      this.y = y;
    }
  }
}
