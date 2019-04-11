package codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Round_1A_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, R, B, C;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();

    for (int t = 1; t <= T; t++) {
      R = readInt();
      B = readInt();
      C = readInt();

      long[] M = new long[C], S = new long[C], P = new long[C];

      for (int i = 0; i < C; i++) {
        M[i] = readInt();
        S[i] = readInt();
        P[i] = readInt();
      }

      long lo = 0;
      long hi = 1L << 60;

      while (lo <= hi) {
        long mid = (lo + hi) / 2;
        ArrayList<Long> items = new ArrayList<Long>();
        for (int i = 0; i < C; i++) {
          long currItems = Math.min(M[i], (mid - P[i]) / S[i]);
          items.add(Math.max(0, currItems));
        }
        Collections.sort(items);
        Collections.reverse(items);
        long totalItems = 0;
        for (int i = 0; i < R; i++) {
          totalItems += items.get(i);
        }
        if (totalItems >= B) {
          hi = mid - 1;
        } else {
          lo = mid + 1;
        }
      }

      out.printf("Case #%d: %d\n", t, lo);
    }

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
