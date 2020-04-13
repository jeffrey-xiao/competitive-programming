package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2020_Qualification_A {

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
      int N = readInt();
      int[][] A = new int[N][N];
      int trace = N, repeated_rows = 0, repeated_cols = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          A[i][j] = readInt() - 1;
        }
        trace += A[i][i];
      }
      boolean[] exists;
      for (int i = 0; i < N; i++) {
        exists = new boolean[N];
        for (int j = 0; j < N; j++) {
          if (exists[A[i][j]]) {
            repeated_rows++;
            break;
          }
          exists[A[i][j]] = true;
        }
      }

      for (int j = 0; j < N; j++) {
        exists = new boolean[N];
        for (int i = 0; i < N; i++) {
          if (exists[A[i][j]]) {
            repeated_cols++;
            break;
          }
          exists[A[i][j]] = true;
        }
      }
      out.printf("Case #%d: %d %d %d%n", t, trace, repeated_rows, repeated_cols);
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
