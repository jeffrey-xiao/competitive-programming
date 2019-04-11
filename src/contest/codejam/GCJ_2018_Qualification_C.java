package contest.codejam;

import java.io.*;
import java.util.*;

public class GCJ_2018_Qualification_C {

  static BufferedReader br;
  static PrintWriter out;
  static StringTokenizer st;

  static boolean[][] grid;
  static int xs, ys;

  public static void main(String[] args) throws IOException {
    br = new BufferedReader(new InputStreamReader(System.in));
    out = new PrintWriter(new OutputStreamWriter(System.out));
    // br = new BufferedReader(new FileReader("in.txt"));
    // out = new PrintWriter(new FileWriter("out.txt"));

    int T = readInt();

    outer:
    for (int t = 1; t <= T; t++) {
      grid = new boolean[1001][1001];
      readInt();
      xs = 2;
      ys = 2;

      while (true) {
        while (check()) {
          out.println(xs + " " + ys);
          out.flush();
          int x = readInt();
          int y = readInt();
          if (x == -1 && y == -1) {
            break outer;
          } else if (x == 0 && y == 0) {
            continue outer;
          }
          grid[x][y] = true;
        }
        if (xs == 7 * 3 + 2 && ys == 2 * 3 + 2) {
          break;
        } else if (xs == 7 * 3 + 2) {
          xs = 2;
          ys++;
        } else {
          xs++;
        }
      }
    }
  }

  static boolean check() {
    if (xs == 7 * 3 + 2 && ys == 2 * 3 + 2) {
      for (int i = -1; i <= 1; i++) {
        for (int j = -1; j <= 1; j++) {
          if (!grid[xs + i][ys + j]) {
            return true;
          }
        }
      }
      return false;
    } else if (xs == 7 * 3 + 2) {
      return !grid[xs - 1][ys - 1] || !grid[xs][ys - 1] || !grid[xs + 1][ys - 1];
    } else if (ys == 2 * 3 + 2) {
      return !grid[xs - 1][ys - 1] || !grid[xs - 1][ys] || !grid[xs - 1][ys + 1];
    } else {
      return !grid[xs - 1][ys - 1];
    }
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
