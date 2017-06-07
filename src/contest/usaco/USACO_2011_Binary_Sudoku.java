package contest.usaco;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2011_Binary_Sudoku {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static char[][] grid = new char[9][];
  static int[][][][][] dp = new int[9][9][1 << 9][1 << 3][2];
  static final int INF = 5000;

  public static void main (String[] args) throws IOException {
    for (int x = 0; x < 9; x++) {
      grid[x] = next().toCharArray();
    }
    for (int x = 0; x < 9; x++) {
      for (int y = 0; y < 9; y++) {
        for (int z = 0; z < 1 << 9; z++) {
          for (int i = 0; i < 1 << 3; i++) {
            dp[x][y][z][i][0] = -1;
            dp[x][y][z][i][1] = -1;
          }
        }
      }
    }
    System.out.println(compute(0, 0, 0, 0, 0));
  }

  private static int compute (int r, int c, int bp, int cp, int rp) {
    if (r < 9 && c < 9 && dp[r][c][cp][bp][rp] != -1)
      return dp[r][c][cp][bp][rp];
    if (r == 9)
      return (cp == 0) ? 0 : INF;
    if (c == 9) {
      if (rp != 0 || (bp != 0 && r % 3 == 2))
        return INF;
      return compute(r + 1, 0, bp, cp, 0);
    }
    int a = (grid[r][c] == '0' ? 1 : 0);
    dp[r][c][cp][bp][rp] = Math.min(a + compute(r, c + 1, bp ^ (1 << c / 3), cp ^ (1 << c), rp ^ 1), (a + 1) % 2 + compute(r, c + 1, bp, cp, rp));
    return dp[r][c][cp][bp][rp];
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
