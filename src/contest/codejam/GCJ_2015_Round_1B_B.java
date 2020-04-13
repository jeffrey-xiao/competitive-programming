package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2015_Round_1B_B {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      int R = readInt();
      int C = readInt();
      int N = readInt();
      if (C < R) {
        int temp = R;
        R = C;
        C = temp;
      }

      out.printf("Case #%d: %d%n", t, solve(R, C, N));
    }

    out.close();
  }

  static int solve(int R, int C, int N) {
    if (N <= (R * C + 1) / 2) {
      return 0;
    }

    if (R == 1) {
      int removed = R * C - N;
      return C - 1 - 2 * removed;
    }

    if (R % 2 == 1 && C % 2 == 1) {
      // 2.3.2    .3.3.
      // .4.4.    3.4.3
      // 3.4.3 OR .4.4.
      // .4.4.    3.4.3
      // 2.3.2    .3.3.
      return Math.min(
          score(R, C, N, (R * C + 1) / 2, 4, ((R - 2) * (C - 2) + 1) / 2),
          score(R, C, N, (R * C) / 2, 0, ((R - 2) * (C - 2)) / 2));
    }
    return score(R, C, N, (R * C + 1) / 2, 2, ((R - 2) * (C - 2) + 1) / 2);
  }

  static int score(int R, int C, int N, int totalRemoveable, int corners, int inside) {
    int edges = totalRemoveable - corners - inside;
    int removed = R * C - N;
    int ret = R * C * 2 - R - C;
    int currRemoved;

    currRemoved = Math.min(removed, inside);
    ret -= currRemoved * 4;
    removed -= currRemoved;

    currRemoved = Math.min(removed, edges);
    ret -= currRemoved * 3;
    removed -= currRemoved;

    currRemoved = Math.min(removed, corners);
    ret -= currRemoved * 2;
    removed -= currRemoved;

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
