package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2008_MRAVOJED {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    char[][] grid = new char[r][c];
    for (int x = 0; x < r; x++)
      grid[x] = next().toCharArray();
    int[][] dp = new int[r][c];
    for (int x = r - 1; x >= 0; x--) {
      for (int y = c - 1; y >= 0; y--) {
        if (x == r - 1 || y == c - 1) {
          if (grid[x][y] == 'x')
            dp[x][y] = 1;
        } else {
          if (grid[x][y] == 'x')
            dp[x][y] = Math.min(dp[x + 1][y], dp[x][y + 1]) + ((grid[x][y] == 'x') ? 1 : 0);
          else
            dp[x][y] = 0;
        }
      }
    }
    int max = 0;
    int x1 = 0, y1 = 0;
    ;
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        if (dp[x][y] > max) {
          max = dp[x][y];
          x1 = x;
          y1 = y;
        }
      }
    }
    System.out.println((x1 + 1) + " " + (y1 + 1) + " " + max);
    grid[x1][y1] = '.';
    for (int x = r - 1; x >= 0; x--) {
      for (int y = c - 1; y >= 0; y--) {
        if (x == r - 1 || y == c - 1) {
          if (grid[x][y] == 'x')
            dp[x][y] = 1;
        } else {
          if (grid[x][y] == 'x')
            dp[x][y] = Math.min(dp[x + 1][y], Math.min(dp[x][y + 1], dp[x + 1][y + 1])) + 1;
          else
            dp[x][y] = 0;
        }
      }
    }
    int prevMax = max;
    max = 1;
    int x2 = x1;
    int y2 = y1;
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        if ((y < y1 || x < x1 || dp[x][y] + x > x1 + prevMax || dp[x][y] + y > y1 + prevMax) && (dp[x][y] > max || (dp[x][y] == max && (Math.abs(x - x1) + Math.abs(y - y1) > Math.abs(x2 - x1) + Math.abs(y2 - y1))))) {
          max = dp[x][y];
          x2 = x;
          y2 = y;
        }
      }
    }
    System.out.println((x2 + 1) + " " + (y2 + 1) + " " + max);
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
