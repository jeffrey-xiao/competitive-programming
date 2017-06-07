package contest.ioi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class IOI_1998_Polygon {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int n;
  static char[] op;
  static int[] num;
  static int[][][] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    op = new char[n];
    num = new int[n];
    dp = new int[n][n][2]; // 0 is min, 1 is max
    for (int i = 0; i < n; i++) {
      op[i] = readCharacter();
      num[i] = readInt();
      for (int j = 0; j < n; j++) {
        dp[i][j][0] = 1 << 30;
        dp[i][j][1] = -1 << 30;
      }
    }
    int max = -1 << 30;
    ArrayList<Integer> edges = new ArrayList<Integer>();
    for (int i = 0; i < n; i++) {
      int ret = dp(i, (i + n - 1) % n, 1);
      if (ret > max) {
        max = ret;
        edges.clear();
        edges.add(i + 1);
      } else if (ret == max) {
        edges.add(i + 1);
      }
    }
    out.println(max);
    for (int i : edges)
      out.print(i + " ");
    out.println();
    out.close();
  }

  static int dp (int i, int j, int k) {
    if ((k == 0 && dp[i][j][k] != 1 << 30) || (k == 1 && dp[i][j][k] != -1 << 30))
      return dp[i][j][k];
    if ((i + 1) % n == j)
      return dp[i][j][0] = dp[i][j][1] = operate(i);
    if (i == j)
      return dp[i][j][0] = dp[i][j][1] = num[i];
    for (int l = i; l % n != j % n; l++) {
      char c = op[(l + 1) % n];
      if (c == 't') {
        dp[i][j][0] = min(dp[i][j][0], dp(i, l % n, 0) + dp((l + 1) % n, j, 0));
        dp[i][j][1] = max(dp[i][j][1], dp(i, l % n, 1) + dp((l + 1) % n, j, 1));
      } else {
        dp[i][j][0] = min(dp[i][j][0], dp(i, l % n, 0) * dp((l + 1) % n, j, 0), dp(i, l % n, 0) * dp((l + 1) % n, j, 1), dp(i, l % n, 1) * dp((l + 1) % n, j, 0), dp(i, l % n, 1) * dp((l + 1) % n, j, 1));
        dp[i][j][1] = max(dp[i][j][1], dp(i, l % n, 0) * dp((l + 1) % n, j, 0), dp(i, l % n, 0) * dp((l + 1) % n, j, 1), dp(i, l % n, 1) * dp((l + 1) % n, j, 0), dp(i, l % n, 1) * dp((l + 1) % n, j, 1));
      }
    }
    return dp[i][j][k];
  }

  static int min (int... val) {
    int ans = 1 << 30;
    for (int i : val)
      ans = Math.min(ans, i);
    return ans;
  }

  static int max (int... val) {
    int ans = -1 << 30;
    for (int i : val)
      ans = Math.max(ans, i);
    return ans;
  }

  static int operate (int i) {
    if (op[(i + 1) % n] == 'x')
      return num[i] * num[(i + 1) % n];
    return num[i] + num[(i + 1) % n];
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
