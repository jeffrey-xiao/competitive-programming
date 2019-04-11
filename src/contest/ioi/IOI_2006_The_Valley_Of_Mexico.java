package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class IOI_2006_The_Valley_Of_Mexico {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n, m;
  static boolean[][] adj;
  static int[][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();

    adj = new boolean[n][n];
    dp = new int[n][n][2];

    for (int i = 0; i < m; i++) {
      int a = readInt() - 1;
      int b = readInt() - 1;
      adj[a][b] = adj[b][a] = true;
    }
    for (int i = 0; i < n; i++)
      for (int j = 0; j < n; j++)
        for (int k = 0; k < 2; k++)
          dp[i][j][k] = -1;
    for (int i = 0; i < n; i++)
      if (isPossible(i, i, 0) > 0) {
        print(i, i, 0);
        out.close();
        return;
      }
    out.println(-1);
    out.close();
  }

  static void print(int l, int r, int type) {
    if (type > 0)
      out.println(r + 1);
    else
      out.println(l + 1);
    if (l == (r + 1) % n)
      return;
    if (dp[l][r][type] == 1)
      print((l - 1 + n) % n, r, 0);
    else
      print(l, (r + 1) % n, 1);

  }

  static int isPossible(int l, int r, int type) {
    if (l == (r + 1) % n)
      return 1;
    if (dp[l][r][type] != -1)
      return dp[l][r][type];
    int x = (l - 1 + n) % n;
    int y = (r + 1) % n;
    int res1 = 0;
    int res2 = 0;
    if (type == 1) {
      res1 = adj[r][x] ? isPossible(x, r, 0) : 0;
      res2 = adj[r][y] ? isPossible(l, y, 1) : 0;
    } else {
      res1 = adj[l][x] ? isPossible(x, r, 0) : 0;
      res2 = adj[l][y] ? isPossible(l, y, 1) : 0;
    }
    if (res1 > 0)
      return dp[l][r][type] = 1;
    else
      return dp[l][r][type] = (res2 > 0) ? 2 : 0;
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
