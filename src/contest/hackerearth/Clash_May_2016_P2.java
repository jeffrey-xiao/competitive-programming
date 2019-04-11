package contest.hackerearth;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Clash_May_2016_P2 {

  static final int MOD = 252;
  static final long M = 1l << 32;
  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;
  static long N;
  static long[][] dp;
  static Matrix A, T, I;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    //br = new BufferedReader(new FileReader("in.txt"));
    //out = new PrintWriter(new FileWriter("out.txt"));

    N = readLong() - 1;

    A = new Matrix();
    T = new Matrix();

    for (int i = 1; i < 10; i++) {
      A.data[i][0] = 1;
    }

    for (int j = 0; j < MOD; j++)
      for (int k = 1; k < 10; k++)
        if (j * 10 % k == 0)
          T.data[(j * 10 + k) % MOD][j] = (T.data[(j * 10 + k) % MOD][j] + 1) % M;

    for (; N > 0; N /= 2) {
      if ((N & 1) > 0)
        A = T.multiply(A);
      T = T.multiply(T);
    }

    long ans = 0;
    for (int i = 0; i < MOD; i++)
      ans = (ans + A.data[i][0]) % M;

    out.println((ans + M) % M);
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

  static class Matrix {
    long[][] data = new long[MOD][MOD];

    Matrix multiply(Matrix m) {
      Matrix ret = new Matrix();
      for (int i = 0; i < MOD; i++)
        for (int k = 0; k < MOD; k++)
          for (int j = 0; j < MOD; j++)
            ret.data[i][j] = (ret.data[i][j] + data[i][k] * m.data[k][j] % M) % M;
      return ret;
    }
  }
}
