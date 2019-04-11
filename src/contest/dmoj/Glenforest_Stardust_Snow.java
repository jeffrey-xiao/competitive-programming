package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Glenforest_Stardust_Snow {

  static final int SIZE = 52;
  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;
  // row, column, snowflakes, max temp, max snowflakes, max move
  static int r, c, s, b, k, m;
  // time, column, temp, snowflakes
  static int[][][][] dp = new int[SIZE][SIZE][SIZE][SIZE];
  static Snow[][] snowflake = new Snow[SIZE][SIZE];

  public static void main(String[] args) throws IOException {
    r = readInt();
    c = readInt();
    s = readInt();
    b = readInt();
    k = readInt();
    m = readInt();
    for (int i = 0; i < s; i++) {
      int temp = readInt();
      int value = readInt();
      int c = readInt(); // pos
      int r = readInt(); // time
      snowflake[r][c] = new Snow(value, temp);
    }
    for (int i = 0; i < SIZE; i++)
      for (int j = 0; j < SIZE; j++)
        for (int k = 0; k < SIZE; k++)
          for (int l = 0; l < SIZE; l++)
            dp[i][j][k][l] = -1;
    System.out.println(solve(0, 1, b, k));
  }

  static int solve(int time, int col, int temp, int snow) {
    if (time == r + 1 || temp == 0 || snow == 0)
      return 0;
    if (dp[time][col][temp][snow] != -1)
      return dp[time][col][temp][snow];
    int ans = 0;
    for (int i = -m; i <= m; i++) {
      if (i + col < 1 || i + col > c)
        continue;
      ans = Math.max(ans, solve(time + 1, i + col, temp, snow));
      if (snowflake[time + 1][i + col] != null && temp > snowflake[time + 1][i + col].temp) {
        ans = Math.max(ans, snowflake[time + 1][i + col].value + solve(time + 1, i + col, temp - snowflake[time + 1][i + col].temp, snow - 1));
      }
    }
    return dp[time][col][temp][snow] = ans;
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

  static class Snow {
    int value, temp;

    Snow(int value, int temp) {
      this.value = value;
      this.temp = temp;
    }
  }
}
