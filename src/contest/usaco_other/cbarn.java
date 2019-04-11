package contest.usaco_other;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/*
ID: jeffrey40
LANG: JAVA
TASK: cbarn
*/
import java.util.StringTokenizer;

public class cbarn {
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, K;
  static long[][] cost;
  static long[] cows;
  static long[][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new FileReader("cbarn.in"));
    out = new PrintWriter(new FileWriter("cbarn.out"));
    //br = new BufferedReader(new InputStreamReader(System.in));
    //out = new PrintWriter(new OutputStreamWriter(System.out));

    N = readInt();
    K = readInt();

    cost = new long[N + 1][N + 1];
    cows = new long[N + 1];
    dp = new long[K + 1][N + 1][N + 1];

    for (int i = 0; i <= K; i++)
      for (int j = 0; j <= N; j++)
        for (int k = 0; k <= N; k++)
          dp[i][j][k] = 1l << 60;

    for (int i = 1; i <= N; i++)
      cows[N - i + 1] = readInt();

    for (int i = 1; i <= N; i++)
      cows[i] += cows[i - 1];

    for (int i = 1; i <= N; i++) {
      cost[i][i] = 0;
      for (int j = i + 1; j <= N; j++)
        cost[i][j] = cost[i][j - 1] + cows[j - 1] - cows[i - 1];
    }

    for (int j = 1; j <= N; j++)
      dp[1][j][j] = cost[1][j];

    for (int i = 2; i <= K; i++)
      for (int j = 1; j <= N - i; j++)
        compute(i, j, 1, N, 1, N);

    long ans = 1l << 60;
    for (int i = K; i <= N; i++) {
      for (int j = 1; j <= i; j++) {
        long curr = dp[K][i][j];

        int first = j;
        int last = i;

        if (last != N)
          curr += cost[last + 1][N] + (cows[N] - cows[last]) * (first);

        ans = Math.min(ans, curr);
      }
    }

    out.println(ans);
    out.close();
    System.exit(0);
  }

  static void compute(int doors, int first, int i, int j, int l, int r) {
    if (i > j)
      return;

    int mid = (i + j) / 2;
    int bestIndex = l;

    for (int k = l; k <= Math.min(r, mid - 1); k++) {
      long val = dp[doors - 1][k][first] + cost[k + 1][mid];
      if (val < dp[doors][mid][first]) {
        dp[doors][mid][first] = val;
        bestIndex = k;
      }
    }

    compute(doors, first, i, mid - 1, l, bestIndex);
    compute(doors, first, mid + 1, j, bestIndex, r);
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

  static String readLine() throws IOException {
    return br.readLine().trim();
  }
}
