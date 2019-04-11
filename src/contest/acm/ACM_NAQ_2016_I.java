package contest.acm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ACM_NAQ_2016_I {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int N, M, P;
  static long[][] grid;
  static long[][] system;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readInt();
    M = readInt();
    P = readInt();

    grid = new long[N][M];
    system = new long[N * M][N * M + 1];

    for (int i = 0; i < N; i++) {
      for (int j = 0; j < M; j++) {
        grid[i][j] = readInt();

        for (int n = 0; n < N; n++)
          for (int m = 0; m < M; m++)
            if (n == i || m == j)
              system[i * M + j][n * M + m] = 1;
        system[i * M + j][N * M] = P - grid[i][j];
      }
    }

    system = solve(system);

    if (system == null) {
      out.println(-1);
    } else {
      int sum = 0;

      for (int i = 0; i < N * M; i++)
        sum += system[i][N * M];
      out.println(sum);

      assert sum <= N * M * P;

      for (int i = 0; i < N * M; i++)
        for (int j = 0; j < system[i][N * M]; j++)
          out.print(i + 1 + " ");
      out.println();

    }

    out.close();
  }

  public static long[][] solve(long[][] A) {
    int M = A.length;
    int N = A[0].length - 1;

    // forward elimination
    for (int i = 0; i < N; i++) {
      int maxIndex = i;
      for (int j = i + 1; j < M; j++)
        if (Math.abs(A[j][i]) > Math.abs(A[maxIndex][i]))
          maxIndex = j;

      long[] tempA = A[i];
      A[i] = A[maxIndex];
      A[maxIndex] = tempA;

      if (A[i][i] < 0) {
        for (int j = i; j <= N; j++)
          A[i][j] *= -1;
      }

      for (int j = i + 1; j < N; j++) {
        long factor = A[j][i];
        for (int k = i; k <= N; k++)
          A[j][k] = (A[j][k] * A[i][i]);
        for (int k = i; k <= N; k++)
          A[j][k] = (A[j][k] - A[i][k] * factor);
      }

      for (int j = 0; j < M; j++) {
        long gcd = 0;
        for (int k = 0; k <= N; k++) {
          gcd = gcd(gcd, A[j][k]);
        }
        gcd = Math.abs(gcd);
        if (gcd == 0)
          continue;
        for (int k = 0; k <= N; k++)
          A[j][k] /= gcd;
      }
    }

    // backwards elimination
    for (int i = N - 1; i >= 0; i--) {
      if (A[i][i] == 0) {
        if (A[i][N] != 0)
          return null;
        else
          continue;
      }
      for (int j = i - 1; j >= 0; j--) {
        long factor = A[j][i];
        for (int k = 0; k <= N; k++)
          A[j][k] = (A[j][k] * A[i][i]);
        for (int k = 0; k <= N; k++) {
          A[j][k] = (A[j][k] - A[i][k] * factor);
        }
      }

      for (int j = 0; j < M; j++) {
        long gcd = 0;
        for (int k = 0; k <= N; k++) {
          gcd = gcd(gcd, A[j][k]);
        }
        gcd = Math.abs(gcd);
        if (gcd == 0)
          continue;
        for (int k = 0; k <= N; k++) {
          A[j][k] /= gcd;
        }
      }
    }

    // dividing to create leading ones
    for (int i = 0; i < N; i++) {
      if (A[i][i] % P == 0) {
        if (A[i][N] != 0)
          return null;
      } else {
        A[i][N] = (A[i][N] % P + P) % P;
        A[i][N] = (divMod(A[i][N], A[i][i])) % P;
        if (A[i][i] != 0)
          A[i][i] = 1;
      }
    }

    return A;
  }

  // O(log P)
  static long divMod(long i, long j) {
    return i * pow(j, P - 2) % P;
  }

  static long gcd(long a, long b) {
    return b == 0 ? a : (gcd(b, a % b));
  }

  static long pow(long a, long b) {
    if (b == 0)
      return 1;
    if (b == 1)
      return a;
    if (b % 2 == 0)
      return pow(a * a % P, b / 2);
    return a * pow(a * a % P, b / 2) % P;
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
