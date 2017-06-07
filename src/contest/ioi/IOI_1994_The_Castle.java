package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_1994_The_Castle {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    int c = readInt();
    int r = readInt();
    int[][] grid = new int[r][c];
    boolean[][] visited = new boolean[r][c];
    ArrayList<Integer> sizes = new ArrayList<Integer>();
    int counter = -1;
    int max = 0;
    int maxCombo = 0;
    int x1 = 0;
    int y1 = 0;
    char dir = ' ';
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        grid[x][y] = readInt();
      }
    }
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        if (grid[x][y] > -1) {
          int size = fillGrid(x, y, grid, counter, visited);
          sizes.add(size);
          max = Math.max(size, max);
          counter--;
        }
      }
    }
    counter = -counter - 1;
    for (int y = 0; y < c; y++) {
      for (int x = r - 1; x >= 0; x--) {
        if (x - 1 >= 0 && grid[x][y] != grid[x - 1][y]) {
          if (sizes.get(-grid[x][y] - 1) + sizes.get(-grid[x - 1][y] - 1) > maxCombo) {
            maxCombo = sizes.get(-grid[x][y] - 1) + sizes.get(-grid[x - 1][y] - 1);
            x1 = x + 1;
            y1 = y + 1;
            dir = 'N';
          }
        }
        if (y + 1 < c && grid[x][y] != grid[x][y + 1]) {
          if (sizes.get(-grid[x][y] - 1) + sizes.get(-grid[x][y + 1] - 1) > maxCombo) {
            maxCombo = sizes.get(-grid[x][y] - 1) + sizes.get(-grid[x][y + 1] - 1);
            x1 = x + 1;
            y1 = y + 1;
            dir = 'E';
          }
        }
      }
    }
    System.out.printf("%d\n%d\n%d\n%d %d %c", counter, max, maxCombo, x1, y1, dir);
  }

  private static int fillGrid (int x, int y, int[][] g, int c, boolean[][] v) {
    if (v[x][y])
      return 0;
    v[x][y] = true;
    int size = 1;
    if (g[x][y] < 8) {
      size += fillGrid(x + 1, y, g, c, v);
    } else {
      g[x][y] -= 8;
    }
    if (g[x][y] < 4) {
      size += fillGrid(x, y + 1, g, c, v);
    } else {
      g[x][y] -= 4;
    }
    if (g[x][y] < 2) {
      size += fillGrid(x - 1, y, g, c, v);
    } else {
      g[x][y] -= 2;
    }
    if (g[x][y] < 1) {
      size += fillGrid(x, y - 1, g, c, v);
    } else {
      g[x][y] -= 1;
    }
    g[x][y] = c;
    return size;
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
