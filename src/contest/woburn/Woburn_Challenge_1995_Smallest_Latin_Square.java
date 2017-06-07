package contest.woburn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Woburn_Challenge_1995_Smallest_Latin_Square {
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    for (int i = readInt(); i > 0; i--) {
      int n = readInt();
      int[][] grid = new int[n][n];
      fillGrid(grid, 0, 0, n);
      for (int x = 0; x < n; x++) {
        for (int y = 0; y < n; y++)
          System.out.print(grid[x][y] + " ");
        System.out.println();
      }
    }
  }

  public static boolean fillGrid (int[][] grid, int x, int y, int n) {
    if (x == n)
      return true;
    for (int z = 1; z <= n; z++) {
      if (isDup(grid, x, y, z)) {
        continue;
      }
      grid[x][y] = z;
      int[][] copy = Arrays.copyOf(grid, grid.length);
      if (y < grid.length - 1 && fillGrid(copy, x, y + 1, n)) {
        return true;
      } else if (y == grid.length - 1 && fillGrid(copy, x + 1, 0, n)) {
        return true;
      }
      grid[x][y] = 0;
    }

    return false;
  }

  public static boolean isDup (int[][] grid, int x, int y, int n) {
    for (int z = 0; z < grid.length; z++) {
      if (grid[z][y] == n || grid[x][z] == n)
        return true;
    }
    return false;
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
