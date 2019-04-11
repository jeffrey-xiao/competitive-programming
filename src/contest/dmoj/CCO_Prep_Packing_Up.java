package contest.dmoj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCO_Prep_Packing_Up {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  static int n, L;
  static long[] s;
  static long[] dp;
  static int[] li = new int[2000010];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    n = readInt();
    L = readInt() + 1;

    s = new long[n + 1];
    dp = new long[n + 1];
    for (int i = 1; i <= n; i++)
      s[i] = readInt() + s[i - 1] + 1;
    int l = 0, r = 0;
    for (int i = 1; i <= n; i++) {
      while (r - l >= 1) {
        if (f(li[l], li[l + 1]) < (s[i] - L))
          l++;
        else
          break;
      }
      dp[i] = dp[li[l]] + sqr(s[i] - s[li[l]] - L);

      while (r - l >= 1) {
        if (f(li[r - 1], li[r]) > f(li[r], i))
          r--;
        else
          break;
      }
      li[++r] = i;
    }
    ps.println(dp[n]);
    ps.close();
  }

  static long sqr(long x) {
    return x * x;
  }

  static long top(int a, int b) {
    return dp[a] - dp[b] + sqr(s[a]) - sqr(s[b]);
  }

  static long bottom(int a, int b) {
    return 2 * (s[a] - s[b]);
  }

  static double f(int a, int b) {
    return (double) (top(b, a)) / (double) (bottom(b, a));
  }

  static boolean greater(int a, int b, int c, int d) {
    return top(b, a) * bottom(d, c) > top(d, c) * bottom(b, a);
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