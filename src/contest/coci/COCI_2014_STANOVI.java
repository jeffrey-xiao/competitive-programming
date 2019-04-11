package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_STANOVI {

  static BufferedReader br;
  static PrintWriter pr;
  static StringTokenizer st;

  static int n, m, K;

  static int[][][][][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    pr = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    // br = new BufferedReader(new FileReader("in.txt"));
    // pr = new PrintWriter(new FileWriter("out.txt"));

    n = readInt();
    m = readInt();
    K = readInt();
    dp = new int[n + 1][m + 1][2][2][2][2];
    for (int i = 0; i <= n; i++)
      for (int j = 0; j <= m; j++)
        for (int k = 0; k < 2; k++)
          for (int l = 0; l < 2; l++)
            for (int x = 0; x < 2; x++)
              for (int y = 0; y < 2; y++) {
                dp[i][j][k][l][x][y] = -1;
                if (k == 0 && l == 0 && x == 0 && y == 0)
                  dp[i][j][k][l][x][y] = 1 << 28;
              }
    pr.println(compute(n, m, 1, 1, 1, 1));
    pr.close();
  }

  // left, right, up, down
  private static int compute(int N, int M, int i, int j, int k, int l) {
    if (dp[N][M][i][j][k][l] != -1)
      return dp[N][M][i][j][k][l];
    int res = (N * M - K) * (N * M - K);
    if (N > 1) {
      for (int x = 1; x < N; x++) {
        res = Math.min(res, compute(x, M, 0, j, k, l) + compute(N - x, M, i, 0, k, l));
      }
    }
    if (M > 1) {
      for (int x = 1; x < M; x++) {
        res = Math.min(res, compute(N, x, i, j, 0, l) + compute(N, M - x, i, j, k, 0));
      }
    }
    dp[N][M][i][j][k][l] = res;
    return res;
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
