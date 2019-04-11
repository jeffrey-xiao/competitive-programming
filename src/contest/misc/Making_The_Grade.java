package contest.misc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Making_The_Grade {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n;
  static int[] a;
  static TreeSet<Integer> ts = new TreeSet<Integer>();
  static int[] h;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    a = new int[n + 1];
    for (int i = 1; i <= n; i++) {
      a[i] = readInt();
      ts.add(a[i]);
    }
    h = new int[ts.size()];
    int cnt = 0;
    for (int i : ts) {
      h[cnt++] = i;
    }
    int[][] dp = new int[n + 1][ts.size()];
    int[] minh = new int[h.length];
    for (int i = 1; i <= n; i++) {
      for (int j = 0; j < h.length; j++)
        dp[i][j] = Math.abs(a[i] - h[j]) + minh[j];
      minh[0] = dp[i][0];
      for (int j = 1; j < h.length; j++)
        minh[j] = Math.min(minh[j - 1], dp[i][j]);
    }
    int ans = Integer.MAX_VALUE;
    for (int j = 0; j < h.length; j++)
      ans = Math.min(ans, dp[n][j]);
    System.out.println(ans);
    pr.close();
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
