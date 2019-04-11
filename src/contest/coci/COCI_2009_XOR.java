package contest.coci;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class COCI_2009_XOR {

  static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
  static PrintWriter ps = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
  static StringTokenizer st;

  static int[] X, Y, R, T;
  static int n;
  static double total = 0;

  public static void main(String[] args) throws IOException {
    n = readInt();
    X = new int[n];
    Y = new int[n];
    R = new int[n];
    T = new int[n];
    for (int i = 0; i < n; i++) {
      X[i] = readInt();
      Y[i] = readInt();
      R[i] = readInt();
      T[i] = X[i] + Y[i] + R[i];
    }
    solve(0, 0, 0, 1 << 30, 0);
    System.out.printf("%.1f", total);
  }

  private static void solve(int i, int x, int y, int t, int count) {
    if (x + y >= t)
      return;
    if (i == n) {
      if (count == 0)
        return;
      int side = t - x - y;
      total += Math.pow(-2, count - 1) * side * side / 2;
      return;
    }
    solve(i + 1, x, y, t, count);
    solve(i + 1, Math.max(x, X[i]), Math.max(y, Y[i]), Math.min(t, T[i]), count + 1);
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
