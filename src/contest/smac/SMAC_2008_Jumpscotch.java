package contest.smac;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SMAC_2008_Jumpscotch {

  static BufferedReader br;
  static PrintWriter ps;
  static StringTokenizer st;

  static int[] min = new int[1000001];
  static int[] dp = new int[1000001];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // ps = new PrintWriter("out.txt");

    int n = readInt();
    int d = readInt();
    int[] a = new int[n];
    for (int i = 0; i < n; i++)
      a[i] = readInt();
    int l = 0, r = 0;
    min[0] = 0;
    dp[0] = a[0];
    for (int i = 1; i < n; i++) {
      if (r > l && min[l] < i - d)
        l++;
      dp[i] = a[i] + dp[min[l]];
      while (r > l && dp[i] <= dp[min[r]])
        r--;
      min[++r] = i;
    }

    ps.println(dp[n - 1]);
    ps.close();
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