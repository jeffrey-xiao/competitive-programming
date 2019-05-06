package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1C_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, R, C;
  static char[][] grid;
  static int[][][][] dp;
  static int[][] lastH, lastV;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      R = readInt();
      C = readInt();
      grid = new char[R][];
      lastH = new int[R][C];
      lastV = new int[R][C];

      for (int i = 0; i < R; i++) {
        grid[i] = next().toCharArray();
      }

      for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
          if (grid[i][j] == '#') {
            lastH[i][j] = j;
          } else if (j > 0) {
            lastH[i][j] = lastH[i][j - 1];
          } else {
            lastH[i][j] = -1;
          }
        }
      }

      for (int j = 0; j < C; j++) {
        for (int i = 0; i < R; i++) {
          if (grid[i][j] == '#') {
            lastV[i][j] = i;
          } else if (i > 0) {
            lastV[i][j] = lastV[i - 1][j];
          } else {
            lastV[i][j] = -1;
          }
        }
      }

      dp = new int[R][R][C][C];
      for (int i = 0; i < R; i++) {
        for (int j = 0; j < R; j++) {
          for (int k = 0; k < C; k++) {
            Arrays.fill(dp[i][j][k], -1);
          }
        }
      }

      int ans = 0;
      for (int i = 0; i < R; i++) {
        if (lastH[i][C - 1] == -1 && (solve(0, i - 1, 0, C - 1) ^ solve(i + 1, R - 1, 0, C - 1)) == 0) {
          ans += C;
        }
      }

      for (int i = 0; i < C; i++) {
        if (lastV[R - 1][i] == -1 && (solve(0, R - 1, 0, i - 1) ^ solve(0, R - 1, i + 1, C - 1)) == 0) {
          ans += R;
        }
      }
      out.printf("Case #%d: %d%n", t, ans);
    }

    out.close();
  }

  static int solve(int r1, int r2, int c1, int c2) {
    if (r1 > r2 || c1 > c2) {
      return 0;
    }
    if (dp[r1][r2][c1][c2] != -1) {
      return dp[r1][r2][c1][c2];
    }

    TreeSet<Integer> ts = new TreeSet<Integer>();
    for (int i = r1; i <= r2; i++) {
      if (lastH[i][c2] < c1) {
        ts.add(solve(r1, i - 1, c1, c2) ^ solve(i + 1, r2, c1, c2));
      }
    }

    for (int i = c1; i <= c2; i++) {
      if (lastV[r2][i] < r1) {
        ts.add(solve(r1, r2, c1, i - 1) ^ solve(r1, r2, i + 1, c2));
      }
    }
    
    if (!ts.contains(0)) {
      return dp[r1][r2][c1][c2] = 0;
    }

    for (int nimber : ts) {
      if (!ts.contains(nimber + 1)) {
        return dp[r1][r2][c1][c2] = nimber + 1;
      }
    }

    System.out.println("XD");
    return -1;
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
