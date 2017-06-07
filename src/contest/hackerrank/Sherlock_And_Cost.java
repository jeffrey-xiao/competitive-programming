package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Sherlock_And_Cost {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //pr = new PrintWriter(new FileWriter("out.txt"));

    int t = readInt();
    for (int qq = 0; qq < t; qq++) {
      int n = readInt();
      int[][] dp = new int[n][2];
      int[] a = new int[n];
      for (int i = 0; i < n; i++)
        a[i] = readInt();
      for (int i = 1; i < n; i++) {
        dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + Math.abs(a[i - 1] - 1));
        dp[i][1] = Math.max(dp[i - 1][0] + Math.abs(a[i] - 1), dp[i - 1][1]);
      }
      System.out.println(Math.max(dp[n - 1][0], dp[n - 1][1]));
    }

    pr.close();
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
