package contest.codejam;

import java.io.*;
import java.util.StringTokenizer;

public class GCJ_2018_Round_2_A {

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

  outer:
    for (int t = 1; t <= T; t++) {

      int C = readInt();
      int[] B = new int[C];

      for (int i = 0; i < C; i++) {
        B[i] = readInt();
      }

      if (B[0] == 0 || B[C - 1] == 0) {
        out.printf("Case #%d: IMPOSSIBLE%n", t);
        continue outer;
      }

      int maxRows = 1;
      int curr = 0;
      for (int i = 0; i < C; i++) {
        for (int j = curr; j < curr + B[i]; j++) {
          maxRows = Math.max(Math.abs(j - i) + 1, maxRows);
        }
        curr += B[i];
      }

      char[][] grid = new char[maxRows][C];

      for (int i = 0; i < maxRows; i++) {
        for (int j = 0; j < C; j++) {
          grid[i][j] = '.';
        }
      }

      curr = 0;
      for (int i = 0; i < C; i++) {
        for (int j = curr; j < curr + B[i]; j++) {
          if (i < j) {
            for (int k = 0; k < j - i; k++) {
              grid[k][j - k] = '/';
            }
          } else if (i > j) {
            for (int k = 0; k < i - j; k++) {
              grid[k][j + k] = '\\';
            }
          }
        }
        curr += B[i];
      }

      out.printf("Case #%d: %d%n", t, maxRows);
      for (int i = 0; i < maxRows; i++) {
        for (int j = 0; j < C; j++) {
          out.printf("%c", grid[i][j]);
        }
        out.println();
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
