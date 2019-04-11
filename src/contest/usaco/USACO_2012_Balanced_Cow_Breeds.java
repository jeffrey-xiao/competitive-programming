package contest.usaco;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class USACO_2012_Balanced_Cow_Breeds {

  static final int MOD = 2012;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N;
  static char[] in;
  static int[] prefix;
  static int[][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    in = readLine().toCharArray();
    N = in.length;

    prefix = new int[N + 1];
    dp = new int[N][N];

    for (int i = 0; i < N; i++)
      for (int j = 0; j < N; j++)
        dp[i][j] = -1;

    for (int i = 1; i <= N; i++)
      prefix[i] = prefix[i - 1] + (in[i - 1] == '(' ? 1 : -1);
    out.println(compute(0, 0));
    out.close();
  }

  static int compute(int i, int sumH) {
    if (i == N)
      return 1;

    if (dp[i][sumH] != -1)
      return dp[i][sumH];

    int sumG = prefix[i] - sumH;
    int ret = 0;

    if (in[i] == '(') {
      ret = (ret + compute(i + 1, sumH + 1) + compute(i + 1, sumH)) % MOD;
    } else {
      if (sumH > 0)
        ret = (ret + compute(i + 1, sumH - 1)) % MOD;
      if (sumG > 0)
        ret = (ret + compute(i + 1, sumH)) % MOD;
    }
    return dp[i][sumH] = ret;
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
