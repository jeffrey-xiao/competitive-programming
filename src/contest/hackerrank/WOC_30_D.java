package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class WOC_30_D {

  static final long INF = 1L << 59;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, K;
  static long[] weight, height;
  static long[][] cost, dp;
  static int[][] index;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    K = readInt();

    cost = new long[N][N];
    dp = new long[K + 1][N];
    weight = new long[N];
    height = new long[N];

    for (int i = 0; i < N; i++) {
      height[i] = readInt();
      weight[i] = readInt();
    }

    for (int i = N - 1; i >= 0; i--) {
      long currCost = 0;
      long currWeight = weight[i];
      for (int j = i - 1; j >= 0; j--) {
        currCost += (height[j + 1] - height[j]) * currWeight;
        currWeight += weight[j];
        cost[j][i] = currCost;
      }
    }

    Arrays.fill(dp[0], INF);

    for (int k = 1; k <= K; k++)
      solve(k, 0, N - 1, 0, N - 1);

    out.println(dp[K][N - 1]);
    out.close();
  }

  static void solve(int k, int i, int j, int l, int r) {
    if (i > j)
      return;

    int mid = (i + j) >> 1;
    int bestIndex = l;
    dp[k][mid] = INF;
    for (int x = l; x <= Math.min(r, mid); x++) {
      long val = cost[x][mid] + (x > 0 ? dp[k - 1][x - 1] : 0);
      if (val < dp[k][mid]) {
        dp[k][mid] = val;
        bestIndex = x;
      }
    }

    solve(k, i, mid - 1, l, bestIndex);
    solve(k, mid + 1, j, bestIndex, r);
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
