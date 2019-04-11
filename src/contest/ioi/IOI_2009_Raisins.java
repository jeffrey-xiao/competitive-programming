package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class IOI_2009_Raisins {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;
  static long[][] sum;

  public static void main(String[] args) throws IOException {
    int r = readInt();
    int c = readInt();
    long[][][][] dp = new long[r][r][c][c];
    for (int i = 0; i < r; i++)
      for (int j = 0; j < r; j++)
        for (int k = 0; k < c; k++)
          for (int l = 0; l < c; l++)
            dp[i][j][k][l] = 1000000000;
    sum = new long[r + 1][c + 1];
    for (int x = 0; x < r; x++) {
      for (int y = 0; y < c; y++) {
        long raisins = readLong();
        dp[x][x][y][y] = raisins;
        sum[x + 1][y + 1] = raisins - sum[x][y] + sum[x + 1][y] + sum[x][y + 1];
      }
    }
    for (int gx = 0; gx < r; gx++) {
      for (int gy = 0; gy < c; gy++) {
        if (gx == 0 && gy == 0)
          continue;
        for (int x = 0; x < r - gx; x++) {
          for (int y = 0; y < c - gy; y++) {
            for (int dx = x; dx <= x + gx && dx + 1 < r; dx++) {
              long a = dp[x][dx][y][y + gy] + dp[dx + 1][x + gx][y][y + gy] + freq(x, x + gx + 1, y, y + gy + 1);
              dp[x][x + gx][y][y + gy] = Math.min(dp[x][x + gx][y][y + gy], a);
            }
            for (int dy = y; dy <= y + gy && dy + 1 < c; dy++) {
              long a = dp[x][x + gx][y][dy] + dp[x][x + gx][dy + 1][y + gy] + freq(x, x + gx + 1, y, y + gy + 1);
              dp[x][x + gx][y][y + gy] = Math.min(dp[x][x + gx][y][y + gy], a);
            }
          }
        }
      }
    }
    System.out.println(dp[0][r - 1][0][c - 1] - sum[r][c]);
  }

  public static long freq(int x1, int x2, int y1, int y2) {
    return sum[x2][y2] + sum[x1][y1] - sum[x2][y1] - sum[x1][y2];
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
}
