package contest.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class WOC_20_D {

  static final int MOD = (int) (1e9 + 7);
  static final int MAX_XOR = 600;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, M, K;
  static boolean[][][] grundy;
  static int[] nimber;
  static int[][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    K = readInt();

    grundy = new boolean[N + 1][N + 1][MAX_XOR];
    dp = new int[M + 1][N + 1][MAX_XOR];
    nimber = new int[N + 1];

    grundy[1][1][0] = true;
    if (K <= 3) {
      for (int i = 2; i <= N; i++) {
        // M = 1 is a special case
        for (int j = 1; j < i; j++) {
          for (int k = 2; k <= K; k++) {
            if (k - 1 > i - j)
              continue;
            // j stones, and i - j stones
            int indexOfPile = -1;

            for (int l = 0; l < MAX_XOR; l++)
              if (grundy[j][1][l])
                indexOfPile = l;

            for (int l = 0; l < MAX_XOR; l++)
              if (indexOfPile != -1 && grundy[i - j][k - 1][l])
                grundy[i][k][indexOfPile ^ l] = true;
          }
        }

        for (int j = 0; j < MAX_XOR; j++) {
          boolean can = false;
          for (int k = 2; k <= K; k++) {
            if (grundy[i][k][j]) {
              can = true;
              break;
            }
          }

          if (!can) {
            grundy[i][1][j] = true;
            break;
          }
        }
      }
    } else {
      for (int i = 1; i <= N; i++)
        grundy[i][1][i - 1] = true;
    }

    for (int i = 1; i <= N; i++)
      for (int j = 0; j < MAX_XOR; j++)
        if (grundy[i][1][j])
          dp[1][i][nimber[i] = j] = 1;

    for (int i = 2; i <= M; i++)
      for (int j = 1; j <= N; j++)
        for (int k = 1; k <= j; k++)
          for (int l = 0; l < MAX_XOR; l++)
            if (dp[i - 1][j - k][l] > 0)
              dp[i][j][l ^ nimber[k]] = (dp[i][j][l ^ nimber[k]] + dp[i - 1][j - k][l]) % MOD;

    int ans = 0;
    for (int i = 1; i < MAX_XOR; i++)
      ans = (ans + dp[M][N][i]) % MOD;

    out.println(ans);
    out.close();
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
