package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Round_1B_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T;
  static char[] direction = {'E', 'W', 'N', 'S'};
  static int[] dx = {-1, 1, 0, 0};
  static int[] dy = {0, 0, -1, 1};

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      long x = readInt();
      long y = readInt();
      if ((x % 2 == 0 && y % 2 == 0) || (Math.abs(x) % 2 == 1 && Math.abs(y) % 2 == 1)) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue;
      }
      long n = 1;
      while (2 * n - 1 < Math.abs(x) + Math.abs(y)) {
        n *= 2;
      }
      StringBuilder ans = new StringBuilder();
      while (n > 0) {
        for (int i = 0; i < 4; i++) {
          long nx = x + dx[i] * n;
          long ny = y + dy[i] * n;
          if (Math.abs(nx) + Math.abs(ny) <= n - 1) {
            x = nx;
            y = ny;
            ans.append(direction[i]);
            break;
          }
        }
        n /= 2;
      }
      out.printf("Case #%d: %s%n", t, ans.reverse().toString());
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
