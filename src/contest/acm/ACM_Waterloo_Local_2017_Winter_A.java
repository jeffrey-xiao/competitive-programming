package contest.acm;

import java.io.*;
import java.util.*;

public class ACM_Waterloo_Local_2017_Winter_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int M, N;
  static char[][] grid;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    while (br.ready()) {
      M = readInt();
      N = readInt();
      grid = new char[M][M];
      for (int i = 0; i < M; i++) {
        for (int j = 0; j < M; j++) {
          grid[i][j] = '.';
        }
      }

      for (int i = 0; i < N; i++) {
        int S = readInt();
        int c = readInt();
        int r = M - 1 - readInt();

        if (S == 0) {
          put(r, c - 1, '_');
          put(r, c, 'o');
          put(r, c + 1, '_');
        } else {
          put(r, c - 1, '_');
          put(r, c, '|');
          put(r, c + 1, '_');
          for (int j = 0; j < S; j++) {
            put(r - j - 1, c - 1, '/');
            put(r - j - 1, c, '|');
            put(r - j - 1, c + 1, '\\');
          }
          put(r - S - 1, c, '^');
        }
      }

      for (int i = 0; i <= M + 1; i++) {
        for (int j = 0; j <= M + 1; j++) {
          if (i == 0 || j == 0 || i == M + 1 || j == M + 1) {
            out.print("*");
            continue;
          }
          out.print(grid[i - 1][j - 1]);
        }
        out.println();
      }
      out.println();
    }

    out.close();
  }

  static void put(int r, int c, char ch) {
    if (r < 0 || c < 0 || r >= M || c >= M) {
      return;
    }
    grid[r][c] = ch;
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
