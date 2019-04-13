package codejam;

import java.io.*;
import java.util.*;

public class GCJ_2019_Round_1A_A {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static int T, R, C;
  static boolean[][] vis;
  static int count;
  static Stack<Integer> rs, cs;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    T = readInt();
    for (int t = 1; t <= T; t++) {
      R = readInt();
      C = readInt();

      if (C < 5) {
        vis = new boolean[R + 1][C + 1];
        rs = new Stack<Integer>();
        cs = new Stack<Integer>();
        count = 0;
        if (solve(1, 1)) {
          out.printf("Case #%d: POSSIBLE%n", t);
          while (!rs.isEmpty()) {
            out.printf("%d %d%n", rs.pop(), cs.pop());
          }
        } else {
          out.printf("Case #%d: IMPOSSIBLE%n", t);
        }
      } else {
        out.printf("Case #%d: POSSIBLE%n", t);
        int row = 1;
        while (R - row + 1 >= 2) {
          if (R - row + 1 == 3) {
            printCol(row, C, true);
            row += 3;
          } else {
            printCol(row, C, false);
            row += 2;
          }
        }
      }
    }

    out.close();
  }

  static void printCol(int r, int c, boolean isThree) {
    for (int i = 0; i < c / 2; i++) {
      out.printf("%d %d%n", r, i + 1);
      out.printf("%d %d%n", r + 1, i + 1 + (c + 1) / 2);
      if (isThree) {
        out.printf("%d %d%n", r + 2, i + 1);
      }
    }
    for (int i = 0; i < (c + 1) / 2; i++) {
      out.printf("%d %d%n", r, c / 2 + i + 1);
      out.printf("%d %d%n", r + 1, i + 1);
      if (isThree) {
        out.printf("%d %d%n", r + 2, c / 2 + i + 1);
      }
    }
  }

  static boolean solve(int r, int c) {
    vis[r][c] = true;
    count++;
    if (count == R * C) {
      rs.add(r);
      cs.add(c);
      return true;
    }
    for (int i = 1; i <= R; i++) {
      if (i == r) {
        continue;
      }
      for (int j = 1; j <= C; j++) {
        if (j == c || vis[i][j] || r - c == i - j || r + c == i + j) {
          continue;
        }
        if (solve(i, j)) {
          rs.add(r);
          cs.add(c);
          return true;
        }
      }
    }
    vis[r][c] = false;
    count--;
    return false;
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
