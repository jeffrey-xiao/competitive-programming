package contest.dmoj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Joey_And_Biology {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int[][] dp;

  public static void main (String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    readInt();
    readInt();
    char[] s1 = (" " + next()).toCharArray();
    char[] s2 = (" " + next()).toCharArray();
    out.println(solve(s1, s2));
    out.close();
  }

  static int solve (char[] s1, char[] s2) {
    int[][] dp = new int[s1.length][s2.length];

    for (int i = 0; i < s1.length; i++)
      dp[i][0] = (i + 2) / 3;

    for (int j = 0; j < s2.length; j++)
      dp[0][j] = (j + 2) / 3;

    for (int i = 1; i < s1.length; i++) {
      for (int j = 1; j < s2.length; j++) {
        int replace = dp[i - 1][j - 1];
        int delete = dp[i][j - 1];
        int insert = dp[i - 1][j];

        if (j - 2 >= 0)
          delete = Math.min(delete, dp[i][j - 2]);
        if (j - 3 >= 0)
          delete = Math.min(delete, dp[i][j - 3]);
        if (i - 2 >= 0)
          insert = Math.min(insert, dp[i - 2][j]);
        if (i - 3 >= 0)
          insert = Math.min(insert, dp[i - 3][j]);

        dp[i][j] = Math.min(replace, Math.min(delete, insert)) + 1;
        if (s1[i] == s2[j])
          dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - 1]);
      }
    }
    return dp[s1.length - 1][s2.length - 1];
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
