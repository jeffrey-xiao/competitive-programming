package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WOC_28_C {

  static final int MOD = (int)(1e9 + 7);
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N;
  static char[] input;
  static long[][] dp = new long[2][8];
  static long[] pow = new long[200001];

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();

    input = readLine().toCharArray();

    pow[0] = 1;
    for (int i = 1; i <= 200000; i++)
      pow[i] = (pow[i - 1] << 1) % MOD;

    long ans = 0;

    for (int i = N - 1; i >= 0; i--) {
      int val = (input[i] - '0') % 8;
      ans = (ans + dp[1][(8 - val * 100 % 8) % 8] * (pow[i])) % MOD;
      for (int j = 0; j < 8; j++)
        dp[1][(val * 10 + j) % 8] = (dp[1][(val * 10 + j) % 8] + dp[0][j]) % MOD;
      dp[0][val] = (dp[0][val] + 1) % MOD;
    }
    ans = (ans + dp[1][0]) % MOD;
    ans = (ans + dp[0][0]) % MOD;

    out.printf("%d\n", ans);
    out.close();
  }

  static long bruteforce() {
    long ret = 0;
    for (int i = 0; i < N; i++) {
      for (int j = i + 1; j < N; j++) {
        for (int k = j + 1; k < N; k++) {
          int a = (input[i] - '0') * 100;
          int b = (input[j] - '0') * 10;
          int c = (input[k] - '0');
          if ((a + b + c) % 8 == 0)
            ret = (ret + (pow[i])) % MOD;
        }
        if (((input[i] - '0') * 10 + input[j] - '0') % 8 == 0)
          ret++;
      }
      if ((input[i] - '0') % 8 == 0)
        ret++;
    }
    return ret;
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
