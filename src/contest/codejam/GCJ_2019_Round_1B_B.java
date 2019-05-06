package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1B_B {

  static final long MOD = 1 << 63;
  static final long MASK = (1 << 7) - 1;

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, W;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    W = readInt();
    for (int t = 1; t <= T; t++) {
      out.println(56);
      out.flush();
      long count1 = readLong();

      long r1 = mask(count1 >> 56);
      long r2 = mask(count1 >> 28);

      out.println(168);
      out.flush();
      long count2 = readLong();

      long r3 = mask(count2 >> 56);
      long r4 = mask(count2 >> 42);

      count1 -= (1L << 56) * r1 + (1L << 28) * r2 + (1L << 18) * r3 + (1L << 14) * r4;
      count1 /= 1L << 9;
      count2 -= (1L << 56) * r3 + (1L << 42) * r4;
      count2 /= 1L << 28;

      long r5 = (count2 - count1) / ((1 << 5) - (1 << 2));
      long r6 = count1 - (1 << 2) * r5;
      out.printf("%d %d %d %d %d %d%n", r1, r2, r3, r4, r5, r6);
      out.flush();

      if (readInt() == -1) {
        out.close();
        return;
      }
    }

    out.close();
  }

  static long mask(long a) {
    return a & MASK;
  }

  static long pow(long a, long b) {
    if (b == 0) {
      return 1;
    }
    if (b == 1) {
      return a;
    }
    if (b % 2 == 0) {
      return pow(a * a % MOD, b / 2);
    }
    return a * pow(a * a % MOD, b / 2) % MOD;
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
