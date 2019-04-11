package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_ZABAVA {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int n = readInt();
    int m = readInt();
    int[] b = new int[m + 1];
    int k = readInt();
    for (int i = 0; i < n; i++)
      b[readInt()]++;
    long[][] dp = new long[m + 1][k + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 0; j <= k; j++) {
        long min = 1 << 30;
        for (int l = 0; l <= j; l++) {
          min = Math.min(min, dp[i - 1][j - l] + noise(b[i], l));
        }
        dp[i][j] = min;
      }
    }
    ps.println(dp[m][k]);
    ps.close();
  }

  public static long noise(int n, int k) {
    k = k + 1;
    long x = n / k;
    long big = n % k;
    long small = k - big;
    return (x) * (x + 1) / 2 * small + (x + 1) * (x + 2) / 2 * big;
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