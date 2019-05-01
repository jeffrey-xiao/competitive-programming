package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1C_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, J, P, S, K;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      J = readInt();
      P = readInt();
      S = readInt();
      K = readInt();

      if (K >= S) {
        out.printf("Case #%d: %d%n", t, J * P * S);
        for (int j = 1; j <= J; j++) {
          for (int p = 1; p <= P; p++) {
            for (int s = 1; s <= S; s++) {
              out.printf("%d %d %d%n", j, p, s);
            }
          }
        }
      } else {
        out.printf("Case #%d: %d%n", t, J * P * K);
        for (int j = 1; j <= J; j++) {
          for (int p = 1; p <= P; p++) {
            for (int k = 0; k < K; k++) {
              int s = (j + p + k) % S + 1;
              out.printf("%d %d %d%n", j, p, s);
            }
          }
        }
      }
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
