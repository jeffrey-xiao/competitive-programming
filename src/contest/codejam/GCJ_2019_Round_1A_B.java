package codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1A_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, N, M;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    N = readInt();
    M = readInt();

    for (int t = 1; t <= T; t++) {
      long[] m = new long[] {12, 13, 14, 15, 16, 17, 18};
      long[] r = new long[7];
      for (int i = 0; i < m.length; i++) {
        for (int j = 0; j < 18; j++) {
          out.printf("%d ", m[i]);
        }
        out.printf("%n");
        out.flush();

        for (int j = 0; j < 18; j++) {
          r[i] += readInt();
        }
        r[i] %= m[i];
        System.err.println(r[i]);
      }
      long[] ans = chineseRemainderTheorem(m, r);
      out.printf("%d%n", ans[0]);
      out.flush();
      int verdict = readInt();
      if (verdict == -1) {
        return;
      }
    }

    out.close();
  }

  static long mod(long a, long b) {
    return ((a % b) + b) % b;
  }

  public static long[] euclid(long a, long b) {
    long x = 1, y = 0, x1 = 0, y1 = 1, t;
    while (b != 0) {
      long q = a / b;
      t = x;
      x = x1;
      x1 = t - q * x1;
      t = y;
      y = y1;
      y1 = t - q * y1;
      t = b;
      b = a - q * b;
      a = t;
    }
    return a > 0 ? new long[] {a, x, y} : new long[] {-a, -x, -y};
  }

  static long[] chineseRemainderTheorem(long m1, long r1, long m2, long r2) {
    long[] res = euclid(m1, m2);
    long g = res[0], s = res[1], t = res[2];
    if (r1 % g != r2 % g)
      return new long[] {0, -1};
    return new long[] {mod(s * r2 * m1 + t * r1 * m2, m1 * m2) / g, m1 * m2 / g};
  }

  static long[] chineseRemainderTheorem(long[] m, long[] r) {
    long[] ret = new long[] {r[0], m[0]};
    for (int i = 1; i < m.length; i++) {
      ret = chineseRemainderTheorem(ret[1], ret[0], m[i], r[i]);
      if (ret[1] == -1)
        break;
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
