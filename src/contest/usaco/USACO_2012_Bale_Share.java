package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class USACO_2012_Bale_Share {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    int n = readInt();
    boolean[][][] dp = new boolean[2][670][670];
    dp[0][0][0] = true;
    int total = 0;
    for (int x = 0; x < n; x++) {
      int curr = readInt();
      total += curr;
      for (int y = 0; y < 670; y++) {
        for (int z = 0; z < 670; z++) {
          if (dp[x % 2][y][z]) {
            dp[(x + 1) % 2][y][z] = true;
            if (y + curr < 670)
              dp[(x + 1) % 2][y + curr][z] = true;
            if (z + curr < 670)
              dp[(x + 1) % 2][y][z + curr] = true;
          }
        }
      }
    }
    int min = 1000000;
    for (int x = 669; x >= 0; x--) {
      for (int y = 669; y >= 0; y--) {
        if (dp[(n - 1) % 2][x][y]) {
          int a = x;
          int b = y;
          int c = total - a - b;
          min = Math.min(min, Math.max(Math.max(a, b), c));
        }
      }
    }
    System.out.println(min);
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
