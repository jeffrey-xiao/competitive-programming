package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Coin_On_The_Table {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n, m, k;
  static char[][] g;
  static int r, c;
  static int[][][] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    k = readInt();
    dp = new int[n][m][k + 1];
    g = new char[n][m];
    for (int i = 0; i < n; i++) {
      g[i] = next().toCharArray();
      for (int j = 0; j < m; j++) {
        if (g[i][j] == '*') {
          r = i;
          c = j;
        }
        for (int l = 0; l <= k; l++)
          dp[i][j][l] = 1 << 30;
      }
    }
    solve(0, 0, 0, 0);
    System.out.println(dp[r][c][k] == 1 << 30 ? -1 : dp[r][c][k]);
    pr.close();
  }

  static void solve (int x, int y, int time, int val) {
    if (x < 0 || x >= n || y < 0 || y >= m || time > k)
      return;
    if (val >= dp[x][y][time])
      return;
    dp[x][y][time] = val;
    if (g[x][y] != '*') {
      solve(x, y + 1, time + 1, val + ((g[x][y] != 'R') ? 1 : 0));
      solve(x, y - 1, time + 1, val + ((g[x][y] != 'L') ? 1 : 0));
      solve(x - 1, y, time + 1, val + ((g[x][y] != 'U') ? 1 : 0));
      solve(x + 1, y, time + 1, val + ((g[x][y] != 'D') ? 1 : 0));
    } else {
      solve(x, y, time + 1, val);
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

  static char readCharacter () throws IOException {
    return next().charAt(0);
  }

  static String readLine () throws IOException {
    return br.readLine().trim();
  }
}
