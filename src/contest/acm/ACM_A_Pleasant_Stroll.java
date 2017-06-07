package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ACM_A_Pleasant_Stroll {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static int[] movey = {-1, 0, 1};
  static int[][][] dp;
  static char[][] grid;
  static int r, c;

  public static void main (String[] args) throws IOException {
    for (int t = readInt(); t > 0; t--) {
      r = readInt();
      c = readInt();
      grid = new char[r][c];
      dp = new int[r][c][c];
      for (int x = 0; x < r; x++)
        for (int y = 0; y < c; y++)
          for (int z = 0; z < c; z++)
            dp[x][y][z] = -1;
      for (int x = 0; x < r; x++)
        grid[x] = next().toCharArray();
      int max = Integer.MIN_VALUE;
      for (int x = 0; x < c; x++) {
        if (grid[0][x] == '#')
          continue;
        for (int y = x + 1; y < c; y++) {
          if (grid[0][y] == '#')
            continue;
          max = Math.max(max, compute(0, x, y));
        }
      }
      System.out.println(max < 0 ? "Detour" : max);
    }
  }

  static int compute (int x, int y1, int y2) {
    if (dp[x][y1][y2] != -1)
      return dp[x][y1][y2];
    int total = 0;
    if (Math.abs(y1 - y2) == 1)
      total++;
    if (x == r - 1) {
      dp[x][y1][y2] = total;
      return total;
    }
    int nx = x + 1;
    int next = -10000000;
    for (int z1 = 0; z1 < 3; z1++) {
      for (int z2 = 0; z2 < 3; z2++) {
        int ny1 = y1 + movey[z1];
        int ny2 = y2 + movey[z2];
        if (ny1 == ny2 || ny1 < 0 || ny2 < 0 || ny1 >= c || ny2 >= c || grid[nx][ny1] == '#' || grid[nx][ny2] == '#')
          continue;
        next = Math.max(next, compute(nx, ny1, ny2));
      }
    }
    total += next;
    dp[x][y1][y2] = total;
    dp[x][y2][y1] = total;
    return total;
  }

  static class State {
    int x, y1, y2;

    State (int x, int y1, int y2) {
      this.x = x;
      this.y1 = y1;
      this.y2 = y2;
    }
  }

  static String next () throws IOException {
    while (st == null || !st.hasMoreTokens())
      st = new StringTokenizer(br.readLine().trim());
    return st.nextToken();
  }

  static long readLong () throws IOException {
    return Long.parseLong(next());
  }

  static int readInt () throws IOException {
    return Integer.parseInt(next());
  }

  static double readDouble () throws IOException {
    return Double.parseDouble(next());
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
