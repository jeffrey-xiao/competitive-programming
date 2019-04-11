package contest.coci;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2014_WTF {

  static final int DOWN = 0;
  static final int LEFT = 1;
  static final int RIGHT = 2;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static int N, R;
  static int[][] A, B;
  static int[][][] dp;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    R = readInt();

    A = new int[N][N];
    B = new int[N][N];
    dp = new int[N][N][3];

    for (int i = 0; i < N; i++) {
      A[0][i] = readInt() * -1;
      int curr = i;
      for (int j = 1; j < N; j++)
        A[j][(curr += R) % N] = A[0][i];
    }

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N - 1; j++) {
        B[i][j] = A[i][j + 1] - A[i][j];
        for (int k = 0; k < 3; k++) {
          dp[i][j][k] = -1 << 30;
        }
      }
    }

    // B is N x N - 1

    for (int i = 0; i < N; i++) {
      // going down
      for (int j = 0; j < N - 1; j++) {
        dp[i][j][DOWN] = B[i][j] + (i > 0 ? getMax(i - 1, j) : 0);
      }

      // going left
      for (int j = N - 3; j >= 0; j--) {
        dp[i][j][LEFT] = B[i][j] + Math.max(dp[i][j + 1][LEFT], dp[i][j + 1][DOWN]);
      }

      // going right
      for (int j = 1; j < N - 1; j++) {
        dp[i][j][RIGHT] = B[i][j] + Math.max(dp[i][j - 1][RIGHT], dp[i][j - 1][DOWN]);
      }
    }

    int ans = -1 << 30;
    for (int j = 0; j < N - 1; j++)
      ans = Math.max(ans, getMax(N - 1, j));
    out.println(ans);
    out.close();
  }

  static int getMax(int i, int j) {
    return Math.max(dp[i][j][DOWN], Math.max(dp[i][j][LEFT], dp[i][j][RIGHT]));
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
