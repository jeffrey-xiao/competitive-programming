package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2016_Round_1C_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, B;
  static long M;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    br = new BufferedReader(new FileReader("/home/jeffreyxiao/Downloads/B-large-practice.in"));
    out = new PrintWriter(new FileWriter("/home/jeffreyxiao/Downloads/out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      B = readInt();
      M = readLong();
      boolean[][] adj = new boolean[B][B];
      for (int i = 1; i < B; i++) {
        adj[0][i] = true;
      }
      if (M > 1L << (B - 2)) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
      } else {
        out.printf("Case #%d: POSSIBLE%n", t);
        String first = Long.toString(M - 1, 2) + "1";
        while (first.length() < B) {
          first = "0" + first;
        }
        out.println(first);
        for (int i = 1; i < B; i++) {
          for (int j = 0; j < B; j++) {
            out.print(i < j ? '1' : '0');
          }
          out.println();
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
