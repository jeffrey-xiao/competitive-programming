package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2010_Quality_Of_Living {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    int h = readInt() - 1;
    int w = readInt() - 1;
    int max = 0;
    int[][] grid = new int[r + 1][c + 1];
    for (int x = 1; x <= r; x++) {
      for (int y = 1; y <= c; y++) {
        grid[x][y] = readInt();
        max = Math.max(grid[x][y], max);
      }
    }
    int low = 1;
    int high = max * 2;
    int mid = 0;
    main : while (low != high) {
      mid = (low + high) / 2;
      int[][] newGrid = new int[r + 1][c + 1];
      boolean justRight = false;
      for (int x = 1; x <= r; x++) {
        for (int y = 1; y <= c; y++) {
          if (grid[x][y] == mid)
            newGrid[x][y] = 0;
          else if (grid[x][y] < mid)
            newGrid[x][y] = -1;
          else
            newGrid[x][y] = 1;
          newGrid[x][y] = newGrid[x][y] - newGrid[x - 1][y - 1] + newGrid[x - 1][y] + newGrid[x][y - 1];
          if (x > h && y > w) {
            int sum = newGrid[x][y] + newGrid[x - h - 1][y - w - 1] - newGrid[x][y - w - 1] - newGrid[x - h - 1][y];
            if (sum < 0) {
              high = mid;
              continue main;
            }
            if (sum == 0)
              justRight = true;
          }
        }
      }
      if (justRight) {
        System.out.println(mid);
        return;
      } else {
        low = mid;
      }
    }
    System.out.println(mid);
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
