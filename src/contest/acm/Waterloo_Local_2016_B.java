package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Waterloo_Local_2016_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static long[][] dp;
  static int N;
  static long M;
  static long MAX = (long)(2e18);

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readLong();

    dp = new long[N][N];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        dp[i][j] = -1;

    dp(0, 0);
    print(0, 0, M);
    out.close();
  }

  static void print (int pos, int val, long m) {
    if (pos == N - 1) {
      if (val == 1)
        System.out.println(")");
      else
        System.out.println("(");
      return;
    }
    if (dp[pos + 1][val + 1] >= m) {
      System.out.print("(");
      print(pos + 1, val + 1, m);
    } else {
      m -= dp[pos + 1][val + 1];
      System.out.print(")");
      print(pos + 1, val - 1, m);
    }
  }

  static long dp (int pos, int val) {
    if (pos == N)
      return val == 0 ? 1 : 0;
    if (dp[pos][val] != -1)
      return dp[pos][val];

    dp[pos][val] = 0;

    if (val > 0) {
      dp[pos][val] += dp(pos + 1, val - 1);
    }
    dp[pos][val] += dp(pos + 1, val + 1);
    if (dp[pos][val] > MAX)
      dp[pos][val] = MAX;
    return dp[pos][val];
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
