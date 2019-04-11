package contest.ccc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2009_Stage_2_Dinner_DP {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n, k;
  static char[] in;
  static int[][][][] dp = new int[101][101][101][2];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    k = readInt();
    in = next().toCharArray();
    int ans = Math.min(solve(0, n - 1, 0, 0), solve(0, n - 1, 0, 1));
    pr.println(ans == 1 << 10 ? -1 : ans);
    pr.close();
  }

  private static int solve(int i, int j, int l, int m) {
    if (i > j) {
      return l >= k ? 1 : 1 << 10;
    }
    if (dp[i][j][l][m] != 0)
      return dp[i][j][l][m];
    int res = 1 << 10;
    if (((in[i] == 'H') ? 1 : 0) == m)
      res = solve(i + 1, j, l + 1, m);
    for (int x = i; x < j; x++) {
      res = Math.min(res, Math.min(solve(i, x, 0, 0), solve(i, x, 0, 1)) + solve(x + 1, j, l, m));
    }
    return dp[i][j][l][m] = res;
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
