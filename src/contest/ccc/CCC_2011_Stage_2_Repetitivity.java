package contest.ccc;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CCC_2011_Stage_2_Repetitivity {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int MOD = 0;

  public static void main(String[] args) throws IOException {
    char[] in = readLine().toCharArray();
    MOD = readInt();
    int len = in.length;
    int[][] dp = new int[len + 1][len + 1];
    for (int i = 0; i <= len; i++)
      dp[i][0] = dp[0][i] = 1;
    for (int i = 1; i <= len; i++) {
      for (int j = 1; j <= len; j++) {
        dp[i][j] = (dp[i][j - 1] + dp[i - 1][j]) % MOD;
        if (in[i - 1] != in[j - 1])
          dp[i][j] = (dp[i][j] - dp[i - 1][j - 1] + MOD) % MOD;
      }
    }
    System.out.println(dp[len][len]);
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
