package codejam;

import java.io.*;
import java.util.StringTokenizer;

public class GCJ_2018_Round_2_D {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static int R, C;
  static int[][] grid;
  static boolean[][] vis;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {

      R = readInt();
      C = readInt();

      grid = new int[R][C];

      for (int i = 0; i < R; i++) {
        String in = readLine();
        for (int j = 0; j < C; j++) {
          grid[i][j] = in.charAt(j) == 'B' ? 0 : 1;
        }
      }

      int ans = 0;

      for (int pattern = 0; pattern < (1 << 4); pattern++) {
        boolean valid = false;
        int currAns = 0;
        for (int i = -1; i < R; i++) {
          for (int j = -1; j < C; j++) {
            int curr = 0;
            vis = new boolean[R][C];

            for (int k = 0; k < R; k++) {
              for (int l = 0; l < C; l++) {
                curr = Math.max(curr, search(k, l, i, j, pattern));
              }
            }

            currAns = Math.max(currAns, curr);
          }
        }

        for (int i = 0; i < R; i++) {
          for (int j = 0; j < C; j++) {
            int lu = (pattern >> 0) & 1;
            int ru = (pattern >> 1) & 1;
            int lb = (pattern >> 2) & 1;
            int rb = (pattern >> 3) & 1;

            boolean currValid = true;
            currValid &= grid[i][j] == lu;
            if (j + 1 < C)
              currValid &= grid[i][j + 1] == ru;
            else if (j != 0)
              currValid = false;
            if (i + 1 < R)
              currValid &= grid[i + 1][j] == lb;
            else if (i != 0)
              currValid = false;
            if (j + 1 < C && i + 1 < R)
              currValid &= grid[i + 1][j + 1] == rb;
            else if (j != 0 || i != 0)
              currValid = false;
            if (currValid)
              valid = true;
          }
        }

        if (valid) {
          ans = Math.max(ans, currAns);
        }
      }

      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static int search(int i, int j, int r, int c, int pattern) {
    if (i < 0 || i >= R || j < 0 || j >= C || vis[i][j]) {
      return 0;
    }
    if (i <= r && j <= c && grid[i][j] != ((pattern >> 0) & 1)) {
      return 0;
    }
    if (i <= r && j > c && grid[i][j] != ((pattern >> 1) & 1)) {
      return 0;
    }
    if (i > r && j <= c && grid[i][j] != ((pattern >> 2) & 1)) {
      return 0;
    }
    if (i > r && j > c && grid[i][j] != ((pattern >> 3) & 1)) {
      return 0;
    }
    vis[i][j] = true;
    return 1 + search(i + 1, j, r, c, pattern) + search(i - 1, j, r, c, pattern) + search(i, j + 1, r, c, pattern) + search(i, j - 1, r, c, pattern);
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

  static class Point {
    int r, c;

    Point(int r, int c) {
      this.r = r;
      this.c = c;
    }
  }
}
